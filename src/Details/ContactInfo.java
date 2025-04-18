package Details;

public class ContactInfo {
    private String phoneNumber;
    private String address;

    // Constructor
    public ContactInfo() {}
    public ContactInfo(String email, String phoneNumber, String address) {
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
    }

    // Getters
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }

    // Setters
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public  String toString(){
        return "Contact Info:" + "\nPhone:" + getPhoneNumber() + "\nAddress:" + getAddress();
   }
}