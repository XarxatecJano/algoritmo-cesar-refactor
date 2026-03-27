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
    static final boolean SKIP_CHARS_OUT_OF_ALPHABET = false;
    static final char FIRST_CHAR_UPPER = 'A';
    static final char FIRST_CHAR_LOWER = 'a';
    static final char LAST_CHAR_UPPER = 'Z';
    static final char LAST_CHAR_LOWER = 'z';
    static final char INVALID_CHAR = '\0';
    static final int ALPHABET_LENGTH = 26;

    private static boolean validString(String s){
        if(s != null && !s.isBlank())
            return true;
        return false;
    }

    private static boolean inRange(int value, int rangeStart, int rangeEnd){
        return value >= rangeStart && value <= rangeEnd;
    }

    private static boolean inLowercase(int letter){
        return inRange(letter, FIRST_CHAR_LOWER, LAST_CHAR_LOWER);
    }

    private static boolean inUppercase(int letter){
        return inRange(letter, FIRST_CHAR_UPPER, LAST_CHAR_UPPER);
    }
    
    private static boolean inAlphabet(char letter){
        int index = (int)letter;
        return inUppercase(index) || inLowercase(index);
    }

    private static int findRealModuleInt(int number, int divisor){
        // Necesario porque la manera en que Java calcula el modulo 
        // de numeros negativos no es igual a la "matematicamente correcta".
        int result = number % divisor;
        if (number < 0)
            result += divisor;
        return result;
    }

    private static char cipherLetter(char letter, int shift){
        char encodedChar = INVALID_CHAR;

        if (inAlphabet(letter)){
            int offset;
            if (inUppercase(letter))
                offset = FIRST_CHAR_UPPER;
            else
                offset = FIRST_CHAR_LOWER;

            int index = letter - offset;
            int indexAfterShift = findRealModuleInt((index + shift), ALPHABET_LENGTH);

            encodedChar = (char)(indexAfterShift + offset);
        }
        else{
            encodedChar = SKIP_CHARS_OUT_OF_ALPHABET ? letter : (char) (letter + shift);
        }

        return encodedChar;
    }

    public static String cipher(String message, int shift){
        String result = null;
        
        if(validString(message)){
            StringBuilder builder = new StringBuilder();
            
            for(int i = 0; i<message.length(); i++){
                char nextLetter = message.charAt(i);
                builder.append(cipherLetter(nextLetter, shift));
            }
            result = builder.toString();
        }

        return result;
    }

    public static String decipher(String message, int shift){
        return cipher(message, shift * -1 );
    }
    
    public static void main(String[] args) {
        
        // He tocado los tests porque las assertions no funcionaban, pero
        // pero la lógica esta intacta.

        int testsPasados = 0;
        int testRealizados = 0;
        // Test 1
        String result1 = cipher("Hello World", 1);
        String expected1 = "Ifmmp!Xpsme";
        String eval1 = String.format("\n%s === '%s'", result1, expected1);
        System.out.println(eval1);
        if(result1.equals(expected1)){
            System.out.println("Test 1: Pasado correctamente");
            testsPasados++;
        }
        else
            System.out.println("Test 1: Error");
        testRealizados++;

        // Test 2
        String ciphered = cipher("Hello World", 3);
        String result2 = decipher(ciphered, 3);
        String expected2 = "Hello World";
        String eval2 = String.format("\n%s === '%s'", result2, expected2);
        System.out.println(eval2);
        if (result2.equals(expected2)){
            System.out.println("Test 2: Pasado correctamente");
            testsPasados++;
        }
        else
            System.out.println("Test 2: Error");
        testRealizados++;
        
        System.out.println("\n%d/%d tests pasados correctamente\n".formatted(testsPasados, testRealizados));
    }
}