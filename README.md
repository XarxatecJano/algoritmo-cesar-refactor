# Ejercicio Clean Code - 1ra entrega

Comentario inicial con indicaciones:
> El Cifrado César es una de las técnicas de cifrado más simples y conocidas. 
> Se trata de un tipo de cifrado de sustitución en el que cada letra del texto sin cifrar es reemplazada por otra letra que se encuentra un número fijo de posiciones hacia abajo en el > alfabeto. Por ejemplo, con un desplazamiento hacia la derecha de 3, la letra E sería reemplazada por H, la F se convertiría en I, y así sucesivamente.
> Esta transformación se puede representar alineando dos alfabetos: el alfabeto cifrado es el alfabeto normal rotado hacia la derecha un cierto número de posiciones.
> 
> A continuación tienes dos funciones que codifican y decodifican usando el cifrado César. Tu tarea consiste en entender el código y refactorizarlo para que sea lo más limpio posible, según > lo visto en la sesión de Clean Code

<br>
<br>

# Refactorización


### # Deshacerse de los "magic number" hardcodeados `const LETTERS = {A: 65, Z: 90...}`

- JavaScript permite comparar strings alfabéticamente (`char >= 'A'`).


### # Eliminación de variables innecesarias

He eliminado estas variables temporales `let newCharToAddToCipher, shiftToApply, currentChar;`. Se usa el valor de retorno de las funciones directamente o constantes dentro del bucle


### # Uso de `for...of` en lugar de `for`

El bucle `for...of` evita errores de "fuera de rango".

### # Modularidad

Separado en:

- **`isUpperCase` / `isLowerCase`**
- **`rotateLetter`**
- **`shiftCharacter`**

### # DRY aplicado

`decipher` y `cipher` compartían mucha lógica común.

### # Uso de `const` sobre `let` o `var`

Se prioriza `const` para todas las variables que no cambian su valor. 