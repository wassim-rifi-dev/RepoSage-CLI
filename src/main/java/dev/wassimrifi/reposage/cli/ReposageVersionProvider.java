package dev.wassimrifi.reposage.cli;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import picocli.CommandLine.IVersionProvider;

@Component
public class ReposageVersionProvider implements IVersionProvider {
    private final String version;

    public ReposageVersionProvider(@Value("${spring.application.version}") String version) {
        this.version = version;
    }

    @Override
    public String[] getVersion() {
        return new String[] { "Resposage CLI " + version };
    }
}
