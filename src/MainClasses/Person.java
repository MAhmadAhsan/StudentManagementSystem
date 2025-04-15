package MainClasses;

import Details.ContactInfo;

public abstract class Person {
    private PersonalInfo personalInfo;
    private ContactInfo contactInfo;

    // Constructor
    public Person(){}
    public Person(PersonalInfo personalInfo, ContactInfo contactInfo) {
        this.setPersonalInfo(personalInfo);
        this.setContactInfo(contactInfo);
    }

    // Getters
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }
    public ContactInfo getContactInfo() {
        if (contactInfo == null) {
            throw new NullPointerException("contactInfo has not been initialized.");
        }
        return contactInfo;
    }

    // Setters
    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    // toString
    public String toString() {
        return getPersonalInfo() + "\n " + getContactInfo();
    }
}