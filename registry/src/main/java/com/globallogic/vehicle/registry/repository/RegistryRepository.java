package com.globallogic.vehicle.registry.repository;

import com.globallogic.vehicle.registry.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistryRepository extends JpaRepository<Vehicle, Integer> {

    Vehicle findById(int id);

    void deleteVehiclesById(int id);
}
