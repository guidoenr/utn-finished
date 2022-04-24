'________________________________________________________________________________________________'
'INTRODUCCION - BASE DE DATOS'
'________________________________________________________________________________________________'

'BASE DE DATOS': Edgar Codd define base de
 datos utilizando estos 3 conceptos.
(estructura- Manipulacion de datos - integridad) # el resto son sub-definiciones 
'Estructura': recopilacion de objetos o relaciones.
segun edgar, una relacion es una estructura bidimensional
 (una matriz) que tiene 
una cabecera, con un conjunto de nombres de atributos
 y un cuerpo.
en estecuerpo, hay varias filas con la instanciacion
 de los atributos de la cabecera,
estos atributos representan la relacion.
el grado de una relacion representa cuantos atributos 
hay, si hay 4 columnas, grado 4
y la cardinalidad representa la cantidad de filas
 o isntanciaciones de esos atributos
la relacion segun codd era esto, distinta la relacion en DER.
para codd , la relacion era la entidad.

'Relacion': subconjunto del producto cartesiano de los dominios de valores 
involucrados.
sobre la estructura tambien entra el concepto de dominio, explicado mas abajo.
propiedades de la relacion:
	- no hay filas/tuplas repetidas -> toda relacion tiene una clave primaria
	- las filas/tuplas no estan ordenadas
	- los atributos no estan ordenados
	- los valores de los atributos son atomicos -> la relacion esta normalizada
'Manipulacion de datos': operaciones sobre las relaciones para producir otras

'Integridad': para obtener precicsion y consistencia.
existen dos tipos de claves: que nos permiten definir las reglas de integridad
	-clave primaria 
	---------------------> definen reglas de integridad
	-clave foranea
si yo digo que un alumno curso 10 materias y tengo esas 10 materias con sus notas
si borro el alumno, quedan esas materias huerfanas con notas de no se quien.
estos son reglas basicas de integridad, que los motores de base de datos las hacen cumplir.
	•'clave primaria': por ejemplo en un alumno, el Nro de Legajo. es elegida segun el contexto
					   para ver cual puede distinguirlo mas, las claves candidatas (como podrian
					   ser dni, cuil, etc) al no ser elegidas se las llaman claves alternas.
	•'clave foraanea': es la clave que permite relacionar entidades, por ejemplo alumnos
					   con una cursada.. la clave foreanea contiene un valor en la cual 
					   tiene que existir ese valor como clave primaria de otra tabla.
					   ej: una cursada tiene una FK cod_materia, que en la tabla 
					   materia, el cod_materia es su PK.
					   no debe ser nula, la FK y la PK estan definidas en el mismo dominio.
					   obs: si la relacion es auto-referencial.. como customer que tiene un
					   atributo 'customer_num_refered_By' ahi si puede aceptar nulos.

'DOMINIO': el dominio es la cantidad de valores posibles 
que puede tomar un atributo,
segun Edgar Codd.
• conceptualmente es la menor unidad semantica de informacion
• son atomicos (no se pueden descomponer sin perder significado) # ej: la ciudad Buenos Aires
# no se puede descomponer sin perder su sentido, no podrias hacer una ciudad 'Buenos' y otra
# que se llama 'Aires'
• conjunto de valores escalares de igual tipo (no puede haber un array)
• no pueden contener nulos. # un nulo describre un atributo con ausencia de valor
el dominio es algo clave, pero los motores de base datos todavia tienen algunas vueltas 
para implementarlo porque es algo muy conceptual y semantico

'REGLAS DE INTEGRIDAD':
#esta relacionada con la CLAVE PRIMARIA- A.K.A-> primary key
'1': 'Regla de integridad de las entidades': ningun componente(atributo) de la PK de una 
relacion base puede aceptar nulos. si la clave primaria identifica univocamente a una tupla 
de la relacion, yo no puedo permitir que los atributos que tiene esa PK sean nulos, porque 
sino no estaria definiendo nada.. si PK es lo que identifica, no puedo admitir que un alumno
tenga un nro de legajo nulo.

'2': 'Regla de integridad referencial': la base de datos, no debe contener valores no nulos
de clave foranea para los cuales no exista un valor concordante de clave primaria en la 
relacion referenciada.
es decir, si yo tengo un FK que apunta a una PK, la FK si no tiene nulos el valor que tiene 
tiene que existir en la FK.
dada una FK determinada, si esa FK tiene valor.. es valor tiene que existir en la tabla donde tengo la PK.
si yo tengo una cursada y tengo un nro de legajo de un alumno, ese numero de legajo debe 
existir en la tabla de alumnos.


'INDEPENDENCIA DE DATOS'
'• independencia logica': si ustedes estan consultando ciertos datos de una entidad, el orden 
de los datos no necesariamente tiene que ser el que consultamos, es decir, lo consultamos 
el orden en el que queramos. SELECT apellido, fecha, nombre ...
'• independencia fisica': es posible modificar la estructura fisica de las tablas , las tecnicas
de acceso, la forma de acceder a los datos , todo sin modificar la aplicacion.


'REGLAS DE CODD': hace tiempo, empresas sacaban bases de datos y desconocian sobre si era 
relacional o no relacional.. entonces Edgar Codd saca un paper "is your DBMS really relational"?
explicando todo.
Codd plantea una serie de reglas que se llaman las 'reglas de codd' en las cuales planteaba
las caracteristicas que tiene que tener un motor para considerarse relacional
• independencia entre el motor de base de datos y los programas que acceden a los datos
(es posible modificar el motor de base de datos o los componentes de aplicacion en forma 
independiente)
• representar la informacion de la estructura de las tablas y sus relaciones mediante 
un catalogo dinamico basado en el modelo relacional. la metadata de las tablas, es decir
la estructura y las relacioens de las tablas tiene que estar en un catalogo adentro de 
la misma base de datos.
• los datos y los metadatos tiene que estar en tablas dentro del modelo de base de datos,
• las reglas de integridad deben guardarse en el catalogo de la base, no en programas de 
aplicacion. 'ej': 'borra un alumno que tiene cursadas relacionadas', el motor de base de datos
tiene que asegurar que eso no se puede hacer.
• soportar informacion faltante mediante valores nulos
• proveer un lenguaje de programacion para 
	- definir los datos
	- manipular los datos, donde debe haber operaciones de alto nivel para insertar, eliminar
	  ,actualizar o buscar (INSERT ,DELETE, UPDATE, SELECT, -- SQL)
    - definicion de restricciones de seugridad, de integridad , de autorizacion y delimitar
      una transaccion

'________________________________________________________________________________________________'
'MODELO/diseño de datos - normalizacion'
'________________________________________________________________________________________________'

obs: aca varios conceptos conceptuales de Codd cambian para poder ser aplicados realmente 
al lenguaje SQL 

'RELACIONES': aca se denomina entidad
las relaciones representan y son asociaciones entre las entidades, de alguna maneras 
estas entidades estan representadas con tablas y se relacionan entre otras.
generalmente se conectan con un verbo 
ej: empresa -> 'realiza' venta
ej: empleado -> 'trabaja' empresa 
ej: alumno -> 'cursa' materia

el modelo de datos se puede comunicar mediante un DER o 
codigo SQL - DDL Data Definition Language(metadata)

'NORMALIZACION': normalizar una base de datos significa , transformar el conjunto de datos
que tiene la base de datos para poder manipularlos de una manera mas consistente y evitar 
la repeticion de datos.
El objetivo principal de la normalizacion es "reducir la redundancia de datos "(minimizar 
la repeticion de datos por lo tanto minimizo inconsistencias)
tambien permite facilitar el mantenimiento de datos, puesto que si quiero modificar algo
lo modifico en un solo lugar.
lo que permite tambien la normalizacion es evitar anomalias en la manipulacion de datos
y una amplia reduccion del impacto de los cambios en los datos.
existen varias formas normales, 1FN- 2FN - ... - 5FN
cada forma normal permite reducir las redudancias nombradas arriba, y cada una de ellas 
introduce restricciones nuevas, donde la primera restriccion que aplica es cumplir la 
forma normal anterior.

'TABLA': - entidad 
es la unidad basica de almacenamiento de datos. Los datos se almacenan en filas y columnas,
son de existencia permanente y poseen un nombre indetificario unico por esquema. ej:cliente

"TABLA TEMPORAL":
Son tablas creadas cuyos datos son de existencia temporal. No son registradas en
las tablas del diccionario de datos. No es posible alterar tablas temporarias. Si
eliminarlas y crear los índices temporales que necesite una aplicación. Las
actualizaciones a una tabla temporal podrían no generar ningún log transaccional si
así se configurara.

'CONSTRAINTS': 
tipos de contraints referenciales

'Ciclic referential Constraint': asegura una relacion de padre-hijo entre tablas. Es el mas
comun. ej : CLIENTE -> FACTURAS

'Self referencing constraint': asegura una relacion de padre -hijo entre la misma tabla
ej: empelados -> empleados 

'multiple path constraint': se refiere a una primary key que tiene multiples foreign keys. 
este caso tambien es muy comun
clientes -> facturas 
clientes -> reclamos 

'integridad semantica': la integridad semantica es la que nos asegura que los datos que 
vamos a almacenar tengan una apropiada configuracion y que respeten las restricciones definidas 
sobre los dominios o los atributos
- DATA TYPE: define el tipo de valor que se puede almacenar en la columna
- DEFAULT: valor insertado en una columna cuando al insertar un registro ningun valor 
fue especificado para dicha columna, el valor default x default es el null 
- UNIQUE: especifica sobre una o mas columnas que la insercion o actualizacion de una fila 
contiene un valor unico en esa columna o conjunto de columnas 
- NOT NULL: asegura que esa columna contenga un valor no nulo ante un insert o un update 
- CHECK


'________________________________________________________________________________________________'
'VISTAS - SNAPSHOTS - INNER JOIN - OUTER JOIN - TRANSACTONS'
'________________________________________________________________________________________________'
----------------------------------------------------------------------------------------
#vista: 1 carrilla
'VISTA': una vista es un conjunto de columnas, reales o virtuales de una misma tabla o no
con algun filtro determinado o no.
de esta forma, "es una presentacion adaptada de los datos contenidos en una o mas tablas".
una vista toma la salida resultante de una consulta y la trata como una tabla, se pueden
usar vistas en la mayoria de las situaciones que se pueden usar tablas
es como un 'SELECT' al que el pongo un nombre.
en realidad, la vista no existe.. 
simplemente es un select con un nombre.
• tiene un nombre especifico
• no aloca espacio de almacenamiento (lo unico q aloja es la metadata)
• no contiene datos almacenados
• esta definida por una consulta que consulta datos de una/varias tablas

che bueno.. pero para que sirve entonces?
una vista sirve para:

1. suministrar un nivel adicional de seguridad restringiendo el acceso a un conjunto
   predeterminado de filas o columnas de una tabla.. puedo otorgarle el acceso a un 
   usuario a la vista, sin que tenga acceso a las tablas reales.. y no saben realmente
   que es lo que estan consultado. es una especie de HoneyPot.
2. oculta la complejidad de los datos
3. simplifica la sentencias al usuario, porque por ejemplo en una vista podes estar calculando
   algo que el usuario nunca va a ver, o no tendria que tirar en el select
4. presenta los datos desde una perspectiva diferente
5. aisla a las aplicaciones de los cambios en la tabla base

las vistas tienen varias restricciones, que dependen segun el motor de la DB, algunas de 
ellas son :

- no se pueden crear indices en las vistas
- una vista depende de las tablas a las que se haga referencia en ella, si se elimina una 
  tabla, todas las views que dependen de ella se borraran o se pasara a estado invalido, 
  dependiendo del motor. lo mismo para el caso de borrar una view de la cual depende de otra
- algunas tiene restringidos los inserts, deletes, updates  
	cuando:
		-> tienen joins
		-> tienen una funcion agregada 
		-> trigger instead of 
- al crear la vista el usuario tiene que tener permisos de select a las columnas de las
  tablas a las cuales le va a pegar 
- no es posible un order by y un union (depende del motor igual )
- si en la tabla existen campos que no permiten nulos y en la vista no aparecen, los inserts fallan
- si en la view no aparece la PK los inserts podrian fallar
- se puede borrar filas desde una view que tenga una columna virtual 
- con la opcion WITH CHECK OPTION , se puede actualizar siempre y cuando el checkeo de la 
  opcion en el where sea verdadero.

la vista tiene un pequeño ruido en terminos de integridad, puesto que podrias insertar datos 
a una tabla original a travez de tu vista.. cuyos datos la vista no puede mostrar
ej: una vista muestra los clientes de california, pero vos podes insertar clientes de new york
para que esto no pase, existe una clausula 'WITH CHECK OPTION' que realiza un chequeo de integridad 
de los datos a insertar o modificar, los cuales deben cumplir con las condiciones del WHERE 
de la vista.
#obs: el WITH CHECK OPTION se agrega al final de la vista.

----------------------------------------------------------------------------------------
'SNAPSHOTS/MATERIALIZED VIEWS/SUMMARIZED TABLES': es una vista, pero que tiene datos.
es una vista que en el momento de crearla, como que le saca una foto a las tablas.
son objetos del esquema de una DB que pueden ser usados para sumarizar, preocumputar, 
ditribuir o replicar datos. Se utiliza sobre todo en DataWareohuse, para sistemas de 
soporte de toma de decision y para computacion movil y/o distribuida.
una diferencia es que estas "si consumen espacio de almacenamiento en disco."
deben ser recalculadas o refrescadas cuando los datos de las tablas master cambian.
pueden ser refrescadas en forma manual o a intervalos de tiempo definidos dependiendo el DBMS

#integridad vs consistencia
'INTEGRIDAD VS CONSISTENCIA'
'TRANSACTIONS': los motores de db tienen varios mecanismos para asegurarse la consistencia 
de los datos.. en realidad, consistencia es un concepto muy parecido al de integridad, 
una busqueda en google nos diria que son lo mismo.. pero aca se puede hacer una divison 
, porque la integridad al nivel del mundo relacional esta definida por codd por dos reglas 
regla integridad entidades y regla integridad referencial pero la consistencia la podemos 
plantear como que los datos de nuestra base de datos tienen que estar correctos en funcion 
a un determinado caso de negocio, que no tiene nada que ver con la integridad tradicional.
ej: si grabo en el sistema una ticket (cabecera, nroticket, cliente, hora, consumidor final,
el detalle: caramelo, bombon etc, etc) cuando esto se graba, quiero que se grabe una fila 
por cada cosa : cabecera, nro ticket, cliente, y demas datos/operaciones como calcular el stock.
todos estos movimientos se tienen que hacer como una unidad atomica de ejecucion, es decir, 
se tienen que ejecutar juntos.. si el sistema no graba todo eso, el dato me queda inconsistente
si tu ticket tiene 10 renglones , en el detalle tiene que haber 10 movimientos de stock digamos
esto, es consistencia.
con este ejemplo podemos ver que la integridad, esta mas atado a las reglas de integridad de Edgar Codd 
y consistencia se ata mas al negocio porque la base de datos por si sola puede asegurar la integridad de 
las datos, gracias a las reglas de integridad que yo creo.. como los constraints , que se chequean todo
el tiempo. ahora la consistencia, no depende de las reglas de integridad.
la transaccion, es el concepto mas importante que hace referencia a la consistencia de los datos 
ejemplo en el BEGINS TRANS , COMMIT , ENDT RANS

una transaction es un conjunto de sentencias SQL que se ejecutan atomicamente en una unidad 
logica de trabajo. Partiendo de que una transaccion lleva la base de datos de un estado correcto 
a otro estado correcto, el motor posee mecanismos de manera de garantizar que la operacion 
completa se ejecute o falle, no permitiendo que queden datos inconsistentes.
cada sentencia de alteracion de datos como insert, update o delete es una transaccion en si 
misma que es llamada singleton transaction.

#ACA SE ASUME Q LA BASE DE DATOS ESTA CONSISTENTE
BEGIN TRANS;
	insert 123
	update abcd 
	insert 321 
committ;
#ACA SE ASUME Q LA BASE DE DATOS ESTA CONSISTENTE

'logs transaccionales': registro donde el motor almacena la informacion de cada operacion
llevada a cabo con los datos 
'recovery': metodo de recuperacion antre caidas

ejemplo de transaction para mantener la consistencia

BEGIN transaction
	insert 1 cliente
	insert 1 fila cabecera de orders
	-------------> si falla, automatic rollback
	insert 1 producto
	insert 2 items
	CONTROL STOCK -----------> STOCK NEGATIVO -> ROLLBACK MANUAL
	COMMIT TRANSACTION

ejemplo2:
CREATE TABLE #numeros
BEGIN TRANSACTION
INSERT INTO #NUMEROS values(2)
SAVE TRAN N2 -- guardo la transaction actual
	BEGIN TRANSACTION 
		INSERT INTO #numeros values(3)
	ROLLBACK TRANSACTRION N2 -- DESHAGO LA TRANSACTION ACTUAL HASTA N2
INSDERT INTO #NUMEROS values(4)
COMMIT TRANS

'FOCO DE EJECUCION DE TRANSACTIONS'
'• A': Atomicidad -> se ejecuta completa o no se ejecuta
'• C': Consistencia -> la base de datos parte de un estado consistente y cuando termine, tiene que quedar consistente
'• I': Isolation (aislamiento) -> las transactions por separado no se pueden chocar, *niveles de aislamiento
'• D': Durabilidad -> cuando yo hago un commit, el dato dura eternamente hasta que yo le diga DELETE


'________________________________________________________________________________________________'
'INDICES' https://www.youtube.com/watch?v=MCTYlMHDkIk&list=PLe5sv7dROOZ1alqLI7UHzkfEzS9DAoQ-E&index=9
'________________________________________________________________________________________________'
#1 carilla
los indices son estructuras opcionales asociadas a una tabla.
la funcion de los indices es la de permitir un acceso mas rapido a los datos de una tabla, 
se pueden crear distintos tipos de indices sobre uno o mas campos.
los indices son logica y fisicamente independientes de los datos en la tabla asociada.
se puede crear o borrar un indice en cualquier momento sin afectar a las tablas base 
o a otros indices.
desde el punto de vista de la integridad, los indices permiten asegurar la unicidad de los 
datos.
tipos de indices hay varios
el indice se guarda en el diccionaro de datos, que es un diccionario que tiene la base de datos 
para guardar datos propios de la base. 
'Btree Index': estructura de indice estandar y mas utilizada, es independiente de la tabla
			   y el indice esta ordenado pero la tabla no.
			   la forma de buscar es parecida a como lo viste en operativos, donde tenes 
			   varias tablas y partis de un numero por ejemplo, y vas buscando como dividiendo
			   segun si el numero es menor o mayor y cuando llegas al ultimo bloque, buscas 
			   secuencialmente hasta encontrarlo.
			   esto asegura que cualquier busqueda que hagas , van a tardar todas lo mismo
			   los motores usan un Btree +(plus), donde los bloques estan conectados como 
			   si fueran hermanos, para evitar volver para el bloque anterior.
'Btree cluster index': es un tipo de indice especial , donde solo puede haber 1 por tabla 
				       cuando uno lo crea la tabla se ordena igual que el indice y queda 
				       ordenada igual que el indice.
'Bitmap index': es un indice de Oracle que esta pensado para almacenar pocas claves con muchas 
				repeticiones, por ej un indice sobre estado civil. este indice arma un Bitmap
				binario, cada bit en el bitmap corresponde a una fila, si el bit esta ON significa
				que la fila con el correspondiente rowid tiene el valor de la clave
'Hash index': este indice esta implementado en tablas de hash y se basan en otros indices 
  			  Btree existentes para una tabla.
'Functional Index': indices cuya clave deriva del resultado de una funcion 
'Reverse key index': invierte los bytes de la clave a indexar, esto sirve para los indices cuyas 
					 claves son una serie constante, por ejemplo un crecimiento ascendete.

'CARACTERISTICAS DIFERENCIADORAS PARA LOS INDICES':
'unique': indice de clave unica, solo admite una fila por clave.. esto son usados por ejemplo 
          en las primary key, porque nos asegura unicidad 	
'duplicado': permite duplicar filas
'simple': la clave tiene una sola columna 
'compuesto': la clave compone de varias columnas, apellido, nombre y sexo por ej


'BENEFICIOS DEL USO DE INDICES'
el beneficio principal es la mejora de la performance, le da una mejora en acceso a los datos
que tiene una complejidad algoritmica log(n) , donde n es la cantidad de claves que tiene un nodo
tambien asegura un mejor ordenamiento de las filas, osea la performance en el order by
tambien asegura valores unicos, por ejemplo para buscar la primary key 
ademas, cuando las columnas que interviene en un JOIN tienen indices se le da mejor performance 
si el sistema logra recuperar los datos a traves de ellas 
y por ultimo, asegura el cumplimiento de constraints y reglas de negocio, es decir, asegura
la integridad referencial por un tema de velocidad en la busqueda

'DESVENTAJA DE LOS INDICES':
el espacio ocupado y el procesamiento: el indice ocupa espacio en disco y si hay una tabla que ocupa 100mb
generado por campos que pesan X bytes , ahora si queres crear un indice por clave primaria 
y el indice ocupa 4bytes por ejemplo, vas a tener mucho mas espacio ocupado de indices 
que de datos y ademas, vas a tener un costo esta en lo que es procesamiento.

'cuando se deberia indexar?':
- indexar columnas que intervienen en joins 
- indexar las columnas donde frecuentemente se realizan filtros 
- indexar columnas que son frecuentemente usadas en orders by 
- evitar duplicacion de indices sobre todo en columnas con pocos valores distintos, ej 'sexo'
- verificar que el tamaño de indice deberia ser pequeño comparado con la fila 
  - tratar sobre todo en crear indices sobre columnas cuya longitud de atributo sea pequeña 
  - no crear indices sobre tablas con poca cantidad de filas
- tratar de usar indices compuestos para incrementar los valores unicos, que quiere decir esto?
  que no se podria buscar por indice de ´SEXO´ por ejemplo, porque si tenes 2 millones de personas 
  va a ser un 0.5 para ambos.. lo que si se podria hacer , es usar indices compuestos pero 
  con mas columnas

'construccion de indices en paralelo': los motores los hacen en paralelo, agarran los datos
de la tabla, los ordenan, los dividen en pedazos y levantan hilos para juntarlos y hacer el arbol 
completo.

indice unico simple : CREATE UNIQUE INDEX ix1_ordenes ON ordenes(n_orden);
indice duplicado y compuesto : CREATE INDEX ix2_ordenes ON ordenes(n_cliente, f_orden);
indice clustered : CREATE CLUSTERED INDEX ix3_ordenes ON ordenes(n_orden);


'________________________________________________________________________________________________'
'SUBQUERYS' https://youtu.be/MCTYlMHDkIk?list=PLe5sv7dROOZ1alqLI7UHzkfEzS9DAoQ-E&t=2000
'________________________________________________________________________________________________'

ejemplo de uso de subquery para traernos otra tabla 
DELETE FROM customer 
WHERE customer_num NOT IN (select distinct customer_num from cust_calls)
AND customer_num NOT IN (select distinct customer_num from orders)
AND custoemr_num NOT IN (select distinct customer_num_refered_By FROM customer c2
                         where customer_num_refered_By is NOT NULL)

'________________________________________________________________________________________________'
'ANSY / SPARC' https://www.youtube.com/watch?v=Bs-Ywj5XnSk&list=PLe5sv7dROOZ1alqLI7UHzkfEzS9DAoQ-E&index=18
'________________________________________________________________________________________________'

'ANSY / SPARC': es una arquitectura teorica para ver la base de datos de otra manera, en la 
cual vemos la base de datos dividida en 3 niveles, este modelo se ajusta bastante bien a la 
mayoria de los sitemas de DB.
cuenta con 3 niveles
• Nivel Externo: es un nivel de vistas, que es a lo cuales acceden los usuarios y es la 
                 percepcion que un usuario tiene en una base de datos. Un select devuelve 
                 un data set que este resulset es la vista que vemos acerca de esos datos.
                 es un nivel de vistas individuales, donde cada usuario tiene una percepcion 
                 de la base de datos diferente.
• Nivel Conceptual : tambien llamado intermedio, es el nivel en el cual se tiene la vista 
                     comun de la base de datos, en general el usuario que accede a este nivel 
                     es un DBA , un developer, un analista y es en definitiva donde se define 
                     el DDL (data definition language).
                     aca se ve que la base de datos tiene tales tablas, los indices, las columnas 
                     es decir, vemos la estructura de la base de datos
• Nivel Interno: aca se tiene una percepcion de los datos de forma fisica, ej separados en 
                 paginas de 4k, se ven los bloques de indices para el acceso, es decir, 
                 se define como se almacenan los datos en disco, es como si fuera el "bajo nivel"


cada nivel tiene una capa de transformacion que simplemente lo que hace es transformar 
los datos de los distintos niveles para poder tener una percepcion diferente.

'FUNCIONES DEL MOTOR DE BASE DE DATOS Y DEL DBMS':
1.• administracion del diccionario de datos: el motor tiene un diccionario de datos que 
internamente lo guarda en tablas en el cual tenemos la definicion de todos los objetos 
que se creen, modifiquen o borren de la base de datos.
un CREATE TABLE se guarda en un diciconario de datos internos a la base de datos, estos 
'metadatos' estan guardados en la misma base de datos en tablas.

2.• control de seguridad: la seguridad involucra la autenticacion, asegurar que el usuario
existe y su clave es correcta permitiendo el acceso a la DB y la autorizacion determinar 
los permisos que tiene el usuario para ejecutar acciones en diferentes objetos de la DB.
los DataBase Management System (DBMS) poseen lo que se llama el catalogo, que el mismo 
esta formado por entidades e interrelaciones (en una esquema relacional son tablas)
el catalogo tiene informacion de usuarios, de roles y perfiles, entre otras cosas.
un rol o un perfil no es mas ni menos que una categoria de usuario, donde yo digo 
este rol tiene determinados permisos y los usuarios que pertenecen al rol, heredan sus permisos.
a su vez, el catalogo guarda las acciones disponibles a realizar sobre los objetos 
por ej, un usuario en el rol marketing solo puede consultar la tabla de ventas.
'sentencias sql relacionadas con la seguridad':
	= GRANT - para otorgar permisos 
	= REVOKE - para revocar permisos previamente otorgados, por default no tenes ningun permiso 
'objetos relacionados con la seguridad':
• vistas : porque es el honeypot, no se ve el query asociado a la vista, sino todo lo que tiene 
• trigger : se podria implementar un trigger en el que cuando se inserte en una tabla , pregunte 
            si se cumple algun permiso para realizar otra accion 
• stored procedures: lo mismo que con triggers 
• sinonimos 
• funciones 

3.• mecanismos para garantizar la integridad de datos: el DBMS cuenta con mecanismos 
para controlar la integridad de los datos en la base de datos. 
aca tambien esta bueno hacer la diferencia de integridad vs consistencia, donde para edgar codd 
las reglas de integridad son unicamente 3 (integridad entidades, integridad referencial, integridad semantica)
un dato integro segun codd es un objeto que cumple con estas 3

algunos de estos mecanismos o objetos son:
. constraints 
	- primary key : atributo o conjunto de atributos que definian univocamente a cada fila de la tabla
	                de la tabla y no podian ser nulos y debian ser unicos. esta intimamente ligada 
	                con la regla de entidad de Codd
	- foreign key : una clave foranea no nula , tiene uqe tener un valor en la clave primaria 
	                que referencia.	               
	- unique, not null, check , default , primary key, foreign key 
. triggers : -> estoy cargando una factura para un cliente de cordoba y no tengo en mi tabla 
				entonces hago un trigger que dispara una consulta a la base de datos de cordoba 
				y se fija si ese cliente existe, aca el trigger nos garantiza la integridad
. indices (unicos) :-> cuando se crea una clave primaria o un UNIQUE el motor nos crea un indice 
                       unico, porque es la forma mas rapida de chequear si el dato existe o no
. views (with check option) :-> de alguna manera, el check option me asegura cierto nivel de integridad
								sobre la vista, no permitiendome cargar datos que la vista no puede 
								mostrar. (#WHERE CLIENTE.STATE = 'CA')
. stored procedures:-> podria hacer un chequeo con un stored procedure que le una tabla ordenes 
					   donde no hay customers nums
. funciones


'________________________________________________________________________________________________'
'BACKUP- RESTORE ' https://youtu.be/Bs-Ywj5XnSk?list=PLe5sv7dROOZ1alqLI7UHzkfEzS9DAoQ-E&t=1961
'________________________________________________________________________________________________'

'________________________________________________________________________________________________'
'CONTROL DE CONCURRENCIA  ' https://youtu.be/Bs-Ywj5XnSk?list=PLe5sv7dROOZ1alqLI7UHzkfEzS9DAoQ-E&t=2502
'________________________________________________________________________________________________'
'CONTROL DE CONCURRENCIA': la concurrencia es otra funcionalidad que tienen los motores DB
los motores DB hacen que se cumplan con dos cosas 
1. LOQUEOS: un lock es una llave/candado a un recurso (parecido al lock de files )
•granularidad de loqueos 
	-nivel de base de datos
	-nivel de tabla
	-nivel de pagina 
	-nivel de fila 
	-nivel de clave de indice 
•tipos de loqueos 
	-compartido(shared)
	-exclusivo (exclusive)
	-promovible (promotable - update)

2. NIVELES DE AISLAMIENTO: definen que las transacciones por separado no se pueden chocar.

"Read uncommitted"
No chequea locks por ​ select ​ en la tablas a consultar, lo que mejora el rendimiento
pero afecta la integridad en cuanto a que existen lecturas sucias (datos actualizados
en una transacción que luego se deshacen por un rollback) y no existen lecturas
repetibles (en la misma transacción poder hacer dos veces la misma consulta
asegurando siempre el mismo resultado).
Pueden existir lecturas sucias, lecturas repetibles, y lecturas fantasmas (Filas que
aparecen durante la transacción que fueron insertadas en otra transacción
concurrente).

"Read committed"
El read committed asegura que sólo leerá datos confirmados por otra transacción, o
sea realizará lecturas sucias.
Pero si bien el read commit ante una lectura de datos chequea la existencia de locks
exclusivos en la tabla a consultar, no asegura lecturas repetibles. Ya que una vez
que leyó los datos, los datos podrían ser bloqueados o modificados por otra
transacción concurrente.
Lecturas no repetibles: en una misma transacción durante su transcurso puede
tener dos llamadas a un mismo select, las cuales arrojaron resultados distintos.

"Repeatable read"
Las lecturas repetibles aseguran que no existan lecturas sucias y que las lecturas
pueden ser repetibles, pero no evitan las lecturas fantasmas. Este nivel de
aislamiento establece locks en los selects para todos los registros consultados,
durante la duración de la transacción. Cuando otra transacción intenta modificar o
borrar algún registro que está bloqueado por este select, quedará en espera.
Las lecturas fantasmas podrán aparecer, ya que la inserción de nuevos registros por
una segunda transacción no asegurará el bloqueo de los mismos hasta que no sean
nuevamente consultados por la transacción original.

"Serializable read"
Es el único que asegura que no existan lecturas sucias, lecturas fantasmas y que
las lecturas puedan ser repetibles. Se realizará un bloqueo de un rango de índice,
según lo que se coloque en el where, y si no es posible bloqueará toda la tabla.
El problema es que se aplicará un nivel de bloqueo que puede afectar a los demás
usuarios en los sistemas multiusuario.