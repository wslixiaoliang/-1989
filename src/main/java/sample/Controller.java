package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

public class Controller {

    @FXML
    private TextField account;

    @FXML
    private TextField password;

    @FXML
    private TextField receiverAccount;

    @FXML
    private TextField receiverNickname;

    @FXML
    private TextField senderNickname;

    @FXML
    private TextField title;

    @FXML
    private HTMLEditor content;

    @FXML
    private Button sendBut;


    public void sendEmail(){

        Email.sendEmail(account.getText(),password.getText(),receiverAccount.getText(),
                receiverNickname.getText(),senderNickname.getText(),title.getText(),content.getHtmlText());

    }

}
