
const ALPHABET_LENGTH = 26;
const LETTERS = {ASCII_UPPER_A: 65, ASCII_UPPER_Z: 90, ASCII_LOWER_A:97 ,ASCII_LOWER_Z:122}

function isUpperCaseLetterOutOfRange(code, shift){
  return code >= LETTERS.ASCII_UPPER_A && code <= LETTERS.ASCII_UPPER_Z && (code + shift > LETTERS.ASCII_UPPER_Z||code -shift < LETTERS.ASCII_UPPER_A);
}

function isLowerCaseOutOfRange(code, shift){
  return code >= LETTERS.ASCII_LOWER_A && code <= LETTERS.ASCII_LOWER_Z && (code + shift > LETTERS.ASCII_LOWER_Z||code-shift < LETTERS.ASCII_LOWER_A);
}

function isOutOfAlphabet(code, shift){
  return isUpperCaseLetterOutOfRange(code, shift) || isLowerCaseOutOfRange(code, shift);
}

function normalizeShift(shift){
  return shift % ALPHABET_LENGTH;
}

function shiftText(text, shift){
  let result = '';
  const normalizedShift = normalizeShift(shift);
  for (let i = 0; i < text.length; i++){
    let character = text[i]
    result += shiftCharacter(character, normalizedShift);
  }

  return result;
}

function shiftCharacter(character, shift){
  const code = character.charCodeAt(0);

  const shiftToApply = isOutOfAlphabet(code, shift)?shift > 0 ? shift - ALPHABET_LENGTH:shift + ALPHABET_LENGTH: shift;
  return String.fromCharCode(code + shiftToApply);
}

function cipher(text, shift) {
  return shiftText(text, shift);
}
  
function decipher(text, shift) {
  return shiftText(text, -shift);
}

console.assert(
  cipher('Hello World', 1) === 'Ifmmp!Xpsme',
  `${cipher('Hello World', 1)} === 'Ifmmp!Xpsme'`,
);
console.assert(
  decipher(cipher('Hello World', 3), 3) === 'Hello World',
  `${decipher(cipher('Hello World', 3), 3)} === 'Hello World'`,
);