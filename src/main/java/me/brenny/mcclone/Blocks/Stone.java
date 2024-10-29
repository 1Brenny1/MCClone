package me.brenny.mcclone.Blocks;

import me.brenny.mcclone.Objects.Block;
import me.brenny.mcclone.Objects.Cube;
import me.brenny.mcclone.Objects.TextureData;

public class Stone extends Cube {
    @Override
    public String getIdentifier() {
        return "stone";
    }

    @Override
    public TextureData getTextureData() {
        return new TextureData(1, 1, 1,1,1,1);
    }
}
