package dev.wassimrifi.reposage.features.commade;

import dev.wassimrifi.reposage.features.github.GitHubReleaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import picocli.CommandLine.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Command(
    name = "update",
    description = "Mise a jour RepoSage."
)
public class ReposageUpdateCommande implements Runnable {
    private static final String REPO = "wassim-rifi-dev/RepoSage-CLI";

    private final String currentVersion;
    private final GitHubReleaseService gitHubReleaseService;

    public ReposageUpdateCommande(@Value("${spring.application.version}") String currentVersion, GitHubReleaseService gitHubReleaseService) {
        this.currentVersion = currentVersion;
        this.gitHubReleaseService = gitHubReleaseService;
    }

    @Option(
            names = {"-c" , "--check"},
            description = "Verifier s'il existe une mise a jour."
    )
    private boolean check;

    @Override
    public void run() {
        try {
            String latestVersion = gitHubReleaseService.getLatestRelease();

            System.out.println("Version installée : " + currentVersion);
            System.out.println("Dernière version  : " + latestVersion);

            if (isUpToDate(latestVersion)) {
                System.out.println("RepoSage CLI est à jour.");
                return;
            }

            System.out.println("Une nouvelle version est disponible !");

            if (!check) {
                System.out.println("Lancez 'reposage update' pour l'installer.");

                String downloadUrl = gitHubReleaseService.getDownloadUrl();

                Path installer = Files.createTempFile("reposage-setup-", ".exe");

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(downloadUrl))
                        .GET()
                        .build();

                HttpResponse<Path> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofFile(installer));

                new ProcessBuilder(
                    installer.toAbsolutePath().toString(),
                    "/VERYSILENT",
                    "/SUPPRESSMSGBOXES",
                    "/NORESTART",
                    "/SP-"
                ).start().waitFor();

                System.out.println("Le program est mis a jour en success.");
                return;
            }

        } catch (HttpClientErrorException.NotFound e) {
            System.out.println("Aucune release publiée trouvée sur GitHub.");
        } catch (HttpClientErrorException.Forbidden | HttpClientErrorException.TooManyRequests e) {
            System.out.println("Limite de requêtes GitHub atteinte. Réessayez plus tard.");
        } catch (ResourceAccessException e) {
            System.out.println("Erreur réseau : impossible de joindre GitHub.");
        } catch (Exception e) {
            System.out.println("Erreur : impossible de vérifier les mises à jour (" + e.getMessage() + ").");
        }
    }

    private boolean isUpToDate(String latestVersion) {
        return latestVersion.equals(currentVersion);
    }
}
