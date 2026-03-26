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

    private static final int UPPERCASE_A = 65;
    private static final int UPPERCASE_Z = 90;
    private static final int LOWERCASE_A = 97;
    private static final int LOWERCASE_Z = 122;


    private static boolean isUppercaseLetter(int charCode) {
        return  charCode >= UPPERCASE_A && charCode <= UPPERCASE_Z;
    }

    private static boolean isLowercaseLetter(int charCode) {
        return  charCode >= LOWERCASE_A && charCode <= LOWERCASE_Z;
    }

    private static boolean isCharLetter(int charCode) {
        return  (isLowercaseLetter(charCode) || isUppercaseLetter(charCode));
    }

    private static boolean isShiftedCharLetter(int charCode, int shift) {
        return isCharLetter(charCode + shift) || isCharLetter(charCode - shift);
    }

    private static int getRemainder(int shift) {
        return shift % ALPHABET_LENGTH;
    }

    private static char shiftChar(int currentChar, int shiftToApply) {
        return (char) (currentChar + shiftToApply);
    }

    private static int getCycleShift(int shift) {

        if(shift > 0) {
            return shift - ALPHABET_LENGTH;
        }

        return  shift + ALPHABET_LENGTH;
    }

    private static int getAppliableShift(int charCode, int shift){

        if (!isCharLetter(charCode)) {
            return shift;
        }

        if (isShiftedCharLetter(charCode,shift)){
            return shift;
        }

        return  getCycleShift(shift);

    }

    private static String getMessage(String text, int initialShift, StringBuilder message){

        for (char c : text.toCharArray()){

            int shiftToApply = getAppliableShift(c, initialShift);
            char newChar = shiftChar(c, shiftToApply);

            message.append(newChar);
        }
        return  message.toString();
    }

    public static String cipher(String text, int shift) {
        StringBuilder cipheredMessage = new StringBuilder();

        shift = getRemainder(shift);

        return getMessage(text, shift, cipheredMessage);
    }


    public static String decipher(String text, int shift) {
        StringBuilder decipheredMessage = new StringBuilder();

        shift = getRemainder(-shift);

        return getMessage(text, shift, decipheredMessage);
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