package dev.wassimrifi.reposage.features.commade;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(
    name = "update",
    description = "Met à jour RepoSage."
)
public class ReposageUpdateCommande implements Runnable {
    @Override
    public void run() {
        System.out.println("Mise a jour...");
    }
}
