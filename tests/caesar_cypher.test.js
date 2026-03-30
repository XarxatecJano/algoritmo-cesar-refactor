function assert(condition, message) {
  if (!condition) {
    throw new Error(`Test failed: ${message}`);
  }
  console.log(`✓ ${message}`);
}

assert(
  cipher('Hello World', 1) === 'Ifmmp!Xpsme',
  "cipher('Hello World', 1) === 'Ifmmp!Xpsme'"
);

assert(
  decipher(cipher('Hello World', 3), 3) === 'Hello World',
  "decipher(cipher('Hello World', 3), 3) === 'Hello World'"
);

console.log('All tests passed.');
