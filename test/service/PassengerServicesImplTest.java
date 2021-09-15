package service;

import entity.Passenger.Passenger;
import entity.Passenger.PassengerUpdateForm;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.PassengerDataBase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassengerServicesImplTest {
    PassengerServices passengerServices;
    Passenger passenger2;
    Passenger passenger3;
    Passenger passenger4;

    @BeforeEach
    void setUp() {
        passengerServices = new PassengerServicesImpl();
        passenger2 = new Passenger("2", "Okoro", "Ovie",
                "ovie@yahoo.com", "0812", "1234");
        passenger3 = new Passenger("3", "Okoro", "Tope",
                "tope@yahoo.com", "0813", "1235");
        passenger4 = new Passenger("4", "Okon", "love", "love@yahoo.com", "1222", "1111");


    }

    @Test
    @DisplayName("Create Passenger")
    void testThatPassengerCanBeCreatedAndAddedToItsDatabase() {
        try {
            passengerServices.createPassenger(passenger2);
            passengerServices.createPassenger(passenger3);
            passengerServices.createPassenger(passenger4);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        assertFalse(PassengerDataBase.getPassengers().isEmpty());
        assertEquals(3, PassengerDataBase.getPassengers().size());
    }

    @Test
    @DisplayName("Passenger Already Exist Test")
    void testThatAPassengerCannotBeCreatedIfAlreadyExist() {
        try {
            passengerServices.createPassenger(passenger2);
            passengerServices.createPassenger(passenger3);
            passengerServices.createPassenger(passenger4);
            passengerServices.createPassenger(passenger4);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> passengerServices.createPassenger(passenger4));
        assertFalse(PassengerDataBase.getPassengers().isEmpty());
        assertEquals(3, PassengerDataBase.getPassengers().size());
    }

    @Test
    @DisplayName("Find Passenger")
    void testToFindPassengerByFirstNameOrLastName() {
        try {
            passengerServices.createPassenger(passenger2);
            passengerServices.createPassenger(passenger3);
            passengerServices.createPassenger(passenger4);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> passengerServices.createPassenger(passenger3));
        assertFalse(PassengerDataBase.getPassengers().isEmpty());
        assertEquals(3, PassengerDataBase.getPassengers().size());


        List<Passenger> foundPassengers = null;

        try {
            foundPassengers = passengerServices.findPassengerByName("Okoro");
        } catch (UserNotFoundException userNotFoundException) {
            System.err.printf("%s: ", userNotFoundException.getLocalizedMessage());
        }
        assertEquals(2, foundPassengers.size());
    }

    @Test
    @DisplayName("Find Passenger")
    void testToThatThrowExceptionsIfUserNotFound() {
        try {
            passengerServices.createPassenger(passenger2);
            passengerServices.createPassenger(passenger3);
            passengerServices.createPassenger(passenger4);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> passengerServices.findPassengerByName("topehashah"));
        assertEquals("Passenger not found on platform", exception.getLocalizedMessage());
    }

    @Test
    @DisplayName("Find passenger by Id")
    void testToFindPassengerByPassengerId() {
        try {
            passengerServices.createPassenger(passenger2);
            passengerServices.createPassenger(passenger3);
            passengerServices.createPassenger(passenger4);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        assertFalse(PassengerDataBase.getPassengers().isEmpty());
        assertEquals(3, PassengerDataBase.getPassengers().size());

        Passenger foundPassenger = null;
        try {
            foundPassenger = passengerServices.findPassengerById("6");
        } catch (UserNotFoundException userNotFoundException) {
            System.err.printf("%s: ", userNotFoundException.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Find Passenger")
    void testThatThrowExceptionsIfUserNotFoundById() {
        try {
            passengerServices.createPassenger(passenger2);
            passengerServices.createPassenger(passenger3);
            passengerServices.createPassenger(passenger4);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> passengerServices.findPassengerById("7"));
        assertEquals("Passenger not found on platform", exception.getLocalizedMessage());

    }
    @Test
    @DisplayName("To Update User")
    void testToUpdateUsersProfile(){
        try {
            passengerServices.createPassenger(passenger2);
            passengerServices.createPassenger(passenger3);
            passengerServices.createPassenger(passenger4);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        PassengerUpdateForm form = new PassengerUpdateForm("Tope","Fame","null","null");
        try {
            passengerServices.updatePassenger("2",form);
        } catch (UserNotFoundException userNotFoundException) {
            System.err.printf("%s: ", userNotFoundException.getLocalizedMessage());
        }
        Passenger foundPassenger = null;
        try {
            foundPassenger = passengerServices.findPassengerById("2");
        } catch (UserNotFoundException userNotFoundException) {
            System.err.printf("%s: ", userNotFoundException.getLocalizedMessage());
        }
        assertEquals("Tope", foundPassenger.getFirstName());
        assertEquals("Fame", foundPassenger.getLastName());
    }

    @Test
    @DisplayName("Delete passenger")
    void testThatSystemCanDeletePassenger(){
        try {
            passengerServices.createPassenger(passenger2);
            passengerServices.createPassenger(passenger3);
            passengerServices.createPassenger(passenger4);
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            System.err.printf("%s: ", userAlreadyExistsException.getLocalizedMessage());
        }
        passengerServices.deletePassenger(passenger2);
    }
}