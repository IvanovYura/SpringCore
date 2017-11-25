import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import utils.ConsoleEventLogger;

class Test {

    static void testPerson() { // add as JUnit test
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("Beans.xml"));
        Person person = (Person) factory.getBean("person");
        person.getName();
        person.getAge();

        //new name
        person.setName("Alex");
        person.getName();

        // try to create an another bean instance
        // it is not a new instance, because Singleton scope is a default
        Person anotherPerson = (Person) factory.getBean("person");
        anotherPerson.setName("Bob");
        anotherPerson.setAge(25);

        anotherPerson.getName();
        anotherPerson.getAge();

        if (anotherPerson.equals(person)) {
            ConsoleEventLogger.log("These are the same objects");
        }
    }

}
