/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package uk.gov.hmcts.reform.idam.api.external;

import uk.gov.hmcts.reform.idam.api.external.model.BulkRegistrationRequest;
import uk.gov.hmcts.reform.idam.api.external.model.BulkRolesRequest;
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
@jakarta.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-07-04T00:25:52.183+01:00")

@Api(value = "BulkUserManagement", description = "the BulkUserManagement API")
public interface BulkUserManagementApi {

    Logger log = LoggerFactory.getLogger(BulkUserManagementApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "", nickname = "batchRoleManagement", notes = "Issue a bulk user role update batch request.", tags={ "Bulk User Management", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Request accepted."),
        @ApiResponse(code = 400, message = "Required parameters are missing or invalid."),
        @ApiResponse(code = 401, message = "Missing bearer token."),
        @ApiResponse(code = 403, message = "Bearer token is expired, or it doesn't have the 'create-user' scope.") })
    @RequestMapping(value = "/users/roles/batch",
        method = RequestMethod.POST)
    default ResponseEntity<Void> batchRoleManagement(@ApiParam(value = "The access token associated to the session." ,required=true) @RequestHeader(value="authorization", required=true) String authorization,@ApiParam(value = "the body of the User JSON object." ,required=true )  @Valid @RequestBody List<BulkRolesRequest> request) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default BulkUserManagementApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "batchUserRegistration", notes = "Issue a bulk user registration batch request.", tags={ "Bulk User Management", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Request accepted."),
        @ApiResponse(code = 400, message = "Required parameters are missing or invalid."),
        @ApiResponse(code = 401, message = "Missing bearer token."),
        @ApiResponse(code = 403, message = "Bearer token is expired, or it doesn't have the 'create-user' scope.") })
    @RequestMapping(value = "/users/registration/batch",
        method = RequestMethod.POST)
    default ResponseEntity<Void> batchUserRegistration(@ApiParam(value = "The access token associated to the session." ,required=true) @RequestHeader(value="authorization", required=true) String authorization,@ApiParam(value = "the body of the User JSON object." ,required=true )  @Valid @RequestBody List<BulkRegistrationRequest> request) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default BulkUserManagementApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
