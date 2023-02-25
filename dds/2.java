//----------------------------------------------------#---------------------------------//
'SINGLE TABLE Inheritance':

te crea una tabla en la base de datos y te divide la tabla por tipos es decir,
aca te haria una sola tabla pero con una columna tipo en la que se dividen las materias
en dos tipos : MateriaObligatioria y MateriaHomogenea
aca hay consultas polimorficas, el query es mucho corto y rapido que en el de la joined 
en polimorficas es la mejor porque va a una sola tabla.
el problema aca es que pueden surgir muchos campos nulos en la tabla, aunque no es un problema
tan grave si no hay muchos campos nulos.

consulta polimorfica: Materia materia ? entityManager().createQuery("from Materia")
consutla no polimorfica: Materia materia ? entityManager().createQuery("from MateriaObligatoria")


@Entity
@Table(name = "materias")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn (name="tipoMateria", type=String)
class Materia{	

}

class MateriaObligatioria extends Materia{}
class MateriaHomogenea extends Materia{}

//----------------------------------------------------#---------------------------------//
	
'JOINED (tabla por clase)':
te crea una tabla por cada subclase que tiene la superclase, aca habria tres tablas
Materia, MateriaObligatioria y MateriaHomogenea
este metodo tiene sentido si me tengo que traer muchos campos, y hay pocos registros
entonces tengo que hacer pocos joins.. pero que pasa si en el intelij la consulta no es 
polimorfica, y quiero buscar por MateriaObligatioria por ejemplo... aca te conviene mucho
el joined, puesto la query es mas corta y es mas eficiente, porque no hace la combinatoria
contra todas las tablas. Es mucho mas eficiente esta tecnica en las consultas de subclases
osea, cuando queres consultar una materia en especifico.. es el 'comodin' entre SINGLE y 
TABLE PER CLASS. Aca los ids no son auto-generados.. pensa que la tabla materias cuando quiera
ir a buscar un ID, si estan repetidos en las subtablas no sabria cual materia agarrar.
OBS: el select que hace para una consulta polimorfica es mas eficiente que TABLE PER CLASS 
,pero no que SINGLE TABLE

@Entity
@Table(name = "materias")
@Inheritance(strategy = JOINED)
class Materia{

}

class MateriaObligatioria extends Materia{
..
}

class MateriaHomogenea extends Materia{
	..
}

//----------------------------------------------------#---------------------------------//
'TABLE PER CLASS (tabla por clase concreta)'
esta strategy tiene el problema de que todas las tablas que se generan , al agregar
un elemento , no podes usar @GeneratedValue porque tendrias dos elementos en dos tablas
distintas con el mismo id=1 (por ejemplo). esto se soluciona haciendo un 
@GeneratedValue(strategy=GenerationType.TABLE) , esto genera ids en una tabla externa 
@GenericGenerator(name = "uuid2", strategy = "uuid2")
'uui': universal unique identifier 
la peor estrategia para cuando queiro una consulta polimorfica, es la menos eficiente de todas
en el DER solamente figuran las tablas concretas

@Entity
@Table(name = "materias")
@Inheritance(strategy = TABLE_PER_CLASS)
class Materia{

	@Id // obs: no se le puede poner el @GeneratedValue
	@GeneratedValue(strategy= GenerationType.TABLE)
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private long ID;

}

class MateriaObligatioria extends Materia{
..
}

class MateriaHomogenea extends Materia{
	..
}

//----------------------------------------------------#---------------------------------//

IMPEDANCE MISMATCH = cosas en objeto java que no se pueden mapear, como por ejemplo 
el polimorfismo, ya que en la DB no existe el comportamiento
'obs2:' hay objetos que nunca se mapean, comos los repositorios, o todos los objetos 
que nos aporten servicios o funcionalidades

COMO MAPEAR
'interfaces(I) ->'
no se pueden mapear, lo que hay que hacer es pasarla a abstract class
o otra opcion es pasarla a ENUMS.
si no tienen estado ninguna de las 
clases que presentan esa interface:
-> se pasan a enums con comportamiento  
si tienen estado: 
-> no me queda otra que pasarla a clases abstractos.

'strategy STATELES (sin atributos)' -> pasarlo a enum
'statregy STATEFULL (con atributos)' -> pasarlo a herencia en codigo java 
asi mapearlo

'enums(E) ->'
se persisten como texto, se les agrega un @Enumerated arriba , para poder 
mapearlos y persistirlos en la db (obviamente en la clase que tiene un enum 
como atributo)

obs: NO TIENEN MAPEO, onda @OneToMany, solamente les va el @Enumerated

obs: NO SON EMBEDABBLES

obs: si tenes una clase que conoce una lista de enums, va @EllementCollection, no @Enumerated
que es algo muy parecido a un @OneToMany
esto tambien se usa para todas las listas de value objets: enums, strings, embeddables, booleans, etc 


/*
class Alumno{
						// podria ser .INT y guardo los numeros 1,2
	@Enumerated(value = EnumType.STRING) //guardo 'MENSUAL, SEMANAL'
	private Inscripcion Inscripcion;
}

//enums con comportamiento
public enum Inscripcion{
	MENSUAL, SEMANAL, ANUAL

	public String quienSoy(){
		switch(this){
			case MENSUAL:
				return 'Soy mensual';
			case SEMANAL:
				return 'Soy semanal';
			case ANUAL:
				return 'Soy anual';
		}
	}
}
*/

'lazy - eager evaluation':
lazy: perezosa
eager: ansiosa
cada mapeo tiene diferentes formas de pedir las cosas
el @ManyToOne -> por default es eager 
el @ManyToMany -> por default es lazy

para cambiar, es @ManyToMany(fetch = FetchType.LAZY )
esto lo que hace, es que el atributo que lo tiene puesto arriba 
cuando vos llamas a la Clase que tiene este atributo, tambien 
te va a traer la tabla que representa ese atributo, si fuera 
Lazy, la trae recien cuando la necesita

'joinColumn':
joinColumn(@JoinColumn)->
si no ponemos esto cuando hacemos u OneToMany hibernate nos va a construir 
una tabla ManyToMany, entonces se pone esto para poder referenciar el 
fk de la tabla	
	  ej ↓
	class ClaseDeNotas{
		JoinColumn(alumno_id)
		List<Nota> notas;
	}

'annotatations ->' @....To.....
pensa que siempre sabes hacia donde va(derecha), y si tenes un objeto y no una lista
es ToOne, entonces te falta el otro lado, que referencia al objeto en el que 
estas parado.. es importante fijarse mucho en el contexto.
		 
'embedded(@Enbedabble):'
@Embeddable ->
con esta anotación, indicamos que la clase puede ser «integrable» dentro de una 
entidad.
los embeddable NO LLEVAN ID, estan dentro de otra clase.
obs: no se pueden embebber herencias, si posicion tuviera sub-clases no se puede.

@Embedded -> 
con esta anotación, indicamos que el campo o la propiedad de una entidad es una instancia
de una clase que puede ser integrable. es decir, para que funcione, el campo que 
hayamos anotado como @Embedded, debe corresponderse con una clase que tenga la anotación @Embeddable.
resumido, todos los campos de la clase a la que le pongo esto arriba, van a estar 
dentro de la clase a la cual le pongo @Embeddable como atributo.
esto rompe el modelo de objetos, tenes que aclararlo en el parcial , romper=modificar
	'ej':
	class Auto{

		@Embedded //obs la posicion NO TIENE ID, los objetos enbebeidos no tienen ID
		Posicion posicion;
	}

	@Embeddable
	class Posicion{
		Long latitud;
		Long longitud;
	}

OBS: no caer al elegir que objeto embeber, siempre tenes que embember los VALUE OBJECT
no los entity, por ejemplo, no podrias embeber jugador en una cancha, porque jugador
es un ENTITY, nos interesa saber su identidad


'Set-List(List<> or Set<>) -> '
ojo aca, hay varias veces en el que tenes que elegir si usar una lista o 
un set.. aca la diferencia es que el SET no tiene orden y permite tambien
que no haya repetidos.. en cambio la lista es al revez, tiene orden y puede 
tener repetidos.. hay que fijarse en que me interesa guardar, por ejemplo en
el parcial de pasajeros que esperaban asientos, me interesaba guardar los 
pasajeros en una cola FIFO (lista) donde se respete el orden de llegada.

se les suele poner @OrderColumn para ordenarlas
ejemplo:
aca se agrega un campo poisicion, lo que hace es que cuando yo inserte 
una nota en la base de datos (que este dentro de un alumno) se va a 
insertar ademas la posicion en el que esta el alumno en la lista 'notas';
	class Alumno{

		@OrderColumn(name = 'posicion')
		@OneToMany
		List<Nota> notas;
	}

'consulta polimorfica':
	entityManager.createQuery("from materia")
				.getResultList()

'mapeo de herencia sin polimorfismo:':
heredamos la clase para tener los atributos como el ID y demas

	@MappedSuperClass
	public abstract class PersistentEntity{

		@Id
		@GeneratedValue
		private Long id;
	}

	heredamos todas las clases que tengan identidad de esta.


-------------------------------------------------'WEB'------------------------------
//-------------------RUTAS API -REST---------------------------------//
HTTP es 'Statelees': quiere decir que es una conexion al estilo pedido-respuesta
por lo tanto, el servidor no se va a acordar quien hizo la peticion.

CLIENTE HTTP     ---------> SERVIDOR 
(firefox,chrome) <--------- 
http: protocolo basado en texto, se envia informacion basada en texto

'peticiones'(request):
/* dame la informacion de /hello.htm */
GET: /hello.htm HTTP/1.1		
User-Agent: Mozilla / 4.0 	   {
Host: www.tutorialspoint/	   {
Accept-language: en-us		   { <-- son los headers, generalmente GET no tiene body}
Accept-Encoding: gzip, deflate {	 pero post, delete, put y demas si.
Connection: Keep-Alive		   {

'response':
200 OK
Date: Mon, 27 jul 2021, 11:45
Server: Apache/2.2.14
Connection: Closed 
<html><body>
<h1> Hello, world! </h1>
</body></html>

'RUTAS - COMO ARMARLAS?'
'Orientadas a procedimientos':usan queryParams para buscar las cosas, en la
url va todo lo que quiera hacer.

/comprar?idProducto=456&idZona=2

/buscarPorPrecio?maximo=100&minimo=10&incluirDescuntos=true

/buscarProducto?id=45

'Orientadas a recursos (REST)': lo que quiero hacer lo determina el verbo.
GET /productos/45
DELETE /productos/45

GET /productos 
GET /productos/45/ventasRecientes

obs: pensa que es mejor esta , si haces un GET  /prudctos/45 y te devuelve un 404 
sabes que el producto 45 no existe, en cambio en la orientada a procedimientos no sabes 
que paso.. no sabes si el producto no esta, o pusiste mal un queryParam o algo.

'API': es la aplicacion que yo expongo para que los clientes consulten mis cosas.
puede ser usada por un navegador, o una aplicacion.

'EJEMPLOS':
'quiero crear una prenda':
POST /prendas /* la prenda va en el body del post y con el verbo POST ya sabemos 
				que se esta creando una prenda nueva porque es para crear*/

'quiero ver una prenda en particular':
GET /prendas/34 
GET /prendas/:id
/* el :id referencia que vamos a pasarle un id para obtener una en particular */

'quiero ver todas las prendas de un guardarropa':
GET guardarropas/:id/prendas
GET guardaropas/1/prendas 
/* el numero referencia al parametro anterior, esta query dice 
'dame del guardaropa 1, todas las prendas' */

'quiero elimintar una prenda'
DELETE /prendas/:id

'quiero las sugerencias de prendas de un evento en particular':
GET /eventos/:id/sugerencias

'ver incidentes con un filtro de skipear los 20 y traerme los 10 primeros':
GET /users/:id/incidentes?skip=20&top10




-------------------------------------------------'ARQUITECTURA'------------------------------
user -> cliente(navegador(aplicacion js)) -> servidor (api rest) -> datos (MySQL)
sabes exactamente como funciona todo lo de arriba, pero ahora, donde alojas todo esto?
osea, que estrategias tenes para guardar todo esto

BARE METAL: significa 'hosteado por uno mismo': yo administro mis servidores fisicos, 
tipo yo compro las computadoras.. al ser self-hosted me tengo que hacer cargo de toda 
la arquitectura para el funcionamiento: (comprar licencias de todo y demas para que funcione 
todo en la red publica) asegurar todo esto es complejo, hay que hacer muchas cosas y tal vez 
en lo que queres inertir plata es en desarrollar el software, y no andar pagando servidores 
o ups para que ande todo.
- app
- datos
- runtime
- middleware
- os
- virtualizacion 
- red 
- almacenamiento
- servidores 


'CLOUD': servicio en la nube, es decir, le contratas los servicios estos en la nube a 
un tercero.. y podes acceder de forma remota.
aca nos despreocupamos de la parte de refrigeracion, servidores, electricidad y demas
hay varias categorias de cloud, a continuacion:

IaaS: 'Infrastructure as a Service': los terceros te dan la infraestructura, es decir 
te resuelven los siguientes marcados en 'verde'
- app
- datos
- runtime
- middleware
- os
- 'virtualizacion '
- 'red'
- 'almacenamiento'
- 'servidores' 
las ventajas que tiene es que te despreocupas de varias infraestructura fisicas, de 
encargarte de armar todo esto que tal vez no queres invertir dinero en esto.
otra ventaja es que de forma rapida podes acceder al hardware, puesto que generalmente 
te lo habilitan rapido.
y las desventajas es que basicamente vos no lo administras totalmente y otra mas 
es que si tal vez queres tener los datos guardados solamente para vos, AWS(amazon web services)
tendria datos que no deberia tener..
aunque desventajas para el uso comun no hay demasiadas, si tal vez depender de un tercero 
para el host lo cual hace que estes atado a esa plataforma, y estar muy acoplado a una plataforma 
es un problema si queres cambiar por X motivos.

PaaS: 'Plataform as a Service': lo que compro aca, ademas de la infraestructura, sino 
tambien la paltaforma en la que va a correr una aplicacion, por ejemplo comprar algo apra 
poder correr aplicaciones java, entonces ellos te generan una maquina que puede correr cosas 
de ese lenguaje entonces, vos le podes pasar un pom.xml en el que se levante la aplicacion 
o un comando a ejecutar.
lo unico que tenes que asegurarte, es que si bajas el repo del software, va a correr en una 
maquina java.
HEROKU por ejemplo es usado para esto, sabe como levantar toda la infraestructura y configurar 
todo para que funcione en esa maquina, es como que nos da un nivel mas de abstraccion.
- app
- datos
- 'runtime'
- 'middleware'
- 'os'
- 'virtualizacion '
- 'red'
- 'almacenamiento'
- 'servidores' 
las ventajas de esto es que heroku hace todo por ejemplo, es como pulear un repo y darle a 
correr, te despreocupas de varias cosas.. es mas rapido a deployar que un IaaS 
desventajas yo no tengo control de como se levanto la vm, cada cuando se restartea, no puedo 
asegurar el sistema operativo que este corriendo.. porque no soy mas responsable.
a mayor nivel de abstraccion, en general va aumentando el precio de las cosas, obviamente 
paaS es mas caro que un IaaS , obviamente

SaaS: 'Software as a Service': aca no nos encargamos de nada, sino solo de usar el software 
ejemplo Dropbox, que lo usas para subir archivos y te despreocupas de todo, hasta del 
almacenamiento.
un sistema de envio de mails tambien, que envie mails a muchas personas.. hacerlo vos solo 
es un quilombo y hay que hacer varias cosas para que no te agarre como spam.. entonces a 
veces es mas facil contratar un servicio de mailing que envie mail.
y el ejemplo mas claro, Gesoc, que es un software para administrar la administracion de compras 
y ventas de una empresa.. entras , te haces una cuenta y lo usas.
otro ejemplo Auth0 que se encarga de toda la parte de organizacion de un sitio.
Gesoc es un SaaS que se va a deployar en un IaaS, como AWS.
- 'app'
- 'datos'
- 'runtime'
- 'middleware'
- 'os'
- 'virtualizacion '
- 'red'
- 'almacenamiento'
- 'servidores' 

//-------------------------------------ESCALAS -------------------------------////

'escala horizontal y vertical :'
<img src="https://youtu.be/HpXeReqyicM?list=PL7cuUUqxhfsNt7ycizHgksigXDesa_IGl&t=7641
