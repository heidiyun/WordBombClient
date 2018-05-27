import processing.core.PApplet;

public class Word2 {
    private String word;
    private float x;
    private float y;
    private float score;
    private boolean isAppear = true;
    private int red;
    private int green;
    private int blue;

    public Word2(String word) {
        this.x = (float) (Math.random() * 700 + 10);
        this.y = 50;
        this.word = word;
        this.score = word.length() * 5;
        this.red = 0;
        this.green = 0;
        this.blue = 80;
    }

    public void update() {
        if (y > 600)
            isAppear = false;
        y += 1;
    }

    public void render(PApplet pApplet) {

        pApplet.fill(red, green, blue);
        pApplet.textSize(20);
        pApplet.text(word, x, y);
    }

    public boolean getIsAppear() {
        return isAppear;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public String getWord() {
        return word;
    }

    public float getScore() {
        return score;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void setGreen(int green) {
        this.green = green;
    }
}