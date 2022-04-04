package Assignment.Gui;

import Assignment.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
            wrongLogin.setText("Wrong username or password!");
        }
    }
}
