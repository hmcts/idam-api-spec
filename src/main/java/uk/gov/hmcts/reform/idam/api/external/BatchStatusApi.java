/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package uk.gov.hmcts.reform.idam.api.external;

import uk.gov.hmcts.reform.idam.api.external.model.BatchResponse;
import uk.gov.hmcts.reform.idam.api.external.model.BatchStatusResponse;
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
@jakarta.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-07-04T00:25:52.228+01:00")

@Api(value = "BatchStatus", description = "the BatchStatus API")
public interface BatchStatusApi {

    Logger log = LoggerFactory.getLogger(BatchStatusApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "", nickname = "batchStatus", notes = "Get the latest state of an accepted batch request.", response = BatchStatusResponse.class, tags={ "Batch Status", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Request accepted.", response = BatchStatusResponse.class),
        @ApiResponse(code = 400, message = "Required parameters are missing or invalid."),
        @ApiResponse(code = 401, message = "Missing bearer token."),
        @ApiResponse(code = 403, message = "Bearer token is expired, or it doesn't have the required scopes."),
        @ApiResponse(code = 404, message = "Batch not found.") })
    @RequestMapping(value = "/api/v1/batch/{batchId}",
        method = RequestMethod.GET)
    default ResponseEntity<BatchStatusResponse> batchStatus(@ApiParam(value = "The access token associated to the session." ,required=true) @RequestHeader(value="authorization", required=true) String authorization,@ApiParam(value = "The id of the batch process.",required=true) @PathVariable("batchId") String batchId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \"createdAt\",  \"success\" : 6,  \"response\" : [ {    \"path\" : \"path\",    \"errorMessages\" : [ \"errorMessages\", \"errorMessages\" ],    \"statusCode\" : 5,    \"status\" : \"status\"  }, {    \"path\" : \"path\",    \"errorMessages\" : [ \"errorMessages\", \"errorMessages\" ],    \"statusCode\" : 5,    \"status\" : \"status\"  } ],  \"totalRequests\" : 0,  \"failed\" : 1,  \"status\" : \"ACCEPTED\"}", BatchStatusResponse.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default BatchStatusApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "cancelBatch", notes = "Cancel a batch operation.", tags={ "Batch Status", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Success."),
        @ApiResponse(code = 400, message = "Required parameters are missing or invalid."),
        @ApiResponse(code = 401, message = "Missing bearer token."),
        @ApiResponse(code = 403, message = "Bearer token is expired, or it doesn't have the required scopes."),
        @ApiResponse(code = 404, message = "Batch not found.") })
    @RequestMapping(value = "/api/v1/batch/{batchId}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> cancelBatch(@ApiParam(value = "The access token associated to the session." ,required=true) @RequestHeader(value="authorization", required=true) String authorization,@ApiParam(value = "The id of the batch process.",required=true) @PathVariable("batchId") String batchId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default BatchStatusApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "getBatches", notes = "Fetch all batches with some optional filters", response = BatchResponse.class, responseContainer = "List", tags={ "Batch Status", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Request accepted.", response = BatchResponse.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Parameters are invalid."),
        @ApiResponse(code = 401, message = "Missing bearer token."),
        @ApiResponse(code = 403, message = "Bearer token is expired, or it doesn't have the required scopes.") })
    @RequestMapping(value = "/api/v1/batch",
        method = RequestMethod.GET)
    default ResponseEntity<List<BatchResponse>> getBatches(@ApiParam(value = "The access token associated to the session. IDAM_SYSTEM_OWNER role is required." ,required=true) @RequestHeader(value="authorization", required=true) String authorization,@ApiParam(value = "Optional parameter allowing filtering by batch status.") @Valid @RequestParam(value = "status", required = false) String status,@ApiParam(value = "Optional parameter allowing filtering by clientId.") @Valid @RequestParam(value = "clientId", required = false) String clientId,@ApiParam(value = "The number of items per page, default of 20") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "The index of the page of items, starting at 0 for the first page") @Valid @RequestParam(value = "page", required = false) Integer page) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"requestClientId\" : \"requestClientId\",  \"processorId\" : \"processorId\",  \"lastModifiedDate\" : \"lastModifiedDate\",  \"requestUserId\" : \"requestUserId\",  \"id\" : \"id\",  \"state\" : \"ACCEPTED\",  \"creationDate\" : \"creationDate\"}, {  \"requestClientId\" : \"requestClientId\",  \"processorId\" : \"processorId\",  \"lastModifiedDate\" : \"lastModifiedDate\",  \"requestUserId\" : \"requestUserId\",  \"id\" : \"id\",  \"state\" : \"ACCEPTED\",  \"creationDate\" : \"creationDate\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default BatchStatusApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "runBatch", notes = "Executes the target batch job.", response = BatchResponse.class, tags={ "Batch Status", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Request accepted.", response = BatchResponse.class),
        @ApiResponse(code = 400, message = "Required parameters are missing or invalid."),
        @ApiResponse(code = 401, message = "Missing bearer token."),
        @ApiResponse(code = 403, message = "Bearer token is expired, or it doesn't have the required scopes."),
        @ApiResponse(code = 404, message = "Batch not found.") })
    @RequestMapping(value = "/api/v1/batch/{batchId}",
        method = RequestMethod.POST)
    default ResponseEntity<BatchResponse> runBatch(@ApiParam(value = "The access token associated to the session." ,required=true) @RequestHeader(value="authorization", required=true) String authorization,@ApiParam(value = "The id of the batch process.",required=true) @PathVariable("batchId") String batchId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"requestClientId\" : \"requestClientId\",  \"processorId\" : \"processorId\",  \"lastModifiedDate\" : \"lastModifiedDate\",  \"requestUserId\" : \"requestUserId\",  \"id\" : \"id\",  \"state\" : \"ACCEPTED\",  \"creationDate\" : \"creationDate\"}", BatchResponse.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default BatchStatusApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}