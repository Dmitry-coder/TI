package sample;

import sun.nio.cs.ext.ISO2022_CN;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadFile {
    public String readA() throws IOException {
        String inputString = "";
        try {
            File file = new File("src\\sample\\input.txt");
            inputString = read(file).stream().map(String::toString).collect(Collectors.joining(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputString;
    }

    private List<String> read(File file) throws IOException {
        return Files.lines(Paths.get(file.getAbsolutePath())).collect(Collectors.toList());
    }
}


























/*
public String encriptStolb(String key, String str) {
        int width = key.length();
        char[] alph = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};
        int indArr[] = new int[key.length()];
        double temp = (str.length() + (key.length() - 1)) / key.length();
        int heigh = (int) Math.ceil(temp);
        char[][] arr = new char[heigh][width];
        int z = 0;
        for (int i = 0; i < heigh; i++) {
            for (int j = 0; j < width; j++) {
                if (z == str.length()) break;
                arr[i][j] = str.charAt(z);
                z++;
            }
        }

        if (str.length() < heigh * width) {
            for (int pos = str.length() % width; pos < width; pos++) {
                arr[heigh - 1][pos] = 'а';
            }
        }

        for (int i = 0; i < heigh; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%2c", arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        char[] tempstr = new char[key.length()];
        for (int i = 0; i < key.length(); i++) {
            tempstr[i] = key.charAt(i);
        }

        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < alph.length; j++) {
                if (key.charAt(i) == alph[j]) {
                    indArr[i] = j;
                    continue;
                }
            }
        }
        System.out.println();

        int[] poryadok = new int[key.length()];
        int iter = 0;
        while (iter < key.length()) {
            for (int point = 0; point < alph.length; point++) {
                for (int j = 0; j < key.length(); j++) {
                    if (point == indArr[j]) {
                        poryadok[iter] = j;
                        iter++;
                    }
                }
            }
        }

        String newStr = "";
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < heigh; i++) {
                if (arr[i][poryadok[j]] != 0) {
                    System.out.printf("%2c", arr[i][poryadok[j]]);
                    newStr += arr[i][poryadok[j]];
                }
            }
        }
        System.out.println();
        System.out.println(newStr);
        return newStr;
    }
 */


/*
public String decriptStolb(String key, String str) {
        char[] alph = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};
        int[] indArr = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < alph.length; j++) {
                if (key.charAt(i) == alph[j]) {
                    indArr[i] = j;
                    continue;
                }
            }
        }
        System.out.println();

        int[] poryadok = new int[key.length()];
        int iter = 0;
        while (iter < key.length()) {
            for (int point = 0; point < alph.length; point++) {
                for (int j = 0; j < key.length(); j++) {
                    if (point == indArr[j]) {
                        poryadok[iter] = j;
                        iter++;
                    }
                }
            }
        }
        String temp = "";
        int heigth = str.length() / key.length();
        int width = key.length();
        System.out.println(heigth + " " + width);
        for (int i = 0; i < width; i++) {
            System.out.print(poryadok[i] + " ");
        }
        System.out.println();
        char arr[][] = new char[heigth][width];
        for (int j = 0; j < width; j++) {
            temp = str.substring(poryadok[j] * heigth, heigth * (poryadok[j] + 1));
            for (int i = 0; i < heigth; i++) {
                arr[i][j] = temp.charAt(i);
            }
        }

        String result = "";
        for (int i = 0 ; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%2c", arr[i][j]);
                result += arr[i][j];
            }
            System.out.println();
        }
        System.out.println(result);
        return result;
    }
 */

