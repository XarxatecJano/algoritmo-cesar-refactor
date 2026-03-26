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
        static final int CAPITAL_A = 65;
        static final int CAPITAL_Z = 90;
        static final int LOWERCASE_A = 97;
        static final int LOWERCASE_Z = 122;
    }

    private static boolean isUpperCaseLetterOutOfRange(int charCode, int shift) {
        return charCode >= Letters.CAPITAL_A && charCode <= Letters.CAPITAL_Z
                && (charCode + shift > Letters.CAPITAL_Z || charCode - shift < Letters.CAPITAL_A);
    }

    private static boolean isLowerCaseOutOfRange(int charCode, int shift) {
        return charCode >= Letters.LOWERCASE_A && charCode <= Letters.LOWERCASE_Z
                && (charCode + shift > Letters.LOWERCASE_Z || charCode - shift < Letters.LOWERCASE_A);
    }

    private static boolean isOutOfAlphabet(int charCode, int shift) {
        return isUpperCaseLetterOutOfRange(charCode, shift) || isLowerCaseOutOfRange(charCode, shift);
    }

    public static char shiftCharToCipher(char character, int shift) {
        shift = shift % ALPHABET_LENGTH;
        int shiftToApply = shift;
        boolean outOfAlphabet = isOutOfAlphabet(character, shift);

        if (outOfAlphabet) {
            shiftToApply = shift += ALPHABET_LENGTH;
        }

        return (char) (character + shiftToApply);
    }

    public static char shiftCharToDecipher(char character, int shift) {
        shift = -shift % ALPHABET_LENGTH;
        int shiftToApply = shift;
        boolean outOfAlphabet = isOutOfAlphabet(character, shift);

        if (outOfAlphabet) {
            shiftToApply = shift += ALPHABET_LENGTH;
        }

        return (char) (character + shiftToApply);
    }

    public static String cipher(String text, int shift) {
        StringBuilder cipher = new StringBuilder();
        char newCharToAddToCipher;

        for (char character : text.toCharArray()) {
            newCharToAddToCipher = shiftCharToCipher(character, shift);
            cipher.append(newCharToAddToCipher);
        }

        return cipher.toString();
    }

    public static String decipher(String text, int shift) {
        StringBuilder decipher = new StringBuilder();
        char newCharToAddToDecipher;

        for (char character : text.toCharArray()) {
            newCharToAddToDecipher = shiftCharToDecipher(character, shift);
            decipher.append(newCharToAddToDecipher);
        }

        return decipher.toString();
    }

    public static void main(String[] args) {
        // Test 1
        String result1 = cipher("Hello World", 1);
        String expected1 = "Ifmmp!Xpsme";
        assert result1.equals(expected1) : String.format("%s === '%s'", result1, expected1);

        // Test 2
        String ciphered = cipher("Hello World", 3);
        String result2 = decipher(ciphered, 3);
        String expected2 = "Hello World";
        assert result2.equals(expected2) : String.format("%s === '%s'", result2, expected2);

        System.out.println("Todos los tests han pasado correctamente");
    }
}