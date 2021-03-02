package dev.dowell.hello.scm.testharness;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class AdjudicatedClaim {
    private String id;

    @JsonUnwrapped
    private MemberInfo memberInfo;

    private List<ClaimTransaction> claimTransactions;

    @Data
    @Setter(AccessLevel.PRIVATE)
    @Builder(toBuilder = true)
    public static class ClaimTransaction {
        String id;
        String accountNumber;
        String accountCode;
        BigDecimal amount;
    }
}
