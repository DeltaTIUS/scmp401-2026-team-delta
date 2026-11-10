package bank;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/** Traces to UR-3, SR-3.3, UC-04. Enabled when the feature is implemented. */
class BalanceInquiryTest {

    private static final String PENDING = "Not implemented yet";

    @Test
    @Disabled(PENDING)
    void customerSeesOwnAccountBalance() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void customerCannotSeeAnotherCustomersBalance() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void currentAccountShowsAvailableFundsIncludingOverdraft() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void inquiryDoesNotChangeData() {
        fail(PENDING);
    }
}
