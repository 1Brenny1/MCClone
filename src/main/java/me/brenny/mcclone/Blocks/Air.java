package me.brenny.mcclone.Blocks;

import javafx.scene.shape.TriangleMesh;
import me.brenny.mcclone.MCClone;
import me.brenny.mcclone.Objects.Block;
import me.brenny.mcclone.Objects.TextureData;
import me.brenny.mcclone.Objects.Vec2;
import me.brenny.mcclone.Objects.Vec3;

public class Air extends Block {
    @Override
    public String getIdentifier() {
        return "air";
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public TextureData getTextureData() {
        return null;
    }

    @Override
    public void constructMesh(TriangleMesh mesh, Vec3 coordinate, Vec2 chunkCoordinate) {
        float halfScale = MCClone.BLOCK_SIZE / 2f;

        float x = (float) coordinate.x*MCClone.BLOCK_SIZE;
        float y = (float) -coordinate.y*MCClone.BLOCK_SIZE; // Negative to fix issue with JavaFX
        float z = (float) coordinate.z*MCClone.BLOCK_SIZE;

        // Add the points but not the faces (makes mesh position correct in meshview)

        mesh.getPoints().addAll(
                -halfScale+x, -halfScale+y, halfScale+z, // 0: FBL
                halfScale+x, -halfScale+y, halfScale+z, // 1: FBR
                -halfScale+x, halfScale+y, halfScale+z, // 2: FTL
                halfScale+x, halfScale+y, halfScale+z, // 3: FTR

                -halfScale+x, -halfScale+y, -halfScale+z, // 4: BBL
                halfScale+x, -halfScale+y, -halfScale+z, // 5: BBR
                -halfScale+x, halfScale+y, -halfScale+z, // 6: BTL
                halfScale+x, halfScale+y, -halfScale+z // 7: BTR
        );
        mesh.getTexCoords().addAll(0,0);
    }
}
