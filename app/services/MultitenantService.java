package services;

import models.Bar;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultitenantService {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Bar bar) {
        sessionFactory.openSession().save(bar);
    }

    public Bar getBar(String id) {
        return (Bar) sessionFactory.openSession().get(Bar.class, id);
    }

    public List<Bar> getBars() {
        return sessionFactory.openSession().createQuery("from Bar").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
