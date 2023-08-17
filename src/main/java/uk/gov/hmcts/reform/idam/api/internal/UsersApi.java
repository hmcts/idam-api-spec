/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package uk.gov.hmcts.reform.idam.api.internal;

import uk.gov.hmcts.reform.idam.api.internal.model.UserStatus;
import uk.gov.hmcts.reform.idam.api.internal.model.UserStatusResult;
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
@jakarta.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-07-04T00:25:50.979+01:00")

@Api(value = "users", description = "the users API")
public interface UsersApi {

    Logger log = LoggerFactory.getLogger(UsersApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "", nickname = "deleteUser", notes = "Deletes the user.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The user has been deleted"),
        @ApiResponse(code = 403, message = "You were unable to delete the user, you don't have permission."),
        @ApiResponse(code = 500, message = "Server error", response = uk.gov.hmcts.reform.idam.api.shared.model.ErrorMap.class) })
    @RequestMapping(value = "/users/{userId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteUser(@ApiParam(value = "The Base64-encoded authorization token" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "The id of the user",required=true) @PathVariable("userId") String userId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "denyRole", notes = "Removes a role from a user", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The details of a user", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class),
        @ApiResponse(code = 404, message = "The user or the role does not exist") })
    @RequestMapping(value = "/users/{userId}/roles/{roleId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<uk.gov.hmcts.reform.idam.api.shared.model.User> denyRole(@ApiParam(value = "The Base64-encoded authorization token" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "The id of the user",required=true) @PathVariable("userId") String userId,@ApiParam(value = "The id of the role",required=true) @PathVariable("roleId") String roleId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("\"\"", uk.gov.hmcts.reform.idam.api.shared.model.User.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "getUser", notes = "Returns the user details.", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The details of a user", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class),
        @ApiResponse(code = 404, message = "The user does not exist"),
        @ApiResponse(code = 500, message = "Server error", response = uk.gov.hmcts.reform.idam.api.shared.model.ErrorMap.class) })
    @RequestMapping(value = "/users/{userId}",
        method = RequestMethod.GET)
    default ResponseEntity<uk.gov.hmcts.reform.idam.api.shared.model.User> getUser(@ApiParam(value = "The Base64-encoded authorization token" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "The id of the user",required=true) @PathVariable("userId") String userId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("\"\"", uk.gov.hmcts.reform.idam.api.shared.model.User.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "getUserByEmail", notes = "Search user by email address.", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The details of a user", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class),
        @ApiResponse(code = 404, message = "The user does not exist."),
        @ApiResponse(code = 500, message = "Internal system error.", response = uk.gov.hmcts.reform.idam.api.shared.model.ErrorMap.class) })
    @RequestMapping(value = "/users",
        method = RequestMethod.GET)
    default ResponseEntity<uk.gov.hmcts.reform.idam.api.shared.model.User> getUserByEmail(@ApiParam(value = "The Base64-encoded authorization token" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@NotNull @ApiParam(value = "The email to search for", required = true) @Valid @RequestParam(value = "email", required = true) String email) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("\"\"", uk.gov.hmcts.reform.idam.api.shared.model.User.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "grantRole", notes = "Grants a role to a user", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The details of a user", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class),
        @ApiResponse(code = 403, message = "The requestor does not have permission to assign this role"),
        @ApiResponse(code = 404, message = "The user or the role does not exist") })
    @RequestMapping(value = "/users/{userId}/roles/{roleId}",
        method = RequestMethod.PATCH)
    default ResponseEntity<uk.gov.hmcts.reform.idam.api.shared.model.User> grantRole(@ApiParam(value = "The Base64-encoded authorization token" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "The id of the user",required=true) @PathVariable("userId") String userId,@ApiParam(value = "The id of the role",required=true) @PathVariable("roleId") String roleId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("\"\"", uk.gov.hmcts.reform.idam.api.shared.model.User.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "registerUser", notes = "Create a new user. Allows an IdAM internal admin to register a new user in the system and set their roles. Requires authorisation.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "User created successfully") })
    @RequestMapping(value = "/users",
        method = RequestMethod.POST)
    default ResponseEntity<Void> registerUser(@ApiParam(value = "The Base64-encoded authorization token" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "" ,required=true )  @Valid @RequestBody uk.gov.hmcts.reform.idam.api.shared.model.RegisterRequest request) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "selfRegisterUser", notes = "Uplift a PIN user via Registration. This allows an authenticated PIN user account to be 'upgraded' to a brand new LoA1 account.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "User created successfully") })
    @RequestMapping(value = "/users/self",
        method = RequestMethod.POST)
    default ResponseEntity<Void> selfRegisterUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody uk.gov.hmcts.reform.idam.api.shared.model.SelfRegisterRequest request,@NotNull @ApiParam(value = "JWT token from PIN temp user", required = true) @Valid @RequestParam(value = "jwt", required = true) String jwt) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "selfRegistration", notes = "Self register a user via a registration process.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "User created successfully") })
    @RequestMapping(value = "/users/selfregister",
        method = RequestMethod.POST)
    default ResponseEntity<Void> selfRegistration(@ApiParam(value = "" ,required=true )  @Valid @RequestBody uk.gov.hmcts.reform.idam.api.shared.model.SelfRegisterRequest request) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "updateUser", notes = "Update user details", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The details of a user", response = uk.gov.hmcts.reform.idam.api.shared.model.User.class),
        @ApiResponse(code = 404, message = "The user  does not exist") })
    @RequestMapping(value = "/users/{userId}",
        method = RequestMethod.PATCH)
    default ResponseEntity<uk.gov.hmcts.reform.idam.api.shared.model.User> updateUser(@ApiParam(value = "The Base64-encoded authorization token" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "The id of the user",required=true) @PathVariable("userId") String userId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody uk.gov.hmcts.reform.idam.api.shared.model.PatchRequest request) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("\"\"", uk.gov.hmcts.reform.idam.api.shared.model.User.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "userStatus", notes = "Sets the status of a user", response = UserStatusResult.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The status of the account", response = UserStatusResult.class),
        @ApiResponse(code = 404, message = "Account not found") })
    @RequestMapping(value = "/users/{userId}/status",
        method = RequestMethod.PUT)
    default ResponseEntity<UserStatusResult> userStatus(@ApiParam(value = "The Base64-encoded authorization token" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "The id of the user",required=true) @PathVariable("userId") String userId,@ApiParam(value = "The new status of the account" ,required=true )  @Valid @RequestBody UserStatus request) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"status\" : \"status\"}", UserStatusResult.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
