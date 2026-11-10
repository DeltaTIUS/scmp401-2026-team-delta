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

### 7.2 Change Control
