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

This Software Requirements Specification (SRS) defines the user requirements, system requirements and non-functional requirements of the **Secure Banking Application**, a web-based system that lets bank customers manage their money online.

The system shall give authenticated customers secure digital access to their accounts so they can open Current or Savings Accounts, check their balances, and make deposits and withdrawals that are processed atomically and recorded in an immutable audit trail.

This document is the agreement between the stakeholders listed in Section 2: the evaluator uses it to assess the product, developers use it as the blueprint for implementation, and testers use it to derive automated JUnit tests. Every requirement in it is traceable to a use case and a test in Section 7.

### 1.2 Scope and System Boundary

The Secure Banking Application covers the web front end and the application logic used by bank customers. Everything inside the boundary is built by Team Delta. The three actors below lie **outside** the system boundary and interact with it:

| Actor | Type | Role |
|---|---|---|
| Customer | Primary actor | Starts every use case through the web interface. |
| Auth Server | Secondary actor | Verifies credentials and one-time verification codes. |
| Core Banking Database | Secondary actor | Stores customers, accounts and the immutable transaction ledger. |

**In scope** for Milestone 1 (one function per user requirement):

| Function | User requirement |
|---|---|
| Secure log in with password and one-time code (multi-factor authentication), including account lockout after repeated failures | UR-1 |
| Customer self-service creation of Current and Savings Accounts | UR-2 |
| Balance inquiry for the customer's own accounts | UR-3 |
| Atomic deposits | UR-4 |
| Atomic withdrawals, following the rules of each account type | UR-5 |

All money amounts are in US dollars (USD).

**Current Accounts and Savings Accounts.** The system supports two account types with different rules:

| Rule | Current Account | Savings Account |
|---|---|---|
| Purpose | Everyday spending | Saving money and earning interest |
| Overdraft | Allowed down to L_max = $500.00 (lowest balance −$500.00) | Not allowed |
| Interest | None | I = B × (r / 12) on the last calendar day of each month, r = 2.5% |
| Minimum balance | None | B_min = $100.00 |
| Withdrawal allowed when | Balance + 500.00 ≥ A | Balance − A ≥ 100.00 |

Example: a Savings Account whose lowest daily balance in a month is B = $1,200.00 earns I = 1,200.00 × (0.025 / 12) = $2.50 that month.

### 1.3 Out of Scope

The following functions are **not** part of this system for Milestone 1. They are excluded to keep the sprint focused on the five user requirements.

| Excluded function | Reason |
|---|---|
| Account creation by bank staff or tellers, and other back-office administration | Product Owner decision: in Milestone 1, customers create their own accounts. |
| Transfers between accounts, to other customers or to other banks | Not one of the five user requirements. |
| Bill payments, cheques and card payments | Not one of the five user requirements. |
| Loans, credit cards and credit underwriting | Separate banking products with their own regulation. |
| Physical cash handling: ATM hardware, cash dispensing and vault management | The system records a withdrawal in the ledger; handing over physical cash is outside the software boundary. |
| Foreign currency accounts and currency exchange | All amounts are in USD. |
| Closing accounts and changing interest rates or overdraft limits | Rates and limits are fixed values in this SRS (r = 2.5%, L_max = $500.00, B_min = $100.00). |
| Native mobile applications | The product is a web application. |

### 1.4 Definitions, Acronyms, and Abbreviations

| Term | Meaning |
|---|---|
| A | The amount of a deposit or withdrawal. Always greater than $0.00. |
| AES-256 | Advanced Encryption Standard with a 256-bit key, used to protect stored data. |
| Atomic transaction | A transaction that either completes fully or has no effect at all. |
| B | The lowest daily balance of a Savings Account during a month, used for interest. |
| B_min | The minimum balance a Savings Account must keep: $100.00. |
| BCrypt | A slow, salted password hashing algorithm. This system uses cost factor 12. |
| CI | Continuous Integration: tests run automatically on every pull request (GitHub Actions). |
| Current Account | A transactional account for everyday spending, with an overdraft facility. |
| ERR_INSUFFICIENT_FUNDS | The error the system returns when a withdrawal would break the account's balance rule. |
| IEEE 830 | The IEEE recommended practice for software requirements specifications that this document follows. |
| JUnit | The Java unit testing framework used for the automated tests named in the RTM. |
| L_max | The overdraft limit of a Current Account: $500.00. |
| Ledger | The immutable, append-only record of every transaction. Entries are never edited or deleted. |
| MFA | Multi-Factor Authentication: proving identity with two or more factors, here a password and a one-time code. |
| NFR | Non-Functional Requirement: a quality of the whole system, such as speed, security or availability. |
| OTP | One-Time Password (one-time verification code): a short code valid for a single login. |
| r | The annual interest rate of a Savings Account: 2.5%. |
| Rollback | Undoing every change of a failed transaction, so the data returns to its state before the transaction. |
| RTM | Requirements Traceability Matrix: the table linking each user requirement to system requirements, use cases and tests. |
| Savings Account | An interest-bearing account with a minimum balance and no overdraft. |
| SR | System Requirement: a detailed technical requirement with inputs, outputs, preconditions, postconditions and exceptions. |
| SRS | Software Requirements Specification: this document. |
| TLS 1.3 | Transport Layer Security version 1.3, used to encrypt all traffic between browser and server. |
| UC | Use Case: a goal an actor reaches by interacting with the system, shown in the UML diagram. |
| UR | User Requirement: a requirement written in natural language from the customer's point of view. |

## 2. Stakeholders and Reader Perspectives

<!-- Owner: Mina. Issue #8 -->

Different stakeholders read this SRS for different purposes:

| Reader category | Stakeholders | What they need from this document | Main sections |
|---|---|---|---|
| Client and evaluator | Course instructor (Dr. Bestoon Hussien) | Confirm that the system meets the educational and business objectives of the milestone. | 1, 7 |
| End users | Bank customers | Confirm that the services they need are described correctly. | 1, 3 |
| Product Owner | Mina | Keep scope, priorities and acceptance criteria consistent with the backlog. | 1, 2, 3 |
| ScrumMaster | Aiman | Plan the sprint, manage change control and maintain traceability. | 7 |
| System architect and security lead | Rozyar | Design authentication, security controls and the UML model. | 4.1, 5.1, 6 |
| Software developers | Rozi, Sahand, Ara | Use the system requirements as the blueprint for back end, database and front end. | 4, 6 |
| Test engineers | Quality assurance and the automated JUnit suite | Derive test cases from requirements and acceptance criteria. | 4, 5, 7 |
| Security and compliance auditors | Bank auditors (external) | Check encryption, password storage and the immutable audit trail. | 5.1, 5.4 |

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

### 4.2 Account Management Logic

<!-- Owner: Sahand. Issue #12. SR-2.1, SR-2.2, SR-2.3 -->

**SR-2.1 [Current Account Overdraft]:** The system shall allow a withdrawal of amount A from a Current Account only if the resulting balance is not lower than -L_max, where L_max = $500.00.
- **Inputs:** Account ID, transaction amount A (A > $0.00).
- **Preconditions:** The user is authenticated; the account exists and its type is CURRENT; A > $0.00.
- **Processing:** The system computes the new balance as (current balance - A). If the new balance is greater than or equal to -$500.00, the withdrawal is accepted. Otherwise it is rejected. A new Current Account is opened with an opening balance of at least $0.00.
- **Outputs:** On success, the updated balance. On rejection, the error code ERR_INSUFFICIENT_FUNDS.
- **Postconditions:** On success, the balance equals the previous balance minus A and one ledger entry is appended. On rejection, nothing changes: no balance change and no ledger entry.
- **Exceptions:** If the new balance would be lower than -$500.00, the system returns ERR_INSUFFICIENT_FUNDS. If A is not greater than $0.00, the request is rejected as an invalid amount. If the account is a Savings Account, SR-2.3 applies instead and no overdraft is allowed.


**SR-2.2 [Savings Account Interest]:** The system shall add interest I = B x (r / 12) to every Savings Account on the last calendar day of each month, where B is the lowest daily balance of that account in that month and r = 2.5% per year.
- **Inputs:** Account ID, B (the lowest daily balance of the month), r = 2.5% per year.
- **Preconditions:** The account type is SAVINGS; it is the last calendar day of the month; B > $0.00.
- **Processing:** The system finds B from the daily balances of the month, calculates I = B x (r / 12), rounds I to the nearest cent, and adds I to the account balance. Example: B = $1,200.00 gives I = 1,200.00 x (0.025 / 12) = $2.50.
- **Outputs:** The interest amount I and the new balance.
- **Postconditions:** The balance is increased by I, one INTEREST ledger entry of amount I is appended, and the matching audit log entry is created.
- **Exceptions:** If B is $0.00 or less, no interest is added. If the interest posting fails, the balance is left unchanged and the failure is logged.


  **SR-2.3 [Savings Minimum Balance]:** The system shall keep the balance of every Savings Account at or above B_min = $100.00, never allow an overdraft on a Savings Account, and open a new Savings Account only with an opening deposit of at least B_min.
- **Inputs:** For a withdrawal: account ID and amount A (A > $0.00). For account opening: customer ID and opening deposit.
- **Preconditions:** The user is authenticated; A > $0.00; the account type is SAVINGS (for a withdrawal) or a new SAVINGS account is requested (for opening).
- **Processing:** For a withdrawal, the system computes (current balance - A). If the result is greater than or equal to $100.00, the withdrawal is accepted. Otherwise it is rejected. For opening, the system accepts the account only if the opening deposit is at least $100.00.
- **Outputs:** On success, the updated balance or the new account. On rejection of a withdrawal, the error code ERR_INSUFFICIENT_FUNDS.
- **Postconditions:** On success, the balance is at least $100.00 and one ledger entry is appended. On rejection, nothing changes: no balance change, no ledger entry, no account created.
- **Exceptions:** If a withdrawal would leave the balance below $100.00, the system returns ERR_INSUFFICIENT_FUNDS. If the opening deposit is below $100.00, the opening request is rejected and no account is created. If A is not greater than $0.00, the request is rejected as an invalid amount.
  
### 4.3 Transaction Processing Logic

<!-- Owner: Rozi. Issue #11. SR-3.1, SR-3.2, SR-3.3 -->

## 5. Non-Functional Requirements and Metrics

### 5.1 Product: Security

<!-- Owner: Rozyar. Issue #10 -->

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
