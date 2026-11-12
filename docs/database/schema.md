# Database Schema

This file describes the database of the Secure Banking Application: the tables, their relationships and the integrity rules that support SR-2.1 to SR-2.3 and the audit trail requirement in section 5.4 of the SRS. Money values are stored as DECIMAL(12,2), never as floating point. Credentials are held by the Auth Server, so this database stores no passwords.

## ER Diagram

```mermaid
erDiagram
    CUSTOMER ||--o{ ACCOUNT : owns
    ACCOUNT ||--o{ LEDGER_ENTRY : records
    LEDGER_ENTRY ||--o| AUDIT_LOG : "is logged in"
    CUSTOMER ||--o{ AUDIT_LOG : "acts in"

    CUSTOMER {
        int customer_id PK
        string full_name
        string email UK
        datetime created_at
    }
    ACCOUNT {
        int account_id PK
        int customer_id FK
        string account_type "CURRENT or SAVINGS"
        string status "ACTIVE or CLOSED"
        decimal balance
        datetime opened_at
    }
    LEDGER_ENTRY {
        int entry_id PK
        int account_id FK
        string entry_type "DEPOSIT, WITHDRAWAL or INTEREST"
        decimal amount
        decimal balance_after
        datetime created_at
    }
    AUDIT_LOG {
        int log_id PK
        int user_id FK
        int entry_id FK "only for monetary transactions"
        string event_type "TRANSACTION or SECURITY_EVENT"
        string event_detail "for example ACCOUNT_LOCKOUT"
        datetime logged_at
    }
```

## Integrity Rules

1. Primary keys: customer_id, account_id, entry_id and log_id are the primary keys of their tables. CUSTOMER.email is UNIQUE and NOT NULL.
2. Foreign keys: ACCOUNT.customer_id references CUSTOMER.customer_id. LEDGER_ENTRY.account_id references ACCOUNT.account_id. AUDIT_LOG.user_id references CUSTOMER.customer_id. AUDIT_LOG.entry_id references LEDGER_ENTRY.entry_id and is filled only for monetary transactions. No account can exist without a customer.
3. Account type and status: ACCOUNT.account_type must be CURRENT or SAVINGS, and ACCOUNT.status must be ACTIVE or CLOSED (CHECK constraints). A new account is created with status ACTIVE, and only ACTIVE accounts accept transactions (SR-3.1, SR-3.2).
4. Current Account balance: balance >= -500.00 (SR-2.1, L_max).
5. Savings Account balance: balance >= 100.00 (SR-2.3, B_min). Savings Accounts never have an overdraft.
6. Amount: LEDGER_ENTRY.amount > 0.00.
7. Append-only: LEDGER_ENTRY and AUDIT_LOG rows can only be inserted and read. UPDATE and DELETE are not allowed. A correction is made by appending a new entry.
8. Audit entries: every AUDIT_LOG row has a timestamp set by the database and a User ID (both NOT NULL). One entry is written for every monetary transaction and every security event, such as an account lockout (SRS section 5.4).
