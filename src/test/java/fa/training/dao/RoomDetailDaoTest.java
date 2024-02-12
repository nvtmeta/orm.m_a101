package fa.training.dao;

import fa.training.dao.impl.RoomDaoImpl;
import fa.training.dao.impl.RoomDetailDaoImpl;
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

public class RoomDetailDaoTest {

    private static RoomDetailDao roomDetailDao;
    private static RoomDao roomDao;
    public static CinemaRoomDetail testCinemaRoomDetail;
    public static CinemaRoom cinemaRoom;


    @BeforeAll
    static void setUp() {
        roomDetailDao = new RoomDetailDaoImpl();
        roomDao = new RoomDaoImpl();

        CinemaRoom cinemaRoom = new CinemaRoom(testCinemaRoomDetail.getCinemaRoomDetailId(), "Test room 1", 100);

        testCinemaRoomDetail = new CinemaRoomDetail();
        testCinemaRoomDetail.setCinemaRoom(cinemaRoom);
        testCinemaRoomDetail.setRoomRate(100);
        testCinemaRoomDetail.setActiveDate(LocalDate.now());
        testCinemaRoomDetail.setRoomDescription("Test Description");
        roomDetailDao.save(testCinemaRoomDetail);

    }

    @AfterAll
    static void tearDown() {
        roomDao.removeById(cinemaRoom.getCinemaRoomId());
        roomDetailDao.removeById(testCinemaRoomDetail.getCinemaRoomDetailId());
    }


    @Test
    public void testSave() {

//        CinemaRoom cinemaRoom = new CinemaRoom(1, "Test room 2", 100);

        // Create a cinema room detail
        CinemaRoomDetail testCinemaRoomDetail = new CinemaRoomDetail();
        testCinemaRoomDetail.setCinemaRoom(cinemaRoom);
        testCinemaRoomDetail.setRoomRate(100);
        testCinemaRoomDetail.setActiveDate(LocalDate.now());
        testCinemaRoomDetail.setRoomDescription("Test Description");

        // Save the cinema room detail
        roomDetailDao.save(testCinemaRoomDetail);

        // Retrieve the saved cinema room detail by ID
        CinemaRoomDetail retrievedCinemaRoomDetail =
                roomDetailDao.getById(testCinemaRoomDetail.getCinemaRoomDetailId());

        // Assert the retrieved cinema room detail is not null
        assertNotNull(retrievedCinemaRoomDetail);

        // Assert the attributes of the retrieved cinema room detail
        assertEquals(testCinemaRoomDetail.getCinemaRoom(), retrievedCinemaRoomDetail.getCinemaRoom());
        assertEquals(testCinemaRoomDetail.getRoomRate(), retrievedCinemaRoomDetail.getRoomRate());
        assertEquals(testCinemaRoomDetail.getActiveDate(), retrievedCinemaRoomDetail.getActiveDate());
        assertEquals(testCinemaRoomDetail.getRoomDescription(), retrievedCinemaRoomDetail.getRoomDescription());

    }

    @Test
    public void saveNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            roomDetailDao.save(null);
        });
    }

    @Test
    public void testFindAll() {

    }
//
//
//    @Test
//    public void testGetById() {
//        CinemaRoom cinemaRoom = roomDao.getById(testRoom.getCinemaRoomId());
//
//        assertNotNull(cinemaRoom);
//        assertEquals(testRoom.getRoomName(), cinemaRoom.getRoomName());
//        assertEquals(testRoom.getSeatQuantity(), cinemaRoom.getSeatQuantity());
//    }
//
//    @Test
//    public void testFindByIdNotExist() {
//        // Assert that the method returns null for a non-existent ID
//        assertEquals(null, roomDao.getById(-1));
//    }
//
//    @Test
//    public void testUpdate() {
//        // Create a new cinema room
//        CinemaRoom updatedRoom = new CinemaRoom();
//        updatedRoom.setCinemaRoomId(testRoom.getCinemaRoomId());
//        updatedRoom.setRoomName("Updated Room");
//        updatedRoom.setSeatQuantity(150);
//
//        // Update the cinema room
//        roomDao.update(updatedRoom);
//
//        // Retrieve the updated cinema room
//        CinemaRoom updatedCinemaRoom = roomDao.getById(testRoom.getCinemaRoomId());
//
//        // Assert that the updated cinema room is not null
//        assertNotNull(updatedCinemaRoom);
//        // Assert that the updated cinema room has the updated properties
//        assertEquals("Updated Room", updatedCinemaRoom.getRoomName());
//        assertEquals(150, updatedCinemaRoom.getSeatQuantity());
//
//    }
//
//    @Test
//    public void testUpdateNotExist() {
//        // Create a cinema room with non-existent ID
//        CinemaRoom cinemaRoom = new CinemaRoom();
//        cinemaRoom.setCinemaRoomId(-1);
//        cinemaRoom.setRoomName("Room 1");
//
//        // Assert that updating the non-existent cinema room throws an exception
//        assertThrows(Exception.class, () -> {
//            roomDao.update(cinemaRoom);
//        });
//
//    }
//
//    @Test
//    public void testRemoveById() {
//        // Remove the test cinema room
//        roomDao.removeById(testRoom.getCinemaRoomId());
//
//        // Assert that the removed room is null
//        assertNull(roomDao.getById(testRoom.getCinemaRoomId()));
//
//    }
//
//    @Test
//    public void testRemoveByIdNotExist() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            roomDao.removeById(-1);
//        });
//    }


}
