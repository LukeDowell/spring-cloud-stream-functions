package dev.dowell.hello.scm.clientingestion;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter(AccessLevel.PRIVATE)
public class ClaimTransaction {
    String id;

    String accountNumber;

    BigDecimal amount;
}
