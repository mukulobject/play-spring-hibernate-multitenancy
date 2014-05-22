package multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import play.Logger;
import web.filter.impl.PlayHibernateSessionFilter;

import java.text.MessageFormat;

public class WebSessionCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {

        if(PlayHibernateSessionFilter.TENANT_THREAD_LOCAL.get() == null)
            return "";
        String tenantId = PlayHibernateSessionFilter.TENANT_THREAD_LOCAL.get().getId();
        Logger.debug(MessageFormat.format("#### Found TenantId=\"{0}\"", tenantId));
        return tenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}
