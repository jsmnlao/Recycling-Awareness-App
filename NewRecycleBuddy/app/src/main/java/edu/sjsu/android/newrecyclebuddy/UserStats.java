package edu.sjsu.android.newrecyclebuddy;

public class UserStats {

    private String name;
    private int plastic_count;
    private int metal_count;
    private int glass_count;
    private int cardboard_count;
    private int paper_count;

    public UserStats() {
    }
    public UserStats(String name, int plastic_count, int metal_count, int glass_count, int cardboard_count, int paper_count) {
        this.name = name;
        this.plastic_count = plastic_count;
        this.metal_count = metal_count;
        this.glass_count = glass_count;
        this.cardboard_count = cardboard_count;
        this.paper_count = paper_count;
    }

    public String getName(){
        return name;
    }
    public int getPlastic_count() {
        return plastic_count;
    }

    public int getMetal_count() {
        return metal_count;
    }
    public int getGlass_count() {
        return glass_count;
    }
    public int getCardboard_count() {
        return cardboard_count;
    }
    public int getPaper_count() {
        return paper_count;
    }



}
