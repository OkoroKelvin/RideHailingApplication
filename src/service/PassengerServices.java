package service;

import entity.Passenger.Passenger;
import entity.Passenger.PassengerUpdateForm;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;

import java.util.List;

public interface PassengerServices {

    void createPassenger(Passenger passenger) throws UserAlreadyExistsException;

    List<Passenger> findPassengerByName(String name) throws UserNotFoundException;

    Passenger findPassengerById(String passengerId) throws UserNotFoundException;

    void updatePassenger(String id, PassengerUpdateForm form) throws UserNotFoundException;

    void deletePassenger(Passenger passenger);
}
