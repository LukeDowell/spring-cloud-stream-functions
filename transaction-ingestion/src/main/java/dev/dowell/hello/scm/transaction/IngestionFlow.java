package dev.dowell.hello.scm.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.function.Function;
import java.util.stream.Stream;


@Configuration
@Slf4j
public class IngestionFlow {

    @Bean
    public Function<Flux<AdjudicatedClaim>, Tuple2<Flux<MemberInfo>, Flux<AdjudicatedClaim.ClaimTransaction>>> consumeAdjudicatedClaim() {
        return adjudicatedClaim ->  {
            log.info("New incoming adjudicated claim: {}", adjudicatedClaim);

            return Tuples.of(
                    Flux.<MemberInfo>fromStream(Stream.empty()),
                    Flux.<AdjudicatedClaim.ClaimTransaction>fromStream(Stream.empty())
            );
        };
    }
}
