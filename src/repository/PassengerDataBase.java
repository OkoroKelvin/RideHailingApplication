package repository;

import entity.Passenger.Passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassengerDataBase {

    private static Map<String, Passenger> passengers = new HashMap<>();

    public static Map<String, Passenger> getPassengers() {
        return passengers;
    }
    public static void addPassenger(Passenger passenger) {
        passengers.put(passenger.getPassengerId(),passenger);
    }
//Singleton

    public static List<Passenger> findFindAllPassenger () {
        return new ArrayList<Passenger>(passengers.values());
    }


    public static void deletePassenger(Passenger passenger) {
        if(passengers.containsKey(passenger.getPassengerId())){
            passengers.remove(passenger.getPassengerId(),passenger);
        }
    }
}
