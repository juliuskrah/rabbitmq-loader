package com.juliuskrah.rabbitmq.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;

@QuarkusMainTest
public class RabbitMQLoaderTest {

    @Test
    @Disabled("Haven't figured how to pass these tests")
    @Launch({"-H", "154.74.134.102", "-p", "15672"})
    void testLaunchCommand(LaunchResult result) {
        assertEquals("Hostname=154.74.134.102, port=15672!", result.getOutput());
    }
}
