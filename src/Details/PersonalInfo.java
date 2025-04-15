package Details;

public class PersonalInfo {
    private String name;
    private int age;
    private String gender;

    // Constructors
    public PersonalInfo(){}
    public PersonalInfo(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    // Getters
    public String getName() {
        if (name == null) {
            throw new NullPointerException("Name has not been initialized.");
        }
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        if (gender == null) {
            throw new NullPointerException("Gender has not been initialized.");
        }
        return gender;
    }
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    // toString
    @Override
    public String toString() {
        return "Name : "+ getName() +"\nAge :  " + getAge() + "\nGender :  " + getGender();
    }
}