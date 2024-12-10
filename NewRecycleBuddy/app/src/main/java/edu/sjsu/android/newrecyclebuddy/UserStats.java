package edu.sjsu.android.newrecyclebuddy;

public class UserStats {

    private String Name;
    private String Email;
    private int plastic_count;
    private int metal_count;
    private int glass_count;
    private int cardboard_count;
    private int paper_count;

    private int total;

    public UserStats() {
    }
    public UserStats(String name, int plastic_count, int metal_count, int glass_count, int cardboard_count, int paper_count) {
        this.Name = name;
        this.plastic_count = plastic_count;
        this.metal_count = metal_count;
        this.glass_count = glass_count;
        this.cardboard_count = cardboard_count;
        this.paper_count = paper_count;
    }

    public UserStats(String name, int total) {
        this.Name = name;
        this.total = total;
    }

    public String getName(){
        return Name;
    }

    public String getEmail() {
        return Email;
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

    public int getTotal() { return total; }

    public void setTotal() {
        this.total = plastic_count + metal_count + glass_count + cardboard_count + paper_count;
    }



}
