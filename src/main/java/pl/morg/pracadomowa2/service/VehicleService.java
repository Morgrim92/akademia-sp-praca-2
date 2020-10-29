package pl.morg.pracadomowa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morg.pracadomowa2.model.Vehicle;
import pl.morg.pracadomowa2.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.getVehicles();
    }

    public Optional<Vehicle> getVehicleById(long id) {
        return vehicleRepository.getVehicleById(id);
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return vehicleRepository.getVehiclesByColor(color);
    }

    public boolean updateVehicle(Vehicle vehicle) {
        return vehicleRepository.updateVehicle(vehicle);
    }

    public boolean updatePartOfVehicle(Vehicle vehicle) {
        return vehicleRepository.updatePartOfVehicle(vehicle);
    }

    public boolean addVehicle(Vehicle vehicle) {
        return vehicleRepository.addVehicle(vehicle);
    }

    public boolean removeVehicle(long id) {
        return vehicleRepository.removeVehicle(id);
    }

}
