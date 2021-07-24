import java.util.Scanner;

public class AtbashEncode {
    public static void main(String[] args) {
        ATBASH();
    }

    private static void ATBASH() {
        ATBASH atbash = new ATBASH();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the plain text: ");
        String inputText = scanner.nextLine();

        String encode = atbash.encodeProcedureATBASH(inputText);

        System.out.println("The encoded String is: " + encode);
    }

    public static class ATBASH {

        public String encodeProcedureATBASH(String inputText) {

            StringBuilder str1 = new StringBuilder();
            StringBuilder encodedText = new StringBuilder();
            String plainText = null;
            String encode;
            int noSpclChar = 0;

            for (int i = 0; i < inputText.length(); i++) {
                if (('A' <= inputText.charAt(i) && inputText.charAt(i) <= 'Z')
                        || ('a' <= inputText.charAt(i) && inputText.charAt(i) <= 'z')) {

                    str1.append(inputText.charAt(i));
                    plainText = str1.toString();
                    noSpclChar = 1;

                } else {

                    noSpclChar = 0;
                    if (plainText != null) {
                        encodedText.append(encodeDecodeATBASH(plainText));
                    }
                    str1.delete(0, i);

                    plainText = null;
                    encodedText.append(inputText.charAt(i));

                }

            }

            if (noSpclChar == 1 & plainText != null) {
                encodedText.append(encodeDecodeATBASH(plainText));
            }

            encode = encodedText.toString();

            return encode;
        }

        private String encodeDecodeATBASH(String inputText) {
            StringBuilder str = new StringBuilder();
            char cipChar;
            int cipInt = 0;
            for (int i = 0; i < inputText.length(); i++) {
                char ch = inputText.charAt(i);
                int chi = charToNum(ch);
                if (0 <= chi & chi <= 24) {
                    cipInt = 25 - ((chi + 25) % 25);
                } else if (chi == 25) {
                    cipInt = 0;
                }
                if ((int) ch >= 65 && (int) ch <= 90) {
                    cipChar = numToChar(cipInt, 1);
                } else {
                    cipChar = numToChar(cipInt, 0);
                }
                str.append(cipChar);
            }

            return str.toString();

        }

        private int charToNum(char c) {
            int ret = -1;
            if ((65 <= c) && (c <= 91)) {
                ret = c - 65;
            } else if ((97 <= c) && (c <= 123)) {
                ret = c - 97;
            }
            return ret;
        }

        private char numToChar(int i, int j) {
            char c = '\0';
            if ((0 <= i) && (i <= 25)) {
                if (j == 1) {
                    c = (char) (i + 65);
                } else {
                    c = (char) (i + 97);
                }
            }
            return c;
        }

    }
}
