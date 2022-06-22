package com.juliuskrah.rabbitmq.cli;

import java.net.InetAddress;
import java.util.Arrays;
import javax.inject.Inject;
import com.rabbitmq.http.client.Client;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * @author Julius Krah
 */
@Command(name = "rabbit-loader", mixinStandardHelpOptions = true, versionProvider = QuarkusVersionProvider.class)
public class RabbitMQLoader implements Runnable {

    @Option(names = {"--host", "-H"}, defaultValue = "localhost",
        description = "RabbitMQ management host name.")
    InetAddress hostName;
    @Option(names = {"-p", "--port"}, defaultValue = "15672",
        description = "RabbitMQ management port.")
    int port;
    @Option(names = {"-P", "--password"}, description = "Passphrase", 
        defaultValue = "guest", interactive = true)
    char[] password;
    @Option(names = {"-U", "--username"}, description = "Username", 
        defaultValue = "guest")
    String username;

    @Inject Client client;

    @Override
    public void run() {
        System.out.printf("Using base URI: http://%s:%d/api/%n", hostName.getHostName(), port);
        System.out.printf("Cluster name: %s%n", client.getClusterName());
        // null out the arrays when done
        Arrays.fill(password, ' ');
    }

}
