package dev.dowell.hello.scm.member;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NewMemberParsed {
    private String id;

    @JsonUnwrapped
    private MemberInfo memberInfo;
}
