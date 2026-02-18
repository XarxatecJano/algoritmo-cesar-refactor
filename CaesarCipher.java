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
    
    private final TextShifter textShifter;

    public CaesarCipher() {

        ShiftNormalizer normalizer = new ShiftNormalizer(26);

        CharShifter charShifter = new CharShifter(
            AlphabetRange.uppercase(),
            AlphabetRange.lowercase(),
            NonLetterPolicy.shifNonLetterPolicy()
        );
        
        this.textShifter = new TextShifter(normalizer, charShifter);
    }

    public String cipher(String text, int shift) {
        return textShifter.shift(text, shift);
    }

    public String decipher(String text, int shift){
        return textShifter.shift(text, -shift);
    }

    public static void main (String[] args){

        CaesarCipher cipher = new CaesarCipher();

        String result1 = cipher.cipher("Hello World", 1);
        String expected1 = "Ifmmp!Xpsme";
        assert result1.equals(expected1);

        String ciphered = cipher.cipher("Hello World", 3);
        String result2 = cipher.decipher(ciphered, 3);
        String expected2 = "Hello World";
        assert result2.equals(expected2);

        System.out.println("Funcionó!!!");
    }
    
}