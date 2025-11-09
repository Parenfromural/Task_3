import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiUserClient {

    private final String baseUrl;
    private final HttpClient httpClient;

    public ApiUserClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
    }

    public UserTokens registerUser(String email, String password, String name) throws Exception {
        String json = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}", email, password, name);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/api/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 && response.statusCode() != 201) {
            throw new RuntimeException("Failed to register user: " + response.body());
        }

        String body = response.body();
        String accessToken = extractJsonField(body, "accessToken");

        return new UserTokens(accessToken);
    }

    public void deleteUser(String accessToken) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/api/auth/user"))
                .header("Authorization", accessToken)
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();
        if (status != 200 && status != 202) {
            throw new RuntimeException("Failed to delete user, status: " + status + ", body: " + response.body());
        }
    }

    private String extractJsonField(String json, String field) {
        String pattern = String.format("\"%s\":\"([^\"]+)\"", field);
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static class UserTokens {
        private final String accessToken;

        public UserTokens(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }


    }
}