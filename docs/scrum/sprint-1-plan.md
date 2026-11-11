# Sprint 1 Plan

**Sprint goal:** a reviewed, IEEE 830-style SRS with UML use case model and full traceability, merged to `main` by the Milestone 1 deadline.

**Deadline:** Wednesday 11 November 2026, 23:59.

| Checkpoint | Time |
|---|---|
| All pull requests open | 18:00 |
| Everything merged to `main` | 22:00 |
| Repository URL and roster submitted | before 23:59 |

## Backlog priority (Product Owner)

UR-1 (#1), UR-2 (#2), UR-3 (#3), UR-4 (#4), UR-5 (#5). Ordered by dependency: nothing works without login, nothing holds money without an account, and withdrawal is the highest-risk story, so it builds on the others.

## Sprint tasks

| Issue | Task | Owner | Branch |
|---|---|---|---|
| #6 | Repository structure and workflow | Aiman | `feature/repo-setup` |
| #7 | CI pipeline and JUnit test classes | Aiman | `feature/ci-tests` |
| #8 | SRS 1-2: Vision, scope, stakeholders | Mina | `feature/srs-vision-scope` |
| #9 | SRS 3: User requirements and wireframes | Ara | `feature/srs-user-reqs` |
| #10 | UML model and SRS 4.1 authentication | Rozyar | `feature/uml-usecase` |
| #11 | SRS 4.3: Atomic transactions | Rozi | `feature/srs-transactions` |
| #12 | SRS 4.2: Account rules and database schema | Sahand | `feature/srs-accounts-db` |
| #13 | Traceability matrix and Scrum records | Aiman | `feature/rtm` |

## Decisions from sprint planning

- UR-1 needs a system requirement for the second factor, so SR-1.3 was added.
- UR-5 traces to SR-3.1, SR-2.1 and SR-2.3, because the withdrawal check differs by account type.
- UR-4 was missing from the sample RTM. It traces to the new SR-3.2 and `DepositTest.java`.
- Balance inquiry gets its own requirement, SR-3.3.
- Test class name follows the assignment brief: `AuthenticationTest.java`.
- For Milestone 1, customers create their own accounts. Staff account creation is out of scope.

## Project board

https://github.com/orgs/DeltaTIUS/projects/1

## Standups

Daily updates go on the pinned standup issue, #14.
