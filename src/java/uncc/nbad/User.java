package uncc.nbad;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String addressField1;
    private String addressFiled2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String password;

    public User() {}

    public String getAddressField1() {
        return addressField1;
    }

    public void setAddressField1(String addressField1) {
        this.addressField1 = addressField1;
    }

    public String getAddressFiled2() {
        return addressFiled2;
    }

    public void setAddressFiled2(String addressFiled2) {
        this.addressFiled2 = addressFiled2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
