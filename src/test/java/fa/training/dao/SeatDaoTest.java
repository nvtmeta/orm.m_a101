package fa.training.dao;

import fa.training.dao.impl.SeatDaoImpl;
import fa.training.entities.Seat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeatDaoTest {

    private static SeatDao seatDao;
    private static Seat testSeat;

    @BeforeAll
    public static void setUp() {
        seatDao = new SeatDaoImpl();

        // Create a test seat
        testSeat = new Seat();
        testSeat.setSeatColumn("A");
        testSeat.setSeatRow(1);
        testSeat.setSeatStatus("Available");
        testSeat.setSeatType("Normal");

        // Save the test seat
        seatDao.save(testSeat);
    }

    @AfterAll
    public static void tearDown() {
        // Remove the test seat
        seatDao.removeById(testSeat.getSeatId());
    }

    @Test
    public void testSave() {
        // Create a new seat
        Seat seat = new Seat();
        seat.setSeatColumn("B");
        seat.setSeatRow(2);
        seat.setSeatStatus("Available");
        seat.setSeatType("VIP");

        // Save the seat
        seatDao.save(seat);

        // Check if the seat is saved successfully
        assertNotNull(seat.getSeatId());

        // Remove the seat
        seatDao.removeById(seat.getSeatId());
    }


    @Test()
    public void testSaveNull() {
        // Save a null seat
        assertThrows(IllegalArgumentException.class, () -> {
            seatDao.save(null);
        });
    }

    @Test
    public void testFindAll() {
        // Get all seats
        List<Seat> seats = seatDao.findAll();
        System.out.println("seat: " + seats.toString());
        // Check if the list is not empty
        assertFalse(seats.isEmpty());
    }

    @Test
    public void testGetById() {
        // Get the test seat by its ID
        Seat seat = seatDao.getById(testSeat.getSeatId());

        System.out.println("seat: " + seat);
        // Check if the retrieved seat is not null
        assertNotNull(seat);
        assertEquals(testSeat.getSeatId(), seat.getSeatId());
    }

    @Test
    public void testGetByIdNull() {
        // Get a non-existing seat by an invalid ID
        Seat seat = seatDao.getById(-1);

        // Check if the seat is null
        assertNull(seat);
    }

    @Test
    public void testUpdate() {
        // Update the test seat
        testSeat.setSeatStatus("Booked");
        seatDao.update(testSeat);

        // Get the updated seat
        Seat updatedSeat = seatDao.getById(testSeat.getSeatId());

        // Check if the seat is updated successfully
        assertEquals("Booked", updatedSeat.getSeatStatus());
    }

    @Test
    public void testUpdateNotExist() {
        Seat seat = new Seat();
        seat.setSeatId(-1);
        seat.setSeatRow(2);
        seat.setSeatColumn("B");
        seat.setSeatStatus("none");
        seat.setSeatType("VIP");

        assertThrows(Exception.class, () -> {
            seatDao.update(seat);
        });
    }

    @Test
    public void testRemoveById() {
        // Remove the test seat
        seatDao.removeById(testSeat.getSeatId());

        // Get the seat by its ID
        Seat seat = seatDao.getById(testSeat.getSeatId());

        // Check if the seat is null after removal
        assertNull(seat);
    }

    @Test
    public void testRemoveByIdNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            seatDao.removeById(-1);
        });
    }

}

