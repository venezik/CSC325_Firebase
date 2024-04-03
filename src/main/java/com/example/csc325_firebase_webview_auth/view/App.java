package com.example.csc325_firebase_webview_auth.view;


import com.example.csc325_firebase_webview_auth.model.FirestoreContext;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    public static Scene scene;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage splashStage = createSplashScreen();
        splashStage.show();


        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();
        Duration delay = Duration.seconds(2);
        PauseTransition pause = new PauseTransition(delay);
        pause.setOnFinished(event -> {
            splashStage.close();
            openMainWindow(primaryStage);
        });
        pause.play();
    }

    private Stage createSplashScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/files/splash.fxml"));
        Scene splashScene = new Scene(root);
        Stage splashStage = new Stage();
        splashStage.setScene(splashScene);
        splashStage.initStyle(StageStyle.UNDECORATED);
        return splashStage;
    }

    private void openMainWindow(Stage primaryStage) {
        try {
            // Load the main application window
            scene = new Scene(loadFXML("/files/AccessFBView.fxml"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void openRegistrationForm(Stage primaryStage) {
        try {
            // Load the main application window
            scene = new Scene(loadFXML("/files/RegistrationForm.fxml"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml ));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
