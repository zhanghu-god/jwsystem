import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

import static de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport.launch;

public class Apprun extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        //加载登录界面绝对路径
        Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login.fxml")));
        stage.setTitle("登录界面");
        stage.setScene(new Scene(root, 550, 350));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}