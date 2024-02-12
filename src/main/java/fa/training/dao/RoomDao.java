package fa.training.dao;

import fa.training.entities.CinemaRoom;

import java.util.List;

public interface RoomDao {

    void save(CinemaRoom seat);

    List<CinemaRoom> findAll();

    CinemaRoom getById(Integer id);

    void update(CinemaRoom seat);

    void removeById(Integer id);
}
