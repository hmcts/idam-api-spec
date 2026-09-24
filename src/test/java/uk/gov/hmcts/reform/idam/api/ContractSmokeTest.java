package uk.gov.hmcts.reform.idam.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.reform.idam.api.external.UserRoleManagementApi;
import uk.gov.hmcts.reform.idam.api.shared.model.RoleDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContractSmokeTest {

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
}
