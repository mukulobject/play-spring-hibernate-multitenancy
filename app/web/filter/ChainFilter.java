package web.filter;

import play.libs.F;
import play.mvc.Http;
import play.mvc.SimpleResult;
import web.action.ProxyChainAction;


public interface ChainFilter {
    public void initialize();

    public void destroy(Http.Context context);

    public F.Promise<SimpleResult> doFilter(ProxyChainAction proxyChainAction);
}
