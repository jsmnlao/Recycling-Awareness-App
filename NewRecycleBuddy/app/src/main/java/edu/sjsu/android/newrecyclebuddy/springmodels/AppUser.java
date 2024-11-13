package edu.sjsu.android.newrecyclebuddy.springmodels;

import java.time.LocalDateTime;

public class AppUser
{
    private String username;
    private LocalDateTime registration;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public LocalDateTime getRegistration()
    {
        return registration;
    }

    public void setRegistration(LocalDateTime registration)
    {
        this.registration = registration;
    }
}
