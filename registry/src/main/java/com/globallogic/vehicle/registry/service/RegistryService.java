package com.globallogic.vehicle.registry.service;

import com.globallogic.vehicle.registry.controller.VehicleSO;
import com.globallogic.vehicle.registry.entities.Vehicle;
import com.globallogic.vehicle.registry.exceptions.RegistryResourceNotFound;
import com.globallogic.vehicle.registry.repository.RegistryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistryService {

    @Autowired
    private RegistryRepository registryRepository;

    @Autowired
    protected ModelMapper modelMapper;

    public VehicleSO get(int id) {
        Vehicle found = registryRepository.findById(id);
        if (found == null) {
            throw new RegistryResourceNotFound("Vehicle with given id does not exist.");
        }
        return modelMapper.map(found, VehicleSO.class);
    }

    public VehicleSO create(VehicleSO so) {
        Vehicle vehicle = modelMapper.map(so, Vehicle.class);
        Vehicle save = registryRepository.save(vehicle);
        return modelMapper.map(save, VehicleSO.class);
    }

    public VehicleSO update(int id, String vin){
        registryRepository.findById(id).setVin(vin);
        registryRepository.flush();
        return modelMapper.map(registryRepository.findById(id), VehicleSO.class);
    }

    public void delete(int id){
        registryRepository.deleteVehiclesById(id);
    }
}