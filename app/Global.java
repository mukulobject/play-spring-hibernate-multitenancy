import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {

    private ApplicationContext ctx;

    @Override
    public void onStart(Application app) {
//        ctx = new AnnotationConfigspringConfigurationNameApplicationContext(AppConfig.class);
        String springConfigurationName = "classpath*:application-context.xml";
        ctx = new ClassPathXmlApplicationContext(springConfigurationName);
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        return ctx.getBean(clazz);
    }

}