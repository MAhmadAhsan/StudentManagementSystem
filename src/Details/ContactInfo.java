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
        if (phoneNumber.matches("^03[0-9]{9}$")) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number. It must start with '03' and be followed by 9 digits.");
        }
    }

    public void setAddress(String address) {
        if(address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address should not be empty.");
        }
        this.address = address;
    }

    @Override
    public  String toString(){
        return "Contact Info:" + "\nPhone: " + getPhoneNumber() + "\nAddress: " + getAddress();
   }
}