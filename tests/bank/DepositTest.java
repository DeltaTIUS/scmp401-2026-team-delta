package bank;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/** Traces to UR-4, SR-3.2, UC-05. Enabled when the feature is implemented. */
class DepositTest {

    private static final String PENDING = "Not implemented yet";

    @Test
    @Disabled(PENDING)
    void depositIncreasesBalanceByAmount() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void zeroOrNegativeAmountIsRejected() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void depositToInactiveAccountIsRejected() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void failedDepositRollsBackBalanceAndLedger() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void depositAppendsImmutableLedgerEntry() {
        fail(PENDING);
    }
}
