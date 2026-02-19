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
      (char + shift > lowerLimit || char + shift < upperLimit)
    );
  }

  function isCharOutOfRange(char, shift) {
    let validChar = false;
      for (let i = 0; i < upperLimitGroup.length; i++) {
        validChar = validChar || checkLimits(char, shift, upperLimitGroup[i], lowerLimitGroup[i],
      );
    }
    return validChar;
  }

  function moduleCalc(shift) {
    return shift % ALPHABET_LENGTH;
  }

  function applyShift(char, shift) {
    let shiftToApply = shift;
    if(isCharOutOfRange(char, shift)) {
      shiftToApply = shift > 0 ? shift - ALPHABET_LENGTH : shift + ALPHABET_LENGTH;
    }
    return shiftToApply
  }

  function cipher(text, shift) {
    let cipher = [];
    shift = moduleCalc(shift);

    for (let i = 0; i < text.length; i++) {
      const currentChar = text.charCodeAt(i)
      const shiftToApply = applyShift(currentChar, shift);
      cipher.push(String.fromCharCode(currentChar + shiftToApply));
    }
    return cipher.join("");
  }

  function decipher(text, shift) {
    return cipher(text, -shift);
  }
  
  // end refactor


  // CHECK VISUAL TEST
  let continueWhile = true
  alert("Welcome to the caesar cypher!")

  while(continueWhile) {
    
    let text = prompt("Choise a word to cypher")
    let shift = parseInt(prompt("Choise a key for your cypher"));
    const textCipher = cipher(text, shift)
    
    const textToCipher = textCipher;
    const textToDecipher = decipher(textCipher, shift);
    alert(`CIPHER: ${textToCipher} === ${textCipher}`);
    alert(`DECIPHER: ${textToDecipher} === ${text}`);

    continueWhile = confirm("Do you want to repeat??")

    console.assert(textToCipher === textCipher, `${textToCipher} === ${textCipher}`);
    console.assert(textToDecipher === text, `${textToDecipher} === ${text}`);
  }
})();