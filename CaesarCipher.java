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

    private static final char UPPERCASE_A = 'A';
    private static final char UPPERCASE_Z = 'Z';
    private static final char LOWERCASE_A = 'a';
    private static final char LOWERCASE_Z = 'z';

    public static String cipher(String text, int shift) {
        return transform(text, normalizeShift(shift));
    }

    public static String decipher(String text, int shift) {
        return transform(text, normalizeShift(-shift));
    }

    private static String transform(String text, int shift) {
        StringBuilder transformedText = new StringBuilder(text.length());

        for (int index = 0; index < text.length(); index++) {
            char currentCharacter = text.charAt(index);
            char transformedCharacter = shiftCharacter(currentCharacter, shift);

            transformedText.append(transformedCharacter);
        }

        return transformedText.toString();
    }

    private static char shiftCharacter(char character, int shift) {
        int adjustedShift = calculateShiftWithinAlphabet(character, shift);
        return (char) (character + adjustedShift);
    }

    private static int calculateShiftWithinAlphabet(int characterCode, int shift) {
        if (!isAlphabeticLetter(characterCode)) {
            return shift;
        }

        if (!shiftExceedsAlphabetBounds(characterCode, shift)) {
            return shift;
        }

        if (shift > 0) {
            return shift - ALPHABET_LENGTH;
        }

        return shift + ALPHABET_LENGTH;
    }

    private static int normalizeShift(int shift) {
        return shift % ALPHABET_LENGTH;
    }

    private static boolean isAlphabeticLetter(int characterCode) {
        return isUppercaseLetter(characterCode) || isLowercaseLetter(characterCode);
    }

    private static boolean isUppercaseLetter(int characterCode) {
        return characterCode >= UPPERCASE_A && characterCode <= UPPERCASE_Z;
    }

    private static boolean isLowercaseLetter(int characterCode) {
        return characterCode >= LOWERCASE_A && characterCode <= LOWERCASE_Z;
    }

    private static boolean shiftExceedsAlphabetBounds(int characterCode, int shift) {
        if (isUppercaseLetter(characterCode)) {
            return characterCode + shift > UPPERCASE_Z || characterCode + shift < UPPERCASE_A;
        }

        if (isLowercaseLetter(characterCode)) {
            return characterCode + shift > LOWERCASE_Z || characterCode + shift < LOWERCASE_A;
        }

        return false;
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
