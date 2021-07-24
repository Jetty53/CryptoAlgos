import java.util.Scanner;

public class AffineDecode {
    public static void main(String[] args) {
        AFFINE();
    }

    private static void AFFINE() {
        AFFINE_DECODE affine = new AFFINE_DECODE();
        Scanner scanner = new Scanner(System.in);
        String encode, decode;
        System.out.println("Enter the encoded text: ");
        encode = scanner.nextLine();
        System.out.println("Enter the A key number ");
        int keyA = scanner.nextInt();
        System.out.println("Enter the B key number ");
        int keyB = scanner.nextInt();

        if ((GCD(keyA, 26) == 1)) {
            decode = affine.decodeProcedureAFFINE(encode, keyA, keyB);
            System.out.println(decode);
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

    static class AFFINE_DECODE {

        public String decodeProcedureAFFINE(String encode, int keyA, int keyB) {

            StringBuilder str2 = new StringBuilder();
            StringBuilder decodedText = new StringBuilder();
            String plainText = null;
            String decode;
            int noSpclChar = 0;

            for (int i = 0; i < encode.length(); i++) {
                if (('A' <= encode.charAt(i) && encode.charAt(i) <= 'Z')
                        || ('a' <= encode.charAt(i) && encode.charAt(i) <= 'z')) {

                    str2.append(encode.charAt(i));
                    plainText = str2.toString();
                    noSpclChar = 1;

                } else {

                    noSpclChar = 0;
                    if (plainText != null) {
                        decodedText.append(decodeAFFINE(plainText, keyA, keyB));
                    }
                    str2.delete(0, i);

                    plainText = null;
                    decodedText.append(encode.charAt(i));

                }

            }

            if (noSpclChar == 1 & plainText != null) {
                decodedText.append(decodeAFFINE(plainText, keyA, keyB));
            }

            decode = decodedText.toString();

            return decode;
        }

        private String decodeAFFINE(String encode, int keyA, int keyB) {
            StringBuilder str = new StringBuilder();
            int afterMod = 0;
            int resCharInt;
            char plainChar;
            for (int i = 1; i <= 26; i++) {
                if (((keyA * i) % 26) == 1) {
                    afterMod = i;
                }
            }

            for (int i = 0; i < encode.length(); i++) {

                char ch = encode.charAt(i);

                resCharInt = (afterMod * ((charToNum(encode.charAt(i))) - keyB)) % 26;

                if (resCharInt < 0) {
                    do {
                        resCharInt = resCharInt + 26;
                    } while (0 > resCharInt || resCharInt > 25);

                }

                if ((int) ch >= 65 && (int) ch <= 90) {
                    plainChar = numToChar(resCharInt, 1);
                } else {
                    plainChar = numToChar(resCharInt, 0);
                }

                str.append(plainChar);
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
