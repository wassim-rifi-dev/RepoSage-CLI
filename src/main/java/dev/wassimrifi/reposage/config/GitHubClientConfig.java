package dev.wassimrifi.reposage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Configuration
public class GitHubClientConfig {

    @Bean
    public RestClient gitHubRestClient(RestClient.Builder builder) {
        return builder
            .baseUrl("https://api.github.com/repos")
            .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github+json")
            .build();
    }
}
