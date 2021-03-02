package dev.dowell.hello.scm.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.ZonedDateTime;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class Member {
    String id;
    String name;
    ZonedDateTime birthDate;
}
