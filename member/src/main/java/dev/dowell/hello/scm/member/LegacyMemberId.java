package dev.dowell.hello.scm.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class LegacyMemberId {
    String memberId;
    String legacyIdentifier;
}
