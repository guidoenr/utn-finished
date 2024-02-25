----------------------------CLASE 1- MANEJO DE ERRORES--------------------------
ERRORES:
"De tiempo de compilación": son los que no permiten que el sistema se construya y 
en consecuencia, evitan que se ejecute.
sstos errores son de los que menos nos preocupan, porque el IDE los sugiere 
corregir.

"De tiempo de ejecucion(falla)": permiten que el sistema continue ejecutandose,
ante la aparicion de un error de este tipo, el prograba aborta su ejecucio normal

"De logica": permiten que el sistema se construya y se ejecute, pero con un 
comportamiento no esperado.
ejemplo, serian un if con signos invertidos

PATRONES DE MANEJO DE ERRORES:
"Fail Fast": el concepto de fail fast es muy sencillo. nos plantea que ante la detección \
de un error de lógica (si el error ya es una falla este principio no nos ayuda), 
hagamos fallar al sistema lo antes posible. 
esto significa que: si hay dos momentos en los que podríamos validar una precondición 
inválida, deberíamos hacerlo en el primero de los momentos. 

'Cosificar el error':
class Comprador
  public void comprar()
     variable producto = elegirProducto()
     retornar pagador
          .realizarPago(producto)
          .siTieneExito(identificador -> 
             guardarIdentificador(identificador, producto))

EXCEPTIONS?
siempre heredar de RuntimeException()

           
ANTI PATRONES DE MANEJO DE EXCEPCIONES:
como regla general deberíamos evitar mezclar distintos mecanismos de manejo de error, 
o al menos, hacerlo con particular cuidado. damos algunos ejemplos: 

"1": "retornar True en caso exitoso y lanzar excepciones en caso de Error:"
el problema está en que nunca devolvería false, entonces ¿para que retornar un valor 
con una única posibilidad? directamente el método no retorna valor y lanza excepción en 
caso de error

metodo inscribir(jugador)
  si puedoInscribirNuevo() entonces
    jugadores.agregar(jugador)
    retornar true
  si no
    lanzar EspacioInsuficienteException()



CUALIDADES DE DISEÑO:
'Cosificar': darle entidad a una cosa, darle un nombre a una cosa .. 
sea a una clase, a un method , a una operacion, es basicamente darle una entidad 
a algo que estaba de forma implicita. Lo abstraigo y genero algo que representa ya 
una entidad, ya una cosa, un objeto, un method.
Tal codigo que es complejo, lo definis como un method, una clase, lo que sea.
es sinonimo de 'abstraer'

'Cohesivo': un objeto es cohesivo cuando su objetivo es claro y no tiene responsabilidadeds 
que tengan que ver con otras cosas. El objetivo del objeto o del method tiene que ser claro 

'Robustez': que pasa cuando el sistema no se usa de la forma mas adecuada? como reacciono?
mientras mas robusto un programa es, mas se banca cuando es mal usado.. malos inputs, 
malos redireccionamientos, comportamiento inoportuno,etc.
entonces, el sistema mas robuto sabe como manejar mejor los errores y como reaccionar.

'Testeabilidad': que tan testeable es nuestro diseño de forma unitaria , es decir ,
que tan facil es probar nuestro programa.

'Flexibilidad': que tan flexible es nuestro sistema, que tan facil es agregar nuevas 
funcionalidades.

'Consistencia': ser consistente en el diseño, tomar decisiones similares sobre 
los mismos problemas.


----------------------------CLASE 2- PATRONES DE DISEÑO--------------------------
'STRATEGY:' lo hiciste en el ejercicio de macowins, es el que mas sabes 
y usas siempre en el laburo.. consiste en tener distintas clases para 
resolver un problema en especifico con diferentes algoritmos, como aca 
que el valorFijoDeUsuario se calcula de 3 formas distintas segun su 
clase. "El objetivo es el mismo, el algoritmo para hacerlo es distinto"
OBS: este usa interface porque no hay codigo en comun

interface EstadoPrenda{ public Float calcularPrecio(); }

/* ya esto es un Strategy, tiene un algoritmo distinto para resolver
la operacion, que es calcular el precio */

class Nueva implements EstadoPrenda{

  public Float calcularPrecio(Float precioBase){
    return precioBase;
  }
}

/* el valor fijo que define el usuario se setea cuando se construye */
class Promocion implements EstadoPrenda{

  Float valorFijoDeUsuario;
  /* esta bien esto aca, pero podria estar en otro lado, falto 
  data en el enunciado */
  public EstadoPrenda(Float valorFijoDeUsuario){
    this.valorFijoDeUsuario = valorFijoDeUsuario;
  }

  public Float calcularPrecio(Float precioBase){
    return precioBase - this.valorFijoDeUsuario;
  }<img src="https://drive.google.com/file/d/1SYovuMN6_tZZag-7JGiv6VPvylfwIx-C/view
}


class Liquidacion implements EstadoPrenda{

  public Float calcularPrecio(Float precioBase){
    return precioBase * 0.5;
  }
} 

-------------------------------------------------------
'TEMPLATE METHOD': consiste en una clase padre, donde en uno de sus methods 
tiene un algoritmo en el cual es comun en sus clases fijas excepto en una 
cosita aparte, esto es capaz lo que mas lo diferencia del strategy, porque el 
algoritmo es completamente distinto, en cambio aca varia en una sola parte
OBS: este usa abstract class porque hay codigo en comun, por lo tanto
hay HERENCIA. en strategy NO, vos podes cambiar la strategy para hacer las cosas.

abstact class Venta{
  public void importe(){
    this.importeBase = this.precioItems();
  }
}

class Efectivo implements MedioDePago{

  public float recargo(Float importeBase){
    return 0;
  } 
}

class Tarjeta implements MedioDePago{

  public float recargo(Float importeBase){
    return this.cantidadDeCuotas * coef + (0.01*importeBase); 

  }
}

------------------------------------------------------- QMP - iteration1
/* gano robustez sarpada , en contra de los strings, porque sino deberia
agregar una validacion extra para ver si el String: "remera" pertenece a
una lista de tiposDePrendaValida */
public enum TipoPrenda{
  ZAPATO, CAMISA_MANGA_CORTA, CAMISA_MANGA_LARGA, PANTALON, REMERA, ..
}

/*lo mismo pa*/
public enum Material{
  POLIESTINERO, ALGODON, JEAN, CUERO ..
}

/* lo mas tryhard, como matcheas que la categoria se corresponda con el tipo?
no tenes forma digamos, puede ir a costa tuya o a costa del usuario.. pensa
si el usuario te pone una remera, vos sos el encargado de setearle que tiene 
parte superior, sino no hay caso, podria haber datos invalidos como 
buzo = PARTE_INFERIOR*/

class TipoPrenda{

  Categoria categoria;
}

TipoPrenda remera = new TipoPrenda(REMERA, Categoria.PARTE_SUPERIOR)
Prenda prenda = new Prenda(remera, Material.ALGODON, 'BLANCO')

/* otra opcion es hacer un dict */
categoriasTipoPrenda = {
  REMERA -> PARTE_SUPERIOR,
  PATANLON -> PARTE_INFERIOR
}


/* color secundario para mis prendas? el setter esta dentro de todo bien, aunque
es un poco sobrediseño, otra opcion es sobrecarga de constructores, o un solo
constructor que permita el null en el colorSEcundario ademas, el setter 
favorece la mutabilidad y el constructor favorece la inmutabilidad, que en este caso
tal vez quiero que la prenda no*/


----------------------------CLASE 3- PATRONES CREACIONALES--------------------------

'CONSTRUCTORES': vamos a tratar de que tengan la menor de cosas posibles, en el 
constructor solamente quiero 'construir y ya', no vamos a preferir muchas validaciones ,
checkeos de not null, seteos de default, entre otras cosas.. todo esto lo vamos a 
preferir en otro lado.
en el constructor vamos a preferir cosas como:

public constructor(Algo algo){
  this.algo = algo;
}

'si la construccion es simple, legible, y no tiene mucha logica, no hace falta usar'
'un patron para construir el objeto.'


---------------
'BUILDER': (mas usado)
- favorecer objetos correctamente construidos
- guardamos estado intermedio en otro objeto
el builder separa la consutrccion de un objeto complejo de su representancion, es decir,
la prenda por un lado y la CONSTRUCCION por el otro.

'estado en el borrador':contrato implicito:'TODOS TIENEN QUE USAR EL BORRRADOR'
class PrendaBuilder{
  TipoDePrenda tipo;
  Material material;
  Color principal;
  Color secundario;
  Trama trama;

  void especificarTipo(TipoPrenda tipo);
    this.tipo = tipo;// check null and set default if required

}

---------------
'FACTORY METHOD': es template method, pero delega un method de construccion, no 
de comportamiento.

abstract class Sastre{
  public Uniforme fabricarUniforme(){
    ..
  }

  public Prenda fabricarParteSuperior();
  public Prenda fabricarParteInferior();
  public Prenda fabricarCalzado();
}


class SastreSanJuan extends Sastre{

  public prenda fabricarParteSuperior(){
    Prenda prenda = new PrendaBuilder()
                        .especificarTipo(..)
                        ..
                        build()
    return prenda;
  }
}

'y asi con mas sastres, es el template method pero fijate que lo que se overradea'
'son methods para construir prendas.'

---------------
'SINGLETON': unica estancia en el sistema, y que haya un unico punto de acceso.



//----------------------------CLASE 4- se entrego el tp--------------------------

----------------------------CLASE 5- MANEJO DEL CAMBIO--------------------------
'CODE SMELLS:'
class Persona{

  public void distancia(latitud, longitud){
    ..
  }

  public void cuantoTardas(latitud, longitud){
    ..
  }

  ESTO ES UN CODE SMELL. (primitive obsession)

  se soluciona con class Ubicacion(
    Latitud langitud;
    Longitud longitud;
    )
}
  
----------------------------------------------------------------------------
#trick->: pensar la herencia como "bueno, esto es UN..?" ejemplo 
una clase admin y un usuario que la herede, "un usuario, es un admin?"

#trick->: tal vez encapsular muchos datos repetitivos en una clase 
DatosPersonales , tales como dni y demas, pero solamente si son similares
esto es llamado 'DATA CLASS'
----------------------------------------------------------------------------

'6.2.1 Misplaced methods'
Ejemplo: tenemos una aplicación para Papá Noel donde debemos calcular la cantidad 
de maldad de una acción. En la clase PapaNoel codificamos:

public double getCantidadMaldad(Accion accion) {
   return accion.getCantidadMaldad();
}

Si no accedemos a ninguna variable ni tampoco utilizamos 
comportamiento interno, posiblemente estamos ubicando mal 
el método: ¿por que no enviar directamente el mensaje a acción?


'6.2.6 Type Tests'
Missing polymorphism
Preguntarle a los objetos "de que tipo sos" es señal de no haber utilizado correctamente objetos polimórficos.

'6.2.15 Feature Envy'
Method needing too much information from another object
Esto ocurre cuando una clase A envía demasiados mensajes a otra 
clase B, quizás porque la clase B no esté ofreciendo el servicio que la clase A necesita.

>>Carta
public String getDestinatario() {
   return persona.getNombre() + " " + persona.getDireccion() + " " 
+ persona.getEdad();
}

La clase Carta está pidiendo demasiada info a la persona, entonces 
debería ser responsabilidad de la persona devolver el domicilio completo:

>>Carta
public String getDestinatario() {
   return persona.asDestinatario();
}
 
>>Persona
public String asDestinatario() {
   return this.getNombre() + " " + this.getDireccion() + " " + this.getEdad();
}

'6.2.11 Primitive Obsession'
Representar con ints, booleans, Strings o enumeraciones cosas que 
podrían ser objetos con comportamiento. Las enumeraciones nos 
llevan a tener sentencias condicionales en lugar de trabajar con 
objetos polimórficos. O bien preferir el Array [] en lugar de 
tener objetos que modelen una colección, basta con ver la rica 
interfaz de Collection en contraposición a lo poco que ofrece el 
array.

'6.2.9 Temporary Field'
Attributes only used partially under certain circumstances
Ejemplo: Una clase Factura que tiene una variable privada double totalConIVA, 
otra double totalSinIVA y además una double total (que es la suma del total con 
IVA y sin IVA), o un Cliente que tiene una variable privada fechaUltimoPago 
cuando también tiene una colección de pagos cada uno con su correspondiente fecha. 
Es decir que "cachea" valores que podría calcular. De todas maneras esto no 
constituye un problema per se, uno puede definir variables calculadas basándose en:
que es costoso resolver el valor y se necesita conocer ese valor muchas más 
veces que las veces que se actualiza
temas de implementación (como la necesidad de guardar ese valor en una base 
de datos relacional para luego ejecutar queries de consulta, así estaríamos 
evitando duplicar la lógica de negocio en el SQL)

Otro ejemplo que puede sonarles conocido: el guerrero tiene como atributo el ítem más valioso.


'6.2.18 Shotgun Surgery'
Small changes affect too many objects
Este ejemplo de Nick Harrison extraído de http://www.simple-talk.com/dotnet/.net-framework/exploring-smelly-code/ ilustra lo que sucede cuando queremos llamar a un stored procedure para recuperar los clientes de una empresa. Si tenemos el siguiente método:

public ResultSet getClientes() {
    SqlConnection con = new SqlConnection(this.connectionString);
    SqlCommand cmd = con.createCommand();
    con.open();
    cmd.commandType = CommandType.StoredProcedure;
    cmd.commandText = "spGetClientes";
    return cmd.getResults(CommandBehavior.CloseConnection);
}


Cuando necesitemos llamar a otro stored procedure para traer la información de un 
cliente específico, seguramente habrá mucho en común con el método getClientes
Cualquier cambio cross-aplicación tiene un altísimo impacto en nuestro sistema 
porque hay que modificar todas las llamadas a los stored procedures. Algunos ejemplos:
queremos medir la performance de los stored procedures para detectar cuellos de botella
si la conexión a la base original está caída debemos conectarnos a otra base 
(que está en otro servidor)
el área de QA recomendó eliminar el prefijo "sp" de todos los stored procedures 
y para salir a producción necesitamos hacer ese cambio (evidentemente se trata 
  de un tema político pero que afecta a la implementación)

En general, cuando tenemos cuestiones arquitecturales aparece este bad smell, 
por eso su naturaleza cross. Algunos ejemplos
manejo de la persistencia, y en general llamadas a componentes externos 
(como una base de datos o un servicio)
manejo de errores: ¿que queremos hacer cada vez que ocurra un error?
activar o desactivar niveles de debug/trace/logueo por temas de performance, 
errores, etc.
configuraciones de una aplicación
en general donde necesitamos hacer algo en muchos lugares, ese algo 
involucra varios pasos (al menos dos) y queremos tener un comportamiento uniforme.

En esos casos, lo ideal es concentrar en un único punto esos pasos 
para que cuando nos pidan un cambio aparentemente pequeño eso no nos impacte en una gran cantidad de objetos.

Refactor que permite resolver este bad smell: Extract Class + Move Method

public ResultSet getClientes() {
    new StoredProcedure().getResults("GetClientes");
}
 
>>StoredProcedure (clase creada por nosotros)
public StoredProcedure() {
    SqlConnection con = new SqlConnection(this.connectionString);
}
 
public ResultSet getResults(String storedProc) {
    SqlCommand cmd = con.createCommand();
    con.open();
    cmd.commandType = CommandType.StoredProcedure;
    cmd.commandText = "sp" + storedProc;
    return cmd.getResults(CommandBehavior.CloseConnection);
}


'BIBLIOTECAS VS FRAMEWORK': btw: clase 5, al final

'bibliolteca, orientada a objetos' <----- FLAMA USAR LA BILBIOTECA ASI
File resizedImage = new Rezizer(this)
                    .setTargetLenght(1200)
                    .setXP(123,1)
                    ..
                    .getResizedFILE();



'esto es una funcion que sabe usar una operacion'
StringUtils.reverseString("nequen") // XD
// ACA ES USO ESTO PORQUE NO ME QUEDA OTRA, UISO EL METHOD DE CLASE
// PORQUE NO ME CONVIENE CREAR UN STRING.


----------------------------------------------------------------------------
#trick->: change my mind: instanceof vs esDeposito()
pensa que el instanceof esta ligado al tipo, y siempre lo va a estar 
entonces.. vos si tenes un movimiento y tiene un method como esDeposito()
pode smodificarlo con el tiempo, como queseyo, algo como
esDeposito(){
  return true if fechaDeHoy > fechaDeAYer (ejemplo pedazo de buena persona)
}
pero ganas en flexibildiad
----------------------------------------------------------------------------
----------------------------CLASE 6- Inyeccion de dependencias--------------------------
<img src="https://docs.google.com/document/d/1LurA-bCEHhCsIPFiFg1rqfIdfe5SdS4wBePfG45nDqg/edit#heading=h.6jvc2n7j3ke5


'Interfaces sincrónicas'

se da cuando un componente le envía un mensaje a otro, éste se queda esperando a que 
el otro termine. Esta es la forma en que trabaja el envío de mensajes en objetos, 
la ejecución de procedimientos en imperativo, o la aplicación de funciones en 
funcional. 
es también la forma más simple de trabajar, porque es fácil razonar sobre ella: si un 
componente A le envía un mensaje a otro B, y luego A le envío otro mensaje a C, 
sabemos con seguridad que la respuesta de C llegará después de la respuesta de B. 
un ejemplo: nos interesa conocer la cotización del dólar de la fecha de hoy. 
si nos paramos desde el componente A y pensamos en hablar con un componente B 
que obtiene la cotización, una opción sincrónica es la siguiente:

BigDecimal dolarHoy = cotizador.obtenerCotizacion()

lo interesante del sincronismo es que sabemos que si la línea termina de ejecutarse 
sin errores, tendremos con total seguridad un resultado, que usarlo en el siguiente 
envío de mensajes:

BigDecimal precioFinal = calculadoraPrecios.obtenerPrecioFinal(dolarHoy)

La contra de esta forma de comunicarnos es que si la comunicación entre A y B es 
lenta o poco confiable, detendrá la ejecución del componente A por quizás demasiado 
tiempo. 



'Interfaces asincrónicas'
cuando A está en condiciones de continuar su trabajo mientras B calcula el resultado, 
o incluso, cuando a A no le importa el resultado de B, podemos implementar interfaces 
asincrónicas. 

en este caso, A le enviará un mensaje a B para solicitar el inicio de la tarea, 
con los parámetros que necesite, y luego continuará su flujo de ejecución.y
en tanto B, cuando tenga el resultado la tarea listo, de alguna forma comunicará 
a A de este suceso. Algunas formas de hacerlo son:

- depositar el resultado de la operación en algún lugar de memoria compartida, el cual 
  A periódicamente revisa
- guardar una referencia a A, y enviar un mensaje a éste cuando haya terminado
- guardar un callback

Ejemplo: podemos construir una interfaz asincrónica para nuestro cotizador de la 
siguiente forma: 

- en un primer momento el componente A dispara el mensaje cotizador.obtenerCotizacion()
... pasa el tiempo, y el cotizador (componente B) pone la respuesta en el cotizador 
cotizador.setUltimaCotizacion(cotizacion) 
más tarde, el componente A irá a buscar la respuesta a cotizador.ultimaCotizacion

- o también podríamos usar callbacks (bloques de código): 
 cotizador.obtenerCotizacion([ resultado | ..usar resultado... ])

Y luego B se encargará de ejecutar el callback cuando sea necesario.

Como casos extremos, podría darse que a A no le importara que B realmente le 
responda algo, sino tan solo que se vea notificado. En este caso, A sólo se debe 
enviarle un mensaje a B con la información que necesite, sin preocuparse por capturar 
un resultado. 
Una interfaz sincrónica que no expone un resultado, es más fácilmente convertible 
a una interfaz asincrónica que una que sí expone un resultado. 


'Inyeccion de dependencias': ya sabes lo que es 
interface Mailer{
  void send(message, clientes);
}

class MailerSTP implements Mailer{}
class MailerGMAIL implements Mailer{}

method(Mailer mailer)) {

  mailer.send(asd, clientes);

}

-> ganas en todo, flexibilidad, customizacion y testeabilidad.


--------------------QMP res 4  + 'ADAPTER'

/* quiero poder conocer las condiciones climaticas de BSAS en un momento 
dado para obtener sugerencias acordes */

#trick-> 'NO SE PUEDE CAMBIAR ESTA INTERFACE, NO PDOES TOCAR NADA, NI AGREGAR METHODS, Ni variables'
'NI TAMPOCO HEREDARLA DE ALGO, NADA, NO TOCAR NADA NADA NADA'

AccuWeatherApi -> vamos a tratar la interface saliente, porque desde mi componente 
estoy agarrando el componente AccuWeatherApi;

{

AccuWeatherAPI apiClima = new AccuWeatherAPI();

List<Map<String, Object>> condicionesClimaticas = apiClima.getWeather(“Buenos Aires, Argentina”); 
condicionesClimaticas.get(0).get("PrecipitationProbability"); //Devuelve un número del 0 al 1

int temperatura = condicionesClimaticas.get(0).get("Temperature").get("Value").toDegress();

}

obs -> esta interface es una poroonga, el uso es una reverenda poronga.

solucion->:

/

/* te la van a dar como interface siempre porque no quieren que sepas como 
esta codeada, quieren que sepas que mensajes entiende la interface/clase */

interface AccuWeatherAPI{
  List<Map<String,Object>> getWeather(String city);
}


'ADAPTER': adapta interfaces que generalmente no son comatibles

class ProveedorClima // conoce al AccuWeatherAPI
  int getTemperatura()

    AccuWeatherAPI apiclima = new AccuWeatherApi();
    List<Map<String,Object>> condicionesClimaticas = apiclima.getWeather("buenos aires")
    return condicionesClimaticas.get(0).get("temperatuire").get("value").toDegress;

/
// se usa asi
int temperatura = proveedorClima.getTemperatura()

----------------------------------------------------------------------------
#trick-> otro proveedor de otro api, tendria que hacer implements a esta interface 
y adentro llamar al api que conozca. pensa que hacer la interface, hace que 
todo sea mas extensible , y en el uso al usar proveedorCLima, instancias el que 
se te cante...
peeero no siempre es necesario, imlpementala cuando tengas varios proveedores y listo.
----------------------------------------------------------------------------
/* quiero configurar facilemtne distintos servicios de proveedor de clima
*/

interface ProveedorClima{ //adapter
  int getTemperatura();
}

class ProveedorClimaAccuWeatherAPI implements ProveedorClima{ //adaptee
  //conoce al AccuWeather
}

class ProveedorClimaWeatherOtherAPI implements ProveedorClima{ //adaptee
  //conoce aal WeatherOTherAPI
}


/*quiero que salga barato y se llame lo menos posible */
el accuweather cobra 0.5 usd por llamada al new.


obs->: pensa que esto te cobra por cada llamada
/
proveedorCLima = new ProveedorClimaAccuWeatherAPI()

@Test
public void obtenerRopasCalidas(){
  assert (ropero.ropas(proveedorClima).size(), 10);
}
/

obs->: se soluciona usando MOCKs

List mockedList = mock(List.class);

when(mockedList.get(0)).thenReturn(23);

----------------------------------------------------------------------------
#trick-> : 
en el ejercicio de FileSystem, vos tenes muchas operacioens en en LowLevelFileSystem fs 
como:

 fs.syncReadFile(..)
 fs.closeFile(..)
 fs.openFile(..)

etc.. fijate que todos los method le pegan a un archivo, entonces el truco aca esta 
en tratar de delegar bien las responsabilidades, es raro pensar que un archivo 
'sabe cerrarse, leerse, o lo que fuera' pero ganas en todo lo que es el middle man 
cuando hagas el API de HighLevelFileSystem.

#trick->: y no darle TAAAAANTO enfasis a las responsabilidades, aca no tenia mucho 
sentido que un archivo sepa leerse, cerrarse o lo que fuera, pero qcyo, gaston lo hace 
asi y ganas en varios factores.
----------------------------------------------------------------------------
{

public class HighLevelFileSystem
  LowLevelFileSystem fs;

  OpenFile open(String path)
    return new OpenFile(this.fs, fs.openFile(path))


public class Archivo 
  LowLevelFileSystem fs;
  int descriptor;

  void close()
    fs.closeFile(this.descriptor)

  int syncRead(byte[] bufferBytes, int bufferStart)
    return fs.syncReadFile(this.fd, ..., N)

  int syncWrite(byte[] buffferbyees, ... , N)
    return fs.synCwriteFile(this.fd, ... , N)

}

#trick->: fijate que todos esos cosos del buffer siempre van juntos ->
inyeccion de dependencias, podrias hacer una clase buffer y que tenga todo eso 




-> anda 

curl -X 'GET' \
  '<img src="https://api.refugiosdds.com.ar/api/hogares?offset=1' \
  -H 'accept: application/json' \
  -H 'Authorization: Bearer CSN4rGPogpUjadBjSbEuwsgZdf02hSX1UtBfGJhsJZEUSO8T21322Oue70V4' > hogares1.json



class Asd{
  public void int = asd;

  
}


----------------------------CLASE 7- COSIFICAR COMPORTAMIENTO--------------------------
'RECONTRA IMPORTANTE ESTA CLASE CUANDO ARRANCA'
en el parcial neflix hay una interfaz que tiene varios parametros en un method 
para hacer algo con un video (idVideo, minutoInicio, duracion, .. ,N)
entonces aca el adapter podria tener un objeto Video que tenga todo eso, parecido 
al de file System

tricks->: no siempre es necesario hacer adapters, en el de los convertidores de imagenes 
del parcial.. no hace falta, porque no es una operacion que tiene efecto o que necesite mockear 
como para adaptar (hablando de los convertidores), que lo unico que hacian eran crear una imagen


--------------------QMP res 5 
/ver clase de nuevo tal vez /

/*quiero poder manejar varios guardaropas para manejar mis prendas segun diversos
 criterios */
class Usuario
  List<Guardaropa> guardaropas;


class Guardarropa
  List<Prenda> prendas;



/*Como usuarie de queMePongo, quiero poder crear guardarropas compartidos con 
otros usuaries (ej, ropa que comparto con mi hermane).  */
/ -> gaston esta usando mucho esto de que un objeto tenga methods que no son 
su responsabilidad, como por ejemplo el method 'compartir' que le pega otro 
usuario de afuera / -<

class Usuario{
  List<Guardarropa> propios;
  List<Guardarropa> compartidos;

  compartir(guardarropa)
    this.guardarropas.add(guardarropa);

}


/*Como usuarie de queMePongo, quiero que otro usuario me proponga tentativamente
 agregar/quitar una prenda al guardarropas. */



/*sugerencias */

/'modelo dos tipos de sugerencias: una de tipo Agregar y otra de tipo Quitar'
/ 'TEMPLATE - METHOD' 

class Guardaropas{

  public void agregarSugerencia(sugerencia){
    this.sugerencias.add(sugerencia);
  }

}


abstract class Sugerencia{

  void aplicarEnGuardarropas(guardarropas){
    this.estado = ACEPTADA;
    this.aplicarOperacion(guardaropas);
  }
    public void deshacer(guardarropas){
    Validate.is(this.estado, ACEPTA);
    this.deshacerOperacion(guardaropas);
  }
}

class SugerenciaQuitar extends Sugerencia{
  
  void aplicarEnGuardaropas()
    guardaropas.quitar(prenda);

  void deshacer()
    Validate.is(this.estado, ACEPTADA)
    guardaropas.agregar(prenda);
}


class SugerenciaAgregar extends Sugerencia{

  void aplicarOperacion(guardaropas)
    guardaropas.agregar(prenda);

  void deshacer(guardaropas)
    guardaropas.quitarPrenda(prenda);

}

----------------------------CLASE 8- NOCION DE EVENTO - OBSERVER--------------------------

obs: ver lo de callable y runnable cuando arranca la clase que es importante


'LAMBDA': 

method postergarAgregado(guiardaropa, prenda)
    return { () => guardaropa.agregar(prenda) } <--- LAMBDA

'OBJETO':

Consumer objeto = .. postergarAgregado(guardarropas, prenda);
objeto.apply();


generalmente tener un objeto me  da un grado enorme de flexibilidad, porque  
puedo decirle .deshacer() como .apply() entonces generalmente objeto > lambda

--------------------QMP res 6

/*Como usuarie de QueMePongo quiero tener una sugerencia diaria de que ponerme 
y que  todas las mañanas, diariamente, esta sea actualizada 
*/

interface Asesor
  Atuendo sugerir(List<Guardarropa> guardaropas);

class Usuario
  public void sugerenciaDiaria()
    this.sugerencia = ServiceLocator.instance().get(Asesor.class).sugerir(this.guardarropas);

/*
Como empleade de QueMePongo quiero poder disparar el cálculo de sugerencias 
diarias para todos los usuarios para poder ejecutar esta acción a principio 
de cada día.*/

class TareaAsignacionDiaria // tener un method con comportamiento estaria mal en un repo
  public void enviarSugerenciaDiaria
    usuarios.forEach(usuario -> usuario.actualizarSugerenciaDiaria())


/*
Como usuarie de QueMePongo, quiero poder conocer cuáles son las últimas 
alertas meteorológicas publicadas en el sistema para estar informado (pudiendo 
  verlas, por ejemplo, al entrar en quemepongo.com) 
*/

interface ProveedorClima
  /int getTemperatura();/ <- este method es el de la entrega anterior
  List<Alerta> getAlertas(String ciudad);

  proveedorClima.getAlertas(unaCiudad);
  

/*
Como empleade de QueMePongo, necesito poder disparar un proceso que 
consulte y actualice la lista de alertas publicadas en el sistema para 
tener control sobre cuándo se publican las mismas 
*/ disparar: mandar un conjunto de mensajes
trick:#->cuando el usuario ejecute la actualizacion el codigo deberia hacer esto 

class RepoAlertas
  List<Alerta> alertas;

  List<Alerta> getAlertasDeCiudad(ciudad)
    this.alertas.filter( alerta -> alerta.getCiudad().equals(ciudad));

  void actualizarA(List<Alerta> alertas)
    alertas = new ProveedorACcuWeather().getAlertas(ciudad)
    this.actualizar(alertas);



RepoALertas.instance().actualizarA(alertas);



---- LLEGA EL OBSERVER / ALERTAS / y demas ..

OBSERVER':

"cuando hay una alerta meteorologica quiero que pase X cosa.. es decir , cuando se dispara un"
"evento, quiero que todos los que esten interesados sean notificados de esto para realizar "
"ciertas acciones"

'Subject': sujeto en el que ocurre el evento, en este caso es el proveedor de clima, que normalmente
tiene una lista de observadores, methods para agregar o quitar observadores y methods para notificar 
a los observadores, en el ejemplo de gaston.. el Subject es la Empresa. 

'Observer': es el observador, que generalmente tiene mensajes como onNotify , cual representa 
que ocurrio algo.. esto es raro, porque el method solamente se crea para que el Subject sepa 
que mensaje mandarle a cada observador, cada observador sabe que hacer son su onNotifiy().
generalmente, para que exista un observer.. deberia existir algun evento que pase en el dominio 
que le interese a los suscriptos. El observer es como la "Class" digamosle, que puede tener 
una lista con todos los que estan interesados , como tambien puede que no.


observaciones:
- es conveniente que un observer no rompa, porque si lanza una excepcion .. rompe 
la llamada a los otros observers.
- es conveniente que se piense al observador como que solo dispara la operacion, es decir, 
solamente saber que se disparo algo y no saber que se hace o se hizo despues.

=---------------------------------qmp

estos requerumientos me estan diciendo "quiero que cuando se dispare una alerta, pasen cosas"

accion principal: setear las alertas 
cosas que se disparan cuando eso ocurra: todo esto de abajo
/*Como usuarie de queMePongo quiero que se actualice mi sugerencia diaria 
con las condiciones climáticas actualizadas cuando se genere algún alerta 
durante el día 



*/

interface InteresadoALERTA{
  hayGranizo(usuario, alerta)
  hayTormenta(usuario, alerta)
}

class Sugeridor iplmenets InteresadoALERTA{

  hayTormenta()
    usuario.sugerir()

  hayGranizo()
    usuario.sugerir() 


}

class Usuario{
  public void hayAlertasNuevas(alertas){
      alertas.each( alerta -> alerta.notificar(this))
  }



void actualizarAlertas()
  this.alertas = proveedorClima.getAlertas(ciudad)
  RepositorioUsuarios.instance().all().each(usuario -> usuario.hayAlertas(this.alertas))


void hayAlertas(alertas)
  this.interesados.each(interesado -> interesado.hayAlertas(alertas))


/*Como usuarie de QueMePongo quiero tener la posibilidad de que ante una 
alerta de tormenta la app me notifique que debo llevarme también un paraguas 
*/


/*Como usuarie de QueMePongo quiero que ante una alerta meteorológica 
de granizo la app  me notifique que evite salir en auto
*/


/*Como usuarie de QueMePongo quiero poder recibir un mail avisándome 
si se generó algún alerta meteorológico y cuál
*/


/*Como usuarie de queMePongo quiero poder configurar cuáles de estas 
acciones (notificaciones, mail, recálculo) quiero que se ejecuten 
y cuáles no, además de soportar nuevas acciones a futuro. 
(No nos interesará, sin embargo, soportar nuevas alertas)*/

----------------------------CLASE 9- PATRONES DE COMUNIACION - REPASO OBSERVER--------------------------

---alternativa 1 - NEFLI RESOLUCION


--registro el listen
reproductor.registerOnStop(new StopHandler())

RepoPelis>>pendientes()
  this.pelis.fitler(peli => peli.estaPendiente())


Peli>>id
Peli>>minutoActual=0

Peli>>estaPendiente()
  if (this.minutoActual != 0)
    return true
  return false

Peli>>continuarViendo()
  reproductor.play(this.id, this.minutoActual)
  this.minutoActual = 0

StopHandler>>onStop(idVideo, minutoActual)
  peli = RepoPelis.getInstance().findById(idVideo)
  peli.minutoActual = minutoActual


-- uso
-- listar las pelis pendientes
RepoPelis.getInstance().pendientes()

-- retomar una peli sin terminar
monstersInc.continuarViendo()


--alternativa 2


--registro los listeneres
reproductor.registerOnStop(new StopHandler())
reproductor.registerOnFinish(new FinishHandler())


RepoPelis>>pendientes()
  this.pelis.fitler(peli => peli.estaPendiente())


Peli>>id
Peli>>minutoActual=0
Peli>>estaPendiente=False

Peli>>estaPendiente()
  return estaPendiente

Peli>>continuarViendo()
  reproductor.play(this.id, this.minutoActual)
  this.estaPendiente = False

StopHandler>>onStop(idVideo, minutoActual)
  peli = RepoPelis.getInstance().findById(idVideo)
  peli.minutoActual = minutoActual
  peli.estaPendiente = True

FinishHandler>>onFinish(idVideo)
  peli = RepoPelis.getInstance().findById(idVideo)
  peli.minutoActual = peli.duracion

-- uso
-- listar las pelis pendientes
RepoPelis.getInstance().pendientes()

-- retomar una peli sin terminar
monstersInc.continuarViendo()

-------------------------CRON - Tareas Programadas
"ejecutar algo cada X tiempo, un proceso , method lo que sea.."

cron:-> 0 * * * java -jar /home/app/tarea.jar  // esto dice ejecuta esta tarea cada X tiempo

        minute-hour-day(month)-month-day(week)


----------------------------CLASE 10- PATRONES DE COMUNIACION - COMPOSITE - STATE --------------------------
State: parecido a la composicion, un objeto tiene un estado y puede ir cambiando de estado 
como para hacer otras cosas, pensalo como lvls de groso, el estado saber cambiarse.
es muy parecido a un strategy asi que imaginalo asi, gaston dijo que en el parcial le chupaba 
un huevo,
#Deuda>>pagar()
  estado.pagar()

#Pendiente>>pagar()
  hace su gracia
  deuda.estado(new Pagada())

#Pagada>>pagar()
  throw new YaEstaPagada()

----------------------------------------------------------------------------
#trick -> cuando estas viendo el tema de los tipos, como filtrar , ver que va a retonarte un
method, una funcion, o lo que fuese.. siempre pensa en 'LO FINAL' que quiere decir?  
ejemplo en el Copia.Me, tenias que ver si documentos eran copias y demas.. aca lo primero 
que pensaste fue bueno "retorno null, true? una lista de documentos, etc" y eso estuvo mal 
vos tenias que ver que cada DOCUMENTO termina con un enum que puede ser COPIA, ORIGINAL, etc 
es REEE por aca, recordar "pensar en como va a quedar todo"'';;}
----------------------------------------------------------------------------


#trick ->
Cuando una 'clase hereda de otra clase (abstracta o no)', estás definiendo que es, pasas de una idea abstracta 
a una concreción. Además, estás definiendo una relacion entre clases.

Cuando implementas una interfaz, 'estás definiendo cómo se comporta, estás cumpliendo un contrato'. 
No implica ninguna relacion con ninguna clase.

#trick ->
en el parcial de consultify tuviste muchas vueltas con el como arrancar, porque pensabas 
lo de instanciar las cosas, acordate que el uso de booleanos, throw exceptions y demas.. esta 
super permitido , olvidate un poco de ver que todo ande y ande y ande y no rompa de la nada 
anda haciendo requerimiento por requerimiento, and KISS bitch! no trates de usar 
composicion, herencia, y toda esa mierda por todos lados

#trick -> 'INPUTS'?
olvidate de los inputs amigo, el tema de como "meter data" siempre dejalo de lado ej 
en los parciales de las preguntas, o el corrector de parciales.. tenias que "Responder 
una pregunta con una rta" -> lo cual, dejalo de lado, ponele simplemente la logica 
no hagas nada del estilo 
pregunta>> void responder(String respuesta) porque no es lo que se quiere
te estan testeando el comportamiento de las clases a vos, no como meter las cosas 
fijate que por ejemplo en la clase pregunta te quedo algo asi 
class Pregunta{
  String pregunta;
  String rta;
  String rtaCorrecta;

  boolean esCorrecta(){  -->>>>>>>> fijate,, donde mierda tenes un input aca?
    return rta.equals(rtaCorrecta)
  }
}

se setea en el constructor? en el method? en la UI? que te chupe 3 huevos.
----------------------------------------------------------------------------
#trick-> gaston te respondio esto 'El problema que tiene es que te lo estas 
guardando en  service afip y asumo que ese objeto es compartido por multiples operaciones
, entonces ese es un problema, deberías guardar el mensaje de error para esa operacion'
cuando le preguntaste lo de service afip que habias hecho

interface ServicioWeb{
  void registrar(double monto)
}

class ServicioAfip implements ServicioWeb{
  String mensajeError;
  void registrar(double Monto){
      try{
          //logica del afip
      }
      except UndeliveredException(undeliveredException){ // o algo asi
          this.mensajeError = undeliveredException.getMessage();
      }
  }
}


----------------------------------------------------------------------------
#trick-> rta de gaston en los criterios
"
Manejo accesible con pausado:
Esta parte tiene un problema modelarlo con estado, que solo te va a marcar como pausada si la condición se esta dando en el momento que la ejecutaste.
El usuario lo que hace es setea el criterio, luego el pausar se puede resolver en la consulta preguntandole al criterio que seteo el admin y se retorna lo 
que calcula la estrategia de pausado en base al estado actual de la consulta.
Para hacerlo asi vas a tener que modelar un criterio que represente que el usuario no le configuro ningun criterio (sirve también para despausarla)
"
mal ->
class CriterioMaximasRespuesta implements CriterioPausa{}>>
        int maximasRespuestas;

        void pausarConsulta(Consulta consulta)
            if (consulta.getRespuestas() >= maximasRespuestas)
                consulta.pausar();

se deberia haber hecho consulta.setCriterio(unCriterio)

consulta.checkSiSeDebePausar(){
  this.criterio.momentoDePausar()
}

y tambien hacer un criteroio que no tenga nada, como para despausarla 

----------------------------------------------------------------------------
#trick-> lo que te dijo gaston cuando usaste un solo builder para crear un formulario
y no una encuesta, siendo ambos clases instancias de la clase abstract Consulta

'El builder tiene sentido, con la validación. Igual tal vez si consulta y encuesta 
son bastantes similares estaría bueno tomar una decisión similar en su creación. Incluso podría 
ser el mismo builder que tiene 2 mensajes distintos crearEncuesta (corre la validación), 
crearFormulario (no hace la validación), entonces tenes el mismo objeto para armar estas 
dos cosas que son muy similares
'
moraleja: por mas que uno solo te defina la implementacion de un builder y el otro no, hacer 
el builder para ganar homogeneidad y poder construir las dos cosas de ahi 

----------------------------------------------------------------------------
#trick-> cuando modelaste MultiValuada como pregunta con varias respuestas, 
hiciste una herencia para Booleana teniendo solo dos respuestas, aca lo que 
te sugerio gaston fue 
'Para la booleana podrías haber usado un creation method de Multivaluada y 
te evitabas la clase. Ejemplo: Multivaluada.crearPreguntaBooleana()'

----------------------------------------------------------------------------
#trick -> adapter, cuando no entendiste las interfaces
'Acá una de las cosas importantes es entender como usar los componentes externos. 
En tu modelo vos no tenes como información los medios de notificación de la persona, 
ni sus datos, sino que estan en otro sistema o que te sabe darlo el otro componente.
Entonces la única forma de obtener esa información es a traves del mail, subscriptoresSystem 
tiene un findByMail que te va a dar el suscriptorDTO.
Entonces lo que tenes que hacer es guardate el mail cuando alguien conteste una consulta 
y con eso vas a poder unir los dos mundos. Con el suscriptorDTO que te da vas a poder saber 
que medios de notificación usa, fijate que tiene 2 mensajes que devuelven un booleano (notifyByMail / notifyByTelegram() )

Y con eso podes jugar un poco mas y tener un adapter que en vez de devolverte un 
suscriptordto te devuelva un suscriptor tuyo con objetos medio de notificación 
parecidos a los que hiciste'

----------------------------------------------------------------------------
#trick -> verificaciones de usuario y demas
en el parcial de hitbug, habia una parte que decia que cada Bag tiene un usuario duenio 
y colaboradores, vos pensaste que tenia que existir algun tipo de verificaicon de que el 
usuario/colaboradores pueda editarlo, pero NO. olvidate de eso, NO MODELES VERIFICACIONES NI NADA DE ESO 
, vos aca estas modelando el dominio.


----------------------------------------------------------------------------
#trick ---------> PATRON COMMAND 
en el parcial de hitbug tenias que poder agregar contenido, removerContenido , editar, y 
varias operaciones mas.. pero un requerimiento pedia que esas operaciones sean ATOMICAS 
'Sin embargo, el problema es que estas modificaciones tienen que ser atómicas: se deben 
poder aplicar de forma que podamos deshacer sus efectos. O al menos, permitir ver en que e
stado estaba un bag en un cierto momento en el tiempo. Ante este requerimiento surgen dos
grandes alternativas: 

  1-> Modelar las modificaciones como objetos: al cosificar a las modificaciones, 
  podemos guardarlas, operarlas, descartarlas, aplicarlas, o incluso modelar 
  operaciones para dehacerlas. 

  2-> Tomar snapshots: guardar el estado en el que estaba un bag antes de aplicar 
  la modificación. Si bien esto no nos da tantas posibilidades, al guardar todos 
  los estados por los que pasó un bag podemos consultarlo en cualquier momento. 
 
'
En esta solución, vamos a ir por la primera estrategia, que es el fundamento de un 
sistema de control de versiones GIT: cuando realizamos una modificación, no simplemente 
la aplicamos, sino que guardamos la información necesaria para poder rehacerla o 
deshacerla en cualquier momento. 

¿Por que? 
1. Primero, porque la segunda opción requiere realizar copias de cada bag que modifiquemos, 
tantas copias como modificaciones hagamos.  Y recordemos que los bags referencian a otros 
bags, así que deberíamos clonar al árbol completo: modificar al bag "Mi Música", no sólo 
implica copiar a "Mi Música", sino también a "Ji ji ji", "Hits de los 80", y así, recursivamente.  
2. Segundo, porque estamos perdiendo una abstracción: 'la operación.'
3. Y tercero, como consecuencia de la pérdida de abstracción, porque en breve veremos que 
es una opción menos poderosa que no nos dará herramientas para implementar los Hit Requests 

entonces, te quedaria algo como: --------> command pattern //patron command

interface Modificacion{ //command
  void ejecutar(Bag bag); ->> como parametro, el objeto que atacas.
  void deshacer(Bag bag); 
}


class CambiarNombre implements Modificacion{ //concrete command
  String viejoNombre;
  String nuevoNombre;

  void ejecutar(Bag bag){}
  void deshacer(Bag bag){}
}

class AgregarContenido implements Modificacion{         class QuitarContenido implements Modificacion{ //concrete command
  Contenido contenido;                                    Contenido contenido;

  void ejecutar(Bag bag){}                                void ejecutar(Bag bag){} 
  void deshacer(Bag bag){}                                void deshacer(Bag bag){}                               
}                                                       }                                     

'¿Por que un hit es un conjunto de modificaciones, en lugar de pensar que la modificación ES el hit?'

class Hit{
  List<Modificacion> modificaciones; //concrete commands
  void realizarSobre(Bag unBag); //adentro el foreach etc
  void deshacerSobre(Bag unBag);
}


Simple: el enunciado plantea que  un 'Hit puede contener el agregado de una canción y el cambio del título 
de una imagen' Por tanto, para representar esta situación, necesitamos que un hit pueda contener 
más de una modificación.

"USO:"
//realizar un Hit con cambios en un Bag
//(aun no estamos considerando usuarios)
unHitRicotero = Hit.new([ CambiarNombre.new("ji ji ji", "Jijiji"), 
                          	.new(elPibeDeLosAstilleros) ])
unHitRicotero.realizarSobre(miMusica)


-> entonces finalizando un Bag nos queda :
Bag>> modificar(Hit)
        hit.realizarSobre(this);


-> requerimiento de ver el estado de un Bag en el pasado, 
parado en un cierto Hit. Por ejemplo, ver cómo estaba el 
Bag "Favoritos" cuando se hizo la modificación "Agrego 
la película de Bob Esponja"

Si lo implementamos adecuadamente, es fácil implementar el segundo 
en términos del primero, realizando una copia del bag, y luego 
volviéndolo al pasado: 

/* nota que esto no tiene efecto, porque clonas el objeto
y le aplicas el command */
class Bag 
    method verEnHit(unHit)
          var comoEstaba = self.clone
          comoEstaba.volverAlPunto(unHit)
          return comoEstaba
}


-------------- REQUERIMIENTO 3 / USUARIOS Y HIT REQUESTS
Esto requiere agregar, como mínimo, al concepto de usuario, el cual.... 
'¡no tiene ninguna responsabilidad clara!' Esto se debe a que la mayor parte de las
 responsabilidades del usuario son realizadas por una persona física, y 
 no por un componente del sistema. Por ejemplo, él o ella sabrán: 

- cuándo aprobar o descartar los hits requests
- cuándo realizar un hit o un hit request y con que modificaciones
- a quienes agregar como colaboradores y cuándo hacerlo

Y lo de los Hit Request campeon???????
class Bag{
  method realizarHitRequest(unHit)
    hitRequests.add(unHit)

  method aceptarHitRequest(unHit)
    ...validar que pertenezca a la lista de hit requests....
    hitRequests.remove(unHit)
    self.modificar(unHit)
}

¡Cuidado! Ahora que tenemos una clase usuario, un error común es agregar métodos del estilo: 
'hacerHit
crearBag
aprobarHitRequest
colaborarEn'

Esto es una mala idea, dado que  nuestro Usuario se convierte en una clase 
fachada (façade) que tiene un mensaje por cada caso de uso del sistema. 
Esto es difícil de mantener, porque terminaremos depositando todo el 
comportamiento del sistema en una misma clase. 

Si para paliar el problema anterior,el usuario delegara en los 
otros objetos del sistema los mensajes anteriores, terminaríamos 
con código como el siguiente...;

Usuario>>
method colaborarEn(unBag) 
     unBag.agregarColaborador(self);

...el cual incurre de forma sistemática en el code smell 'misplaced method': 
podriamos simplemente invertir receptor y argumento y eliminar ese método 
del usuario sin perder funcionalidades. O incluso, escribir código como el siguiente....

Usuario>>
method aprobarHitRequest(unBag, unHit) 
     unBag.aprobarHitRequest(unHit);

...el cual es un claro ejemplo de 'middle man'

'CONSIDERACION FINAL'
Como consideración final, se podría incorporar al 
usuario como parámetro a los métodos que requieran la validación de permisos....

class Bag
     method realizarHitRequest(usuario, hit)
           self.validarAcceso(usuario)
           self.realizarHitRequest(hit)

...o agregar esa responsabilidad a la clase usuario (que en este caso no sería un middle man, porque interviene self): 

class Usuario
     method realizarHitRequest(bag, hit)
           bag.validarAcceso(self)
           bag.realizarHitRequest(hit)

-----
CLIENT SIDE / SERVER SIDE
"server side": 