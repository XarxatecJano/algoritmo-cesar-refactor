public class CaesarCipher {
    
    private static final int ALPHABET_LENGTH = 26;
    
    private static class Letters {
        static final int A_UPPERCASE = 65;
        static final int Z_UPPERCASE = 90;
        static final int A_LOWERCASE = 97;
        static final int Z_LOWERCASE = 122;
    }
    
     private static boolean isOutOfRange(int charCode, int shift, int min, int max) {
        return (charCode >= min && charCode <= max) && 
            (charCode + shift > max || charCode - shift < min);
    }
    
    private static boolean isOutOfAlphabet(int charCode, int shift) {
        return isOutOfRange(charCode, shift, Letters.A_UPPERCASE, Letters.Z_UPPERCASE) ||
            isOutOfRange(charCode, shift, Letters.A_LOWERCASE, Letters.Z_LOWERCASE);
    }
    
    private static String shiftText(String text, int shift, int correction) {
        StringBuilder result = new StringBuilder();
        for (char charCode : text.toCharArray()) {
            int shiftToApply = isOutOfAlphabet(charCode, shift) ? shift + correction : shift;
            result.append((char) (charCode + shiftToApply));
        }
        return result.toString();
    }
    
    private static int limitShift(int shift) {
        return shift % ALPHABET_LENGTH;
    }

    public static String cipher(String text, int shift) {
        return shiftText(text, limitShift(shift), -ALPHABET_LENGTH);
    }
    
    public static String decipher(String text, int shift) {
        return shiftText(text, -limitShift(shift), ALPHABET_LENGTH);
    }

    private static void assertResult(String result, String expected) {
        assert result.equals(expected) :
            String.format("%s === '%s'", result, expected);
    }
    
    public static void main(String[] args) {
        final int SHIFT_ONE = 1;
        final int SHIFT_THREE = 3;

        assertResult(cipher("Hello World", SHIFT_ONE), "Ifmmp!Xpsme");

        String ciphered = cipher("Hello World", SHIFT_THREE);
        assertResult(decipher(ciphered, SHIFT_THREE), "Hello World");

        System.out.println("Todos los tests han pasado correctamente");
    }
}