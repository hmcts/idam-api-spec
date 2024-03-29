/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package uk.gov.hmcts.reform.idam.api.external;

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
@jakarta.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-07-04T00:25:49.793+01:00")

@Api(value = "Default", description = "the Default API")
public interface DefaultApi {

    Logger log = LoggerFactory.getLogger(DefaultApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Authenticate Username and Password", nickname = "authenticate", notes = "On success, it returns a cookie reference to either a new or existing session. If MFA is required, it will initiate the OTP flow, returning AuthId cookie allowing flow completion instead. ", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Authentication is successful, or waiting for another call with OTP."),
        @ApiResponse(code = 401, message = "Username password combination not found") })
    @RequestMapping(value = "/authenticate",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> authenticate(@ApiParam(value = "The user's username") @RequestParam(value="username", required=false)  String username,@ApiParam(value = "The user's password") @RequestParam(value="password", required=false)  String password,@ApiParam(value = "The redirectUri parameter to run against policies to work out if OTP is required") @RequestParam(value="redirectUri", required=false)  String redirectUri,@ApiParam(value = "The original IP address of the auth request (user)") @RequestParam(value="originIp", required=false)  String originIp) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DefaultApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Finishes User Authentication using OTP", nickname = "authenticateOtp", notes = "Finishes an authentication flow by supplying an OTP for MFA.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Authentication is successful."),
        @ApiResponse(code = 400, message = "Incorrect OTP"),
        @ApiResponse(code = 401, message = "User not authorised") })
    @RequestMapping(value = "/authenticate?service=otpe",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> authenticateOtp(@ApiParam(value = "The one-time password sent to complete the OTP flow", required=true) @RequestParam(value="otp", required=true)  String otp,@ApiParam(value = "A reference to an already existing authentication flow requiring MFA.", required=true) @RequestParam(value="authId", required=true)  String authId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DefaultApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
