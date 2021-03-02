package dev.dowell.hello.scm.member;

import lombok.*;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NewMemberCreated {
    private String id;
    private String memberId;
}
