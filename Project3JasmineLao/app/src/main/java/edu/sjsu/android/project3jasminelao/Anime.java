package edu.sjsu.android.project3jasminelao;

public class Anime {
    private final int imageID;
    private final int name, description;

    // alt + insert for faster setup

    public Anime(int imageID, int name, int description) {
        this.imageID = imageID;
        this.name = name;
        this.description = description;
    }

    public int getImageID() {
        return imageID;
    }

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

}
