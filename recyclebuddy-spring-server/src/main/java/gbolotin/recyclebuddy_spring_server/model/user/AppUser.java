package gbolotin.recyclebuddy_spring_server.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;

@Entity
public class AppUser
{
    @Id
    private String username;
    private OffsetDateTime registration;
    private String name;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public OffsetDateTime getRegistration()
    {
        return registration;
    }

    public void setRegistration(OffsetDateTime registration)
    {
        this.registration = registration;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
