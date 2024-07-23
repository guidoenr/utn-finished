# FINALES de IA (preguntas)

### 1. Los sistemas basados en conocimiento pueden tener la base de conocimientos vacia una vez esta en produccion.
```py
los SBC no son mas que sistemas que utilizan conocimientos para resolver problemas,
los SBC utilizan la gran llamada "base de conocimientos" la cual tiene estos conocimientos.

suelen tener un motor de inferencia para tomar decisiones, a diferencia de 
la logica tradicional de cualquier algoritmo.
este motor es el que aplica las reglas a los hechos para deducir nueva info 
o tomar decisiones.

y esta base que tiene? hechos, como "los gatos tienen 4 patas" o reglas, como
" si un animal tiene cuatro patas y hace miau -> es un gato "

dado esta explicacion, y haciendo notar que la base de conocimentos es literalmente
el core de estos sistemas, seria muy raro que esten en produccion sin esta base
dado que no tendrian de donde poder tomar referencia de sus decisiones, osea, no
tendrian hechos ni reglas para decidir.
```

### 2. Es imprescindible testear los pipelines de un sistema que integra varios modelos de IA

```py
totalmente imprescindible, dado que los modelos de IA no siempre son deterministicos
y pueden tener variaciones en sus salidas debido a cambios en los datos de entrada
o modificaciones en los modelos, actualizaciones, etc.

las pruebas nos dan consistencia en los resultados.

y asimismo esta bueno hacer varios tests como para garantixar la precision, 
confiabilidad, rendimiento, seguridad, disponibilidad, (entre otros 90
atributos de calidad)
```

### 3. El emparrillado sirve para identificar las relaciones implicitas entre conceptos.

```py
si, sirve para eso y varias cosas mas, pero justamente la identificacion de las relaciones implicitas entre conceptos es realizada en la ultima etapa de esta
tecnica, llamada:
" Interpretacion o analisis de resultados "

El emparrillado en si, es una tecnica para conocer el conocimiento de un experto, conocida como una tecnica de "Educcion de conocimientos". 
vincula una lista de elementos homogeneos y representativos sobre la base de un conjunto bipolar de caracteristicas.

- es considerada una tecnica intrusiva e incompleta (porque habla
de lo que el experto sabe)

- usada sola no es util para la construccion de una BDC
```

### 4. Un algoritmo genetico es una buena opcion para diagramar los temas por dia que se van a dictar a lo largo de una cursada de una materia
```py
Lo mas curioso de los algoritmos geneticos es que estan basados en el proceso
de seleccion natural de el gran "Charles Darwin", porque usan tecnicas basadas
en la genetica y la evolucion para resolver problemas 
complejos de busqueda/optimizacion.

estos algoritmos tienen varios elementos, como:

- "Poblacion": conjunto de posibles soluciones al problema, cada solucion es 
un individuo de la poblacion, se podria decir que la poblacion es el
conjunto de soluciones.
- "Cromosomas": representa a un individuo, en los cromosomas esta codificado
una posible solucion, es como el "codigo" de un X individuo.
- "Genes": Cada gen representa una caracteristica o variable de la solucion.
- "Fitness": (aptitud): medida de cuan buena es una solucion para resolver
el problema.

y a su vez, tiene "PASOS", como:
1. Inicializacion: crea la poblacion inicial random
2. Evaluacion: evalua la aptitud de c/indivudo en la poblacion.
3. Seleccion: se seleccionan individuos de mayor aptitud (aca entra los metodos como ruleta/torneo)
4. Cruzamiento (crossover): combinar parte de dos indivudos padres para producir hijos.
5. Mutacion: alterar uno o mas genes del individuo para que haya variabilidad en la poblacion y evitar la convergencia.
6. Reemplazo: crear una nueva generacion reemplazando algunos/todos los individuos con los nuevos hijos.
7. Iteracion: repetir todo hasta cumplir un criterio de terminacion (como un numero fijo de generaciones o una aptitud optima alcanzada)

## Ejemplo de Aplicación
"Problema del Viajante "
> Encontrar la ruta más corta que visite un conjunto de ciudades y regrese a la ciudad de origen.

- "Cromosoma": Representa una ruta posible, osea, es la solucion, imaginate (4-5-2-1)
- "Gen": Representa una ciudad en la ruta, osea podria ser 5
- "Fitness": Evaluada como la longitud total de la ruta (las rutas más cortas tienen mayor fitness).


y respondiendo a la pregunta, como lo usarias para diagramar los temas que se van
a dar en una cursada? eso es NADA QUE VER A ESTO.
```

### 5. (!!!!) [BE CAREFUL WITH THIS STUDIALA]Te daban el arbol de relaciones de un emparrillado y el texto y tenias que poner las observaciones que encontrabas en el arbol y ademas desarrollar sobre las relaciones

### 6. Para implementar un Sistema inteligente exitosamente es necesario conocer todas las tecnologias existentes en ia.

### 7. RNA entrenamiento? como se hace

### 8. Diferencia entre Sistema basado en conocimiento y Sistema experto

### 9. Tipos de aprendizaje (online u offline)

### 10. En qué situación se usan los algoritmos genéticos

### 11. por qué en el emparrillado la fase de interpretación es la más importante?

### 12. En el análisis de protocolo, que son los metacomentarios y para que sirven?

### 13. En un Sistema experto tradicional, el ingeniero en conocimiento de donde saca los conocimientos? (era responder con fuentes privadas, publicas y ejemplos de cada fuente)

### 14. Si tuviera que implementar una red neuronal de backpropagation, que pasos habría que seguir? ( era hablar desde como el profesional busca el conocimiento, pasando por la arquitectura de la RNA, entrenamiento y validación)

### 15. Como interviene la lógica en un sistema experto tradicional? (era hablar de la arquitectura de SBC, que el conocimiento se almacena con reglas, hechos de forma explicita en una base de conocimientos y luego del motor de inferencias)