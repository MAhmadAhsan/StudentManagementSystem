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
        return gender;
    }
    // Setters
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name should not be empty.");
        }
        this.name = name;
    }
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age should not be negative.");
        }
        this.age = age;
    }
    public void setGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Gender should not be empty.");
        }else if (!gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f")){
            throw new IllegalArgumentException("Gender should be 'm' or 'f'.");
        }
        this.gender = gender.toLowerCase();
    }

    // toString
    @Override
    public String toString() {
        return "Personal Details:"+ "\nName : "+ getName() +"\nAge :  " + getAge() + "\nGender :  " + getGender();
    }
}