package gbolotin.recyclebuddy_spring_server.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class AppUser
{
    @Id
    private String username;
    private Date registration;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Date getRegistration()
    {
        return registration;
    }

    public void setRegistration(Date registration)
    {
        this.registration = registration;
    }
}
