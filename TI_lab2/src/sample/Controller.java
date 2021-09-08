package sample;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button readButton;

    @FXML
    private Button encryptionButton;

    @FXML
    private Button inputButton;

    @FXML
    private TextField initialStateField;

    @FXML
    private Label incorrectKey;

    @FXML
    private Label plainTextLabel;

    @FXML
    private Label newTextLabel;

    @FXML
    private TextArea plainTextArea;

    @FXML
    private TextArea encryptedTextArea;

    @FXML
    private Label keyLabel;

    @FXML
    private TextArea keyArea;

    @FXML
    private Button decryptionButton;

    @FXML
    private Label counterScreen;

    File fileObject;

    byte[] byteArray;

    byte[] encryptByteArray;

    String initial_state="";

    String fullFileName = "";

    @FXML
    void initialize() {

        //кнопка выбора файла
        readButton.setOnAction(event -> {
            keyArea.clear();
            encryptedTextArea.clear();
            String fileName = "";
            Node source = (Node) event.getSource();
            Stage primaryStage = (Stage) source.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("C:\\MyJava\\TI_lab2"));

            fileChooser.setTitle("Выбор файла");
            fileObject = fileChooser.showOpenDialog(primaryStage);
            try {
                fileName = fileObject.getPath();
                System.out.println(fileName);
            } catch (Exception e) {
                System.out.println("ERROR");
                plainTextArea.clear();
            }

            for (int i = fileName.length() - 1; i >= 0; i--) {
                if (fileName.charAt(i) == '.') {
                    fullFileName = fileName.substring(i);
                    System.out.println(fullFileName);
                    break;
                }
            }

            //чтение файла в случае его досягаемости
            if (fileName.length() != 0) {
                byteArray = new byte[(int) fileObject.length()];
                try {
                    FileInputStream input = new FileInputStream(fileName);
                    input.read(byteArray, 0, byteArray.length);
                } catch (FileNotFoundException e) {
                    System.out.println("FileNotFounded");
                } catch (IOException e) {
                    System.out.println("IOException");
                }


                //разбить на группы по 8
                String plain_bits = "";
                for (int i = 0; i < byteArray.length; i++) {

                    String str = String.format("%8s", Integer.toBinaryString(byteArray[i] & 0xFF)).replace(' ', '0');
                    if (plain_bits.length() < 5100) {
                        plain_bits += str + " ";
                    }
                }

                plainTextArea.setText(plain_bits);
            }
        });

        //строка ввода начального регистра
        initialStateField.textProperty().addListener(((observable, oldValue, newValue) -> {
            char[] checkRegistr = newValue.toCharArray();
            int counter = 0;
            for (int i = 0; i < checkRegistr.length; i++){
                if (checkRegistr[i] == '0' || checkRegistr[i] == '1')
                    counter++;
                if (counter < 34) {
                    counterScreen.setText(String.valueOf(counter + " Мало"));
                }
                if (counter == 34) {
                    counterScreen.setText(String.valueOf(counter + " Хватит"));
                }
                if (counter > 34) {
                    counterScreen.setText(String.valueOf(counter + " Много"));
                }
            }
        }));

        //кнопка "Ввод"
        inputButton.setOnAction(event -> {
            initial_state = initialStateField.getText();
            incorrectKey.setText("");

        });

        //кнопка "Шифровать"
        encryptionButton.setOnAction(event -> {
            String fileNameE = "C:\\MyJava\\TI_lab2\\encrypt";
            //filename.concat(fullFileName);
            fileNameE += fullFileName;
            if (cheking()) {
                encrypt();
                //printTextInFile(encryptByteArray, "C:\\MyJava\\TI_lab2\\encrypt_");
                printTextInFile(encryptByteArray, fileNameE);
            } else {
                incorrectKey.setText("Некорректный ввод!!!");
            }
        });

        //кнопка "Расшифровать"
        decryptionButton.setOnAction(event -> {
            String filenameR = "C:\\MyJava\\TI_lab2\\result";
            //filenameR.concat(fullFileName);
            filenameR += fullFileName;
            if (cheking()) {
                encrypt();
                //printTextInFile(encryptByteArray, "C:\\MyJava\\TI_lab2\\results_");
                printTextInFile(encryptByteArray, filenameR);
            } else {
                incorrectKey.setText("Некорректный ввод!!!");
            }
        });



    }

    //метод шифрования
    public void encrypt() {
        int a = (int) Long.parseLong(initial_state, 2);
        Register reg = new Register(a);
        encryptByteArray = new byte[byteArray.length];
        String key = "";
        for (int i = 0; i < byteArray.length; i++) {
            byte keyByte = (byte) reg.shift();
            String str = String.format("%8s", Integer.toBinaryString(keyByte & 0xFF)).replace(' ', '0');
            if (key.length() < 5100) {
                key += str + " ";
            }
            System.out.println(keyByte);
            encryptByteArray[i] = (byte) ((int) keyByte ^ (int) byteArray[i]);
        }
        keyArea.setText(key);
        String encrypt = "";
        for (int i = 0; i < encryptByteArray.length; i++) {
            String str = String.format("%8s", Integer.toBinaryString(encryptByteArray[i] & 0xFF)).replace(' ', '0');
            if (encrypt.length() < 5100) {
                encrypt += str + " ";
            }
        }
        encryptedTextArea.setText(encrypt);
    }

    //запись в файл
    public void printTextInFile(byte[] result, String filename) {
        try {
            FileOutputStream fw = new FileOutputStream(filename);
            fw.write(result);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    //проверка ввода нач регистра
    public boolean cheking() {
        boolean bool = true;
        if (initial_state.length() == 34) {
            for (int i = 0; i < initial_state.length(); i++) {
                if (initial_state.charAt(i) != '1') {
                    if (initial_state.charAt(i) != '0') {
                        bool = false;
                        break;
                    }
                }
            }
        } else {
            bool = false;
        }
        return bool;
    }

}
