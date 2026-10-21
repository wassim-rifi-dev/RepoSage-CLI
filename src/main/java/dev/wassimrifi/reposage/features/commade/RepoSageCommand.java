package dev.wassimrifi.reposage.features.commade;

import dev.wassimrifi.reposage.cli.ReposageVersionProvider;
import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

@Component
@Command(
        name = "resposage",
        mixinStandardHelpOptions = true,
        versionProvider = ReposageVersionProvider.class,
        description = "Analyse intelligente de dépôts GitHub."
)
public class RepoSageCommand implements Runnable {

    @Spec
    private Model.CommandSpec spec;

    @Option(
            names = {"-v", "--version"},
            versionHelp = true,
            description = "Afficher la version."
    )
    private boolean version;

    @Option(
            names = {"-h" , "--help"},
            help = true,
            description = "Affiche l'aide."
    )
    private boolean help;

    @Override
    public void run() {
        spec.commandLine().usage(System.out);
    }
}
