package net.sympower.cityzen.apxrefactored.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.sympower.cityzen.apxrefactored.model.HourlyResponse;
import net.sympower.cityzen.apxrefactored.service.IvxApxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ivx")
@Tag(name = "ivx-file-parser-api-controller")
@Slf4j
public class IvxFileParserController {


    private final IvxApxService ivxApxService;

    @Autowired
    IvxFileParserController(IvxApxService ivxApxService) {
        this.ivxApxService = ivxApxService;
    }

    @GetMapping(path = "/hourlyQuotes", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            method = "GET",
            description = "Returns the desired quotes hourly",
            summary = "getHourlyQuotes")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Inquire Hourly Quotes",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = HourlyResponse.class))
                            })
            })

    public ResponseEntity<List<HourlyResponse>> getHourlyQuotes() {
        log.info("Inside IvxFileParserController::getHourlyQuotes");
        return ResponseEntity.ok(ivxApxService.getHourlyQuotes());
    }


}


