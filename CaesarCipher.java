
public class CaesarCipher {

    private static final int ALPHABET_LENGTH = 26;

    public static String cipher(String text, int shift) {
        return applyShift(text, shift);
    }

    public static String decipher(String text, int shift) {
        return applyShift(text, -shift);
    }

    private static String applyShift(String text, int shift) {
        StringBuilder result = new StringBuilder();
        shift = shift % ALPHABET_LENGTH;

        for(int i = 0; i<text.length();i++){
            char currentChar = text.charAt(i);
            result.append(shiftCharacter(currentChar, shift));
        }

        return result.toString();
    }

    private static char shiftCharacter(char c, int shift) {
        return (char) (c + shift);
    }


    public static void main(String[] args) {
        // Test 1
        String result1 = cipher("Hello World", 1); 
        String expected1 = "Ifmmp!Xpsme"; 
        //String expected1 = "Ifmmp!Xpsmf"; 
        if (!result1.equals(expected1)) {
            throw new RuntimeException("Test 1 fallado");
        }
        // Test 2
        String ciphered = cipher("Hello World", 3);
        String result2 = decipher(ciphered, 3);
        String expected2 = "Hello World";
        if (!result2.equals(expected2)) {
        throw new RuntimeException("Test 2 fallado");
        }    
        System.out.println("Todos los tests han pasado correctamente");
    }
}