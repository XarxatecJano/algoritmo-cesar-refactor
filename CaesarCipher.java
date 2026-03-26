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

    public static void main(String[] args) {
        // Test 1
        String result1 = cipher("Hello World", 1);
        String expected1 = "Ifmmp!Xpsme";
        assert  result1.equals(expected1) :
                String.format("%s === '%s'", result1, expected1);

        // Test 2
        String ciphered = cipher("Hello World", 3);
        String result2 = decipher(ciphered, 3);
        String expected2 = "Hello World";
        assert result2.equals(expected2) :
                String.format("%s === '%s'", result2, expected2);

        System.out.println("Todos los tests han pasado correctamente");
    }

    public static String cipher(String text, int shift) {
        shift = calculateShift(shift);
        return shiftCharacters(text, shift);
    }

    public static String decipher(String text, int shift) {
        shift = calculateShift(shift);
        return  shiftCharacters(text, -shift);
    }

    private static String shiftCharacters(String text, int shift) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            stringBuilder.append(transformChar(currentChar, shift));
        }

        return stringBuilder.toString();
    }

    private static char transformChar(char currentChar, int shift) {
        char newChar;

        if (Character.isUpperCase(currentChar)) {
            newChar = rotateChar(currentChar, shift, 'A', 'Z');
        } else if (Character.isLowerCase(currentChar)){
            newChar = rotateChar(currentChar, shift, 'a', 'z');
        } else {
            newChar = (char)(((int)currentChar) + shift);
        }

        return newChar;
    }

    private static char rotateChar(char currentChar, int shift, int min, int max) {

        char newAsciiCode = (char)((int)currentChar + shift);

        if (newAsciiCode > max) {
            newAsciiCode -= ALPHABET_LENGTH;
        } else if (newAsciiCode < min) {
            newAsciiCode += ALPHABET_LENGTH;
        }

        return newAsciiCode;
    }

    private static int calculateShift(int shift) {
        if (shift < -ALPHABET_LENGTH) {
            shift = (shift % ALPHABET_LENGTH) + ALPHABET_LENGTH;
        } else if (shift > ALPHABET_LENGTH){
            shift = shift%ALPHABET_LENGTH;
        }
        return shift;
    }
}