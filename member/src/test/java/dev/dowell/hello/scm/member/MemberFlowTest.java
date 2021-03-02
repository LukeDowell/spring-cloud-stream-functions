package dev.dowell.hello.scm.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.support.MessageBuilder;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.notNullValue;

public class MemberFlowTest {

    private static final PodamFactory PODAM_FACTORY = new PodamFactoryImpl();

    @Test
    public void adjudicated_claim_triggers_new_member_parse() {
        var testBinderConfig = TestChannelBinderConfiguration.getCompleteConfiguration(MemberFlow.class);
        try (ConfigurableApplicationContext c = new SpringApplicationBuilder(testBinderConfig).run()) {
            var objectMapper = c.getBean(ObjectMapper.class);
            var inputDestination = c.getBean(InputDestination.class);
            var outputDestination = c.getBean(OutputDestination.class);
            var expectedNewMemberName = "Bob Slowly";
            var adjudicatedClaim = PODAM_FACTORY.manufacturePojo(AdjudicatedClaim.AdjudicatedClaimBuilder.class)
                    .memberInfo(MemberInfo.builder().name(Optional.of(expectedNewMemberName)).build())
                    .build();
            var inputMessage = MessageBuilder.withPayload(adjudicatedClaim).build();

            inputDestination.send(inputMessage, "new-adjudicated-claim");

            await().atMost(5, TimeUnit.SECONDS).until(
                    () -> {
                        var message = outputDestination.receive(1000L, "new-member-parsed");
                        return objectMapper.readValue(message.getPayload(), NewMemberParsed.class);
                    }, notNullValue()
            );
        }
    }
}
