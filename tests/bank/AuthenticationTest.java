package bank;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/** Traces to UR-1, SR-1.1 to SR-1.3, UC-01. Enabled when the feature is implemented. */
class AuthenticationTest {

    private static final String PENDING = "Not implemented yet";

    @Test
    @Disabled(PENDING)
    void passwordIsStoredAsBcryptHashWithCostFactor12() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void correctPasswordStillRequiresVerificationCode() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void wrongVerificationCodeDeniesAccess() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void threeFailedAttemptsWithinFiveMinutesLockAccountFor15Minutes() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void lockedAccountRejectsCorrectPassword() {
        fail(PENDING);
    }

    @Test
    @Disabled(PENDING)
    void lockoutWritesSecurityAuditEntry() {
        fail(PENDING);
    }
}
