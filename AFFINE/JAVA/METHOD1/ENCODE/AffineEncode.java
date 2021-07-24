import java.util.Scanner;

public class AffineEncode {

    public static void main(String[] args) {
        AFFINE();
    }

    private static void AFFINE() {
        AFFINE_ENCODE affine = new AFFINE_ENCODE();
        Scanner scanner = new Scanner(System.in);
        String encode;
        System.out.println("Enter the plain text: ");
        String inputText = scanner.nextLine();
        System.out.println("Enter the A key number ");
        int keyA = scanner.nextInt();
        System.out.println("Enter the B key number ");
        int keyB = scanner.nextInt();

        if ((GCD(keyA, 26) == 1)) {
            encode = affine.encodePreProcedureAFFINE(inputText, keyA, keyB);
            System.out.println(encode);
        } else {
            System.out.println("Sorry, invalid key input.");
        }

    }

    private static int GCD(int a, int b) {
        if (b != 0) {
            return GCD(b, a % b);
        } else {
            return a;
        }
    }

    static class AFFINE_ENCODE {

        public String encodePreProcedureAFFINE(String inputText, int keyA, int keyB) {

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
                        encodedText.append(encodeAFFINE(plainText, keyA, keyB));
                    }
                    str1.delete(0, i);

                    plainText = null;
                    encodedText.append(inputText.charAt(i));

                }

            }

            if (noSpclChar == 1 & plainText != null) {
                encodedText.append(encodeAFFINE(plainText, keyA, keyB));
            }

            encode = encodedText.toString();

            return encode;
        }

        private String encodeAFFINE(String inputText, int keyA, int keyB) {

            StringBuilder str = new StringBuilder();
            char cipChar;
            for (int i = 0; i < inputText.length(); i++) {
                char ch = inputText.charAt(i);
                int chi = charToNum(inputText.charAt(i));
                int cipInt = ((keyA * chi) + keyB) % 26;
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


