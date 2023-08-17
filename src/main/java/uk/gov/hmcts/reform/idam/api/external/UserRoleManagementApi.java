/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package uk.gov.hmcts.reform.idam.api.external;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@jakarta.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-07-04T00:25:51.786+01:00")

@Api(value = "UserRoleManagement", description = "the UserRoleManagement API")
public interface UserRoleManagementApi {

    Logger log = LoggerFactory.getLogger(UserRoleManagementApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Removes a role from the user", nickname = "denyRoleToUser", notes = "Removes a role from a user. The role must exist in the system for the operation to be successful.", authorizations = {
        @Authorization(value = "auth", scopes = {
            @AuthorizationScope(scope = "manage-user", description = "allows modifying a User resource")
            })
    }, tags={ "User Role Management", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "The role has been revoked successfully from the user."),
        @ApiResponse(code = 400, message = "The user is not assigned to this role."),
        @ApiResponse(code = 401, message = "Missing bearer token."),
        @ApiResponse(code = 403, message = "Bearer token is expired, or it doesn't have the 'manage-user' scope, or the provided role is not included in the current user's assignable roles."),
        @ApiResponse(code = 404, message = "The user or the role do not exist.") })
    @RequestMapping(value = "/users/{userId}/roles/{roleName}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> denyRoleToUser(@ApiParam(value = "The access token associated to the session." ,required=true) @RequestHeader(value="authorization", required=true) String authorization,@ApiParam(value = "The id of the user.",required=true) @PathVariable("userId") String userId,@ApiParam(value = "The name of the role to revoke.",required=true) @PathVariable("roleName") String roleName) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UserRoleManagementApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Grants one or more roles to the user", nickname = "grantRolesToUser", notes = "Grants one or more roles to the user. The role(s) must exist in the system for the operation to be successful.", authorizations = {
        @Authorization(value = "auth", scopes = {
            @AuthorizationScope(scope = "manage-user", description = "allows modifying a User resource")
            })
    }, tags={ "User Role Management", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Role granted successfully"),
        @ApiResponse(code = 400, message = "The user is already assigned to this role or the role does not exist."),
        @ApiResponse(code = 401, message = "Missing bearer token."),
        @ApiResponse(code = 403, message = "Bearer token is expired, or it doesn't have the 'manage-user' scope, or some of the roles to assign are not part of the current user's assignable roles."),
        @ApiResponse(code = 404, message = "The user to be updated could not be found.") })
    @RequestMapping(value = "/users/{userId}/roles",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    default ResponseEntity<Void> grantRolesToUser(@ApiParam(value = "The access token associated to the session." ,required=true) @RequestHeader(value="authorization", required=true) String authorization,@ApiParam(value = "The userId of the user to be updated.",required=true) @PathVariable("userId") String userId,@ApiParam(value = "the body of the User JSON object containing the fields to update." ,required=true )  @Valid @RequestBody List<uk.gov.hmcts.reform.idam.api.shared.model.RoleDefinition> request) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UserRoleManagementApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Replaces the entire set of role grants to the user", nickname = "replacesRolesGrants", notes = "Grants one or more roles to the user. The role(s) must exist in the system for the operation to be successful.", authorizations = {
        @Authorization(value = "auth", scopes = {
            @AuthorizationScope(scope = "manage-user", description = "allows modifying a User resource")
            })
    }, tags={ "User Role Management", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Role grants replaced successfully."),
        @ApiResponse(code = 400, message = "One of the roles to assign does not exist."),
        @ApiResponse(code = 401, message = "Missing bearer token"),
        @ApiResponse(code = 403, message = "Bearer token is expired, or it doesn't have the 'manage-user' scope, or some of the roles to assign are not part of the current user's assignable roles."),
        @ApiResponse(code = 404, message = "The user to be updated could not be found.") })
    @RequestMapping(value = "/users/{userId}/roles",
        method = RequestMethod.PUT)
    default ResponseEntity<Void> replacesRolesGrants(@ApiParam(value = "The access token associated to the session." ,required=true) @RequestHeader(value="authorization", required=true) String authorization,@ApiParam(value = "The userId of the user to be updated.",required=true) @PathVariable("userId") String userId,@ApiParam(value = "the body of the User JSON object containing the fields to update." ,required=true )  @Valid @RequestBody List<uk.gov.hmcts.reform.idam.api.shared.model.RoleDefinition> request) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UserRoleManagementApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}