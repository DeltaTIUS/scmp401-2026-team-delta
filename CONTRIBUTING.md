# Contributing

## Branches

| Branch | Purpose |
|---|---|
| `main` | Production-ready releases. Protected. |
| `develop` | Integration branch. Protected. |
| `feature/*` | All work happens here, branched from `develop`. |

Nobody pushes directly to `main` or `develop`, including administrators.

## Workflow

1. Pick your issue on the project board and move it to In Progress.
2. Branch from `develop`: `git checkout -b feature/<name>`.
3. Commit in small steps from your own GitHub account.
4. Open a pull request into `develop` and link the issue with `Closes #N`.
5. The ScrumMaster or System Architect reviews it. Authors cannot approve their own work.
6. Merge with a merge commit. Squash and rebase merges are turned off so each person's commits stay in the history.

Releases are a pull request from `develop` into `main`, approved by the Product Owner.

## Commit messages

`type(scope): summary`, for example `docs(srs): add UR-1 to UR-3 with rationale`.

Types: `docs`, `feat`, `fix`, `test`, `ci`, `chore`.

## Standups

Post your daily update on the pinned standup issue: done, next, blockers.
