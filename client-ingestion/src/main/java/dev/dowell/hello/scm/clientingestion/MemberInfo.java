package dev.dowell.hello.scm.clientingestion;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.ZonedDateTime;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class MemberInfo {
    String id;
    String name;
    ZonedDateTime birthDate;
}
