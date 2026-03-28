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

    private static class ASCII {
        static final int A = 65; 
        static final int Z = 90;
        static final int a = 97; 
        static final int z = 122;
    }

    private static boolean isUpperCaseLetter(int c) {
        return c >= ASCII.A && c <= ASCII.Z;
    }

    private static boolean isLowerCaseLetter(int c) {
        return c >= ASCII.a && c <= ASCII.z;
    }

    private static int adjustShiftForOutofRange(int currentChar, int shift) {
        if (isUpperCaseLetter(currentChar)) {
            if (currentChar + shift > ASCII.Z) {
                return shift - ALPHABET_LENGTH;
            }
            if (currentChar + shift < ASCII.A) {
                return shift + ALPHABET_LENGTH;
            }
        } else if (isLowerCaseLetter(currentChar)) {
            if (currentChar + shift > ASCII.z) {
                return shift - ALPHABET_LENGTH;
            }
            if (currentChar + shift < ASCII.a) {
                return shift + ALPHABET_LENGTH;
            }
        }
        return shift;
    }

    private static String applyShift(String text, int shift) {
        StringBuilder result = new StringBuilder();
        int shiftToApply = shift % ALPHABET_LENGTH;

        for (int i = 0; i < text.length(); i++) {
            int currentChar = (int) text.charAt(i);
            int finalShift = adjustShiftForOutofRange(currentChar, shiftToApply);
            char newCharToAdd = (char) (currentChar + finalShift);
            result.append(newCharToAdd);
        }

        return result.toString();
    }

    public static String cipher(String text, int shift) {
        return applyShift(text, shift);
    }

    public static String decipher(String text, int shift) {
        return applyShift(text, -shift);
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