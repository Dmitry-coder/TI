package sample;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private TextField inputP;

    @FXML
    private Button chooseFile;

    @FXML
    private TextArea preFile;

    @FXML
    private TextArea postFile;

    @FXML
    private Button inputReg;

    @FXML
    private TextArea keyArea;

    @FXML
    private Button encrypt;

    @FXML
    private Button decrypt;

    @FXML
    private Label numReg;

    @FXML
    private Label err;

    @FXML
    private TextField inputK;

    @FXML
    private TextField inputX;

    @FXML
    private TextField inputG;

    @FXML
    private Label numP;

    @FXML
    private Label numX;

    @FXML
    private Label numG;

    @FXML
    private TextArea allG;

    int tmpI = 0;

    File file;

    byte[] fileByteArray;

    byte[] encryptByteArray;

    long[] allPervoobr;

    String ext = "";

    long p = 0, k = 0, x = 0, yKey = 0, g = 0;

    @FXML
    void initialize() {
        allG.setWrapText(true);
        preFile.setWrapText(true);
        keyArea.setWrapText(true);
        postFile.setWrapText(true);
        allG.setEditable(false);
        preFile.setEditable(false);
        keyArea.setEditable(false);
        postFile.setEditable(false);
        err.setVisible(false);

        inputG.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                char[] register = newValue.toCharArray();
                String tmpReg = "";
                for (int i = 0; i < register.length; i++){
                    if (Character.isDigit(register[i]))
                        tmpReg += register[i];
                }
                inputG.setText(tmpReg);
            }
        });

        inputP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                char[] register = newValue.toCharArray();
                String tmpReg = "";
                for (int i = 0; i < register.length; i++){
                    if (Character.isDigit(register[i]))
                        tmpReg += register[i];
                }
                if (tmpReg != "") {
                    getAllPervoobr(Long.parseLong(tmpReg));
                    String tmpString = "";
                    for (int i = 0; i < allPervoobr.length; i++) {
                        tmpString += allPervoobr[i] + " ";
                    }
                    allG.setText(tmpString);
                }
                inputP.setText(tmpReg);
            }
        });

        inputK.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                char[] register = newValue.toCharArray();
                String tmpReg = "";
                for (int i = 0; i < register.length; i++){
                    if (Character.isDigit(register[i]))
                        tmpReg += register[i];
                }
                inputK.setText(tmpReg);
            }
        });

        inputX.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                char[] register = newValue.toCharArray();
                String tmpReg = "";
                for (int i = 0; i < register.length; i++){
                    if (Character.isDigit(register[i]))
                        tmpReg += register[i];
                }
                inputX.setText(tmpReg);
            }
        });

        inputG.textProperty().addListener(((observable, oldValue, newValue) -> {
            long tmp = 0;
            try {
                tmp = Long.parseLong(inputG.getText());
            } catch (Exception e) {
                System.out.println("Da da ya");
            }
            boolean checkPerv = false;
            for (int i = 0; i < allPervoobr.length; i++){
                if (tmp == allPervoobr[i]) {
                    checkPerv = true;
                    break;
                }
            }

            if (checkPerv) {
                inputG.setStyle("-fx-border-color: green");
                numG.setText("");
            } else {
                inputG.setStyle("-fx-border-color: red");
                numG.setText("Не является первообразной");
            }
        }));

        inputP.textProperty().addListener(((observable, oldValue, newValue) -> {
            long tmp = 0;
            try {
                tmp = Long.parseLong(inputP.getText());
            } catch (Exception e) {
                System.out.println("Da da ya");
            }
            if (tmp > 2 && tmp < 10000) {
                if (isSimple(tmp)) {
                    inputP.setStyle("-fx-border-color: green");
                    numReg.setText("");
                } else {
                    inputP.setStyle("-fx-border-color: red");
                    numReg.setText("Не простое");
                }
            } else {
                numReg.setText("Введите число < 10000 и > 2");
                inputP.setStyle("-fx-border-color: red");
            }
        }));

        inputK.textProperty().addListener(((observable, oldValue, newValue) -> {
            long tmp = 0;
            long tempP = 0;
            try {
                tmp = Long.parseLong(inputK.getText());
                tempP = Long.parseLong(inputP.getText());
            } catch (Exception e) {
                System.out.println("Da da ya");
            }

            if (inputK.getText().length() != 0) {
                inputK.setStyle("-fx-border-color: red");
                numP.setText("Введите число меньшее " + tempP + " и >1");

                if (tmp < tempP && tmp > 1) {
                    inputK.setStyle("-fx-border-color: green");
                    numP.setText("");
                }
            } else {
                inputK.setStyle("-fx-border-color: red");
                numP.setText("");
            }
        }));

        inputX.textProperty().addListener(((observable, oldValue, newValue) -> {
            long tmp = 0;
            long tempP = 0;
            try {
                tmp = Long.parseLong(inputX.getText());
                tempP = Long.parseLong(inputP.getText());
            } catch (Exception e) {
                System.out.println("Da da ya");
            }

            if (inputX.getText().length() != 0) {
                inputX.setStyle("-fx-border-color: red");
                numX.setText("Введите число меньшее " + tempP + " и >1");

                if (tmp < tempP && tmp > 1) {
                    inputX.setStyle("-fx-border-color: green");
                    numX.setText("");
                }
            } else {
                inputX.setStyle("-fx-border-color: red");
                numX.setText("");
            }
        }));

        chooseFile.setOnAction(event -> {
            String fileName = "";
            try {
                Stage chooserWindow = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                chooserWindow.setScene(new Scene(root, 1280, 720));
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("C:\\MyJava\\shifr3"));
                fileChooser.setTitle("Выбор файла");
                file = fileChooser.showOpenDialog(chooserWindow);
                fileName = file.getPath();
                int last = fileName.lastIndexOf('.');
                ext = fileName.substring(last);
            } catch (IOException e){
                System.out.println("IOException");
            }

            if (fileName.length() != 0) {
                fileByteArray = new byte[(int) file.length()];
                try {
                    FileInputStream input = new FileInputStream(fileName);
                    input.read(fileByteArray, 0, fileByteArray.length);
                } catch (FileNotFoundException e) {
                    System.out.println("FileNotFounded");
                } catch (IOException e) {
                    System.out.println("IOException");
                }

                String preBytes = "";
                for (int i = 0; i < fileByteArray.length; i++) {
                    String str = String.valueOf(Integer.parseInt(Integer.toBinaryString(fileByteArray[i] & 0xFF), 2));
                    if (preBytes.length() < 5040) {
                        preBytes += str + " ";
                    }
                }
                preFile.setText(preBytes);
            }
        });

        inputReg.setOnAction(event -> {
            if (numReg.getText() == "")
                p = Long.parseLong(inputP.getText());
            if (numP.getText() == "")
                k = Long.parseLong(inputK.getText());
            if (numX.getText() == "")
                x = Long.parseLong(inputX.getText());
            if(numG.getText() != "" ||  inputG.getText().equals("")) {
                g = GetPRoot(p);
            } else {
                g = Long.parseLong(inputG.getText());
            }
            if (p != 0 && k != 0 && x != 0){
                BigInteger gTemp = BigInteger.valueOf(g);
                BigInteger powTemp = gTemp.pow((int)x);
                BigInteger pTemp = BigInteger.valueOf(p);
                BigInteger yTemp = powTemp.mod(pTemp);
                String stringY = "" + yTemp;
                yKey = Integer.parseInt(stringY);
                keyArea.setText(String.format("(%s, %s, %s)", yKey, g, p));
                System.out.println(tmpI);
            }
        });

        encrypt.setOnAction(event -> {
                encrypt();
                printFile(encryptByteArray, "C:\\MyJava\\shifr3\\encrypt\\encrypt" + ext);
        });

        decrypt.setOnAction(event -> {
                decrypt();
                printFile(encryptByteArray, "C:\\MyJava\\shifr3\\decrypt\\decrypt" + ext);
        });

    }

    public void encrypt() {
        encryptByteArray = new byte[2*fileByteArray.length];
        String encrypt = "";
        long a, b;
        for (int i = 0; i < fileByteArray.length; i++) {
            BigInteger gTemp = BigInteger.valueOf(g);
            BigInteger firstPowTemp = gTemp.pow((int)k);
            BigInteger pTemp = BigInteger.valueOf(p);
            BigInteger aTemp = firstPowTemp.mod(pTemp);
            String stringA = "" + aTemp;
            a = Integer.parseInt(stringA);

            BigInteger yTemp = BigInteger.valueOf(yKey);
            BigInteger secondPowTemp = yTemp.pow((int)k);
            BigInteger tmpMessage = BigInteger.valueOf(Integer.parseInt(Integer.toBinaryString(fileByteArray[i] & 0xFF), 2));
            BigInteger proizv = secondPowTemp.multiply(tmpMessage);
            BigInteger bTemp = proizv.mod(pTemp);
            String stringB = "" + bTemp;
            b = Integer.parseInt(stringB);

            String str = String.format("(%s %s)", a, b);
            if (encrypt.length() < 5040) {
                encrypt += str + " ";
            }
            encryptByteArray[i*2] = (byte) a;
            encryptByteArray[i*2 + 1] = (byte) b;
        }
        postFile.setText(encrypt);
    }

    public void decrypt() {
        encryptByteArray = new byte[fileByteArray.length/2];
        String encrypt = "";
        long a, b, res;
        for (int i = 0; i < encryptByteArray.length; i++) {
            a = Integer.parseInt(Integer.toBinaryString(fileByteArray[i*2] & 0xFF), 2);
            b = Integer.parseInt(Integer.toBinaryString(fileByteArray[i*2+1] & 0xFF), 2);
            BigInteger aTemp = BigInteger.valueOf(a);
            BigInteger powTemp = aTemp.pow((int)(p - 1 - x));
            BigInteger bTemp = BigInteger.valueOf(b);
            BigInteger proiz = powTemp.multiply(bTemp);
            BigInteger pTemp = BigInteger.valueOf(p);
            BigInteger resTemp = proiz.mod(pTemp);
            String stringRes = "" + resTemp;
            res = Integer.parseInt(stringRes);

            encryptByteArray[i] = (byte) res;
            String str = String.format("%s", res);
            if (encrypt.length() < 5040) {
                encrypt += str + " ";
            }
        }
        postFile.setText(encrypt);
    }

    public void printFile(byte[] result, String filename) {
        try {
            FileOutputStream fw = new FileOutputStream(filename);
            fw.write(result);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void getAllPervoobr(long p){
        int tmpSize = 0;
        for (long i = 0; i < p; i++) {
            if (IsPRoot(p, i)) {
                tmpSize++;
            }
        }

        allPervoobr = new long[tmpSize];
        //int tmpI = 0;
        for (long i = 0; i < p; i++) {
            if (IsPRoot(p, i)) {
                allPervoobr[tmpI] = i;
                tmpI++;
            }
        }
    }

    public static long GetPRoot(long p) {
        for (long i = 0; i < p; i++)
            if (IsPRoot(p, i))
                return i;
        return 0;
    }

    public static boolean IsPRoot(long p, long a) {
        if (a == 0 || a == 1)
            return false;
        long last = 1;

        Set<Long> set = new HashSet<>();
        for (long i = 0; i < p - 1; i++) {
            last = (last * a) % p;
            if (set.contains(last))
                return false;
            set.add(last);
        }
        return true;
    }

    public boolean isSimple(long num){
        long temp;
        for (int i = 2; i <= num/2; i++) {
            temp = num % i;
            if (temp == 0) {
                return false;
            }
        }
        return true;
    }
}