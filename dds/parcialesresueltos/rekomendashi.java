// implemento la clase PersistentObject para poder heredar
// de las demas entidades
@MappedSuperClass
class PersistentObject{
    @Id @GeneratedValue
    Long id;
}


/* mapeo la clase TipoDePedido a clase abstracta
dado que las clases que la implementan usan atributos
por lo tanto, no me queda otra que convertirla
a clase abstracta 
tambien, opto por usar SINGLE_TABLE como estrategia
de mapeo de herencias, dado que un pedido conoce
al tipo de pedido, lo cual ya pareciera que se consulta
polimorficamente.. y single_table es la mejor en consultas
polimorficas dado que hay una sola tabla donde esta todo
y no es necesario hacer JOINS (joined) o UNIONS (table_per_class)
a pesar de que en el peor de los casos voy a tener 2 campos 
en nulos , prefiero la performance a costas de tener 
que verificar estos nulos del parte del codigo
*/


@Entity
@Inheritance(SINGLE_TABLE)
@DiscriminatorColumn(name="tipoPedido")
abstract class TipoDePedido extends PersistentObject{
    Set armarSet(..)
}

class Grupal extends TipoDePedido{
    int cantidadcomensales;
}

class Individual extends TipoDePedido{
    Number precioMaximo;
    int coeficienteDeVariedad;
}

--------------------------------------------------
@Entity
class Pedido extends PersistentObject{

    @EllementCollection
    List<TipoDeCoccion> coccionesPreferidas;

    
    @EllementCollection
    @OrderColumn // xq importa el orden
    List<Categoria> categoriasPreferidas;

    /* un pedido solamente puede tener
    un solo tipo, o es grupal o es individual
    y no viceversamente */
    @ManyToOne
    TipoDePedido tipoDePedido;

    Set recomendar(..)
}

/* el enunciado da a entender que el Set
y el Catalogo de piezas son servicios en si
por lo tanto, no tienen mucho sentido como entidad
lo unico que hacen es darnos info para mostrarle
al usuario , buscar preferencias,
piezas posibles, etc.. por lo tanto no los persisto
en cambio, si persisto todo lo que tiene que ver 
con las selecciones, piezas e ingredientes*/

--------------------------------------------------
@Entity
class Seleccion extends PersistentObject{
    int cantidad;

    /* el atributo cantidad me dice
    cuantas piezas de estas voy a tener
    por lo tanto, una seleccion en este contexto
    solamente puede tener una pieza, peero..
    una pieza puede estar en varias selecciones */
    @ManyToOne 
    Pieza pieza;
}

@Entity
class Pieza extends PersistentObject{
    String nombre;
    Number precio;
    Imagen imagen;

    @Enumerated(EnumType = "STRING")
    TipoDeCoccion tipoDeCoccion;

    @ManyToMany 
    List<Ingrediente> ingredientes;
}

@Entity
class Ingrediente extends PersistentObject{

    @Enumerated(EnumType = "STRING")
    Categoria categoria;
    
}

