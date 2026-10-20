package dev.wassimrifi.reposage.features.commade;

import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.*;

@Component
@Command(
        name = "resposage",
        mixinStandardHelpOptions = true,
        version = "Resposage CLI 0.1.0",
        description = "Analyse intelligente de dépôts GitHub."
)
public class RepoSageCommand implements Runnable {
    @Override
    public void run() {
    }
}
