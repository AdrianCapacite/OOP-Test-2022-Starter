package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Nematode {
    String name;
    int length, limbs;
    char gender;
    boolean eyes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLimbs() {
        return limbs;
    }

    public void setLimbs(int limbs) {
        this.limbs = limbs;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public boolean isEyes() {
        return eyes;
    }

    public void setEyes(boolean eyes) {
        this.eyes = eyes;
    }

    public Nematode(String name, int length, int limbs, char gender, boolean eyes) {
        this.name = name;
        this.length = length;
        this.limbs = limbs;
        this.gender = gender;
        this.eyes = eyes;
    }

    public Nematode(TableRow tr) {
        this.name = tr.getString("name");
        this.length = tr.getInt("length");
        this.limbs = tr.getInt("limbs");
        this.gender = tr.getString("gender").charAt(0);
        this.eyes = tr.getInt("eyes") == 1;
    }

    public void render(PApplet p) {

    }
}
