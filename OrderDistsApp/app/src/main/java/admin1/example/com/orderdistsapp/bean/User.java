package admin1.example.com.orderdistsapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User{

    private String firstName;
    private String lastName;

    public User(String test, String user) {

        this.firstName=test;
        this.lastName=user;
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
}
