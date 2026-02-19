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

(() => {
  const ALPHABET_LENGTH = 26;
  const LETTERS = {
    A: 65,
    Z: 90,
    a: 97,
    z: 122,
  };

  const upperLimitGroup = [LETTERS.A, LETTERS.a];
  const lowerLimitGroup = [LETTERS.Z, LETTERS.z];

  function checkLimits(char, shift, upperLimit, lowerLimit) {
    return (
      char >= upperLimit &&
      char <= lowerLimit &&
      (char + shift > lowerLimit || char - shift < upperLimit)
    );
  }

  function isCharOutOfRange(char, shift) {
    let validChar = false;
    for (let i = 0; i < upperLimitGroup.length; i++) {
      validChar = checkLimits(char, shift, upperLimitGroup[i], lowerLimitGroup[i],
      );
    }
    return validChar;
  }

  function moduleCalc(shift) {
    return (shift = shift % ALPHABET_LENGTH);
  }

  function applyShift(char, shift) {
    let shiftToApply = isCharOutOfRange(char, shift) ? shift - ALPHABET_LENGTH : shift;
    return shiftToApply
  }

  function cipher(text, shift) {
    let cipher = "";
    let newCharToAdd, shiftToApply, currentChar;

    shift = moduleCalc(shift);

    for (let i = 0; i < text.length; i++) {
      currentChar = text.charCodeAt(i)
      shiftToApply = applyShift(currentChar, shift);
      newCharToAdd = String.fromCharCode(currentChar + shiftToApply);
      cipher = cipher.concat(newCharToAdd);
    }
    return cipher
  }

  function decipher(text, shift) {
    return cipher(text, -shift);
  }

  // CHECK TEST
  let text = "Hello World";
  let textCipher = "Ifmmp!Xpsme";
  let shift = 30;
  const textToCipher = cipher(text, shift);
  const textToDecipher = decipher(textCipher, shift);

  console.assert(textToCipher === textCipher, `${textToCipher} === ${textCipher}`);
  console.assert(textToDecipher === text, `${textToDecipher} === ${text}`);
})();