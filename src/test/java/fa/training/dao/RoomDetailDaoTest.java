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


    @BeforeAll
    static void setUp() {
        roomDetailDao = new RoomDetailDaoImpl();

    }


    @Test
    public void testSave() {

        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setRoomName("Room 1");
        cinemaRoom.setSeatQuantity(50);

        Seat seat = new Seat();
        seat.setSeatColumn("A");
        seat.setSeatRow(1);
        seat.setSeatStatus("Available");
        seat.setSeatType("Normal");
        seat.setCinemaRoom(cinemaRoom);

        List<Seat> seats = Stream.of(seat).collect(Collectors.toList());
        cinemaRoom.setSeats(seats);

        CinemaRoomDetail testRoomDetail = new CinemaRoomDetail();
        testRoomDetail.setCinemaRoom(cinemaRoom);
        testRoomDetail.setRoomRate(10);
        testRoomDetail.setActiveDate(LocalDate.now());
        testRoomDetail.setRoomDescription("Room 1");
        roomDetailDao.save(testRoomDetail);

    }

    @Test
    public void saveNotExist() {
        assertThrows(Exception.class, () -> {
            roomDetailDao.save(null);
        });
    }

    @Test
    public void testFindAll() {

        List<CinemaRoomDetail> roomDetails = roomDetailDao.findAll();
        assertFalse(roomDetails.isEmpty());
        assertEquals(1, roomDetails.size());
    }

    @Test
    public void testGetById() {

        CinemaRoomDetail roomDetail = roomDetailDao.getById(1);
        assertNotNull(roomDetail);
    }

    @Test
    public void testFindByIdNotExist() {

      CinemaRoomDetail cinemaRoomDetail = roomDetailDao.getById(100000);

      assertNull(cinemaRoomDetail);
    }

    //
    @Test
    public void testUpdate() {
        CinemaRoomDetail cinemaRoomDetail = roomDetailDao.getById(1);

        // Update the cinema room
        cinemaRoomDetail.setRoomRate(20);
        roomDetailDao.update(cinemaRoomDetail);

        // Retrieve the updated cinema room
        CinemaRoomDetail updatedCinemaRoom = roomDetailDao.getById(1);

        // Assert that the updated cinema room is not null
        assertNotNull(updatedCinemaRoom);
        // Assert that the updated cinema room has the updated properties
        assertEquals(20, updatedCinemaRoom.getRoomRate());
    }

    @Test
    public void testUpdateNotExist() {
        CinemaRoomDetail cinemaRoomDetail = roomDetailDao.getById(100000);

        assertThrows(Exception.class, () -> {
            cinemaRoomDetail.setRoomRate(20);
            roomDetailDao.update(cinemaRoomDetail);
        });
    }

    @Test
    public void testRemoveById() {

        // Remove the test seat
        roomDetailDao.removeById(1);

        assertNull(roomDetailDao.getById(1));

    }

    @Test
    public void testRemoveByIdNotExist() {
        assertThrows(Exception.class, () -> {
            roomDetailDao.removeById(-1);
        });
    }


}
