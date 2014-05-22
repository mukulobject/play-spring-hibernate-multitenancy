package web.filter.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import play.Logger;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.SimpleResult;
import web.Tenant;
import web.action.ProxyChainAction;
import web.filter.ChainFilter;


public class PlayHibernateSessionFilter implements ChainFilter {
    public static final ThreadLocal<Tenant> TENANT_THREAD_LOCAL = new ThreadLocal<Tenant>();
    private Http.Context context;
    private Action<?> delegate;
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void destroy(Http.Context context) {
        Logger.debug("PlayHibernateSessionFilter now cleared, as request processing completed");
    }

    @Override
    public F.Promise<SimpleResult> doFilter(ProxyChainAction proxyChainAction) {
        Logger.debug("PlayHibernateSessionFilter Applied!");
        context = proxyChainAction.getHttpContext();
        delegate = proxyChainAction.getAction();
        F.Promise<SimpleResult> simpleResultPromise = null;
        boolean participate = false;
        try {
            if (TENANT_THREAD_LOCAL.get() == null || TENANT_THREAD_LOCAL.get().getSession() == null) {
                Tenant tenant = new Tenant();
                tenant.setId(proxyChainAction.getHttpContext().request().getHeader("X-TenantId"));
                TENANT_THREAD_LOCAL.set(tenant);
                tenant.setSession(sessionFactory.openSession());
                participate = true;
            }
            simpleResultPromise = delegate.call(context);
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                if (participate && TENANT_THREAD_LOCAL.get().getSession() != null)
                    SessionFactoryUtils.closeSession(TENANT_THREAD_LOCAL.get().getSession());
                TENANT_THREAD_LOCAL.remove();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return simpleResultPromise;
    }

}
