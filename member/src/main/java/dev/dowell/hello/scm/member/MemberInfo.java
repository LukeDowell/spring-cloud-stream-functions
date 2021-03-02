package dev.dowell.hello.scm.member;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.Optional;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
