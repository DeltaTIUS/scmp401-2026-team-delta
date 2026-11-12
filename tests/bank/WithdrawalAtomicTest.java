package bank;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/** Traces to UR-5, SR-3.1, SR-2.1, SR-2.3, UC-03. Enabled when the feature is implemented. */
class WithdrawalAtomicTest {

    private static final String PENDING = "Not implemented yet";

    @Test
    @Disabled(PENDING)
    void zeroOrNegativeAmountIsRejected() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void currentAccountAllowsWithdrawalWithinOverdraft() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void currentAccountRejectsWithdrawalBeyondOverdraft() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void savingsAccountRejectsWithdrawalBelowMinimumBalance() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void insufficientFundsReturnsErrorAndRollsBack() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void successfulWithdrawalAppendsImmutableLedgerEntry() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void expiredSessionWithdrawsNothing() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void successfulWithdrawalLeavesOldBalanceMinusAmount() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void currentAccountAt200CanWithdrawExactly700() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void currentAccountAt200CanWithdraw650ButNot701() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void savingsAccountCanBeLeftAtExactly100() {
        fail(PENDING);
    }
}
