# OpenAPI Generator compatibility overrides

The generated Java contracts are consumed directly by `idam-api`. Keep the
following compatibility choices when upgrading OpenAPI Generator, and verify
them against the latest released JAR before publishing a new major version.

## Required header validation

`beanValidationQueryParams.mustache` is a narrowly scoped override of the
Spring generator partial with the same name. OpenAPI Generator uses that partial
for both query and header parameters and normally adds `@NotNull` to every
required parameter.

Swagger Codegen 2 added `@NotNull` to required query parameters but not to
required headers. Adding the constraint to generated interface headers changes
their Bean Validation method metadata. Controllers that retain the previous
validation annotations then fail at runtime with Hibernate Validator error
`HV000151` because an overriding method may not redefine parameter constraints.

The override therefore suppresses inferred `@NotNull` only when
`isHeaderParam` is true. It deliberately retains:

- `@NotNull` on required query parameters;
- explicit size, range, pattern and format constraints;
- request-body cascade validation;
- generated model validation.

When updating the generator, compare this partial with the upstream Spring
template and carry forward any unrelated template changes.

## Response media types

OpenAPI Generator 7 emits `produces = "*/*"` when a Swagger operation has a
response schema but no `produces` declaration. Spring Framework 7 can negotiate
that wildcard to `application/octet-stream` for requests without an `Accept`
header, which prevents Jackson from writing JSON model responses.

Operations returning models, collections, or other structured response bodies
therefore declare `application/json` explicitly in their Swagger definitions.
The testing-support operation that returns a raw PIN string declares
`text/plain`. Keep genuinely non-JSON responses on their specific media type;
do not use a wildcard as a fallback.

`GET /pin` uses query parameters for `redirect_uri`, `client_id` and `state`.
Swagger Codegen 2 treated its legacy `formData` declarations as request
parameters without adding a consumed media type, but OpenAPI Generator 7
interprets them as multipart form data and adds `consumes =
"multipart/form-data"`. Describing them as query parameters preserves the
existing `@RequestParam` Java signature and prevents the spurious consumes
condition.

## Model constructors and collection defaults

Swagger Codegen 2 generated no-argument model constructors and initialized
required array properties to empty lists while leaving optional arrays null.
The Spring generator is configured with:

- `generatedConstructorWithRequiredArgs=false` to avoid adding new public
  required-argument constructors;
- `containerDefaultToNull=true` so optional collections remain null;
- `defaultToEmptyContainer=array` so required, non-nullable arrays start empty.

The distinction is significant for Bean Validation. Required collection
getters already carried `@NotNull` in version 4.1.0, but omitted JSON properties
were accepted because their fields had been initialized to empty lists. It also
keeps service deletion safe when an allowed-role list is omitted.

OpenAPI Generator's content-aware equality for named collection models such as
`ArrayOfStrings` is retained deliberately. Swagger Codegen 2 considered any two
instances of the same collection model equal regardless of their contents,
while still producing content-dependent hash codes. Do not reproduce that
invalid equality contract as a compatibility override.

## Consumer-owned runtime dependencies

`idam-api` is the only consumer of this artifact and supplies Spring Web,
Jackson annotations and Jakarta Validation through its Spring Boot application
dependencies. These libraries are therefore compile-only here and are not
published in the `idam-api-spec` POM. This prevents the contract artifact from
selecting framework versions for the application while retaining everything
needed to compile generated sources. Tests declare their own runtime libraries
explicitly.

## Named collection schemas

Alias-model generation is enabled so existing public collection classes such as
`PatchRequest`, `ArrayOfServices` and `EvaluatePoliciesResponse` remain in API
method signatures. The responses for `getAssignableRoles` and
`getRolesForService` are intentionally declared as inline string arrays so they
continue to generate as `List<String>`, matching version 4.1.0.

## Shared schema mappings

Import and schema mappings keep common definitions in the existing `shared`
package instead of generating duplicate models in each API package.

## URI and Boolean types

The `URI` generator type is mapped to `String`, and Boolean getters use the
`is` prefix. These settings preserve the Java types and accessor names consumed
by `idam-api`.

## Policy map models

`ActionMap`, `ConditionMap` and `SubjectMap` remain small handwritten
`HashMap<String, Object>` compatibility classes. OpenAPI Generator 7 otherwise
generates composition-based classes that are not assignable to `Map`, breaking
existing MapStruct mappings and callers.
