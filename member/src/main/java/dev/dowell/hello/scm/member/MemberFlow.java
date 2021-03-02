package dev.dowell.hello.scm.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@Configuration
public class MemberFlow {

    @Bean
    public LegacyMemberIdRepository legacyMemberIdRepository() {
        return new LegacyMemberIdRepositoryImpl();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new SimpleMemberRepository();
    }

    @Bean
    public Function<AdjudicatedClaim, Mono<NewMemberParsed>> parseNewMembers(MemberRepository memberRepository,
                                                                              LegacyMemberIdRepository legacyMemberIdRepository) {
        return adjudicatedClaim -> adjudicatedClaim.getMemberInfo().getMemberId()
                .flatMap(memberRepository::getMember)
                .map((member) -> Mono.<NewMemberParsed>empty())
                .orElseGet(() -> adjudicatedClaim.getMemberInfo().getLegacyIdentifier()
                            .map(legacyMemberIdRepository::getLegacyIdentifierForMemberId)
                            .map((legacyId) -> Mono.<NewMemberParsed>empty())
                            .orElse(Mono.just(NewMemberParsed.builder()
                                    .id(UUID.randomUUID().toString())
                                    .memberInfo(adjudicatedClaim.getMemberInfo())
                                    .build())
                            )
                );
    }

    @Bean
    public Function<NewMemberParsed, NewMemberCreated> ingestParsedMembers(MemberRepository memberRepository) {
        return newMemberParsed -> {
            var memberId = UUID.randomUUID().toString();

            return NewMemberCreated.builder()
                    .id(UUID.randomUUID().toString())
                    .memberId(memberId)
                    .build();
        };
    }
}
