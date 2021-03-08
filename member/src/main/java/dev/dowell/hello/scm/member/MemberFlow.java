package dev.dowell.hello.scm.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Configuration
public class MemberFlow {

    @Bean
    public LegacyMemberIdRepository legacyMemberIdRepository() {
        return new SimpleLegacyMemberIdRepository();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new SimpleMemberRepository();
    }

    @Bean
    public IntegrationFlow parseNewMemberFlow(MemberRepository memberRepository,
                                              LegacyMemberIdRepository legacyMemberIdRepository) {
        return IntegrationFlows.from("new-adjudicated-claim")
                .logAndReply(LoggingHandler.Level.INFO);
    }

    // TODO Probably need to drop the following down into an IntegrationFlow, returning Optional doesn't make sense
    @Bean
    public Function<AdjudicatedClaim, NewMemberParsed> parseNewMembers(MemberRepository memberRepository,
                                                                                 LegacyMemberIdRepository legacyMemberIdRepository) {
        return adjudicatedClaim -> {
            var existingMember = adjudicatedClaim.getMemberInfo()
                    .getMemberId()
                    .flatMap(memberRepository::getMember);

            if (existingMember.isPresent()) {
                // Nothing
            }

            var existingLegacyMember = adjudicatedClaim.getMemberInfo()
                    .getLegacyIdentifier()
                    .flatMap(legacyMemberIdRepository::getLegacyIdentifierForMemberId);

            if (existingLegacyMember.isPresent()) {
                // Nothing
            }

            return NewMemberParsed.builder()
                    .id(UUID.randomUUID().toString())
                    .memberInfo(adjudicatedClaim.getMemberInfo())
                    .build();
        };
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
