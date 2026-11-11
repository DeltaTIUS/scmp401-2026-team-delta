# Software Requirements Specification (SRS)

**Project:** Secure Banking Application | **Course:** SCMP 401 Software Engineering Studio

| | |
|---|---|
| **Team Name** | Team Delta |
| **Date and Version** | 2026-11-11, Version 1.0 (Draft for Milestone 1 Review) |
| **Document Authors** | Mina (Product Owner), Rozyar (System Architect) |
| **Course and Instructor** | SCMP 401 Software Engineering Studio, Dr. Bestoon Hussien |
| **Document Standard** | Adapted from IEEE 830-1998 and Ian Sommerville Chapter 4 |

## Document Preface and Revision History

<!-- Owner: Aiman -->

| Version | Date | Description of Changes | Author(s) | Approved By |
|---|---|---|---|---|
| v0.1 | 2026-11-11 | Document skeleton | Aiman | |

## 1. Introduction and System Vision

<!-- Owner: Mina. Issue #8 -->

### 1.1 Purpose of the System

### 1.2 Scope and System Boundary

### 1.3 Out of Scope

### 1.4 Definitions, Acronyms, and Abbreviations

## 2. Stakeholders and Reader Perspectives

<!-- Owner: Mina. Issue #8 -->

## 3. User Requirements Definition

<!-- Owner: Ara. Issue #9 -->

### UR-1 Security and Authentication

### UR-2 Account Creation

### UR-3 Balance Inquiry

### UR-4 Deposit

### UR-5 Withdrawal

## 4. Detailed System Requirements Specification

### 4.1 Authentication and Access Control

<!-- Owner: Rozyar. Issue #10. SR-1.1, SR-1.2, SR-1.3 -->

Authentication is handled by the Auth Server, which stores the credential records and verifies passwords and one-time verification codes. A customer reaches account services only after passing SR-1.1 and then SR-1.3, and only while SR-1.2 has not locked the account.

**SR-1.1 [User ID and Password Verification]:** Upon user login, the system shall prompt for User ID and Password. Passwords shall be hashed using BCrypt with cost factor 12; only the hash shall be stored and compared, and the plain-text password shall never be stored or logged.

| Field | Specification |
|---|---|
| Inputs | User ID and Password, sent over TLS 1.3. |
| Processing | The Auth Server loads the stored BCrypt hash for the User ID and checks the entered password against it with BCrypt (cost factor 12). |
| Outputs | Success: the customer is passed to the one-time code step (SR-1.3). Failure: the message "Invalid User ID or password". |
| Preconditions | The customer is registered; the account is not locked (SR-1.2); the connection uses TLS 1.3. |
| Postconditions | Success: a pending login is created that grants no account service until SR-1.3 completes, and the failed-attempt counter is reset to 0. Failure: the failed-attempt counter for the User ID increases by 1. |
| Exceptions | Unknown User ID or wrong password: the same generic message is returned in both cases, so the response does not reveal which User IDs exist. Auth Server unavailable: login is refused with "Service temporarily unavailable" and no session is created. |

**SR-1.2 [Account Lockout]:** If three (3) consecutive incorrect password attempts are detected for the same User ID within 5 minutes, the system shall lock the account for 15 minutes and write a security audit log entry.

| Field | Specification |
|---|---|
| Inputs | Failed password attempts from SR-1.1, each with the User ID and the time of the attempt. |
| Processing | The Auth Server counts consecutive failed attempts per User ID. When the 3rd failure occurs within 5 minutes of the 1st, the account is locked until the time of the 3rd failure + 15 minutes. |
| Outputs | The message "Account locked. Try again in 15 minutes". |
| Preconditions | The User ID exists and the account is not already locked. |
| Postconditions | Account status = LOCKED with an unlock time; a security audit log entry is appended with timestamp, User ID and event type ACCOUNT_LOCKED (audit log rules in Section 5.4). After 15 minutes the account unlocks automatically and the counter resets to 0. |
| Exceptions | Login attempt while locked: rejected with the same lock message, even if the password is correct, and the lock time is not extended. A successful login before the 3rd failure resets the counter to 0. Failures older than 5 minutes no longer count towards the lock. |

**SR-1.3 [One-Time Verification Code]:** After a correct password (SR-1.1), the system shall require a one-time verification code before any account service is shown. The code shall be 6 digits, valid for 5 minutes, and usable only once.

| Field | Specification |
|---|---|
| Inputs | The pending login from SR-1.1; the 6-digit code entered by the customer. |
| Processing | The Auth Server generates a random 6-digit code, sends it to the customer's registered mobile number by SMS, and stores only its hash with an expiry time of 5 minutes. The entered code is compared with the stored hash. |
| Outputs | Success: an authenticated session token is issued and the customer's account services are shown. Failure: the message "Invalid or expired code". |
| Preconditions | SR-1.1 succeeded for this User ID less than 5 minutes ago; the account is not locked. |
| Postconditions | Success: the code is marked as used and cannot be used again; an authenticated session exists for the customer. |
| Exceptions | Wrong code: the customer may retry; after 3 wrong codes the code is cancelled and the customer must restart from SR-1.1. Expired code: rejected; the customer must restart from SR-1.1. SMS cannot be sent: login stops with "Verification code could not be sent" and no session is created. |

### 4.2 Account Management Logic

<!-- Owner: Sahand. Issue #12. SR-2.1, SR-2.2, SR-2.3 -->

### 4.3 Transaction Processing Logic

<!-- Owner: Rozi. Issue #11. SR-3.1, SR-3.2, SR-3.3 -->

## 5. Non-Functional Requirements and Metrics

### 5.1 Product: Security

<!-- Owner: Rozyar. Issue #10 -->

| NFR Category | Metric Property | Target Quantitative Requirement | Validation Method |
|---|---|---|---|
| Product (Security) | Data in transit | 100% of network traffic between client and server shall use TLS 1.3; connections offering TLS 1.2 or lower shall be refused. | Wireshark network audit of a full session, plus a forced TLS 1.2 connection attempt that must fail |
| Product (Security) | Data at rest | Customer and account data stored in the Core Banking Database shall be encrypted with AES-256. | Database configuration review and inspection of the raw storage files |
| Product (Security) | Password storage | 100% of stored passwords shall be BCrypt hashes with cost factor 12; zero plain-text passwords shall exist in storage or logs. | JUnit test (`AuthenticationTest`) checking the `$2b$12$` hash prefix, and a search of the database and logs for test passwords |
| Product (Security) | Brute-force protection | An account shall lock after 3 failed password attempts within 5 minutes and stay locked for 15 minutes (SR-1.2). | JUnit test (`AuthenticationTest`) using a controllable clock |
| Product (Security) | Multi-factor authentication | 100% of logins shall pass the one-time verification code (SR-1.3) before any account data is shown. | JUnit test (`AuthenticationTest`), and a manual check that no account page loads with only a password |
| Product (Security) | Session timeout | An authenticated session shall expire after 15 minutes of inactivity; any later request is sent back to Log In. | Manual test: leave a session idle for 15 minutes, then try to view a balance |

### 5.2 Product: Speed and Availability

<!-- Owner: Rozi. Issue #11 -->

### 5.3 Organizational: CI/CD

<!-- Owner: Aiman. Issue #13 -->

| NFR Category | Metric Property | Target Quantitative Requirement | Validation Method |
|---|---|---|---|
| Organizational | Automated testing | 100% of pull requests shall pass the automated JUnit suite before merge. | GitHub Actions CI pipeline |
| Organizational | Peer review | 100% of changes to `main` and `develop` shall be merged through a pull request with at least one approval from the ScrumMaster or System Architect. | GitHub branch protection rules and pull request history |

### 5.4 External: Legal and Audit Trail

<!-- Owner: Sahand. Issue #12 -->

## 6. Graphical Models and Tabular Use Case Specification

### 6.1 UML Use Case Diagram

<!-- Owner: Rozyar. Issue #10 -->

### 6.2 UC-01: Log In

<!-- Owner: Rozyar. Issue #10 -->

### 6.3 UC-02: Create Account

<!-- Owner: Rozyar. Issue #10 -->

### 6.4 UC-03: Withdraw Funds

<!-- Owner: Rozi. Issue #11 -->

### 6.5 UC-04: Check Balance

<!-- Owner: Rozi. Issue #11 -->

### 6.6 UC-05: Deposit Funds

<!-- Owner: Rozi. Issue #11 -->

## 7. Requirements Traceability and Change Control

<!-- Owner: Aiman. Issue #13 -->

### 7.1 Requirements Traceability Matrix

Each user requirement traces through its system requirements and use case to an automated JUnit test class in `tests/bank/`.

| User Requirement | System Requirement | UML Use Case | Automated JUnit Test |
|---|---|---|---|
| UR-1 [Security] | SR-1.1, SR-1.2, SR-1.3 | UC-01: Log In | `AuthenticationTest.java` |
| UR-2 [Account Creation] | SR-2.1, SR-2.2, SR-2.3 | UC-02: Create Account | `AccountCreationTest.java` |
| UR-3 [Balance Inquiry] | SR-3.3 | UC-04: Check Balance | `BalanceInquiryTest.java` |
| UR-4 [Deposit] | SR-3.2 | UC-05: Deposit Funds | `DepositTest.java` |
| UR-5 [Withdrawal] | SR-3.1, SR-2.1, SR-2.3 | UC-03: Withdraw Funds | `WithdrawalAtomicTest.java` |

Every system requirement appears in at least one row. SR-2.1 and SR-2.3 appear twice because the account rules set at creation also govern withdrawals.

The test cases are derived from the acceptance criteria of the user stories. They remain disabled, and are reported as skipped by the CI pipeline, until the features are implemented in a later milestone.

### 7.2 Change Control

1. A change is proposed as a GitHub issue stating the affected requirement IDs and the reason.
2. The Product Owner accepts or rejects it, and sets its priority in the backlog.
3. The change is made on a `feature/*` branch and merged through a reviewed pull request.
4. The same pull request updates every affected row of the RTM and the affected test classes.
5. The revision history in the preface records the new version.
