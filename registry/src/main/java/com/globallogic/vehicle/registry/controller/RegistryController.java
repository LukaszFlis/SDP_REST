package com.globallogic.vehicle.registry.controller;

import com.globallogic.vehicle.registry.service.RegistryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registry")
@Slf4j
@Api("Article Management API")
public class RegistryController {

    @Autowired
    private RegistryService registryService;

    @ApiOperation(value = "Returns a specified entity.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entity found")})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleSO get(@PathVariable(name = "id") int id) {
        VehicleSO vehicleSO = registryService.get(id);
        log.info("Returning vehicle={}", vehicleSO);
        return vehicleSO;
    }


    @ApiOperation(value = "Creates an entity.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vehicle entry created")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public VehicleSO create(@RequestBody VehicleSO so) {
        return registryService.create(so);
    }

    @ApiOperation(value = "Update an entity.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vehicle vin updated.")})
    @PostMapping(path = "/update")
    @ResponseStatus(code = HttpStatus.OK)
    public VehicleSO update(@RequestParam(name = "id") int id, @RequestParam(name = "vin") String vin) {
        return registryService.update(id, vin);
    }

    @ApiOperation(value = "Remove vehicle by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vehicle was deleted.")})
    @PostMapping(path = "/remove")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@RequestParam(name = "id") int id) {
        registryService.delete(id);
    }
}