# Informacion relevada
Con el fin de dar una puesta a tierra hasta el momento, en pantalla pueden ver varios parametros o variables llamemosle "mas importantes" ya que son una especie de nucleo de nuestra simulacion, que al momento de simular.. nos dimos cuenta de como cambia la decision del "que hacer" jugando con estas mismas. Estas 4 variables, son las mas tenidas en cuenta al momento de tomar la decision: ver si escalar, desescalar, la cantidad de servidores que tenemos andando en el momento, el porcentaje de tiempo ocioso de cada uno, etc.

- **El intervalo entre peticiones**: notamos como reduciendo minimamente el intervalo, ya la decision cambia dado que recibimos un gran volumen de peticiones que resultan en una carga importante para los servidores actuales y no le quedaba otra al programa que de-escalar.
- **Tiempo de atencion**: variable parecida en el sentido de la carga, o el procesamiento... dado que mientras mas tardaba en procesarse, mas servidores se necesitaban, es decir, mas se tardaba en dar una respuesta a esta misma, lo cual, seguida de la mano con el promedio de espera en cola maximo:
- **PEC**: que es un atributo de performance, se optaba tambien por escalar.. y mas si este valor era alto (que obviamente es lo no esperado en cualquier aplicacion-servidor o webserver porque uno de los atributos que se priorizan mayormente es la velocidad con que obtenes una respuesta independientemente de tu conexion a internet.
- **PTO**: y por ultimo, el porcentjae de tiempo ocioso, dado que es una variable muy importante para el descalamiento, donde simplemente nos servia para ver si debemos descalar (dado que no queremos que esten servidores funcionando sin operar, principalmente por los costos, que es un objetivo de nuestra simulacion)


Nos van a servir para tomar la decision de escalar, descalar, cantidad de servers, etc..

# Analisis Previo
Analizando el caso y las variables, tenemos como datos el IAP, TA, y DEE, como pueden notar.. todos los datos estan dados en minutos, no hay dato dado en densidades.. por lo que el paso 1 directamente se descarta y esto nos lleva a analizar el paso 2, que habla de los costos fijos. El costo total, esta dado en minutos
Analizando el caso , y siguiendo los pasos para determinar la metodologia.. definimos optar por DT constante dado que el paso 2 nos saco por el mismo, ya que el costo total esta dado en minutos y no podemos iterar por menos de dicha unidad.
Como ya mencionamos, las variables de control son los umbrales de escalado, que aca escritas explicitamente denotan que son realmente, como se puede apreciar son solamente un promedio de espera en minutos y un porcentaje de tiempo ocioso del servidor.
Los resultados matchean considerablemente con los objetivos ya mencionados de la simulacion, que era encontrar la mejor relacion costo-performacne, aca se puede apreciar como estan los costos y para medir la performance, el promedio de espera en cola.
Ultima pero no menos importante, la variable de estado, que simplemente es la cantidad de servidores activos.

# Tabla de eventos propios
En total tenemos 3 eventos, los cuales son el de-escalado ya mencionado, la aplicacion de escalado y el pedido de escalado.
- **De-escalado**: el descalado es un evento propio porque sucede por que si, no hay explicacion de porque sucede esto dado que es automatico. Ahora, me podrian decir "no, esto sucede porque tenemos servidores andando de sobra y no queremos pagar por algo que no usamos" pero no es tan asi, dado que no hay que perder de foco que las requests las hacen los usuarios, y a pesar de que sea una variable bastante medible, esta puede variar mucho en el tiempo dado que las que las ejecutan son personas.. y el comportamiento de una persona en un sitio web, aplicacion o servidor es muy sporadico y mas si la simulacion estaria referida a una aplicacion de venta de ropa por ejemplo. Por eso, el de-escalado cierra mas como un evento propio
- **Pedido de escalado**: antes de hablar de la aplicacion del escalado, quiero hablar del pedido, que es basicamente un evento que se compromete para un DT futuro porque es el encargado de lograr que se ejecute la aplicacion de escalado, y asi el unico que puede escribir en la TEF, que como pueden notar, esta el evento FPE escrito en ella.
- **Aplicacion de escalado**: es basicamente un evento que sucede gracias al pedido, y es el que ya se hablo y menciono bastante y consiste en agregar un servidor.
Como pueden notar, estos eventos modifican la variable de estado, que es la cantidad de servidores.

# Conclusiones
Como final, en cuanto a conclusiones decidimos remarcar 3 puntos importantes sobre la simulacion que se pueden ver en pantalla.
pero la conclusion general es que logramos encontrar un intervalo o rango de valores donde existe el mejor balance entre costo y performance.
dado que como se pudo apreciar con los escenarios mostrados anteriormente, llega un punto critico llamemosle, donde reducir el promedio de espera en cola conlleva 
a un gran aumento de los costos por tan solo milisegundos en el tiempo de espera.
Donde se pudo ver en los 3 escenarios como hubo un aumento de casi 1000 dolares por 20 milisegundos de tiempo en espera.
Lo cual, nos da a entender, que a pesar de que podria mejorar mucho mas la performance..
