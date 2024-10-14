package gbolotin.recyclebuddy_spring_server.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class AppUser
{
    @Id
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
