package fa.training.dao;

import fa.training.dao.impl.RoomDaoImpl;
import fa.training.dao.impl.RoomDetailDaoImpl;
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

public class RoomDaoTest {

    static RoomDao roomDao;
    static SeatDao seatDao;
    static RoomDetailDao roomDetailDao;
    private static CinemaRoom testRoom;


    @BeforeAll
    public static void setUp() {
        roomDao = new RoomDaoImpl();
        seatDao = new SeatDaoImpl();
        roomDetailDao = new RoomDetailDaoImpl();

        // Create an instance of the RoomDao implementation
        roomDao = new RoomDaoImpl();

        // Create a test room and set its properties
        testRoom = new CinemaRoom();
        testRoom.setRoomName("Test Room");
        testRoom.setSeatQuantity(50);

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

        List<Seat> seats = seatDao.findAll();
        if (seats.isEmpty()) {
            seatDao.save(seat1);
            seatDao.save(seat2);
        }

        // Add the seats to the room
        testRoom.setSeats(seats);

        // Create test cinema room detail
        CinemaRoomDetail testRoomDetail = new CinemaRoomDetail();
        testRoomDetail.setCinemaRoom(testRoom);
        testRoomDetail.setRoomRate(10);
        testRoomDetail.setActiveDate(LocalDate.now());
        testRoomDetail.setRoomDescription("Test Description");

        // Set the cinema room detail in the cinema room
        testRoom.setCinemaRoomDetail(testRoomDetail);

        // Save the test cinema room
        roomDao.save(testRoom);
    }

    @AfterAll
    public static void tearDown() {
        // Remove the test cinema room after all test cases are executed
        roomDao.removeById(testRoom.getCinemaRoomId());
    }


    @Test
    public void testSave() {
        // Create a new cinema room
        CinemaRoom newRoom = new CinemaRoom();
        newRoom.setRoomName("New Room");
        newRoom.setSeatQuantity(100);

        // Create seats for the cinema room
        List<Seat> seats = Stream.of(
                        new Seat(1, "A", 1, "AVAILABLE", "NORMAL"),
                        new Seat(2, "A", 2, "AVAILABLE", "NORMAL"),
                        new Seat(3, "B", 1, "AVAILABLE", "NORMAL")
                ).peek(seat -> seat.setCinemaRoom(newRoom))
                .collect(Collectors.toList());

        // Set the seats in the cinema room
        newRoom.setSeats(seats);

        // Create a cinema room detail
        CinemaRoomDetail roomDetail = new CinemaRoomDetail();
        roomDetail.setRoomRate(10);
        roomDetail.setActiveDate(LocalDate.now());
        roomDetail.setRoomDescription("New Room with Dolby Atmos");

        // Set the cinema room detail for the cinema room
        newRoom.setCinemaRoomDetail(roomDetail);
        // Save the cinema room
        roomDao.save(newRoom);

        assertNotNull(newRoom.getCinemaRoomId(), "Failed to save the new room");
        seats.forEach(seat -> assertNotNull(seat.getSeatId(), "Failed to save seat"));

        // Delete the saved entities
        roomDao.removeById(newRoom.getCinemaRoomId());
        System.out.println("seats" + seats);

//        seats.forEach(seat -> {
//            seatDao.removeById(seat.getSeatId());
//        });
//
    }


    @Test
    public void testFindByIdNotExist() {
        // Assert that the method returns null for a non-existent ID
        assertEquals(null, roomDao.getById(-1));
    }

    @Test
    public void testUpdate() {
        // Create a new cinema room
        CinemaRoom updatedRoom = new CinemaRoom();
        updatedRoom.setCinemaRoomId(testRoom.getCinemaRoomId());
        updatedRoom.setRoomName("Updated Room");
        updatedRoom.setSeatQuantity(150);

        // Update the cinema room
        roomDao.update(updatedRoom);

        // Retrieve the updated cinema room
        CinemaRoom updatedCinemaRoom = roomDao.getById(testRoom.getCinemaRoomId());

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
        assertThrows(Exception.class, () -> {
            roomDao.update(cinemaRoom);
        });

    }

    @Test
    public void testRemoveById() {
        // Remove the test cinema room
        roomDao.removeById(testRoom.getCinemaRoomId());

        // Assert that the removed room is null
        assertNull(roomDao.getById(testRoom.getCinemaRoomId()));

    }

    @Test
    public void testRemoveByIdNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            roomDao.removeById(-1);
        });
    }


}
