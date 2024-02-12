package fa.training.dao;

import fa.training.entities.Seat;

import java.util.List;

public interface SeatDao {

    void save(Seat seat);

    List<Seat> findAll();

    Seat getById(Integer id);

    void update(Seat seat);

    void removeById(Integer id);
}
