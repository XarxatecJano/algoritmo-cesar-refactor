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


import java.util.stream.Collectors;

public class CaesarCipher {

    private static final int ALPHABET_LENGTH = 26;
/**
 * Aqui comprueba primero si era una letra en cuyo caso no hace falta hacer mas comprobaciones,
 * luego comprueba que si era mayuscula o minuscula andes de cambiar siga siendolo despues de cambiar,
 * por que si no me da un error al salirse del abecedario
 * **/
    private static boolean isOutOfAlphabet(char charCode, int shift) {
         char result = (char) (charCode + shift);
         if (!Character.isAlphabetic(charCode)) return false;
         if (Character.isUpperCase(charCode) && !Character.isUpperCase(result)) return true;
         if (Character.isLowerCase(charCode) && !Character.isLowerCase(result))return true;
         return false;
    }
    public static String decipher(String text, int shift) {
        return cipher(text, -shift);
    }
    public static String cipher(String text, int shift) {
        return cipherFunctional(text,shift);
    }

    private static int getShiftToApply(int shift, char currentChar) {
        if (isOutOfAlphabet(currentChar, shift)) {
            if (shift > 0) return shift - ALPHABET_LENGTH;
            else return shift + ALPHABET_LENGTH;
        }
        return shift;
    }
    private static String cipherFunctional(String text, int shift){
        final int normalShift = shift % ALPHABET_LENGTH;
        return text.chars()
                .map(c -> c + getShiftToApply(normalShift, (char)c))
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
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