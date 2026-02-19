public class CaesarCipher {
    
    private static final int ALPHABET_LENGTH = 26;

    private static class Letters {
        static final int A_UPPERCASE = 65;
        static final int Z_UPPERCASE = 90;
        static final int A_LOWERCASE = 97;
        static final int Z_LOWERCASE = 122;
    }

    private static boolean letterOutOfRange(int charCode, int shift, int min, int max) {
    return charCode >= min && charCode <= max &&
           (charCode + shift > max || charCode + shift < min);  
    }

    private static boolean outOfAlphabet(int charCode, int shift) {
    return letterOutOfRange(charCode, shift, Letters.A_UPPERCASE, Letters.Z_UPPERCASE) ||
           letterOutOfRange(charCode, shift, Letters.A_LOWERCASE, Letters.Z_LOWERCASE);   
    }

    public static String cipher(String text, int shift) {
    return shiftText(text, shift);
    }

    public static String decipher(String text, int shift) {
    return shiftText(text, -shift);
    }

    private static String shiftText(String text, int shift) {
    StringBuilder result = new StringBuilder();
    shift = shift % ALPHABET_LENGTH;

    for (int i = 0; i < text.length(); i++) {
        int currentChar = text.charAt(i);
        int shiftToApply = outOfAlphabet(currentChar, shift) ?
                        (shift > 0 ? shift - ALPHABET_LENGTH : shift + ALPHABET_LENGTH)
                        : shift;
        result.append((char) (currentChar + shiftToApply));
    }
    return result.toString();
    }
    
    public static void main(String[] args) {
    final int SHIFT_1 = 1;
    final int SHIFT_3 = 3;

    assert cipher("Hello World", SHIFT_1).equals("Ifmmp!Xpsme") : "Test 1 failed";
    assert decipher(cipher("Hello World", SHIFT_3), SHIFT_3).equals("Hello World") : "Test 2 failed";

    System.out.println("Todos los tests han pasado correctamente");
    }
}