# Contributing to The Vault API

## Branch Strategy

| Branch | Purpose |
|--------|---------|
| `master` | Production - protected, PR only |
| `dev` | Integration - protected, PR only |
| `feature/xxx` | New feature |
| `fix/xxx` | Bug fix |
| `chore/xxx` | Infrastructure, config, tooling |

## Workflow

```
git checkout dev
git pull origin dev
git checkout -b feature/module-01-auth
# ... write code ...
git push origin feature/module-01-auth
# → open PR to dev on GitHub
```

## Commit Message Convention

Format: `type(scope): short description`

| Type | When to use |
|------|-------------|
| `feat` | New feature |
| `fix` | Bug fix |
| `chore` | Config, tooling, dependencies |
| `refactor` | Code change with no feature/fix |
| `test` | Adding or updating tests |
| `docs` | Documentation only |

Examples:
```
feat(auth): add JWT token generation
fix(quiz): resolve null pointer on body shape calculator
chore(ci): add GitHub Actions pipeline
migration(auth): add V1 users table
```

## PR Checklist

Before opening a PR, make sure:

- [ ] Code follows feature-based package structure (`com.thevault.<feature>/`)
- [ ] Migration file added if any entity was created or modified
- [ ] Unit tests added for new service methods
- [ ] `@Operation` Swagger annotations added on new endpoints
- [ ] `.env.example` updated if new environment variables were added
- [ ] `mvn verify` passes locally (builds + tests + checkstyle)

## Package Structure

Every feature follows this structure:

```
com.thevault.api.<feature>/
  controller/
  dto/
  entity/
  enums/
  mapper/
  repository/
  service/
    impl/
```

## Code Style

- Checkstyle is enforced on every build (`mvn verify`)
- 4-space indentation (no tabs)
- No unused imports
- Braces required on all blocks
