package me.brenny.mcclone;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.TriangleMesh;

public class TextureManager {

    public static Image TEXTURE;
    public static PhongMaterial MATERIAL = new PhongMaterial();

    public static void init() {
        TEXTURE = new Image("terrain.png", 256*25, 256*25, false, false, false);
        MATERIAL.setDiffuseMap(TEXTURE);
        //MATERIAL.setSelfIlluminationMap(TEXTURE);
    }

    public static void loadTextureData(TriangleMesh mesh) {
        final float SCALE = 1f/16f;

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 16; x++) {
                mesh.getTexCoords().addAll(SCALE*(x+1),SCALE*(y+1));
                mesh.getTexCoords().addAll(SCALE*x,SCALE*(y+1));
                mesh.getTexCoords().addAll(SCALE*(x+1),SCALE*y);
                mesh.getTexCoords().addAll(SCALE*x,SCALE*y);
            }
        }
    }
}
