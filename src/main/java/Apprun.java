import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Objects;
import java.io.IOException;

public class Apprun extends javafx.application.Application {

    @Override
    public void start(@NotNull Stage stage) throws Exception {
        //加载登录界面绝对路径
        try {
            Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));
            stage.setTitle("登录界面");
            stage.setScene(new Scene(root, 550, 350));
            stage.show();
        } catch (NullPointerException e) {
            System.out.println("Error loading FXML file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading FXML file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}