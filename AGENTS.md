# Agent guidance

## Working agreement

Plan code changes and summarise them for confirmation before applying them.

Keep changes narrowly scoped and preserve unrelated work already present in the
working tree. Do not push tags or publish to Azure Artifacts unless the user
explicitly asks for that external action.

## Project purpose

This repository contains the Swagger 2.0 specifications and generated Java
contracts for the HMCTS IdAM APIs. The published Maven coordinate is
`com.github.hmcts:idam-api-spec`.

`idam-api` is the only consumer. Treat generated Java signatures, annotations,
model defaults and Spring request mappings as a public compatibility contract,
even when a Swagger change appears source-compatible.

The main project documentation is in [README.md](README.md). Generator-specific
compatibility decisions and their rationale are in
[src/main/openapi-templates/README.md](src/main/openapi-templates/README.md).

## Build and generation

- Use JDK 21 and the checked-in Gradle wrapper. The wrapper currently uses
  Gradle 9.7.1.
- Run `./gradlew generateApi` to generate contracts without running the full
  build.
- Run `./gradlew check` while developing.
- Run `./gradlew clean build` before handing work over. This is also the command
  used by CI and the release workflow.
- Run `./gradlew publishToMavenLocal` to install the default
  `5.0.0-SNAPSHOT` locally for consumer testing.

Java contracts and models are generated from the specifications in
`src/main/resources`. Generated files are written below
`build/generated/openapi`; never edit or commit them. Make durable changes in a
Swagger file, the generator configuration in `build.gradle`, or a template in
`src/main/openapi-templates`.

Most of `src/main/java` was deliberately replaced by build-time generation.
`ActionMap`, `ConditionMap` and `SubjectMap` are the exceptions: they are small,
handwritten `HashMap<String, Object>` compatibility types required by existing
callers and MapStruct mappings. Their normal content-based `HashMap` equality is
intentional; do not restore the invalid equality emitted by Swagger Codegen 2.

## Contract compatibility

Before changing the generator, its options, templates or specifications, review
`ContractSmokeTest`. It discovers every generated API interface and currently
locks the expected count at 28. If an interface is intentionally added or
removed, update the count and retain the audit across the complete generated
surface.

Preserve these established behaviours unless a breaking change is explicitly
approved:

- Every generated endpoint method is abstract. Do not generate default 501
  implementations, inherited loggers, or servlet/ObjectMapper helper methods.
- Structured model and collection responses explicitly produce
  `application/json`; raw text uses its specific media type. Generated mappings
  must not produce or consume `*/*`.
- `GET /pin` has no `consumes` condition. Its request values are query
  parameters, not multipart form data.
- `/testing-support/cache/refresh` and the generated `cacheRefresh` method must
  remain absent.
- Preserve the exact positional annotations on `accessToken`.
- Preserve the intentional `selfRegisterUser(String jwt,
  SelfRegisterRequest body)` parameter order.
- `getAssignableRoles` and `getRolesForService` return `List<String>`, not a
  named array wrapper.
- Required collections default to empty lists while optional collections remain
  null. Relevant required collection getters retain their validation metadata.
- Generated models retain no-argument constructors rather than adding required-
  argument constructors.
- Named collection models such as `ArrayOfStrings` use content-aware equality.
  `DeletedData` default instances remain equal.
- Shared definitions continue to generate in the existing `shared` package.
- The generator's `URI` type remains mapped to `String`, and Boolean getters use
  the `is` prefix.

The `beanValidationQueryParams.mustache` override deliberately suppresses
inferred `@NotNull` on required header parameters while retaining query,
request-body and model validation. This avoids incompatible validation metadata
when `idam-api` controller methods override the generated interfaces. Compare
the override with the upstream OpenAPI Generator template whenever upgrading the
generator.

## Dependencies and publication metadata

`idam-api` supplies the runtime framework. Spring Web, Jackson annotations,
Jakarta Validation and Jakarta Annotation therefore remain `compileOnly` here
and must not be exposed as transitive dependencies in the published POM.
Jackson Databind is used only by tests.

The `verifyPublishedDependencies` task enforces the published dependency
allowlist and is part of `check`. If a new published dependency is genuinely
required, explain why the consumer cannot supply it before changing the
allowlist.

Dependencies and GitHub Actions are maintained by Renovate using the shared
HMCTS configuration in `.github/renovate.json`.

## Consumer verification

This repository verifies generation, compilation, serialization and contract
invariants. It does not compile `idam-api` as part of its build.

For a generator or public-contract change:

1. Run `./gradlew clean build` here.
2. Publish the snapshot with `./gradlew publishToMavenLocal`.
3. Point `idam-api` at `com.github.hmcts:idam-api-spec:5.0.0-SNAPSHOT` and compile
   or test the affected modules manually.

That downstream check is especially important for method signatures, parameter
annotations, validation metadata, response media types and model defaults.

## Release process

CI runs `./gradlew clean build` for pull requests into `master` and pushes to
`master`.

Azure publication is tag-driven:

1. Use an exact `X.Y.Z` tag such as `5.0.0`. Tags prefixed with `v`, snapshots
   and prerelease suffixes are rejected.
2. The publication workflow validates the tag and runs
   `./gradlew clean build`.
3. `./gradlew publish` runs only after that build succeeds. Azure credentials
   are scoped to the publishing step.

The release workflow is `.github/workflows/ado_artifacts_build.yml`. Local
snapshot publication does not require Azure credentials.
