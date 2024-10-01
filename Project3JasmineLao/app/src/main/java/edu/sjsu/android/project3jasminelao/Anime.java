package edu.sjsu.android.project3jasminelao;

public class Anime {
    private final int imageID;
    private final String name, description;

    // alt + insert for faster setup

    public Anime(int imageID, String name, String description) {
        this.imageID = imageID;
        this.name = name;
        this.description = description;
    }

    public int getImageID() {
        return imageID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
