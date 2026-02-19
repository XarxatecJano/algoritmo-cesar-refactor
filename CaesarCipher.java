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
        // Test 1
        String result1 = cipher("Hello World", 1);
        String expected1 = "Ifmmp!Xpsme";
        assert result1.equals(expected1) : 
            String.format("%s === '%s'", result1, expected1);
        
        // Test 2
        String ciphered = cipher("Hello World", 3);
        String result2 = decipher(ciphered, 3);
        String expected2 = "Hello World";
        assert result2.equals(expected2) : 
            String.format("%s === '%s'", result2, expected2);
        
        System.out.println("Todos los tests han pasado correctamente");
    }
}