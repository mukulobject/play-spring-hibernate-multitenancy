package web.filter.impl;


import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.SimpleResult;
import web.action.FilterBean;
import web.action.impl.DefaultProxyChainAction;
import web.filter.ChainFilter;
import web.filter.GenericPlayFilterBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayFilterChain extends GenericPlayFilterBean {

    private Map<String, List<ChainFilter>> filtersMap = new HashMap<String, List<ChainFilter>>();
    private String[] filtersOrder = {"HIBERNATE_FILTER"};

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {

    }

    @Override
    public Action<?> doFilter(final FilterBean filterBean) {
        return new Action.Simple() {

            public F.Promise<SimpleResult> call(Http.Context context) throws Throwable {
                F.Promise<SimpleResult> simpleResultPromise = null;
                for (String order : filtersOrder) {
                    List<ChainFilter> filters = filtersMap.get(order);
                    for (ChainFilter filter : filters) {
                        simpleResultPromise = filter.doFilter(new DefaultProxyChainAction(context, delegate, filterBean.getMethod()));
                        if (simpleResultPromise != null) {
                            try {
                                return simpleResultPromise;
                            } finally {
                                for (String order1 : filtersOrder) {
                                    List<ChainFilter> filters1 = filtersMap.get(order1);
                                    for (ChainFilter filter1 : filters1) {
                                        filter1.destroy(context);
                                    }
                                }
                            }
                        }
                    }
                }
                try {
                    return simpleResultPromise;
                } finally {
                    for (String order : filtersOrder) {
                        List<ChainFilter> filters = filtersMap.get(order);
                        for (ChainFilter filter : filters) {
                            filter.destroy(context);
                        }
                    }
                }
            }
        };
    }

    /**
     * Sets the filters to be applied by the filter chain
     *
     * @param filtersMap a map with the filters to be applied by the filter chain
     */
    public void setFiltersMap(Map<String, List<ChainFilter>> filtersMap) {
        this.filtersMap = filtersMap;
    }

    @Override
    public void destroy() throws Exception {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
