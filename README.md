# IdAM API specifications

Swagger 2.0 specifications for the HMCTS Identity and Access Management (IdAM)
REST APIs. The specifications are packaged as resources in a Java library so
that other services can consume a versioned copy.

## Specifications

All specifications are in [`src/main/resources`](src/main/resources).

| File | API |
| --- | --- |
| [`openid-connect.yaml`](src/main/resources/openid-connect.yaml) | OpenID Connect and OAuth 2 endpoints |
| [`internal-admin-api.yaml`](src/main/resources/internal-admin-api.yaml) | Internal administration and user-management endpoints |
| [`external-user-mgmt-api.yaml`](src/main/resources/external-user-mgmt-api.yaml) | External user and role management |
| [`external-sso-api.yaml`](src/main/resources/external-sso-api.yaml) | Federated single sign-on services |
| [`external-dynamic-role-assign-api.yaml`](src/main/resources/external-dynamic-role-assign-api.yaml) | Dynamic role assignment |
| [`external-policies-api.yaml`](src/main/resources/external-policies-api.yaml) | Policy management and evaluation |
| [`external-bulk-request-api.yaml`](src/main/resources/external-bulk-request-api.yaml) | Bulk user registration and role requests |
| [`external-batch-status-api.yaml`](src/main/resources/external-batch-status-api.yaml) | Bulk-request batch status |
| [`shared.yaml`](src/main/resources/shared.yaml) | Shared data definitions used when generating models |

The endpoint in `shared.yaml` is a placeholder for code generation and is not a
service endpoint that should be called.

## Getting started

### Prerequisites

- JDK 21
- No separate Gradle installation is required; use the included wrapper.

Clone the repository and check that it builds:

```bash
git clone git@github.com:hmcts/idam-api-spec.git
cd idam-api-spec
./gradlew check
```

Create the library locally with:

```bash
./gradlew build
```

The generated JAR is written to `build/libs/` and contains the Java API
contracts, models, and YAML specifications. The YAML files are placed at the
root of its classpath.

## Code generation

Java contracts and models are generated from all nine Swagger specifications
with OpenAPI Generator during compilation. Generated sources are written below
`build/generated/openapi/`; they are build output and must not be edited or
committed.

Three small map models remain in `src/main/java` as compatibility types. They
preserve the public `HashMap` classes exposed by earlier releases while the API
interfaces and other models are generated.

Deliberate generator mappings and template overrides are documented in the
[OpenAPI Generator compatibility notes](src/main/openapi-templates/README.md).

Generate the sources without running the rest of the build with:

```bash
./gradlew generateApi
```

`./gradlew check` regenerates the sources, validates that the specifications can
be processed, compiles the generated Java, runs the tests, and checks the
published dependency allowlist.

## Using the artifact

The library is published to the HMCTS Azure Artifacts `hmcts-lib` Maven feed.
Once that feed is configured for your project, add the dependency using the
required released version:

```gradle
dependencies {
    implementation 'com.github.hmcts:idam-api-spec:<version>'
}
```

Release versions come from the `RELEASE_VERSION` environment variable used by
the publishing workflow. Local builds use `5.0.0-SNAPSHOT`. Check the repository
tags or the artifact feed for available release versions.

## Making changes

1. Edit the relevant file in `src/main/resources`.
2. Keep the document compatible with Swagger 2.0 (`swagger: '2.0'`).
3. Update the specification's `info.version` when the API contract version
   changes.
4. Run `./gradlew check` before opening a pull request.

Changes to an API contract can affect generated clients and consuming services.
Clearly describe breaking changes, additions, and deprecations in the pull
request.

See the [contribution guidelines](.github/CONTRIBUTING.md) for the full process.

## Publishing

Pull requests and pushes to `master` run `./gradlew clean build` in GitHub
Actions. To publish a release, create and push a tag in `X.Y.Z` format, for
example `5.0.0`. The publishing workflow validates the tag and runs the complete
build before publishing to Azure Artifacts using:

```bash
./gradlew publish
```

Publishing requires `AZURE_DEVOPS_ARTIFACT_USERNAME` and
`AZURE_DEVOPS_ARTIFACT_TOKEN`. The workflow sets `RELEASE_VERSION` to the tag
name. If the build or tests fail, the publishing step is not run.

## Licence

This project is licensed under the [MIT License](LICENSE).
