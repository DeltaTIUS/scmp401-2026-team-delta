package bank;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/** Traces to UR-2, SR-2.1 to SR-2.3, UC-02. Enabled when the feature is implemented. */
class AccountCreationTest {

    private static final String PENDING = "Not implemented yet";

    @Test
    @Disabled(PENDING)
    void customerCanCreateCurrentAccount() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void customerCanCreateSavingsAccount() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void newAccountStartsActive() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void currentAccountBalanceStopsAtOverdraftLimit() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void monthlyInterestUsesMinimumDailyBalance() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void savingsAccountHasNoOverdraft() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void interestOnMinimumBalance1200Is250() {
        fail(PENDING);
    }
}
