// implemento la clase PersistentObject para poder heredar
// de las demas entidades que se van a persistir
@MappedSuperClass
class PersistentObject{
    @Id @GeneratedValue
    Long id;
}

/* la interfaz TipoPersonaje es stateless, dado que
sus hijos no tienen ningun atributo, por lo tanto
opto por convertir esta inferfaz a un Enum con comportamiento */

public enum TipoPersonaje{
    HECHICHERO, COMERCIANTE, GUERRERO
    /* methods de c/u 
    comerciar(), pelear(), buscarItem()*/
}

/* la interfaz Item es statefull, dado que sus hijos
tienen atributos, por lo tanto no me queda otra
que pasarla a clase abstracta y elegir un mapeo de herencia.
al ver que el enunciado habla mucho de un "Item" en si
(como por ejemplo el inventario del personaje),
pero se ve que el Personaje conoce directamente a un Arma
(lo cual va a ser una consulta no-polimorfica), opto
por usar JOINED para asi tener los items bien distinguidos
a pesar de que pareciera haber mas consultas polimorficas
que no-polimorficas, voy a optar por JOINED, ademas,
hay bastantes clases y diferencias de atributos entre
las mismas lo cual me podria generar considerables
campos en nulos (aunque esto no es un problema de performance)
pero con Joined es un problema menos.
pense en usar SINGLE_TABLE al principio por lo mencionado primero de
muchas consultas polimorficas e iba a preferir la performance..
pero al ver la relacion de Personaje con Arma, prefiero
que cada Item tenga su tabla, y asi Personaje su relacion
con Arma.
de todas formas, JOINED, no es tan mala en consultas polimorficas
dado que un JOIN no es tan costozo*/

@Entity
@Inheritance("JOINED")
class Item extends PersistentObject{
    costo()
    nombre()
}

class Accesorio extends Item{
    int costo; String nombre;
}

class Regalo extends Item{
    String fraseMotivacional
    String nombre 
}

class BolsaDeMonedas extends Item{
    int monedas;
}

class Arma extends Item{
    int costo, int defensa, int ataque
    String nombre;
}

-------------------------------------------
@Entity
class Ubicacion extends PersistentObject{
    String nombre;

    /*De igual manera, si no pertenecen a 
    ningún personaje están en una y sólo 
    una ubicación. */
    @OneToMany
    @JoinColumn("ubicacion_id")
    List<Item> items;
    
    /* no se habla mucho de esto, pero
    por sentido comun, una coordenada */
    @Embedded
    Coordenadas coordenadas;
}


/* las coordenadas para el usuario se van a calcular
en tiempo real, a diferencia de las ubicaciones.. que
van a ser embebidas dentro de la clase, dado que sentido 
por si sola como entidad no posee
*/
@Embeddable
class Coordenadas{
    int x, int y, int nivel;
}

----------------------------------------

@Entity
class Personaje extends PersistentObject{
    String nombre;
    int monedas;

    /* el enunciado quiere saber el orden 
    en el que se visitaron las ubicaciones 
    para un usuario, por eso el @OrderColumn */
    @OrderColumn 
    @ManyToMany
    List<Ubicacion> ubicacionesVisitadas;

    /* segun el enunciado, un item es unico
    y puede estar en un solo personaje o en una 
    ubicacion
    "Las instancias de ítems no se comparten 
    entre personajes: dos personajes no pueden 
    tener el mismo ítem a la vez. De igual manera, 
    si no pertenecen a ningún personaje están en una 
    y sólo una ubicación." */
    @OneToMany
    @JoinColumn(name = "personaje_id")
    List<Item> inventario;

    @Enumerated(EnumType="STRING")
    TipoPersonaje tipoPersonaje;

    /* en objetos si vamos a ir actualizandono creo que quiera que levantemos el server
    la posicion */
    Coordenadas coordenadas; 
}

------------------------
1- 
'pantalla 1:'
pantalla general de ubicaciones: GET /ubicaciones

al tocar boton editar: GET /ubicaciones/:id

al tratar de crear una ubicacion: GET /ubicaciones/nueva

'pantalla 2:'
para acceder a la ubicacion: GET /ubicaciones/nueva 

cuando clickeas el boton guardar: POST /ubicaciones 

donde la informacion de la creacion puede ir en el body / parametros
	
'pantalla 3:'
obtener la ubicacion en particular: GET /ubicaciones/:idduelosGanados

al clickear Guardar: POST /ubicaciones/:id
donde aca estamos limitados por las acciones de un FORM, el mejor 
seria PUT para actualizar el contenido.

agregar item: GET /ubicaciones/:id/items/nuevo
dado que el boton guardar recien es el que crea 
el recurso rest 

'pantalla 4:'

acceso general:	GET /ubicaciones/:id/items/nuevo

al clickear el boton guardar: POST  /ubicaciones/:id/items

2- Exponer un API REST para buscar y consultar estadísticas públicas de cada personaje en juego, 
para que se puedan desarrollar otros negocios en torno al juego.

GET /personajes -> obtenes todos los personajes del juego
GET /personajes/:id -> obtener el personaje 
GET /personajes/:id/ubicacion -> obtener la ubicacion del personaje
GET /personajes/:id/info -> informacion tal como su Tipo, Nombre, Edad, Arma que esta usando , etc 
GET /personajes/:id/items -> ver los items 

con estas rutas, podes obtener toda la informacion que necesitas sobre un 
personaje, ahora, si lo que se quieren son estadisticas se podrian crear 
algo asi : GET /estadisticas/personajes/:id 

donde podria haber tambien 
GET /estadisticas/personajes/:id/duelosGanados
GET /estadisticas/personajes/:id/itemsConseguidos

etc..


C- ARQUITECTURA 
1. 
a. la cualidad necesaria para analizar es la escabilidad de tiene esa arquitectura base 

b. no podemos escalar horizontalmente aca, dado que no tenemos ningun servicio
que pueda redirigir el trafico (como un balanceador de cargas) de ser asi, podria 
escalar horizontalmente.. pero al no tenerlo, solamente puedo escalar verticalmente 
los componentes de la arquitectura, tales como el servidor, la db, etc.. 

c. podria tenerse varios servidores distintos corriendo nuestra aplicacion 
donde ahi si necesitariamos el balanceador de carga.. y ganamos en el sentido 
de que si se cae un server, tenemos mas servers corriendo con nuestra aplicacion
tenemos la posibilidad de escalar tanto vertical como horizontalmente 
podriamos tambien al momento de releasear una nueva version, hacerlo en 1 
server a la vez y que los demas funcionen, entre otras cosas .

2.
a. la cualidad involucrada aca es la disponibilidad, porque si la db no responde 
los usuarios no pueden jugar correctamente

b- hacer que las actualizaciones de las ubicaciones sean 
asincronicas. lo cual es posible porque el usuario no depende 
de la respuesta del servicio para jugar, las ubicaciones 
se guardan por seguridad.
tambien lo que se podria hacer, es un mantenimiento semanal 
a la base de datos.. como tambien agregar mas, entre otras cosas .