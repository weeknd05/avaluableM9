# Programa de Ataque de Diccionario

## Descripción
Este programa está diseñado para realizar ataques de diccionario a una lista de cuentas, utilizando dos enfoques diferentes: un enfoque de un solo hilo y un enfoque multihilo. El objetivo es encontrar contraseñas de cuentas a través del método de fuerza bruta, comparando hashes de contraseñas posibles.

## Clases
El programa consta de dos clases principales:

### `AtacSimple`
Esta clase realiza un ataque de diccionario utilizando un único hilo de ejecución. Procesa un array de palabras (`diccionari`) generando hashes para cada palabra y luego compara estos hashes con los hashes de las contraseñas de las cuentas. Este enfoque es más sencillo y directo, pero puede ser más lento para diccionarios grandes o un gran número de cuentas.

### `AtacMulti`
`AtacMulti` es una versión mejorada de `AtacSimple`, diseñada para utilizar múltiples hilos y así aumentar la velocidad del proceso de ataque. Divide el diccionario en partes y asigna cada parte a un hilo diferente, permitiendo que se realicen varias comparaciones de hashes en paralelo. Esta clase es particularmente útil para acelerar el proceso cuando se manejan grandes volúmenes de datos.

## Funcionamiento
Ambas clases toman un array de palabras (`diccionari`) como entrada y generan hashes para cada palabra utilizando un algoritmo de hashing. Luego, comparan estos hashes con los hashes de las contraseñas almacenadas en las cuentas de usuarios (`Compte`). Si un hash generado coincide con el hash de una contraseña, se considera que la contraseña ha sido "descubierta" y se registra el resultado.

## Uso
Para utilizar el programa, se debe ejecutar la clase `App`, la cual presenta una interfaz de usuario en consola que permite elegir entre el ataque de un solo hilo o el ataque multihilo. Después de elegir el método de ataque, el programa solicitará la ubicación del diccionario de palabras y procederá con el ataque correspondiente.

