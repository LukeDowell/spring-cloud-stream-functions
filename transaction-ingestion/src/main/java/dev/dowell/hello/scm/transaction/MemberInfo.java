package dev.dowell.hello.scm.transaction;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Optional;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class MemberInfo {
    @Builder.Default
    Optional<String> name = Optional.empty();

    @Builder.Default
    Optional<ZonedDateTime> birthDate = Optional.empty();

    @Builder.Default
    Optional<String> legacyIdentifier = Optional.empty();
}
