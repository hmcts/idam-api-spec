/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package uk.gov.hmcts.reform.idam.api.internal;

import uk.gov.hmcts.reform.idam.api.internal.model.ForgotPasswordDetails;
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

@Api(value = "validateResetPasswordToken", description = "the validateResetPasswordToken API")
public interface ValidateResetPasswordTokenApi {

    Logger log = LoggerFactory.getLogger(ValidateResetPasswordTokenApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "", nickname = "validateResetPasswordToken", notes = "Validate reset password JWT. The token is generated following a forgotPassword request and is sent to the user over email.", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "JWT is valid.", response = String.class),
        @ApiResponse(code = 400, message = "JWT is invalid."),
        @ApiResponse(code = 404, message = "JWT not found. Returns the initial request back (if exists) to resume user journey.", response = ForgotPasswordDetails.class) })
    @RequestMapping(value = "/validateResetPasswordToken",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<String> validateResetPasswordToken(@ApiParam(value = "JWT from the validation email" ,required=true) @RequestHeader(value="token", required=true) String token,@ApiParam(value = "JWT from the validation email" ,required=true) @RequestHeader(value="code", required=true) String code) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ValidateResetPasswordTokenApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
