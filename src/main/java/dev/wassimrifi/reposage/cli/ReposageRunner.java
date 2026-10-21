package dev.wassimrifi.reposage.cli;

import dev.wassimrifi.reposage.features.commade.RepoSageCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
public class ReposageRunner implements CommandLineRunner {
    private final RepoSageCommand repoSageCommand;
    private final ReposageSpringFactory factory;

    public ReposageRunner(RepoSageCommand repoSageCommand, ReposageSpringFactory factory) {
        this.repoSageCommand = repoSageCommand;
        this.factory = factory;
    }

    @Override
    public void run(String... args) {
        int exitCode = new CommandLine(repoSageCommand, factory).execute(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }
}
