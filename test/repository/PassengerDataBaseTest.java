package repository;

import entity.Passenger.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerDataBaseTest {

    @BeforeEach
    void setUp() {

    }
    @Test
    @DisplayName("To add passenger to dataBase")
    void testThatPassengerCanBeAddedToPassengerDataBase(){
        assertTrue(PassengerDataBase.getPassengers().isEmpty());
        Passenger passenger1= new Passenger("1","Kelvin","Okoro",
                "kelvin@yahoo.com","081999","1234");
        PassengerDataBase.addPassenger(passenger1);
        assertFalse(PassengerDataBase.getPassengers().isEmpty());
        assertEquals(1,PassengerDataBase.getPassengers().size());
    }

}