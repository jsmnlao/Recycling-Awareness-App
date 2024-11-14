package gbolotin.recyclebuddy_spring_server.model.score;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Score
{
    @Id
    private String username;

    private int typesRecycled;
    private int cardboardRecycled;
    private int paperRecycled;
    private int glassRecycled;
    private int metalRecycled;
    private int plasticRecycled;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getTypesRecycled()
    {
        return typesRecycled;
    }

    public void setTypesRecycled(int typesRecycled)
    {
        this.typesRecycled = typesRecycled;
    }

    public int getCardboardRecycled()
    {
        return cardboardRecycled;
    }

    public void setCardboardRecycled(int cardboardRecycled)
    {
        this.cardboardRecycled = cardboardRecycled;
    }

    public int getPaperRecycled()
    {
        return paperRecycled;
    }

    public void setPaperRecycled(int paperRecycled)
    {
        this.paperRecycled = paperRecycled;
    }

    public int getGlassRecycled()
    {
        return glassRecycled;
    }

    public void setGlassRecycled(int glassRecycled)
    {
        this.glassRecycled = glassRecycled;
    }

    public int getMetalRecycled()
    {
        return metalRecycled;
    }

    public void setMetalRecycled(int metalRecycled)
    {
        this.metalRecycled = metalRecycled;
    }

    public int getPlasticRecycled()
    {
        return plasticRecycled;
    }

    public void setPlasticRecycled(int plasticRecycled)
    {
        this.plasticRecycled = plasticRecycled;
    }
}
