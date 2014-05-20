package multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

public class WebSessionCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static final Logger LOG = LoggerFactory.getLogger(WebSessionCurrentTenantIdentifierResolver.class);

    private HttpServletRequest request;

    public void setRequest(HttpServletRequest request){
        this.request = request;
    }

    @Override
    public String resolveCurrentTenantIdentifier() {

//        if(null == request)
//        {
//            HttpServletRequest curRequest =
//                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                            .getRequest();
//
//        }
//        String tenantId = request.getHeader("X-TenantId");
//
//        if(null == tenantId)
//            tenantId = "tenant1";

        LOG.info(MessageFormat.format("Found TenantId=\"{0}\"", "tenant1"));

        return "tenant1";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}
