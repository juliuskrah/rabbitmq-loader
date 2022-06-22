package com.juliuskrah.rabbitmq.cli;

import org.eclipse.microprofile.config.ConfigProvider;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

/**
 * @author Julius Krah
 */
public class QuarkusVersionProvider implements IVersionProvider {
    @Spec CommandSpec spec;
    
    @Override
    public String[] getVersion() throws Exception {
        return new String[] {
            spec.qualifiedName() + " " + ConfigProvider.getConfig().getValue("quarkus.application.version", String.class),
            "os name: " + System.getProperty("os.name")
        };
    }
    
}
