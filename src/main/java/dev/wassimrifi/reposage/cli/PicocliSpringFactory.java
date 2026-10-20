package dev.wassimrifi.reposage.cli;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
public class PicocliSpringFactory implements CommandLine.IFactory {
    private final ApplicationContext context;

    public PicocliSpringFactory(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <K> K create(Class<K> cls) throws Exception {
        try {
            return context.getBean(cls);
        } catch (Exception e) {
            return CommandLine.defaultFactory().create(cls);
        }
    }
}
