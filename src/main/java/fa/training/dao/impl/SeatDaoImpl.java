package fa.training.dao.impl;

import fa.training.dao.SeatDao;
import fa.training.entities.Seat;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class SeatDaoImpl implements SeatDao {
    @Override
    public void save(Seat seat) {
        Session session = HibernateUtils.getCurrentSession();

        session.beginTransaction();
        session.persist(seat);
        session.getTransaction().commit();
    }

    @Override
    public List<Seat> findAll() {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        List<Seat> seats = session.createQuery("from SEAT", Seat.class).getResultList();
        session.getTransaction().commit();
        return seats;
    }

    @Override
    public Seat getById(Integer id) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        Seat seat = session.get(Seat.class, id);
        session.getTransaction().commit();
        return seat;
    }

    @Override
    public void update(Seat seat) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        session.merge(seat);
        session.getTransaction().commit();
    }

    @Override
    public void removeById(Integer id) {
        Seat seat = getById(id);
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        session.remove(seat);
        session.getTransaction().commit();
    }
}
