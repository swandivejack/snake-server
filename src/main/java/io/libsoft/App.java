package io.libsoft;

import io.libsoft.controller.DrawController;
import io.libsoft.model.Model;
import io.libsoft.network.Server;
import io.libsoft.util.Props;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


  private static final String LAYOUT_RESOURCE = "layout.fxml";
  private DrawController controller;

  public static void main(String[] args) {
    Props.get();

    launch(args);

  }


  private void initialize(){
    Model model = new Model();
    model.addSnake("test");
    Server server = Server.start();
    controller.setModel(model);

  }

  @Override
  public void start(Stage stage) throws Exception {

    ClassLoader classLoader = getClass().getClassLoader();
    FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource(LAYOUT_RESOURCE));
    Parent root = fxmlLoader.load();
    controller = fxmlLoader.getController();
    Scene scene = new Scene(root);
    stage.setResizable(true);
    stage.setScene(scene);
    stage.sizeToScene();
    stage.setTitle("default");
    stage.show();
    setStageSize(stage, root);
    initialize();

    stage.setOnCloseRequest(event -> {
      System.exit(10);
    });
  }


  @Override
  public void stop() throws Exception {
    controller.stop();
    super.stop();
  }

  private void setStageSize(Stage stage, Parent root) {
    Bounds bounds = root.getLayoutBounds();
    stage.setMinWidth(root.minWidth(-1) + stage.getWidth() - bounds.getWidth());
    stage.setMinHeight(root.minHeight(-1) + stage.getHeight() - bounds.getHeight());
  }

}
