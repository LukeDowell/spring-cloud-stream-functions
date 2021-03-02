package dev.dowell.hello.scm.testharness;

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
    private Optional<String> memberId = Optional.empty();

    @Builder.Default
    private Optional<String> name = Optional.empty();

    @Builder.Default
    private Optional<ZonedDateTime> birthDate = Optional.empty();

    @Builder.Default
    private Optional<String> legacyIdentifier = Optional.empty();
}
