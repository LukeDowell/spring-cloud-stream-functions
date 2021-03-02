package dev.dowell.hello.scm.member;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class NewMemberParsed {
    private String id;

    @JsonUnwrapped
    private MemberInfo memberInfo;
}
