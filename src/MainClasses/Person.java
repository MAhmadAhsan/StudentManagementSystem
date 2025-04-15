package MainClasses;

import Details.ContactInfo;
import Details.Credentials;
import Details.PersonalInfo;

public abstract class Person {

    private PersonalInfo personalInfo;
    private ContactInfo contactInfo;
    private Credentials credentials;

    // Constructor
    public Person(){}
    public Person(PersonalInfo personalInfo, ContactInfo contactInfo, Credentials credentials) {
        this.setPersonalInfo(personalInfo);
        this.setContactInfo(contactInfo);
        this.setCredentials(credentials);
    }

    // Getters
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }
    public ContactInfo getContactInfo() {
        return contactInfo;
    }
    public Credentials getCredentials() {
        return credentials;
    }

    // Setters
    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    // toString
    public String toString() {
        return getPersonalInfo() + "\n " + getContactInfo() + "\n " + getCredentials();
    }
}