package web;

import org.hibernate.Session;

public class Tenant {

    private String id;
    private Session session;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
