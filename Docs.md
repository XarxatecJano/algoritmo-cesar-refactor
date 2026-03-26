
### 1. He sustituido la clase Letters por constantes con caracteres legibles.

Este cambio mejora la legibilidad y elimina números mágicos.

### 2. He eliminado la duplicación entre <code>cipher</code> y <code>decipher</code>

Ambos métodos tenían prácticamente la misma estructura, con la única diferencia real siendo el signo de
desplazamiento y el ajuste final del mismo. Haciendo este cambio dejo de mezclar responsabilidad de alto
nivel con detalles internos de recorrido y transformación.

### 3. He extraído la lógica comun a <code>transform</code>

He creado el método transform el cual encapsula el proceso general de transformación del texto,
independientemente de si se trata de cifrar o descifrar.

### 4. He añadido a <code>StringBuilder</code> capacidad inicial

Esto es una mejora que aunque pequeña es correcta a nivel de rendimiento, he aplicado el principio de Boy
Scout aquí. Reservar esa capacidad desde el inicio evita redimensionamientos internos necesarios.

### 5. He extraído la transformación de un carácter a <code>shiftCharacter</code>

Lo he extraído a un método propio, así el código gana claridad y modularidad.

### 6. He centralizado el cálculo del desplazamiento en <code>calculateShiftWithinAlphabet</code>

La decisión sobre si había que sumar o restar <code>ALPHABET_LENGTH</code> estaba repetida entre <code>cipher</code>
y <code>decipher</code>. Con este cambio centralizo la política de ajuste del desplazamiento en un único método.

### 7. He mejorado los nombres de métodos booleanos y de variables

De esta manera comunico mejor la intención de <code>isOutOfAlphabet</code> porque dejo claro que la comprobación
del desplazamiento, no solo del carácter.

### 8. Commits Posteriores.

He realizado 1 commit posterior para correguir un nombre de variable sobredescriptivo y eliminar archivos innecesarios.

# Resumen

> He centrado la refactorización en eliminar duplicación, mejorar nombres, extraer responsabilidades a métodos
> pequeños y cohesivos, eliminar números mágicos y centralizar la lógica del desplazamiento.
