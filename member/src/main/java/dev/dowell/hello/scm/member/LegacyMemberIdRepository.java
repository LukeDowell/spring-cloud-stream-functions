package dev.dowell.hello.scm.member;

import java.util.Optional;

public interface LegacyMemberIdRepository {
    void saveLegacyMemberId(LegacyMemberId legacyMemberId);
    Optional<String> getLegacyIdentifierForMemberId(String memberId);
}
