package me.brenny.mcclone.Objects;

public class TextureData {
    public int top;
    public int bottom;
    public int left;
    public int right;
    public int front;
    public int back;

    public TextureData(int top, int bottom, int left, int right, int front, int back) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.front = front;
        this.back = back;
    }

    public void process() {
        int factor = 4;
        this.top *= factor;
        this.bottom *= factor;
        this.left *= factor;
        this.right *= factor;
        this.front *= factor;
        this.back *= factor;
    }
}
