public class CaesarCipher {
    
    private static final int ALPHABET_LENGTH = 26;
    
    private static class Letters {
        static final int A = 65;
        static final int Z = 90;
        static final int a = 97;
        static final int z = 122;
    }
    
    private static boolean isUpperCaseLetterOutOfRange(int charCode, int shift) {
        return charCode >= Letters.A && charCode <= Letters.Z && 
               (charCode + shift > Letters.Z || charCode - shift < Letters.A);
    }
    
    private static boolean isLowerCaseOutOfRange(int charCode, int shift) {
        return charCode >= Letters.a && charCode <= Letters.z && 
               (charCode + shift > Letters.z || charCode - shift < Letters.a);
    }
    
    private static boolean isOutOfAlphabet(int charCode, int shift) {
        return isUpperCaseLetterOutOfRange(charCode, shift) || 
               isLowerCaseOutOfRange(charCode, shift);
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