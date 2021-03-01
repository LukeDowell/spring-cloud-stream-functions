package dev.dowell.hello.scm.clientingestion;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class AdjudicatedClaim {
    String id;

    @JsonUnwrapped
    MemberInfo memberInfo;

    List<ClaimTransaction> claimTransactions;
}
