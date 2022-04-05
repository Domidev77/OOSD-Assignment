package Assignment.Gui;

import Assignment.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button buttonLogin;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public LoginController(){
    }

    public void processActionEvent(ActionEvent event) throws IOException {
        processLogin();
    }

    public void processKeyboardPressed(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            processLogin();
        }
    }
    public static void showErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred");
        alert.setContentText(message);//ww  w . j  a  va2s  .  co  m

        alert.showAndWait();
    }

    public void processLogin() throws IOException{
        Main m = new Main();
        if(username.getText().toString().equals("Warden") && password.getText().toString().equals("123")){
            wrongLogin.setText("Success!");

            m.changeScene("WardenView.fxml");
        }
        else if(username.getText().toString().equals("All") && password.getText().toString().equals("123")){
            wrongLogin.setText("Success!");

            m.changeScene("UweAccommodationView.fxml");
        }
        else if(username.getText().toString().equals("Manager") && password.getText().toString().equals("123")){
            wrongLogin.setText("Success!");

            m.changeScene("ManagerView.fxml");
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogin.setText("Please enter your data");
        }
        else {
            showErrorPopup("Wrong username or password!");
            wrongLogin.setText("Wrong username or password!");
        }
    }
}
