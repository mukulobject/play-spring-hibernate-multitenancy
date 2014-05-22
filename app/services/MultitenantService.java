package services;

import models.Bar;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import web.filter.impl.PlayHibernateSessionFilter;

import java.util.List;

@Service
public class MultitenantService {

    public void save(Bar bar) {
        getThreadLocalSessionFactory().save(bar);
    }

    public Bar getBar(String id) {

        return (Bar) getThreadLocalSessionFactory().get(Bar.class, id);
    }

    public List<Bar> getBars() {
        return getThreadLocalSessionFactory().createQuery("from Bar").list();
    }

    private Session getThreadLocalSessionFactory(){
         return PlayHibernateSessionFilter.TENANT_THREAD_LOCAL.get().getSession();
    }
}
