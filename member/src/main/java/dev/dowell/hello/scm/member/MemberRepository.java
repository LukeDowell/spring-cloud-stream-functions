package dev.dowell.hello.scm.member;

import java.util.Optional;

public interface MemberRepository {
    void saveMember(Member Member);
    Optional<Member> getMember(String id);
}
