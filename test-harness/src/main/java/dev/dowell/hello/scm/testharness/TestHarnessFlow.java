package dev.dowell.hello.scm.testharness;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class TestHarnessFlow {

    @Bean
    public Supplier<AdjudicatedClaim> supplyAdjudicatedClaim() {
        var random = new Random();
        return () -> AdjudicatedClaim.builder()
                .id(UUID.randomUUID().toString())
                .claimTransactions(
                        IntStream.rangeClosed(1, random.nextInt(4))
                                .mapToObj((i) -> supplyClaimTransaction().get())
                                .collect(Collectors.toList())
                )
                .memberInfo(supplyMemberInfo().get())
                .build();
    }

    private Supplier<AdjudicatedClaim.ClaimTransaction> supplyClaimTransaction() {
        return () -> {
            var random = new Random();
            var accountCode = (char) random.nextInt('z' - 'a' + 1);
            return AdjudicatedClaim.ClaimTransaction.builder()
                    .id(UUID.randomUUID().toString())
                    .accountCode(String.valueOf(accountCode))
                    .accountNumber(UUID.randomUUID().toString())
                    .amount(BigDecimal.valueOf(random.nextDouble() * 100.0))
                    .build();
        };
    }

    private Supplier<MemberInfo> supplyMemberInfo() {
        return () -> {
            var random = new Random();
            var memberBuilder = MemberInfo.builder();

            if (random.nextBoolean()) {
                memberBuilder.legacyIdentifier(Optional.of(UUID.randomUUID().toString()));
            } else {
                var birthDate = ZonedDateTime
                        .now(ZoneId.of("CST", ZoneId.SHORT_IDS))
                        .minus(25, ChronoUnit.YEARS);
                memberBuilder.name(Optional.of(String.format("Income Unit #%s", random.nextInt())));
                memberBuilder.birthDate(Optional.of(birthDate));
            }

            return memberBuilder.build();
        };
    }
}
