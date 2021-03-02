package dev.dowell.hello.scm.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class IngestionFlowTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {

    }

    @Test
    public void destinationTest() {
        InputDestination inputDestination = context.getBean(InputDestination.class);
        OutputDestination outputDestination = context.getBean(OutputDestination.class);
    }
}
