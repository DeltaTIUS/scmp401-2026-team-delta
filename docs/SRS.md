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
| v0.2 | 2026-11-11 | Traceability matrix, change control and CI/CD NFRs | Aiman | Rozyar |

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

### 4.2 Account Management Logic

<!-- Owner: Sahand. Issue #12. SR-2.1, SR-2.2, SR-2.3 -->

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
