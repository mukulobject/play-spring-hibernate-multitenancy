package web.filter;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import play.mvc.Action;
import web.action.FilterBean;

public abstract class GenericPlayFilterBean implements InitializingBean, DisposableBean {

    public abstract void onStart();

    public abstract void onStop();

    public abstract Action<?> doFilter(FilterBean filterBean);
}
