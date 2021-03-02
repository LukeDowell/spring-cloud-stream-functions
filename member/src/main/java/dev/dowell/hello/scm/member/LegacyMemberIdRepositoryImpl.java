package dev.dowell.hello.scm.member;

import java.util.HashMap;
import java.util.Optional;

public class LegacyMemberIdRepositoryImpl implements LegacyMemberIdRepository {

    private final HashMap<String, String> memberIdToLegacyIdentifierMap = new HashMap<>();

    @Override
    public void saveLegacyMemberId(LegacyMemberId legacyMemberId) {
        memberIdToLegacyIdentifierMap.putIfAbsent(legacyMemberId.getMemberId(), legacyMemberId.getLegacyIdentifier());
    }

    @Override
    public Optional<String> getLegacyIdentifierForMemberId(String memberId) {
        return Optional.ofNullable(memberIdToLegacyIdentifierMap.get(memberId));
    }
}
