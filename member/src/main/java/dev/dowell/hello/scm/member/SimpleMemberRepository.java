package dev.dowell.hello.scm.member;

import java.util.HashMap;
import java.util.Optional;

public class SimpleMemberRepository implements MemberRepository {

    private final HashMap<String, Member> memberMap = new HashMap<>();

    @Override
    public void saveMember(Member member) {
        memberMap.putIfAbsent(member.getId(), member);
    }

    @Override
    public Optional<Member> getMember(String id) {
        return Optional.ofNullable(memberMap.get(id));
    }
}
