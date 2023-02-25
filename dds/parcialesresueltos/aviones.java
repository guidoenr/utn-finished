// implemento la clase PersistentObject para poder heredar
// de las demas entidades
@MappedSuperClass
class PersistentObject{
    @Id @GeneratedValue
    Long id;
}

/*
    la posicion no tiene sentido como entidad
    por si sola, solamente tiene sentido para
    un lugar en especifico, entonces opto por
    embeberla aadentro de la clase lugar

*/

@Embedabble
class Posicion{
    longitud
    latitud
}

@Entity
class Lugar extends PersistentObject{

     String nombre, descripcion, pais;

     @Embedded
     Posicion posicion;
}


@Entity
class Viaje extends PersistentObject{
    String fecha, descripcion;

    /*
        un destino/ origen puede estar 
        en multiples viajes, pero un viaje
        solamente tiene un origen y un destino
    */
    @ManyToOne
    Lugar destino;

    @ManyToOne
    Lugar origen;

    /*
        lo mismo aca, un asiento puede estar unicamente
        en un viaje, y obvio.. el viaje tiene muchos asientos
    */
    @OneToMany
    List<Asiento> asientos;

}


/*   opto por SINGLE_TABLE de una porque lo que mas me importa aca
    es el primer requerimiento del enunciado, que es 
    "saber los asientos libres de un vuelo" por lo cual, esta hablando
    de asientos de forma polimorfica, SINGLE_TABLE es la mejor en esto
    dado que es una sola tabla discriminada por tipo, no hay que hacer joins
    ni nada.. entonces el query es mucho mas corto
    a pesar de tener varios campos distintos, y que el el maximo de nulosva a ser 4..
    opto por esta opcion dado que prefiero mucho mas de varios asientos, tiene mucho sentido
    a pesar de que el enunciado no lo diga
    entonces voy por la performance en vez de permitir nulos, lo cual lo podria controlar
    en mi aplicacion */
@Entity
@Inheritance(SINGLE_TABLE)
@DiscriminatorColumn(name ="TipoAsiento") 
class Asiento extends PersistentObject{
    int numero;

    /* un asiento solamente corresponde
    a un pasajero, peeero el pasajero puede
    estar en distintos asientos de diferentes
    viajes.. dado que peude ir por ejemplo a 
    USA en X asiento y volver de USA en otro Y
    asiento de otro viaje */
    @ManyToOne 
    Pasajero pasajeroAsignado;

    /* un passajero puede estar en espera
    de varios asientos, tiene mucho sentido
    a pesar de que el enunciado no lo diga */
    @ManyToMany
    @OrderColumn // el enunciado dice que importa el orden, entonces esto es onda un FIFO para 
    List<Pasajero> pasajerosEnEspera;
}


class PrimeraClase extends Asiento{
    pulgadasTv, tipoComando, masajeador, comidaGourmet...

}

class Turista extends Asiento{
    cubreCabeza, almohada, bolsaVomital...
}

@Entity
class Pasajeroextends PersistentObject{
    String nombre,edad,sexo

    /* aca no se puede reutilizar digamos el tipo de pasajero
    porque cada tipo de pasajero tiene un numero de pasajero frecuente
    o una categoria, por lo cual no se podria hacer un 
    @ManyToOne pensando solamente en que un tipo puede estar
    en varios pasajeros */
    @OneToOne
    TipoPasajero tipoPasajero;
}


/*
    al ser TipoPasajero una interfaz
    no me queda otra que pasarlo a clase abstracta
    porque Normal y Frecuente, tienen atributos
    por lo tanto -> no pueden ser enums dado 
    que termina siendo statefull
    y uso SINGLE_TABLE aca porque el Pasajero conoce
    a su tipo, y no sabe que se va a traer cuando se lo consulte
    porque tenes 2 hijos, entonces es una consulta polimorfica

*/
@Entity
@Inheritance(SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPasajero") 
abstract class TipoPasajero extends PersistentObject{

    costo(asiento);
}

class Normal extends TipoPasajero{

    /* un tipo pasajero puede tener 
    unicamente una sola categoria, no
    viceversamente.. puesto que una categoria
    A, B o C puede estar en distintos tipos */

    @Enumerated(type= "String") 
    Categoria categoria;

}

class Frecuente extends TipoPasajero{
    int nroPasajeroFrecuente
}

public enum Categoria {
    A,B,C
}

-----------------------------------------------------

"PARTE B"

- Listado de vuelos
1 - GET /vuelos&fechaDesde={value}&fechaHasta={value}
2 - GET /vuelos/:id/detalle

- Detalle de un vuelo 
1 - POST /vuelos/:id/favorito | DELETE /vuelos/:id/favorito
2 - POST /vuelos/:id
    donde en el body podria ir la info para 
    agregar a mi carrito 

    o sino :
    POST /micarrito/vuelo/:id 
    teniendo el id del vuelo  
    