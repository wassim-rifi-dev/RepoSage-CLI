package dev.wassimrifi.reposage.features.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class GitHubReleaseService {
    private final String owner;
    private final String repo;

    private final RestClient restClient;
    private final ObjectMapper objectMapper;


    public GitHubReleaseService(
            RestClient gitHubRestClient,
            ObjectMapper objectMapper,
            @Value("${spring.application.owner}") String owner,
            @Value("${spring.application.repo}") String repo
    ) {
        this.owner = owner;
        this.repo = repo;

        this.restClient = gitHubRestClient;
        this.objectMapper = objectMapper;
    }

    public String getLatestRelease() {
        String response = getGithubReleaseResponse();

        JsonNode json = objectMapper.readTree(response);

        String latestVersion = json.get("tag_name").asString()
                .substring(1).trim();

        return latestVersion;
    }
    
    public void getDownloadUrl() {
        String response = getGithubReleaseResponse();

        JsonNode json = objectMapper.readTree(response);

        JsonNode assets = json.get("assets");

        for (JsonNode asset : assets) {
            String name = asset.get("name").asString();

            if (name.endsWith(".exe")) {
                String downloadUrl = asset.get("browser_download_url").asString();

                System.out.println(downloadUrl);
                break;
            }
        }
    }

    private String getGithubReleaseResponse() {
        return restClient.get()
                .uri("/{owner}/{repo}/releases/latest" , owner , repo)
                .retrieve()
                .body(String.class);
    }
}
