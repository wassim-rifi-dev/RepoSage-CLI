package dev.wassimrifi.reposage.features.commade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import tools.jackson.databind.ObjectMapper;

@Component
@Command(
    name = "update",
    description = "Mise a jour RepoSage."
)
public class ReposageUpdateCommande implements Runnable {
    private static final String REPO = "wassim-rifi-dev/RepoSage-CLI";

    private final String currentVersion;

    public ReposageUpdateCommande(@Value("${spring.application.version}") String currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Option(
            names = {"-c" , "--check"},
            description = "Verifier s'il existe une mise a jour."
    )
    private boolean check;

    @Override
    public void run() {
        if (!check) {
            System.out.println("Mise à jour pas encore disponible. Utilisez : reposage update --check");
            return;
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/repos/" + REPO + "/releases/latest"))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request , HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Aucune release trouvée.");
                return;
            }

            String latestVersion = new ObjectMapper().readTree(response.body())
                    .get("tag_name").asText().substring(1).trim();

            System.out.println("Version installée : " + currentVersion);
            System.out.println("Dernière version  : " + latestVersion);

            if (latestVersion.equals(currentVersion)) {
                System.out.println("RepoSage CLI est à jour.");
            } else {
                System.out.println("Une nouvelle version est disponible !");
            }

        } catch (Exception e) {
            System.out.println("Erreur : impossible de vérifier les mises à jour.");
        }
    }
}
