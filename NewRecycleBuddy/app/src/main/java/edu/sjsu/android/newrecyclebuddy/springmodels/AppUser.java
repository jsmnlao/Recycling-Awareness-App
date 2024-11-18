package edu.sjsu.android.newrecyclebuddy.springmodels;

import java.time.OffsetDateTime;

public class AppUser
{
    private String email;
    private OffsetDateTime registration;
    private String name;
    private String password;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public OffsetDateTime getRegistration()
    {
        return registration;
    }

    public void setRegistration(OffsetDateTime registration)
    {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
