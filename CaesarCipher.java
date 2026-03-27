/*
El Cifrado César es una de las técnicas de cifrado más simples y conocidas. 
Se trata de un tipo de cifrado de sustitución en el que cada letra del texto sin cifrar es reemplazada por otra letra 
que se encuentra un número fijo de posiciones hacia abajo en el alfabeto. 
Por ejemplo, con un desplazamiento hacia la derecha de 3, la letra E sería reemplazada por H, 
la F se convertiría en I, y así sucesivamente.
Esta transformación se puede representar alineando dos alfabetos: el alfabeto cifrado es el alfabeto normal 
rotado hacia la derecha un cierto número de posiciones.

A continuación tienes dos funciones que codifican y decodifican usando el cifrado César.
Tu tarea consiste en entender el código y refactorizarlo para que sea lo más limpio posible, 
según lo visto en la sesión de Clean Code
*/

public class CaesarCipher {
    
    private static final int ALPHABET_LENGTH = 26;
    
    private static class Letters {
        static final int A_UPPER_CASE = 65;
        static final int Z_UPPER_CASE = 90;
        static final int A_LOWER_CASE = 97;
        static final int Z_LOWER_CASE = 122;
    }

    private static boolean isLetterInShiftRange(int charCode, int shift, int minRange, int maxRange) {
        return (charCode >= minRange && charCode <= maxRange &&
                (charCode + shift > maxRange || charCode - shift < minRange));
    }

    private static boolean isLetterInAlphabet(int charCode, int shift) {
        return (isLetterInShiftRange(charCode, shift, Letters.A_UPPER_CASE, Letters.Z_UPPER_CASE) ||
                (isLetterInShiftRange(charCode, shift, Letters.A_LOWER_CASE, Letters.Z_LOWER_CASE)));
    }

    private static int calculateShiftToApply(int charCode, int shift) {
        return isLetterInAlphabet(charCode, shift) ?
                shift - ALPHABET_LENGTH : shift;
    }

    public static String cipher(String text, int shift) {
        text = text.trim();
        StringBuilder cipher = new StringBuilder();
        char newCharToAddToCipher;
        int shiftToApply, currentChar;
        shift = shift % ALPHABET_LENGTH;
        
        for (int i = 0; i < text.length(); i++) {
            System.out.println(i);
            currentChar = (int) text.charAt(i);
            shiftToApply = calculateShiftToApply(currentChar, shift);
            newCharToAddToCipher = (char) (currentChar + shiftToApply);
            cipher.append(newCharToAddToCipher);
        }
        return cipher.toString();
    }
    
    public static String decipher(String text, int shift) {
        shift = -shift;
        return cipher(text, shift);
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