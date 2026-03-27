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

private static final int ALPHABET_LENGTH = 26;

private static class Letters {
    static final int A_UPPERCASE = 65;
    static final int Z_LOWERCASE = 122;
}

private static boolean isOutOfAlphabet(int charCode, int shift) {
    return charCode >= Letters.A_UPPERCASE && charCode <= Letters.Z_LOWERCASE &&
            (charCode + shift > Letters.Z_LOWERCASE || charCode - shift < Letters.A_UPPERCASE);
}

public static String cipher(String text, int shift) {
    StringBuilder cipher;
    shift = shift % ALPHABET_LENGTH;

    cipher = traduct(text, shift);

    return cipher.toString();
}

public static String decipher(String text, int shift) {
    StringBuilder decipher;

    shift =- shift % ALPHABET_LENGTH;

    decipher = traduct(text, shift);

    return decipher.toString();
}

public static StringBuilder traduct(String text, int shift) {
    StringBuilder retry = new StringBuilder();
    int shiftToApply;
    for (char x : text.toCharArray()){
        shiftToApply = applyShift(x, shift);
        retry.append((char) (x + shiftToApply));
    }
    return retry;
}

public static int applyShift(int currentChar, int shift) {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    if (isOutOfAlphabet(currentChar, shift)) {
        return (stackTrace[2].getMethodName().equals("cipher")) ? shift - ALPHABET_LENGTH
                : shift + ALPHABET_LENGTH;
    } else {
        return shift;
    }
}

void main() {
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

    IO.println("Todos los tests han pasado correctamente");
}