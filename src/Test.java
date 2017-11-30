
public class Test {

    private int age; //|AGE IS A INT TYPE VARIABLE
    private String name; //|NAME IS A STRING TYPE VARIABLE

    public Test(int argAge, String argName) {
        this.age = argAge;
        this.name = argName;
    }
    //|――――|GETAGE METHOD RETURNS INT―――――――――――――――――――|

    public int getAge() {
        return this.age;
    }
    //|――――|SETAGE METHOD TAKE INT TYPE ARGUMENT――――――――|

    public void setAge(int argAge) {
        this.age = argAge;
    }
    //|――――|GETNAME METHOD RETURNS STRING―――――――――――――――|

    public String getName() {
        return this.name;
    }
    //|――――|SETNAME METHOD TAKE STRING TYPE ARGUMENT――――|

    public void setName(String argName) {
        this.name = argName;
    }

    public String toString() {
        return "STRING_VALUE_OF_THE_TEST_MODEL: " + this.age + this.name;
    }
}
