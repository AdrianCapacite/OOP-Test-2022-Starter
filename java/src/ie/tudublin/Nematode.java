package ie.tudublin;

import javax.swing.text.Segment;

import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PShapeSVG.Text;
import processing.data.TableRow;

public class Nematode {
    PApplet p;
    String name;
    int length;
    char gender;
    boolean limbs, eyes;
    PVector pos;
    PVector targetPos;

    private final int SEGMENT_SIZE = 50;
    private int SEGMENT_SIZE2_3 = SEGMENT_SIZE * 2 / 3;
    private final float SPEED_COEF = 0.2f;
    private final int FONT_SIZE = 100;
    private final int NAME_HEIGHT = 100 + SEGMENT_SIZE;
    private int id;

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

    public boolean getLimbs() {
        return limbs;
    }

    public void setLimbs(boolean limbs) {
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

    public Nematode(int id, PApplet p, String name, int length, boolean limbs, char gender, boolean eyes) {
        this.id = id;
        this.p = p;
        this.name = name;
        this.length = length;
        this.limbs = limbs;
        this.gender = gender;
        this.eyes = eyes;
        this.pos = new PVector(0, 0);
        this.targetPos = new PVector(0, 0);
    }

    public Nematode(int id, PApplet p, TableRow tr) {
        this(
                id,
                p,
                tr.getString("name"),
                tr.getInt("length"),
                tr.getInt("limbs") == 1,
                tr.getString("gender").charAt(0),
                tr.getInt("eyes") == 1);
    }

    public void render() {
        // p.noFill();
        p.pushMatrix();
        p.translate(pos.x, pos.y - SEGMENT_SIZE / 2 * length);

        p.fill(255);
        p.textSize(FONT_SIZE);
        p.textAlign(PApplet.CENTER, PApplet.CENTER);
        p.text(name, 0, -NAME_HEIGHT);

        p.stroke(255);
        p.fill(0);
        p.strokeWeight(SEGMENT_SIZE / 10);

        // Draw
        for (int i = 0; i < length; i++) {
            p.pushMatrix();
            p.translate(0, i * SEGMENT_SIZE);
            renderSegment(p, i == 0, i == length - 1);
            p.popMatrix();
        }
        p.popMatrix();

    }

    public void renderSegment(PApplet p, boolean first, boolean last) {
        if (limbs) {
            p.line(-SEGMENT_SIZE, 0, SEGMENT_SIZE, 0);
        }
        if (first) {
            // Render eyes
            p.line(0, 0, -SEGMENT_SIZE2_3, -SEGMENT_SIZE2_3);
            p.line(0, 0, SEGMENT_SIZE2_3, -SEGMENT_SIZE2_3);
            p.circle(-SEGMENT_SIZE2_3, -SEGMENT_SIZE2_3, SEGMENT_SIZE / 10);
            p.circle(SEGMENT_SIZE2_3, -SEGMENT_SIZE2_3, SEGMENT_SIZE / 10);
        } else if (last) {
            // render tail
            if (gender != 'n') {
                if (gender == 'f' || gender == 'h') {
                    p.circle(0, 0, SEGMENT_SIZE / 2);
                }
                if (gender == 'm' || gender == 'h') {
                    p.line(0, 0, 0, SEGMENT_SIZE);
                    p.circle(0, SEGMENT_SIZE, SEGMENT_SIZE / 8);
                }
            }
        }

        p.circle(0, 0, SEGMENT_SIZE);

    }

    public void update(int index, int population) {
        // Nematode visability, positioning, animation
        pos.y = p.height / 2;
        // 0 in middle, left is 13, right is 1, halfway point i wrap
        int position = (index + id) % population - population / 2;
        targetPos.x = p.width / 2 + position * p.width / 2;

        pos.x = PApplet.lerp(pos.x, targetPos.x, SPEED_COEF);

    }
}
