package dev.wassimrifi.reposage.features.command;

import dev.wassimrifi.reposage.cli.ReposageVersionProvider;
import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

@Component
@Command(
        name = "reposage",
        mixinStandardHelpOptions = true,
        versionProvider = ReposageVersionProvider.class,
        description = "Analyse intelligente des repos GitHub.",
        subcommands = {
                UpdateCommand.class
        }
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
