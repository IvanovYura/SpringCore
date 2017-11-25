import utils.ConsoleEventLogger;

public class Person {

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void getName() {
        ConsoleEventLogger.log(String.format("Your name is %s", name));
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void getAge() {
        ConsoleEventLogger.log(String.format("Your age is %d", age));
    }

    public void init() {
        ConsoleEventLogger.log("Bean is initialized");
    }

}
