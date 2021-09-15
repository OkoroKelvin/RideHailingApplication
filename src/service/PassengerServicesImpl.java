package service;

import entity.Passenger.Passenger;
import entity.Passenger.PassengerUpdateForm;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import repository.PassengerDataBase;

import java.util.ArrayList;
import java.util.List;

public class PassengerServicesImpl implements PassengerServices {

    @Override
    public void createPassenger(Passenger passenger) throws UserAlreadyExistsException {
        if (PassengerDataBase.getPassengers().containsKey(passenger.getPassengerId())) {
            throw new UserAlreadyExistsException("Passenger already exists");
        }
        PassengerDataBase.addPassenger(passenger);
    }

    @Override
    public List<Passenger> findPassengerByName(String name) throws UserNotFoundException {
        List<Passenger> foundPassenger = new ArrayList<>();
        for (Passenger passenger : PassengerDataBase.findFindAllPassenger()) {
            if (passenger.getFirstName().equals(name) || passenger.getLastName().equals(name)) {
                foundPassenger.add(passenger);
            }
        }
        if (foundPassenger.isEmpty()) {
            throw new UserNotFoundException("Passenger not found on platform");
        }
        return foundPassenger;
    }

    @Override
    public Passenger findPassengerById(String passengerId) throws UserNotFoundException {
        Passenger foundPassenger = PassengerDataBase.getPassengers().get(passengerId);
        if (foundPassenger == null) {
            throw new UserNotFoundException("Passenger not found on platform");
        }
        return foundPassenger;
    }

    @Override
    public void updatePassenger(String id, PassengerUpdateForm form) throws UserNotFoundException {
        Passenger passenger = findPassengerById(id);
        if(form.firstName() != null){
            passenger.setFirstName(form.firstName());
        }if(form.lastName()!= null){
            passenger.setLastName(form.lastName());
        }if(form.phoneNumber() !=null){
            passenger.setPhoneNumber(form.phoneNumber());
        }if(form.email() != null){
            passenger.setEmail(form.email());
        }
        PassengerDataBase.addPassenger(passenger);
    }

    @Override
    public void deletePassenger(Passenger passenger) {
        if(PassengerDataBase.getPassengers().containsValue(passenger)){
            PassengerDataBase.deletePassenger(passenger);
        }
    }

}
