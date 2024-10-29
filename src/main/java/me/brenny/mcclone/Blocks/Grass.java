package me.brenny.mcclone.Blocks;

import me.brenny.mcclone.Objects.Cube;
import me.brenny.mcclone.Objects.TextureData;

public class Grass extends Cube {
    @Override
    public String getIdentifier() {
        return "grass";
    }

    @Override
    public TextureData getTextureData() {
        return new TextureData(0, 2, 3,3,3,3);
    }
}
