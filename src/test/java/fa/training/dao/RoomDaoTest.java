package fa.training.dao;

import fa.training.dao.impl.RoomDaoImpl;
import fa.training.entities.CinemaRoom;
import fa.training.entities.CinemaRoomDetail;
import fa.training.entities.Seat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RoomDaoTest {

    static RoomDao roomDao;


    @BeforeAll
    public static void setUp() {
        roomDao = new RoomDaoImpl();
    }


    @Test
    public void testSave() {
        // Create a test room and set its properties
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setRoomName("Test Room");
        cinemaRoom.setSeatQuantity(50);

        // Create test seats for the room
        Seat seat1 = new Seat();
        seat1.setSeatColumn("A");
        seat1.setSeatRow(1);
        seat1.setSeatStatus("Available");
        seat1.setSeatType("Normal");

        Seat seat2 = new Seat();
        seat2.setSeatColumn("B");
        seat2.setSeatRow(1);
        seat2.setSeatStatus("Available");
        seat2.setSeatType("Normal");

        List<Seat> seats = Stream.of(seat1, seat2).collect(Collectors.toList());

        // Add the seats to the room
        cinemaRoom.setSeats(seats);

        // Create test cinema room detail
        CinemaRoomDetail testRoomDetail = new CinemaRoomDetail();
        testRoomDetail.setCinemaRoom(cinemaRoom);
        testRoomDetail.setRoomRate(10);
        testRoomDetail.setActiveDate(LocalDate.now());
        testRoomDetail.setRoomDescription("Test Description");

        // Set the cinema room detail in the cinema room
        cinemaRoom.setCinemaRoomDetail(testRoomDetail);

        // Save the test cinema room
        roomDao.save(cinemaRoom);

        CinemaRoom savedRoom = roomDao.getById(cinemaRoom.getCinemaRoomId());
        assertNotNull(savedRoom);
    }

    @Test
    public void testSaveNotExist() {
        // Create a test room and set its properties
        CinemaRoom cinemaRoom = new CinemaRoom();
        // Save the test cinema room
        assertThrows(Exception.class, () -> roomDao.save(cinemaRoom));

    }


    @Test
    public void testFindAll() {
        List<CinemaRoom> rooms = roomDao.findAll();


        assertEquals(1, rooms.size());
    }

    @Test
    public void testFindById() {
        CinemaRoom room = roomDao.getById(1);
        assertNotNull(room);

    }


    @Test
    public void testFindByIdNotExist() {
        // Assert that the method returns null for a non-existent ID
        assertNull(roomDao.getById(-1));
    }

    @Test
    public void testUpdate() {
        // Create a new cinema room
        CinemaRoom updatedRoom = new CinemaRoom();
        updatedRoom.setCinemaRoomId(1);
        updatedRoom.setRoomName("Updated Room");
        updatedRoom.setSeatQuantity(150);

        // Update the cinema room
        roomDao.update(updatedRoom);

        // Retrieve the updated cinema room
        CinemaRoom updatedCinemaRoom = roomDao.getById(1);

        // Assert that the updated cinema room is not null
        assertNotNull(updatedCinemaRoom);
        // Assert that the updated cinema room has the updated properties
        assertEquals("Updated Room", updatedCinemaRoom.getRoomName());
        assertEquals(150, updatedCinemaRoom.getSeatQuantity());

    }

    @Test
    public void testUpdateNotExist() {
        // Create a cinema room with non-existent ID
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setCinemaRoomId(-1);
        cinemaRoom.setRoomName("Room 1");

        // Assert that updating the non-existent cinema room throws an exception
        assertThrows(Exception.class, () -> roomDao.update(cinemaRoom));

    }

    @Test
    public void testRemoveById() {
        // Remove the test cinema room
        roomDao.removeById(1);

        // Assert that the removed room is null
        assertNull(roomDao.getById(1));

    }

    @Test
    public void testRemoveByIdNotExist() {
        assertThrows(IllegalArgumentException.class, () -> roomDao.removeById(-1));
    }


}
