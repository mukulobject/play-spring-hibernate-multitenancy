package services;

import models.Bar;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultitenantService {

    private SessionFactory sessionFactory;

    public void save(Bar bar) {
        sessionFactory.getCurrentSession().save(bar);
    }

    public Bar getBar(String id) {
        return (Bar) sessionFactory.getCurrentSession().get(Bar.class, id);
    }

    public List<Bar> getBars() {
        return sessionFactory.getCurrentSession().createQuery("from BAR").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
