package web.action;

import play.mvc.Action;
import play.mvc.Http;

import java.lang.reflect.Method;

public interface FilterBean {
    public Http.Request getRequest();

    public Method getMethod();

    public Action<?> getAction();
}
