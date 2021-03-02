package dev.dowell.hello.scm.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.function.Function;
import java.util.stream.Stream;


@Configuration
@Slf4j
public class IngestionFlow {

    @Bean
    public Function<AdjudicatedClaim, Tuple2<Mono<MemberInfo>, Flux<AdjudicatedClaim.ClaimTransaction>>> consumeAdjudicatedClaim() {
        return adjudicatedClaim ->  {
            log.info("New incoming adjudicated claim: {}", adjudicatedClaim);

            return Tuples.of(
                    Mono.just(MemberInfo.builder().build()),
                    Flux.fromStream(Stream.empty())
            );
        };
    }
}
