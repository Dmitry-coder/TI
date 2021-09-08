package sample;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class Controller {

    ReadFile readF = new ReadFile();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textResult;

    @FXML
    private TextField textKeyWord;

    @FXML
    private RadioButton radButStolb;

    @FXML
    private ToggleGroup choise;

    @FXML
    private RadioButton radButPleyfer;

    @FXML
    private RadioButton radButVizhener;

////////////////////////////////////////////////ШИФРОВКА/////////////////////////////////////////////////////////////
    @FXML
    private void encript() throws IOException {
        String rawStr = readF.readA();
        String rawKey = textKeyWord.getText();

        if (rawKey.length() != 0 && rawStr.length() != 0) {
            if (radButStolb.isSelected()) {
                //////////////////////lines filter///////////////////////////
                String str;
                char[] alph = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
                str = rawStr.toLowerCase();
                String result = "";
                for (int i = 0; i < str.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (str.charAt(i) == alph[j]) {
                            result = result + alph[j];
                        }
                    }
                }
                System.out.println(result);
                if (result == "")
                    textResult.setText("Строка пуста. Требуется корректный ввод");

                /////////////////////////keys filter////////////////////////
                String lowerKey = rawKey.toLowerCase();
                String key = "";
                for (int i = 0; i < lowerKey.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (lowerKey.charAt(i) == alph[j]) {
                            key = key + alph[j];
                        }
                    }
                }
                System.out.println(key);

                if (key.length() != 0 && result.length() != 0) {

                    if (key.length() > result.length())
                        key = key.substring(0, result.length());

                    System.out.println(key);

                    String strOut = encriptStolb(key, result);
                    textResult.setText(strOut);
                    try (PrintWriter out = new PrintWriter("src\\sample\\output.txt")) {
                        out.println(strOut);
                    }
                } else
                    textResult.setText("строка пуста");
            }
            if (radButPleyfer.isSelected()) {
                //////////////////////lines filter///////////////////////////
                String str;
                char[] alph = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
                str = rawStr.toLowerCase();
                String result = "";
                for (int i = 0; i < str.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (str.charAt(i) == alph[j]) {
                            result = result + alph[j];
                        }
                    }
                }
                System.out.println(result);

                for (int p = 0; p < result.length(); p++) {
                    if (result.charAt(p) == 'x') {
                        if (result.charAt(p++) != 0) {
                            if (result.charAt(p++) == 'x') {
                                textResult.setText("два х подряд");
                                return;
                            }
                        }
                    }
                }

                if (result == "")
                    textResult.setText("Строка пуста. Требуется корректный ввод");

                /////////////////////////keys filter////////////////////////
                String lowerKey = rawKey.toLowerCase();
                String key = "";
                for (int i = 0; i < lowerKey.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (lowerKey.charAt(i) == alph[j]) {
                            key = key + alph[j];
                        }
                    }
                }
                System.out.println(key);

                if (key.length() != 0 && result.length() != 0) {
                    String newKey = "";
                    for (int i = 0; i < key.length(); i++) {
                        char ch = key.charAt(i);
                        if (newKey.indexOf(ch) == -1) {
                            newKey = newKey + ch;
                        } else {
                            newKey.replace(String.valueOf(ch), ""); // added this to your existing code
                        }
                    }

                    System.out.println(newKey + " ");

                    //if (key.length() > result.length())
                        //newKey = key.substring(0, result.length());

                    String strOut = encriptPleyfer(newKey, result);
                    textResult.setText(strOut);
                    try (PrintWriter out = new PrintWriter("src\\sample\\output.txt")) {
                        out.println(strOut);
                    }
                } else
                    textResult.setText("строка пуста");
            }
            if (radButVizhener.isSelected()) {
                String str;
                char[] alph = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
                str = rawStr.toLowerCase();
                String result = "";
                for (int i = 0; i < str.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (str.charAt(i) == alph[j]) {
                            result = result + alph[j];
                        }
                    }
                }
                System.out.println(result);

                if (result == "")
                    textResult.setText("Строка пуста. Требуется корректный ввод");

                /////////////////////////keys filter////////////////////////
                String lowerKey = rawKey.toLowerCase();
                String key = "";
                for (int i = 0; i < lowerKey.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (lowerKey.charAt(i) == alph[j]) {
                            key = key + alph[j];
                        }
                    }
                }
                System.out.println(key);
                if (key.length() != 0 && result.length() != 0) {

                    if (key.length() > result.length())
                        key = key.substring(0, result.length());

                    System.out.println(key);

                    //key = key.substring(0, result.length());
                    System.out.println(key);
                    String strOut = encriptVizh(key, result);
                    textResult.setText(strOut);
                    try (PrintWriter out = new PrintWriter("src\\sample\\output.txt")) {
                        out.println(strOut);
                    }
                } else
                    textResult.setText("строка пуста");
            }
        } else
            textResult.setText("строка пуста");
    }

/////////////////////////////////////////////////ШИФРОВКА СТОЛБЦАМИ/////////////////////////////////////////////////////
    @FXML
    public String encriptStolb(String key, String text){

        String[] textmas = text.split("");
        String[] letters = key.split("");

        int size = letters.length;
        char[] letter = new char[size];

        for(int i = 0; i < letters.length; i++){
            letter[i] = letters[i].charAt(0);
        }

        int[] keys = new int[size];
        int adder = 1;

        for(int i = 0; i < size; i++){
            keys[i] = adder;
            adder++;
        }
        for(int repeater = 0; repeater < 2; repeater++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (letter[i] > letter[j] && keys[i] < keys[j]) {
                        int temp = keys[i];
                        keys[i] = keys[j];
                        keys[j] = temp;
                    } else if (letter[i] == letter[j] && i < j) {
                        if (keys[i] > keys[j]) {
                            int temp = keys[i];
                            keys[i] = keys[j];
                            keys[j] = temp;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(letter[i] == letter[j] && i < j) {
                    if (keys[i] > keys[j]) {
                        int temp = keys[i];
                        keys[i] = keys[j];
                        keys[j] = temp;
                    }
                }
            }
        }

        int height = textmas.length/letters.length + 1;
        String[][] matrix = new String[height][size];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < size; j++){
                matrix[i][j] = "0";
            }
        }

        int counter = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < size; j++){
                if(counter < textmas.length){
                    if(textmas[counter].equals(" ")) {
                        matrix[i][j] = "_";
                    } else {
                        matrix[i][j] = textmas[counter];
                    }
                    counter++;
                }

            }
        }

        String crypted = "";
        int changer = 1;
        for(int f = 0; f < size; f++) {
            for (int i = 0; i < size; i++) {
                if(changer == keys[i]) {
                    for (int k = 0; k < height; k++){
                        if(matrix[k][i].equals("0")){
                        } else {
                            crypted += matrix[k][i];
                        }
                    }
                }
            }
            changer++;
        }

        return crypted;
    }

/////////////////////////////////////////////////ШИФРОВКА ПЛЕЙФЕРА/////////////////////////////////////////////////////
    @FXML
    public String encriptPleyfer(String key, String str) {
        char[] alph = {'a','b','c','d','e','f','g','h','i','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        //////////////////////подготовка строки для шифровки////////////////////
        for (int i = 0; i < str.length(); i++) {
            if (i != str.length() - 1) {
                if (str.charAt(i) == str.charAt(i + 1)) {
                    str = str.substring(0, i + 1) + "x" + str.substring(i + 1);
                }
            }
        }
        System.out.println(str);



        if ((str.length() %2) != 0)
            str = str + "x";
        System.out.println(str);

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'j') {
                str = str.substring(0, i) + "i" + str.substring(i + 1);
            }
        }
        System.out.println(str);

        ///////////////////////матрица символов для шифровки//////////////////
        char[][] arr = new char[5][5];
        int xI = 0, xJ = 0;
        for (int i = 0; i < key.length(); i++) {
            if (xJ == 4) {
                arr[xI][xJ] = key.charAt(i);
                xI++;
                xJ = 0;
                //i++;
                continue;
            }
            if (xJ != 4) {
                arr[xI][xJ] = key.charAt(i);
                xJ++;
            }
        }

        int size = alph.length - key.length();
        char[] tempAlph = new char[size];
        char[] keyArr = new char[key.length()];
        for (int i = 0; i < keyArr.length; i++) {
            keyArr[i] = key.charAt(i);
        }

        int x = 0;
        for (int i = 0; i < alph.length; i++) {
            if (x < tempAlph.length) {
                if (!isExist(i,keyArr)) {
                    tempAlph[x] = alph[i];
                    x++;
                }
            }
        }

        for (int i = 0; i < tempAlph.length; i++) {
            System.out.print(tempAlph[i]);
        }
        System.out.println();

        int z = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] == 0) {
                    if (z < tempAlph.length) {
                        arr[i][j] = tempAlph[z];
                        z++;
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("%2c", arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        ////////////////////шифрование//////////////////////////////
        size = str.length() / 2;
        char[][] matrix = new char[size][2];
        x = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = str.charAt(x);
                x++;
                System.out.printf("%2c", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        int y1 = 0, y2 = 0, x1 = 0, x2 = 0;
        for (int para = 0; para < size; para++) {

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (matrix[para][0] == arr[i][j]) {
                        y1 = i;
                        x1 = j;
                    }
                    if (matrix[para][1] == arr[i][j]) {
                        y2 = i;
                        x2 = j;
                    }
                }
            }

            if (y1 == y2) {
                if (x1 == 4) {
                    x1 = 0;
                } else {
                    x1++;
                }
                if (x2 == 4) {
                    x2 = 0;
                } else {
                    x2++;
                }
            } else
            if (x1 == x2) {
                if (y1 == 4) {
                    y1 = 0;
                } else {
                    y1++;
                }
                if (y2 == 4) {
                    y2 = 0;
                } else {
                    y2++;
                }
            } else {
                int temp1 = x2;
                int temp2 = x1;
                x1 = temp1;
                x2 = temp2;
            }
            matrix[para][0] = arr[y1][x1];
            matrix[para][1] = arr[y2][x2];
            System.out.printf("%2c%2c", matrix[para][0], matrix[para][1]);
            System.out.println();
        }

        String result = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; j++) {
                result += matrix[i][j];
            }
        }
        System.out.println(result);

        return result;
    }

    public static boolean isExist(int i, char[] key) {
        char[] alph = {'a','b','c','d','e','f','g','h','i','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for (int j = 0; j < key.length; j++) {
            if (alph[i] == key[j]) return true;
        }
        return false;
    }


//////////////////////////////////////////////////ШИФРОВКА ВИЖЕНЕРА/////////////////////////////////////////////////////
    @FXML
    public String encriptVizh(String rawKey, String str) {
        char[] alph = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};
        String key = "";
        if (rawKey.length() != str.length()) {
            if (rawKey.length() < str.length()) {
                key = rawKey + str.substring(0, str.length() - rawKey.length());
            }
            if (rawKey.length() > str.length()) {
                key = rawKey.substring(0, str.length());
            }
        } else
            key = rawKey;
        System.out.println(key);

        int[] c = new int[str.length()];
        int[] p = new int[str.length()];
        int[] k = new int[key.length()];
        System.out.println();
        System.out.println("индексы строки");
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < alph.length; j++) {
                if (str.charAt(i) == alph[j]) {
                    p[i] = j;
                    System.out.printf("%3d", p[i]);
                }
            }
        }
        System.out.println();
        System.out.println("индексы ключа");
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < alph.length; j++) {
                if (key.charAt(i) == alph[j]) {
                    k[i] = j;
                    System.out.printf("%3d", k[i]);
                }
            }
        }
        System.out.println();
        System.out.println("итоговые индексы");
        for (int i = 0; i < str.length(); i++) {
            c[i] = ((p[i] + k[i])) % 33;
            System.out.printf("%3d", c[i]);
        }
        System.out.println();

        String result = "";
        for (int i = 0; i < c.length; i++) {
            result += alph[c[i]];
        }
        System.out.print(result);
        return result;
    }

///////////////////////////////////////РАСШИФРОВКА//////////////////////////////////////////////////////////////////////
    @FXML
    private void decript() throws IOException {
        String rawStr = readF.readA();
        String rawKey = textKeyWord.getText();

        if (rawKey.length() != 0 && rawStr.length() != 0) {
            if (radButStolb.isSelected()) {
                String str;
                char[] alph = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
                str = rawStr.toLowerCase();
                String result = "";
                for (int i = 0; i < str.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (str.charAt(i) == alph[j]) {
                            result = result + alph[j];
                        }
                    }
                }
                System.out.println(result);

                if (result == "")
                    textResult.setText("Строка пуста. Требуется корректный ввод");

                /////////////////////////keys filter////////////////////////
                String lowerKey = rawKey.toLowerCase();
                String key = "";
                for (int i = 0; i < lowerKey.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (lowerKey.charAt(i) == alph[j]) {
                            key = key + alph[j];
                        }
                    }
                }
                System.out.println(key);

                if (key.length() != 0 && result.length() != 0) {

                    if (key.length() > result.length())
                        key = key.substring(0, result.length());

                    String strOut = decriptStolb(key, result);
                    textResult.setText(strOut);
                    try (PrintWriter out = new PrintWriter("src\\sample\\output.txt")) {
                        out.println(strOut);
                    }
                } else
                    textResult.setText("строка пуста");
            }

            if (radButPleyfer.isSelected()) {
                //////////////////////lines filter///////////////////////////
                String str;
                char[] alph = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
                str = rawStr.toLowerCase();
                String result = "";
                for (int i = 0; i < str.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (str.charAt(i) == alph[j]) {
                            result = result + alph[j];
                        }
                    }
                }
                System.out.println(result);

                if (result == "")
                    textResult.setText("Строка пуста. Требуется корректный ввод");

                /////////////////////////keys filter////////////////////////
                String lowerKey = rawKey.toLowerCase();
                String key = "";
                for (int i = 0; i < lowerKey.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (lowerKey.charAt(i) == alph[j]) {
                            key = key + alph[j];
                        }
                    }
                }
                System.out.println(key);
                String newKey = "";
                for (int i = 0; i < key.length(); i++) {
                    char ch = key.charAt(i);
                    if (newKey.indexOf(ch) == -1) {
                        newKey = newKey + ch;
                    } else {
                        newKey.replace(String.valueOf(ch), ""); // added this to your existing code
                    }
                }

                System.out.println(newKey + " ");

                if (key.length() != 0 && result.length() != 0) {

                    if (key.length() > result.length())
                        key = key.substring(0, result.length());

                    String strOut = decriptPleif(newKey, result);
                    textResult.setText(strOut);
                    try (PrintWriter out = new PrintWriter("src\\sample\\output.txt")) {
                        out.println(strOut);
                    }
                } else
                    textResult.setText("строка пуста");
            }
            if (radButVizhener.isSelected()) {
                String str;
                char[] alph = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
                str = rawStr.toLowerCase();
                String result = "";
                for (int i = 0; i < str.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (str.charAt(i) == alph[j]) {
                            result = result + alph[j];
                        }
                    }
                }
                System.out.println(result);

                if (result == "")
                    textResult.setText("Строка пуста. Требуется корректный ввод");

                /////////////////////////keys filter////////////////////////
                String lowerKey = rawKey.toLowerCase();
                String key = "";
                for (int i = 0; i < lowerKey.length(); i++) {
                    for (int j = 0; j < alph.length; j++) {
                        if (lowerKey.charAt(i) == alph[j]) {
                            key = key + alph[j];
                        }
                    }
                }
                System.out.println(key);

                if (key.length() != 0 && result.length() != 0) {

                    if (key.length() > result.length())
                        key = key.substring(0, result.length());

                    String strOut = decriptVizh(key, result);
                    textResult.setText(strOut);
                    try (PrintWriter out = new PrintWriter("src\\sample\\output.txt")) {
                        out.println(strOut);
                    }
                } else
                    textResult.setText("строка пуста");
            }

        } else
            textResult.setText("строка пуста");
    }
///////////////////////////////РАСШИФРОВКА СТОЛБЦАМИ/////////////////////////////////////////////////////////////////
    public String decriptStolb(String key, String crypted){

        String[] letters = key.split("");

        int size = letters.length;
        char[] letter = new char[size];

        for(int i = 0; i < letters.length; i++){
            letter[i] = letters[i].charAt(0);
        }

        int[] keys = new int[size];
        int adder = 1;

        for(int i = 0; i < size; i++){
            keys[i] = adder;
            adder++;
        }
        for(int repeat = 0; repeat < 2; repeat++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (letter[i] > letter[j] && keys[i] < keys[j]) {
                        int temp = keys[i];
                        keys[i] = keys[j];
                        keys[j] = temp;
                    } else if (letter[i] == letter[j] && i < j) {
                        if (keys[i] > keys[j]) {
                            int temp = keys[i];
                            keys[i] = keys[j];
                            keys[j] = temp;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                if (letter[i] == letter[j] && i < j){
                    if (keys[i] > keys[j]) {
                        int temp = keys[i];
                        keys[i] = keys[j];
                        keys[j] = temp;
                    }
                }
            }
        }

        int checker = crypted.length() % keys.length;
        int difference = 0;
        String cryptReset = "";

        if(checker != 0){
            difference = (int)Math.ceil(crypted.length()/keys.length) + 1;

            int count = checker;
            boolean[] marker = new boolean[keys.length];

            for(int i = 0; i < keys.length; i++){
                if(count > 0){
                    marker[i] = true;
                } else {
                    marker[i] = false;
                }
                count--;
            }
            int added = 0;

            for(int i = 1; i <= keys.length; i++){
                for(int j = 0; j < keys.length; j++){
                    if(keys[j] == i){
                        if(marker[j]){
                            for(int k = 0; k < difference; k++){
                                cryptReset += crypted.charAt(k + added);
                            }
                            added += difference;
                        } else if(!marker[j]){
                            for(int k = 0; k < difference; k++){
                                if(k == difference - 1){
                                    cryptReset += "0";
                                } else {
                                    cryptReset += crypted.charAt(k + added);
                                }
                            }
                            added += difference - 1;
                        }
                    }
                }
            }
        } else{
            difference = (int)Math.ceil(crypted.length()/keys.length);
            int added = 0;

            for(int i = 1; i <= keys.length; i++){
                for(int j = 0; j < keys.length; j++){
                    if(keys[j] == i){
                        for(int k = 0; k < difference; k++){
                            cryptReset += crypted.charAt(k + added);
                        }
                        added += difference;
                    }
                }
            }
        }

        String[] strMass = new String[keys.length];

        int added = difference;
        int minus = 0;
        for(int i = 0; i < keys.length; i++){
            strMass[i] = cryptReset.substring(minus,added);
            minus += difference;
            added += difference;
        }

        String[] strMassRebuild = new String[keys.length];
        for(int i = 0; i < keys.length; i++){
            for(int j = 0; j < keys.length; j++){
                if(keys[j] == i + 1) {
                    strMassRebuild[j] = strMass[i];
                }
            }
        }

        String finalString = "";

        int height = (int) cryptReset.length()/keys.length;

        String[][] strMatrix = new String[height][keys.length];
        for(int i = 0 ; i < height; i++){
            for(int j = 0; j < keys.length; j++){
                strMatrix[i][j] = String.valueOf(strMassRebuild[j].charAt(i));
                if(strMatrix[i][j].equals("0")){
                } else if(strMatrix[i][j].equals("_")) {
                    finalString += " ";
                }
                else {
                    finalString += strMatrix[i][j];
                }
            }
        }
        return finalString;
    }

////////////////////////////////РАСШИФРОВКА ПЛЕЙФЕРА/////////////////////////////////////////////////////////////////
    public String decriptPleif(String key, String str) {
        char[] alph = {'a','b','c','d','e','f','g','h','i','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        //////////////////////////////матрица символов для шифровки//////////////////////////////////
        char[][] arr = new char[5][5];
        int xI = 0, xJ = 0;
        for (int i = 0; i < key.length(); i++) {
            if (xJ == 4) {
                arr[xI][xJ] = key.charAt(i);
                xI++;
                xJ = 0;
                continue;
            }
            if (xJ != 4) {
                arr[xI][xJ] = key.charAt(i);
                xJ++;
            }
        }

        int size = alph.length - key.length();
        char[] tempAlph = new char[size];
        char[] keyArr = new char[key.length()];
        for (int i = 0; i < keyArr.length; i++) {
            keyArr[i] = key.charAt(i);
        }

        int x = 0;
        for (int i = 0; i < alph.length; i++) {
            if (x < tempAlph.length) {
                if (!isExist(i,keyArr)) {
                    tempAlph[x] = alph[i];
                    x++;
                }
            }
        }

        for (int i = 0; i < tempAlph.length; i++) {
            System.out.print(tempAlph[i]);
        }
        System.out.println();

        int z = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] == 0) {
                    if (z < tempAlph.length) {
                        arr[i][j] = tempAlph[z];
                        z++;
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("%2c", arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        ////////////////////расшифровка/////////////////////////////////
        size = str.length() / 2;
        char[][] matrix = new char[size][2];
        x = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = str.charAt(x);
                x++;
                System.out.printf("%2c", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        int y1 = 0, y2 = 0, x1 = 0, x2 = 0;
        for (int para = 0; para < size; para++) {

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (matrix[para][0] == arr[i][j]) {
                        y1 = i;
                        x1 = j;
                    }
                    if (matrix[para][1] == arr[i][j]) {
                        y2 = i;
                        x2 = j;
                    }
                }
            }

            if (y1 == y2) {
                if (x1 == 0) {
                    x1 = 4;
                } else {
                    x1--;
                }
                if (x2 == 0) {
                    x2 = 4;
                } else {
                    x2--;
                }
            } else
            if (x1 == x2) {
                if (y1 == 0) {
                    y1 = 4;
                } else {
                    y1--;
                }
                if (y2 == 0) {
                    y2 = 4;
                } else {
                    y2--;
                }
            } else {
                int temp1 = x2;
                int temp2 = x1;
                x1 = temp1;
                x2 = temp2;
            }
            matrix[para][0] = arr[y1][x1];
            matrix[para][1] = arr[y2][x2];
            System.out.printf("%2c%2c", matrix[para][0], matrix[para][1]);
            System.out.println();
        }

        String result = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; j++) {
                if (matrix[i][j] != 'x') {
                    if (matrix[i][j] == 'j') {
                        result += 'i';
                    } else {
                        result += matrix[i][j];
                    }
                }
            }
        }
        System.out.println(result);
        return result;
    }

//////////////////////////////РАСШИФРОВКА ВИЖЕНЕРА//////////////////////////////////////////////////////////////
    public String decriptVizh(String key, String str) {
        char[] alph = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};
        int knowedNum = key.length();
        if (key.length() != str.length()) {
            if (key.length() < str.length()) {
                key = key + str.substring(0, str.length() - key.length());
            }
            if (key.length() > str.length()) {
                key = key.substring(0, str.length());
            }
        }
        int[] p = new int[str.length()];
        int[] k = new int[str.length()];
        int[] c = new int[str.length()];
        System.out.println(key);
        System.out.println();
        System.out.println("индексы строки");
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < alph.length; j++) {
                if (str.charAt(i) == alph[j]) {
                    p[i] = j;
                    System.out.printf("%3d", p[i]);
                }
            }
        }
        System.out.println();
        System.out.println("индексы ключа");
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < alph.length; j++) {
                if (key.charAt(i) == alph[j]) {
                    k[i] = j;
                    System.out.printf("%3d", k[i]);
                }
            }
        }
        System.out.println();
        System.out.println("итоговые индексы");
        for (int i = 0; i < str.length(); i++) {
            c[i] = (p[i] - k[i] + 33) % 33;
            System.out.printf("%3d", c[i]);
            if (knowedNum < str.length()) {
                k[knowedNum] = c[i];
                knowedNum++;
            }
        }
        System.out.println();

        String result = "";
        for (int i = 0; i < c.length; i++) {
            result += alph[c[i]];
        }
        System.out.print(result);
        return result;
    }

    @FXML
    void initialize() {

    }
}