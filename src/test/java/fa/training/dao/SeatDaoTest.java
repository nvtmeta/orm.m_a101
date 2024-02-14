package fa.training.dao;

import fa.training.dao.impl.SeatDaoImpl;
import fa.training.entities.CinemaRoom;
import fa.training.entities.CinemaRoomDetail;
import fa.training.entities.Seat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SeatDaoTest {

    private static SeatDao seatDao;

    @BeforeAll
    public static void setUp() {
        seatDao = new SeatDaoImpl();
    }


    @Test
    public void testSave() {

        // Create a test room and set its properties
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setRoomName("Test Room");
        cinemaRoom.setSeatQuantity(50);

        // Create test seats for the room
        Seat seat = new Seat();
        seat.setSeatColumn("A");
        seat.setSeatRow(1);
        seat.setSeatStatus("Available");
        seat.setSeatType("Normal");
        seat.setCinemaRoom(cinemaRoom);

        Seat seat2 = new Seat();
        seat2.setSeatColumn("B");
        seat2.setSeatRow(1);
        seat2.setSeatStatus("Available");
        seat2.setSeatType("Normal");
        seat2.setCinemaRoom(cinemaRoom);

        List<Seat> seatList = Stream.of(seat, seat2).collect(Collectors.toList());

        // Add the seats to the room
        cinemaRoom.setSeats(seatList);


        // Save the test cinema room
        seatDao.save(seat);

        Seat seatSave = seatDao.getById(seat.getSeatId());
        // Check if the saved seat is not null
        assertNotNull(seatSave);
    }


    @Test()
    public void testSaveNull() {
        // Create a null seat
        Seat seat = new Seat();
        // Save a null seat
        assertThrows(Exception.class, () -> {
            seatDao.save(seat);
        });
    }

    @Test
    public void testFindAll() {
        // Get all seats
        List<Seat> seats = seatDao.findAll();
        System.out.println("seat: " + seats.toString());
        // Check if the list is not empty
        assertFalse(seats.isEmpty());
        assertEquals(2, seats.size());
    }

    @Test
    public void testGetById() {
        // Get the test seat by its ID
        Seat seat = seatDao.getById(1);

        // Check if the retrieved seat is not null
        assertNotNull(seat);
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
        Seat seat = seatDao.getById(1);
        seat.setSeatStatus("Unavailable");
        seatDao.update(seat);

        Seat seatUpdate = seatDao.getById(1);
        assertEquals("Unavailable", seatUpdate.getSeatStatus());
    }

    @Test
    public void testUpdateNotExist() {
        Seat seat = new Seat();
        seat.setSeatStatus("Unavailable");

        assertThrows(Exception.class, () -> {
            seatDao.update(seat);
        });
    }

    @Test
    public void testRemoveById() {
        // Remove the test seat
        seatDao.removeById(1);

        // Get the seat by its ID
        Seat seat = seatDao.getById(1);

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

