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
    
    public static String cipher(String text, int shift) {
        StringBuilder cipher = new StringBuilder();
        char newCharToAddToCipher;
        int shiftToApply, currentChar;
        shift = shift % ALPHABET_LENGTH;
        
        for (int i = 0; i < text.length(); i++) {
            currentChar = (int) text.charAt(i);
            shiftToApply = isOutOfAlphabet(currentChar, shift) ? 
                          shift - ALPHABET_LENGTH : shift;
            newCharToAddToCipher = (char) (currentChar + shiftToApply);
            cipher.append(newCharToAddToCipher);
        }
        return cipher.toString();
    }
    
    public static String decipher(String text, int shift) {
        StringBuilder decipher = new StringBuilder();
        char newCharToAddToDecipher;
        int shiftToApply, currentChar;
        shift = -shift % ALPHABET_LENGTH;
        
        for (int i = 0; i < text.length(); i++) {
            currentChar = (int) text.charAt(i);
            shiftToApply = isOutOfAlphabet(currentChar, shift) ? 
                          shift + ALPHABET_LENGTH : shift;
            newCharToAddToDecipher = (char) (currentChar + shiftToApply);
            decipher.append(newCharToAddToDecipher);
        }
        return decipher.toString();
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