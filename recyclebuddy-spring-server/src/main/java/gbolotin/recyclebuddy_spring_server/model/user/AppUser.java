package gbolotin.recyclebuddy_spring_server.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;

@Entity
public class AppUser
{
    @Id
    private String email;
    private OffsetDateTime registration;
    private String name;
    private String password; // TODO: password encryption and other security stuff

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
