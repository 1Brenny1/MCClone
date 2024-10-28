package me.brenny.mcclone;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import me.brenny.mcclone.Objects.Vec2;

public class MCClone {

    public static MCClone INSTANCE; {INSTANCE = this;}
    public static double FOV = 60;
    public static final Vec2 DEFAULT_SCREEN_SIZE = new Vec2(1080, 720);
    public static final int BLOCK_SIZE = 100;

    public static PhongMaterial MATERIAL = new PhongMaterial();
    public static Image TEXTURE;

    public static void main(String[] args) {
        ChunkManager.init();

        Window window = new Window();
        window.init(args);
    }

    public static void onWindowStart() {
        MCClone.TEXTURE = new Image("terrain_fix.png");
        MCClone.MATERIAL.setDiffuseMap(MCClone.TEXTURE);
        MCClone.MATERIAL.setSelfIlluminationMap(MCClone.TEXTURE);
    }
}
