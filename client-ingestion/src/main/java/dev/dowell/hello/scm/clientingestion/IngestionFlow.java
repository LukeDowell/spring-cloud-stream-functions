package dev.dowell.hello.scm.clientingestion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class IngestionFlow {

    @Bean
    public Consumer<MemberInfo> consumeMemberInfo() {
        return System.out::println;
    }

    @Bean
    public Supplier<MemberInfo> supplyMemberInfo() {
        return () -> MemberInfo.builder()
                .id(UUID.randomUUID().toString())
                .birthDate(ZonedDateTime.now(ZoneId.of("America/Chicago")).minus(25, ChronoUnit.YEARS))
                .name("Income Unit")
                .build();
    }
}
