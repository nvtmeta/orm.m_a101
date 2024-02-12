package fa.training.dao;

import fa.training.entities.CinemaRoomDetail;

import java.util.List;

public interface RoomDetailDao {


    void save(CinemaRoomDetail seat);

    List<CinemaRoomDetail> findAll();

    CinemaRoomDetail getById(Integer id);

    void update(CinemaRoomDetail seat);

    void removeById(Integer id);
}
