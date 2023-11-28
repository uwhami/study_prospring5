package com.apress.prospring5.ch10.sec3;

import org.joda.time.DateTime;

import java.net.URL;
import java.text.SimpleDateFormat;

public class Singer {

    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private URL personalSite;

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

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public URL getPersonalSite() {
        return personalSite;
    }

    public void setPersonalSite(URL personalSite) {
        this.personalSite = personalSite;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Singer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + sdf.format(birthDate.toDate()) +
                ", persionalSite=" + personalSite +
                '}';
    }
}
