package me.brenny.mcclone;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import me.brenny.mcclone.Objects.Vec3;

public class Window extends Application {

    public static final StackPane root = new StackPane();
    public static Scene scene;

    private Vec3 cameraPosition = new Vec3(0,0,0);

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(root, MCClone.DEFAULT_SCREEN_SIZE.x, MCClone.DEFAULT_SCREEN_SIZE.y, true);
        MCClone.onWindowStart();


        // Global Ilumination
        AmbientLight ambientLight = new AmbientLight(Color.rgb(255,255,255,0.5));
        root.getChildren().add(ambientLight);

        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setTranslateZ(1000);
        camera.setTranslateY(0);
        camera.setRotationAxis(new Point3D(1,0,0));
        camera.setRotate(-30);
        scene.setCamera(camera);

        scene.setOnKeyPressed((keyEvent -> {
            switch (keyEvent.getText()) {
                case "w":
                    cameraPosition.z += 1;
                    break;
                case "s":
                    cameraPosition.z -= 1;
                    break;
                case "a":
                    cameraPosition.x -= 1;
                    break;
                case "d":
                    cameraPosition.x += 1;
                    break;
                case "q":
                    cameraPosition.y -= 1;
                    break;
                case "e":
                    cameraPosition.y += 1;
                    break;
            }

            camera.setTranslateX(cameraPosition.x*MCClone.BLOCK_SIZE);
            camera.setTranslateY(cameraPosition.y*MCClone.BLOCK_SIZE);
            camera.setTranslateZ(cameraPosition.z*MCClone.BLOCK_SIZE);
        }));

        scene.widthProperty().addListener(((observableValue, oldValue, newValue) -> {
            double aspectRatio = newValue.doubleValue() / MCClone.DEFAULT_SCREEN_SIZE.y; // Screen Height
            camera.setFieldOfView(MCClone.FOV / aspectRatio); // FOV
        }));

        scene.heightProperty().addListener(((observableValue, oldValue, newValue) -> {
            double aspectRatio = newValue.doubleValue() / MCClone.DEFAULT_SCREEN_SIZE.x; // Screen Width
            camera.setFieldOfView(MCClone.FOV * aspectRatio); // FOV
        }));
        stage.setScene(scene);

        stage.setTitle("MCClone");
        stage.show();
    }

    public void init(String[] args) {
        launch(args);
    }
}
