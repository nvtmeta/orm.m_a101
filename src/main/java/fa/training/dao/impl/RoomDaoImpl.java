package fa.training.dao.impl;

import fa.training.dao.RoomDao;
import fa.training.entities.CinemaRoom;
import fa.training.entities.Seat;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public void save(CinemaRoom cinemaRoom) {
        Session session = HibernateUtils.getCurrentSession();

        session.beginTransaction();
        session.persist(cinemaRoom);
        session.getTransaction().commit();
    }

    @Override
    public List<CinemaRoom> findAll() {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        List<CinemaRoom> rooms = session.createQuery("from CINEMA_ROOM", CinemaRoom.class).getResultList();
        session.getTransaction().commit();
        return rooms;
    }

    @Override
    public CinemaRoom getById(Integer id) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        CinemaRoom room = session.get(CinemaRoom.class, id);
        session.getTransaction().commit();
        return room;
    }

    @Override
    public void update(CinemaRoom cinemaRoom) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        session.merge(cinemaRoom);
        session.getTransaction().commit();
    }

    @Override
    public void removeById(Integer id) {
        CinemaRoom cinemaRoom = getById(id);
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        session.remove(cinemaRoom);
        session.getTransaction().commit();
    }
}
