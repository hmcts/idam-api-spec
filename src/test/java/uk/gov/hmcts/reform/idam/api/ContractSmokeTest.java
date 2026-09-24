package uk.gov.hmcts.reform.idam.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uk.gov.hmcts.reform.idam.api.external.model.EvaluatePoliciesRequest;
import uk.gov.hmcts.reform.idam.api.external.OpenIdConnectApi;
import uk.gov.hmcts.reform.idam.api.external.UserRoleManagementApi;
import uk.gov.hmcts.reform.idam.api.internal.ActivateApi;
import uk.gov.hmcts.reform.idam.api.internal.PinApi;
import uk.gov.hmcts.reform.idam.api.internal.TestingSupportApi;
import uk.gov.hmcts.reform.idam.api.internal.UsersApi;
import uk.gov.hmcts.reform.idam.api.internal.model.ActivateRequest;
import uk.gov.hmcts.reform.idam.api.internal.model.DeletedData;
import uk.gov.hmcts.reform.idam.api.internal.model.Service;
import uk.gov.hmcts.reform.idam.api.internal.model.ServiceUpdate;
import uk.gov.hmcts.reform.idam.api.shared.model.ArrayOfStrings;
import uk.gov.hmcts.reform.idam.api.shared.model.PatchRequest;
import uk.gov.hmcts.reform.idam.api.shared.model.RoleDefinition;
import uk.gov.hmcts.reform.idam.api.shared.model.SelfRegisterRequest;
import uk.gov.hmcts.reform.idam.api.shared.model.UpdateRole;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContractSmokeTest {

    private static final int EXPECTED_GENERATED_API_COUNT = 28;
    private static final String GENERATED_API_PACKAGE_PATH = "uk/gov/hmcts/reform/idam/api";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void serializesAndDeserializesGeneratedModels() throws Exception {
        RoleDefinition role = new RoleDefinition().name("caseworker");

        String json = objectMapper.writeValueAsString(role);
        RoleDefinition deserialized = objectMapper.readValue(json, RoleDefinition.class);

        assertEquals("{\"name\":\"caseworker\"}", json);
        assertEquals(role, deserialized);
    }

    @Test
    void retainsValidationMetadata() throws Exception {
        NotNull annotation = RoleDefinition.class
            .getMethod("getName")
            .getAnnotation(NotNull.class);

        assertNotNull(annotation);
    }

    @Test
    void exposesSpringWebContracts() throws Exception {
        assertTrue(UserRoleManagementApi.class.isInterface());
        assertEquals(
            ResponseEntity.class,
            UserRoleManagementApi.class
                .getMethod("denyRoleToUser", String.class, String.class, String.class)
                .getReturnType()
        );
    }

    @Test
    void auditsEveryGeneratedApiMapping() throws Exception {
        Set<Class<?>> apiTypes = generatedApiTypes();

        assertEquals(EXPECTED_GENERATED_API_COUNT, apiTypes.size());
        apiTypes.forEach(apiType -> {
            assertTrue(apiType.isInterface(), apiType.getName());
            Arrays.stream(apiType.getDeclaredMethods()).forEach(method -> {
                String endpoint = apiType.getSimpleName() + "." + method.getName();
                RequestMapping mapping = method.getAnnotation(RequestMapping.class);

                assertNotNull(mapping, endpoint + " must have @RequestMapping");
                assertEquals(1, mapping.value().length, endpoint + " must have exactly one path");
                assertEquals(1, mapping.method().length, endpoint + " must have exactly one HTTP method");
                assertFalse(
                    Arrays.asList(mapping.produces()).contains(MediaType.ALL_VALUE),
                    endpoint + " must not produce */*"
                );
                assertFalse(
                    Arrays.asList(mapping.consumes()).contains(MediaType.ALL_VALUE),
                    endpoint + " must not consume */*"
                );
                assertTrue(Modifier.isAbstract(method.getModifiers()), endpoint + " must be abstract");
                assertFalse(method.isDefault(), endpoint + " must not have a default implementation");
            });
        });
    }

    @Test
    void usesCorrectResponseMediaTypes() throws Exception {
        assertProduces(
            ActivateApi.class.getMethod("activateUser", ActivateRequest.class),
            MediaType.APPLICATION_JSON_VALUE
        );
        assertProduces(
            UsersApi.class.getMethod("updateUser", String.class, String.class, PatchRequest.class),
            MediaType.APPLICATION_JSON_VALUE
        );
        assertProduces(
            TestingSupportApi.class.getMethod("getPinByUserId", String.class),
            MediaType.TEXT_PLAIN_VALUE
        );
    }

    @Test
    void pinLoginDoesNotConsumeMultipartFormData() throws Exception {
        Method method = PinApi.class.getMethod(
            "loginWithPin",
            String.class,
            String.class,
            String.class,
            String.class
        );

        assertArrayEquals(new String[0], method.getAnnotation(RequestMapping.class).consumes());
    }

    @Test
    void accessTokenRetainsItsMappingAndPositionalAnnotations() throws Exception {
        Method method = OpenIdConnectApi.class.getMethod(
            "accessToken",
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class,
            String.class
        );
        RequestMapping mapping = method.getAnnotation(RequestMapping.class);

        assertArrayEquals(new String[]{"/token"}, mapping.value());
        assertArrayEquals(new RequestMethod[]{RequestMethod.POST}, mapping.method());
        assertProduces(method, MediaType.APPLICATION_JSON_VALUE);
        assertArrayEquals(new String[]{MediaType.APPLICATION_FORM_URLENCODED_VALUE}, mapping.consumes());

        Parameter[] parameters = method.getParameters();
        RequestHeader authorization = parameters[0].getAnnotation(RequestHeader.class);
        assertNotNull(authorization);
        assertEquals("Authorization", authorization.value());
        assertFalse(authorization.required());

        String[] requestParameterNames = {
            "grant_type",
            "refresh_token",
            "code",
            "redirect_uri",
            "client_id",
            "client_secret",
            "scope",
            "username",
            "password",
            "code_verifier"
        };
        for (int index = 0; index < requestParameterNames.length; index++) {
            assertRequestParam(
                parameters[index + 1],
                requestParameterNames[index],
                index == 0
            );
        }
    }

    @Test
    void selfRegisterUserRetainsItsIntentionalParameterOrder() throws Exception {
        Method method = UsersApi.class.getMethod(
            "selfRegisterUser",
            String.class,
            SelfRegisterRequest.class
        );
        Parameter[] parameters = method.getParameters();

        assertRequestParam(parameters[0], "jwt", true);
        RequestBody requestBody = parameters[1].getAnnotation(RequestBody.class);
        assertNotNull(requestBody);
        assertTrue(requestBody.required());
    }

    @Test
    void removedCacheRefreshEndpointStaysAbsent() throws Exception {
        assertTrue(generatedApiTypes().stream()
            .flatMap(apiType -> Arrays.stream(apiType.getDeclaredMethods()))
            .noneMatch(method -> method.getName().equals("cacheRefresh")));
    }

    @Test
    void requiredCollectionsDefaultToEmptyWhileOptionalCollectionsRemainNull() {
        EvaluatePoliciesRequest policyRequest = new EvaluatePoliciesRequest();
        UpdateRole roleUpdate = new UpdateRole();
        Service service = new Service();
        ServiceUpdate serviceUpdate = new ServiceUpdate();

        assertTrue(policyRequest.getResources().isEmpty());
        assertTrue(roleUpdate.getAssignableRoles().isEmpty());
        assertTrue(roleUpdate.getConflictingRoles().isEmpty());
        assertTrue(service.getAllowedRoles().isEmpty());
        assertTrue(serviceUpdate.getAllowedRoles().isEmpty());
        assertNull(serviceUpdate.getOnboardingRoles());
        assertNull(serviceUpdate.getOauth2RedirectUris());
        assertNull(serviceUpdate.getSsoProviders());
    }

    @Test
    void requiredCollectionValidationMetadataIsRetained() throws Exception {
        assertNotNull(EvaluatePoliciesRequest.class.getMethod("getResources").getAnnotation(NotNull.class));
        assertNotNull(UpdateRole.class.getMethod("getAssignableRoles").getAnnotation(NotNull.class));
        assertNotNull(UpdateRole.class.getMethod("getConflictingRoles").getAnnotation(NotNull.class));
        assertNotNull(ServiceUpdate.class.getMethod("getAllowedRoles").getAnnotation(NotNull.class));
    }

    @Test
    void generatedModelsRetainNoArgumentConstructorsOnly() {
        assertOnlyNoArgumentConstructor(EvaluatePoliciesRequest.class);
        assertOnlyNoArgumentConstructor(UpdateRole.class);
        assertOnlyNoArgumentConstructor(ServiceUpdate.class);
    }

    @Test
    void namedStringArraysUseContentAwareEquality() {
        ArrayOfStrings first = new ArrayOfStrings();
        first.add("first");
        ArrayOfStrings second = new ArrayOfStrings();
        second.add("second");

        assertFalse(first.equals(second));
    }

    @Test
    void deletedDataDefaultsRemainEqual() {
        assertEquals(new DeletedData(), new DeletedData());
    }

    private static void assertProduces(Method method, String... expectedMediaTypes) {
        RequestMapping mapping = method.getAnnotation(RequestMapping.class);

        assertNotNull(mapping);
        assertArrayEquals(expectedMediaTypes, mapping.produces());
    }

    private static void assertOnlyNoArgumentConstructor(Class<?> modelType) {
        assertEquals(1, modelType.getConstructors().length);
        assertEquals(0, modelType.getConstructors()[0].getParameterCount());
    }

    private static void assertRequestParam(Parameter parameter, String name, boolean required) {
        RequestParam requestParam = parameter.getAnnotation(RequestParam.class);

        assertNotNull(requestParam, "Missing @RequestParam for " + name);
        assertEquals(name, requestParam.value());
        assertEquals(required, requestParam.required(), "Unexpected required flag for " + name);
    }

    private static Set<Class<?>> generatedApiTypes()
        throws IOException, URISyntaxException {
        Path classesRoot = Path.of(
            OpenIdConnectApi.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        );
        Path apiPackageRoot = classesRoot.resolve(GENERATED_API_PACKAGE_PATH);

        try (Stream<Path> classes = Files.walk(apiPackageRoot)) {
            return classes
                .filter(Files::isRegularFile)
                .filter(path -> path.getFileName().toString().endsWith("Api.class"))
                .filter(path -> !path.getFileName().toString().contains("$"))
                .map(path -> loadClass(classesRoot, path))
                .filter(Class::isInterface)
                .collect(Collectors.toUnmodifiableSet());
        }
    }

    private static Class<?> loadClass(Path classesRoot, Path classFile) {
        String className = classesRoot.relativize(classFile).toString()
            .replace('/', '.')
            .replace('\\', '.')
            .replaceFirst("\\.class$", "");

        try {
            return Class.forName(className, false, OpenIdConnectApi.class.getClassLoader());
        } catch (ClassNotFoundException exception) {
            throw new IllegalStateException("Unable to load generated API " + className, exception);
        }
    }
}
