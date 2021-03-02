package dev.dowell.hello.scm.transaction;

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
    String id;

    @JsonUnwrapped
    MemberInfo memberInfo;

    List<ClaimTransaction> claimTransactions;

    @Data
    @Setter(AccessLevel.PRIVATE)
    @Builder(toBuilder = true)
    public static class ClaimTransaction {
        String id;
        String accountNumber;
        AccountCode accountCode;
        BigDecimal amount;
    }

    public enum AccountCode {
        A,
        B,
        C,
        D
    }
}
