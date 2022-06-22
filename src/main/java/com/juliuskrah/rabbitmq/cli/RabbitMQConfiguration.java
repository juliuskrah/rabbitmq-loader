package com.juliuskrah.rabbitmq.cli;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rabbitmq.http.client.Client;
import com.rabbitmq.http.client.ClientParameters;
import picocli.CommandLine;

/**
 * @author Julius Krah
 */
@ApplicationScoped
public class RabbitMQConfiguration {
    private static final Logger log = LoggerFactory.getLogger(RabbitMQConfiguration.class);

    @Produces
    @ApplicationScoped 
    Client rabbitClient(CommandLine.ParseResult parseResult) throws MalformedURLException, URISyntaxException, UnknownHostException {
        InetAddress address = parseResult.matchedOptionValue('H', InetAddress.getLocalHost());
        int port = parseResult.matchedOptionValue('p', 15672);
        String username = parseResult.matchedOptionValue('U', "");
        char[] password =  new char[]{'a'};
        if (parseResult.commandSpec().userObject() instanceof RabbitMQLoader loader) {
            password = loader.password == null ? new char[0] : loader.password;
        }
        var url = new URL("http://" + address.getHostAddress() + ":" + port + "/api/");
        if(log.isInfoEnabled())
            log.info("Using base URI: {}", url);
        return new Client(
            new ClientParameters()
                .url(url)
                .username(username)
                .password(String.valueOf(password))
        );
    }
}
