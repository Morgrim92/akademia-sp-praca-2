package pl.morg.pracadomowa2.repository;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import pl.morg.pracadomowa2.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VehicleRepository {
    List<Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        vehicles.add(new Vehicle(1L, "Audi", "A4", "Black"));
        vehicles.add(new Vehicle(2L, "Toyota", "Corolla", "Red"));
        vehicles.add(new Vehicle(3L, "Seat", "Ibiza", "White"));
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Optional<Vehicle> getVehicleById(long id) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getId() == id)
                .findFirst();
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public boolean updateVehicle(Vehicle vehicle) {
        Optional<Vehicle> foundedVehicle = vehicles.stream()
                .filter(updatedVehicle -> updatedVehicle.getId() == vehicle.getId())
                .findFirst();
        if (foundedVehicle.isPresent()) {
            vehicles.remove(vehicle);
            return vehicles.add(foundedVehicle.get());
        }
        return false;
    }

    public boolean updatePartOfVehicle(Vehicle vehicle) {
        Optional<Vehicle> vehicleToPartialUpdate = vehicles.stream()
                .filter(updatedVehicle -> updatedVehicle.getId() == vehicle.getId())
                .findFirst();
        if (vehicleToPartialUpdate.isPresent()) {
            if (vehicle.getColor() != null) {
                vehicleToPartialUpdate.get().setColor(vehicle.getColor());
            }
            if (vehicle.getModel() != null) {
                vehicleToPartialUpdate.get().setModel(vehicle.getModel());
            }
            if (vehicle.getVehicleBrand() != null) {
                vehicleToPartialUpdate.get().setVehicleBrand(vehicle.getVehicleBrand());
            }
        }
        return vehicleToPartialUpdate.isPresent();
    }

    public boolean addVehicle(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }

    public boolean removeVehicle(long id) {
        Optional<Vehicle> vehicleToRemove = vehicles.stream()
                .filter(removedVehicle -> removedVehicle.getId() == id)
                .findFirst();
        return vehicleToRemove.isPresent() && vehicles.remove(vehicleToRemove.get().getId());
    }

}
