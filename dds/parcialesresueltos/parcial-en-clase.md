// parcial en clase resuelto por gaston
// 2canciones

- obs: en el parcial podes no justificar los mapeos @ManyToMany etc
- obs: acordate tambien que las flechitas son como atributos, no solamente
un 'conoce'

```java

@MappedSuperClass
class PersistentObject{
    @Id @GeneratedValue
    Long id;
}

@Entity
class Playlist extends PersistentObject{
    /* porque una playlist puede tener varios
    subscriptores y viceversa */
    @ManyToMany
    List<Usuario> suscriptores;

    @ManyToOne
    User duenio;
    
    @ManyToMany
    // esto para ordenar, porque importa el orden en el enunciado
    @OrderColumn
    List<Contenido> contenidos;

    @Enumerated
    Visibilidad visibilidad;
}

/*
@JoinTable -> importante cuando tenes varias listas en varias clases
ej:
class Playlist:
  @ManyToMany
    List<Usuario> suscriptores;

  @ManyToMany
    List<Usuario> followers;

aca si es necesario el JoinTable en los de followers
*/
```

![](images/2021-11-18-10-15-49.png)

```java
SINGLE_TABLE parece que va como trompada aca porque 
fijate que la playlist es como que "Accede al contenido" 
por la flecha esa, entonces te da a entender que va 
a haber consultas polimorficas, medio como que 
importan un huevo los X campos en nulos que va a 
haber


@Entity
@Inheritance(SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoContenido")
class Contenido extends PersistentObject{

    @ManyToOne
    User duenio;

    @Embedded
    Estadistica estadistica;

    @Enumerated
    Clasificacion clasificacion;
    
}

@Entity
class Podcast extends Contenido{
    fechaInicio
    fechaFin
}

@Entity
class Cancion extends Contenido{
    duracion
    fechaSubido
}

/*
no tiene sentido por si sola porque tiene likes y dislikes,
*/
@Embeddable
class Estadistica{
    int likes;
    int dislikes;
}

// porque es stateless, solamente tiene
// comportamiento
public enum Clasificacion{
    MENORES{
        validarAcceso()
    }

    ADOLESCENTES{
        validarAcceso()
    }

    ADULTOS{
        validarAcceso()
    }
}


```

---
## **B - REST** 
![](images/2021-11-18-11-04-08.png)
```python
obs: esto es perfil de usuario, sep uede editar
y consultar, por eso no va POST al /usuarios, y va
al /me

- GET /usuarios/me
- GET /perfil

: al presionar guardar (el mas correcto seria este)
- PUT /usuarios/me
- PUT /perfil

: limitacion de formularios (pero se usa este
porque es un FORM)

- POST /usuarios/me
- POST /perfil/

```
---
![](images/2021-11-18-11-10-54.png)
```python

1 - buscar canciones (query params)
    - GET /canciones
    - GET /canciones?nombre={name}
    - GET /canciones/{id}

2- iniciar la reproduccion
    # donde /reproduccion es como sumarle el contador
    - POST /canciones/:id/reproduccion
    
3- dar megusta o deshacer el megusta dado
    - POST /canciones/:id/like
    - DELETE /canciones/:id/like

    - (como estas en una session, es para vos)

: limitacion form
    - POST /canciones/:id/like
    - POST /canciones/:id/like/remove
```
---
![](images/2021-11-18-11-25-55.png)
```python

"mostrar la pantalla" - GET /playlist/:id

obs: aca hasta que no apretas guardar no 
se actualiza nada, entonces en el punto 1

1- nada
2- nada
3- POST /playlist/:id
    donde viaja todo el el body{
        nombre = asd
    }

```

# Arquitectura
![](images/2021-11-18-11-37-53.png)
```python
"Arquitectura 1": escalabilidad vertical (1 solo nodo)
y la aplicacion todo eso corre en un solo server
entoncs aca solamente podes escalar verticalmente
aca los SPOF son basicamente casi todos, la aplicacion
la db o el server.. porque se cae algo y listo

"Arquitectura 2": al tener un balanceador de carga
tengo tanto escalado horizontal como vertical
porque puedo escalar verticalmente los servers
mejorandolos, + ram, + cpu etc... 
o escalar horizontalmente poniendo mas servers
aca los SPOFS son el balanceador de carga y 
la DB 

como la peticion puede llegar a distintos servidores
los servidores tienen que ser stateless, o si la session
la vas a guardar en memoria (servidor con estado) tenes 
que guardar siempre todo en el mismo servidor, se podria
configurar en el algoritmo del balanceador de carga
el StickySession se llama, lo que hace el balanceador de carga
y te guarda el server donde vos lo mandaste en un principio
eso puede ir por request

```