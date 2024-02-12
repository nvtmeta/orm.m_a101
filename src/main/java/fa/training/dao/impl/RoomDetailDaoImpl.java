package fa.training.dao.impl;

import fa.training.dao.RoomDetailDao;
import fa.training.entities.CinemaRoom;
import fa.training.entities.CinemaRoomDetail;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class RoomDetailDaoImpl implements RoomDetailDao {
    @Override
    public void save(CinemaRoomDetail cinemaRoomDetail) {
        Session session = HibernateUtils.getCurrentSession();

        session.beginTransaction();
        session.persist(cinemaRoomDetail);
        session.getTransaction().commit();
    }

    @Override
    public List<CinemaRoomDetail> findAll() {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        List<CinemaRoomDetail> list = session.createQuery("from CINEMA_ROOM_DETAIL").list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public CinemaRoomDetail getById(Integer id) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        CinemaRoomDetail cinemaRoomDetail = session.get(CinemaRoomDetail.class, id);
        session.getTransaction().commit();
        return cinemaRoomDetail;
    }

    @Override
    public void update(CinemaRoomDetail cinemaRoomDetail) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        session.merge(cinemaRoomDetail);
        session.getTransaction().commit();
    }

    @Override
    public void removeById(Integer id) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        CinemaRoomDetail cinemaRoomDetail = session.get(CinemaRoomDetail.class, id);
        session.remove(cinemaRoomDetail);
        session.getTransaction().commit();
    }
}
