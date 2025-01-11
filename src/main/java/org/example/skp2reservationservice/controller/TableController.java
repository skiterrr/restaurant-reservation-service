package org.example.skp2reservationservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.example.skp2reservationservice.dto.RestaurantDTO;
import org.example.skp2reservationservice.dto.TableDTO;
import org.example.skp2reservationservice.security.CheckSecurity;
import org.example.skp2reservationservice.service.TableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @Operation(summary = "Get all tables")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Page<TableDTO>> findAll(@RequestHeader(value = "Authorization") String authorization,
                                                  @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(tableService.getAllTables(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER"})
    public ResponseEntity<TableDTO> getTableByID(@RequestHeader(value = "Authorization") String authorization,
                                                 @PathVariable("id") long id) {
        return new ResponseEntity<>(tableService.getTableByID(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<TableDTO> createTable(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestBody TableDTO tableDTO) {
        return new ResponseEntity<>(tableService.createTable(tableDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<TableDTO> updateTable(@RequestHeader(value = "Authorization") String authorization,
                                                @PathVariable("id") long id, @RequestBody TableDTO tableDTO) {
        return new ResponseEntity<>(tableService.updateTable(id, tableDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> deleteRestaurant(@RequestHeader(value = "Authorization") String authorization,
                                              @PathVariable("id") long id) {
        tableService.deleteTable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
