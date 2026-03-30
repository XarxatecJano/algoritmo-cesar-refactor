const ALPHABET_LENGTH = 26;


function cipher(text, shift) {
  let result = '';

  for (const char of text) {
    result += shiftCharacter(char, shift);
  }

  return result;
}


function decipher(text, shift) {
  return cipher(text, -shift);
}


function shiftCharacter(char, shift) {
  if (isUpperCase(char)) {
    return rotateLetter(char, 'A', shift);
  }
  if (isLowerCase(char)) {
    return rotateLetter(char, 'a', shift);
  }

  const charCode = char.charCodeAt(0);
  return String.fromCharCode(charCode + shift);
}


function isUpperCase(char) {
  return char >= 'A' && char <= 'Z';
}


function isLowerCase(char) {
  return char >= 'a' && char <= 'z';
}


function rotateLetter(char, baseLetter, shift) {
  const charCode = char.charCodeAt(0);
  const baseCode = baseLetter.charCodeAt(0);
  
  const normalizedShift = shift % ALPHABET_LENGTH;

  const newCode = baseCode + (charCode - baseCode + normalizedShift + ALPHABET_LENGTH) % ALPHABET_LENGTH;
  
  return String.fromCharCode(newCode);
}