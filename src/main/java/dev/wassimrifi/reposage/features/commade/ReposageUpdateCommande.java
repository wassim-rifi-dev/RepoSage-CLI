package dev.wassimrifi.reposage.features.commade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

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
    }
}
