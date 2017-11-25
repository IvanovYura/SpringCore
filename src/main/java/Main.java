import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main {

    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("Beans.xml"));
        App app = (App) factory.getBean("app");
        app.logEvent("Some event for 1");
        app.logEvent("Some event for 2");

        //Test.testPerson();
    }

}
