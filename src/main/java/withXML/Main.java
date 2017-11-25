package withXML;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) new XmlBeanFactory(new ClassPathResource("Beans.xml"));
        App app = (App) ctx.getBean("app");
        // app.logEvent();
        ctx.close();
        //withXML.Test.testPerson();
    }

}
