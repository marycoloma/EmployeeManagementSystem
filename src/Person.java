public abstract class Person {
    private int personId;
    private String name;
    private int age;

    public int getId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void displayInfo();
}
