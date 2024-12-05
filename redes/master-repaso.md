# Unos pocos repasos antes del final
| **Capa del Modelo OSI**            | **Protocolo(s)**                                                                                               |
|------------------------------------|----------------------------------------------------------------------------------------------------------------|
| **Capa 1: Capa Física**            | Ethernet, Wi-Fi (IEEE 802.11), Bluetooth, Fiber Optics, DSL, Infraestructura de cable coaxial, etc.           |
| **Capa 2: Capa de Enlace de Datos**| Ethernet, Wi-Fi, PPP (Point-to-Point Protocol), ARP (Address Resolution Protocol), HDLC, Frame Relay, etc.    |
| **Capa 3: Capa de Red**            | IP (Internet Protocol), ICMP (Internet Control Message Protocol), IPsec, IPv4, IPv6, OSPF, BGP, RIP, etc.    |
| **Capa 4: Capa de Transporte**     | TCP (Transmission Control Protocol), UDP (User Datagram Protocol), SCTP (Stream Control Transmission Protocol)|
| **Capa 5: Capa de Sesión**         | NetBIOS, PPTP (Point-to-Point Tunneling Protocol), RPC (Remote Procedure Call), SMB (Server Message Block)    |
| **Capa 6: Capa de Presentación**   | SSL/TLS (Secure Sockets Layer / Transport Layer Security), JPEG, GIF, ASCII, EBCDIC, MPEG, etc.               |
| **Capa 7: Capa de Aplicación**     | HTTP, FTP (File Transfer Protocol), SMTP (Simple Mail Transfer Protocol), POP3, IMAP, DNS, DHCP, Telnet, etc.  |


## RIP (Routing Information Protocol)
protocolo de ruteo de los mas antiguos usados en redes, se basa principalmente en el algoritmo de **Vector  de distancia**, es muy conocido x su simplicidad.

-> es de enrutamiento dinamico (las tablas de ruteo se comparten en el momento para determinar la mejor ruta)
-> las metricas estan basadas en los `hops` que tiene que dar un paquete, para decidir la mejor ruta.
-> los maximos HOPS permitidos son `15`

esto de `"Vector Distancia"` no es mas que el algoritmo que usa RIP y consiste en que los routers manden la tabla de ruteo a sus neighbors cada `30` segundos, asi estos la pueden actualizar dinamicamente y quasi-en-tiempo-real para asi estar todos sincronizados y actualizados, esto es una shit porque congestiona toda la red y hay mucho trafico.

RIP tiene algo magico que es asignarle numeros de metrica infinitos a rutas que provocarian bucles, como `Metric=90909090`, a esto se le llama `Poison reverse`.



## DHCP 
DHCP o Dynamic Host Configuration Protocol se entiende con `DORA` en sus requests, pero basicamente es un protocolo encargado de asignar direcciones IP automáticamente a dispositivos en una red.

Host                                DHCPServer (corre en tu router red local privada)
------------------------------------------------------------------------------------
`DHCPDiscover`                  --> "broadcast,alguien me puede dar alguna ip viejo"
`0.0.0.0`                       --> `255.255.255.255`
------------------------------------------------------------------------------------
"te puedo dar `192.168.0.10`"   <--  `DHCPOffer`
------------------------------------------------------------------------------------
`DHCPRequest `                  --> "la acepto papa, guardamela"
------------------------------------------------------------------------------------
"listo te guardo `192.168.0.10" <--  `DHCPAck`

### Ip-Lease
el Ip Lease es basicamente un contrato temporal entre el Host y el DHCP Server
esta IP que fue ofrecida en el DHCPOffer tiene un `lease time` o tiempo limitado.
una vez que este tiempo expira, la direccion IP vuelve a estar disponible en el pool de direcciones para otros hosts.

De todas formas, el host, puede renovar el Lease enviando otro DHCPRequest

# ARP gratuito? => 'MENSAJE ESPECIAL DE ARP'
Un ARP gratuito (en inglés, gratuitous ARP) es un mensaje especial del protocolo ARP (Address Resolution Protocol) que un dispositivo **envía para anunciar o confirmar su propia dirección IP y MAC en la red local**. No es una solicitud para resolver una dirección IP, sino un anuncio.

de ahi la palabra `gratis`, te doy la info gratis bro, lo mando yo para anunciarme en la red.
este mensaje es un broadcast, enviado a `FF:FF:FF:FF:FF:FF` (broadcast en Ethernet)


aca tenemos la tabla ARP de mi pc , que contiene las relaciones entre direcciones IP y direcciones MAC>
```bash
┌──(guido㉿kali)-[~]
└─$ arp -a
_gateway (192.168.0.1) at e4:c0:e2:96:00:a7 [ether] on wlan0
_gateway (192.168.0.1) at e4:c0:e2:96:00:a7 [ether] on eth0
```

# Ethernet: detectar y corregir errores?

Primero que nada hoy en dia las colisiones son historia, en redes ethernet
con switches modernos y demases, y con comunicacion FULL-DUPLEX, es decir, cada
dispositivo con un medio de comunicacion dedicado.

En ethernet clasico:

- Las tramas Ethernet, vienen con un campo llamado `FCS`, que significa
  `Frame Check Sequence`, cual basicamente contiene un numero que es unico (en teoria)
  y es generado a partir de los demas datos de la trama, parecido a una funcion de Hash.
  El algoritmo que lo calcula se llama `CRC`, y tiene como fin que al llegarle al receptor
  , este lo vuelva a calcular con los datos y matchee el `FCs` que le llego ,para
  garantizar la integridad de la trama y que es valida, o que no tuvo errores.
  Ethernet no corrije errores, solo los detecta y los throwea o descarta.

# IP(Internet Protocol): detectar y corregir errores?

IP fue diseñado para ser simple y eficiente, este protocolo tampoco corrige errores,
pero tiene mecanismos para detectarlos en cierta parte de los datos y asi descartarlos.

Los datagramas IP cuentan con un field de 2 bytes llamado `Header Checksum`
que se calcula sumando los valores binarios de todas las partes del header IP, y tomando
el complemento +1 de ese resultado.
Muy parecido a como lo hace Ethernet, con diferencia de que aca `no se tienen en cuenta los datos`
para calcular ese numero, solamente el header, de ahi su nombre ...

El receptor vuelve a calcular eso segun los fields en el header para garantizar la integridad del paquete
y que no hubo errores en el mismo, `INVALID CHECKSUM`.

# ATM: detectar y corregir errores?

ATM (Asynchronous Transfer Mode), a diferencia de los anteriores, es un protocolo que utiliza
celdas de tamaño fijo para transmitir los datos, y tiene mecanismos no solamente para detectar, sino
tambien para corregir errores.
por que `ASynchronous`? aca las celdas se transmiten de manera asincrona en lugar de sincrona,
se envia cada celda segun sea necesario

las tramas ATM se dividen en celdas de size fijo de 53 byte, lo cual genera que el proceso
de comunicacion sea mas simple y acelere el procesamiento.

- 5 bytes para el header
- 48 bytes para los datos (payload)

ATM cuenta con un campo llamado `HEC` = `Header Error Control`, que como ethernet, usa un algoritmo
`CRC-8` sobre los primeros 4 bytes del header.
aca si se detecta que el valor calculado no coinicide con el que se envio al principio, hay
un error en el header... ATM puede corregir errores de 1bit en el encabezado utilizando este campo,
osea, si el error es de 1 bit, el receptor intenta corregirlo basandose en el algoritmo.
si hay mas de 1 bit de error, no se puede corregir y se descarta.

# MPLS: detectar y corregir errores? :X:

`¡MPLS! (Multiprotocol Label Switching)` es una tecnologia multiprotocolo, diseniada para acelerar
el enrutamiento y mejorar la calidad de servicio.

MPLS no incluye mecanismos de deteccion o correcion dentro de si (o de sus tramas) --
este depende de protocolos `subyacentes (que estan debajo)` como Ethernet o IP, para manejar
esos problemas.

Recordar que MPLS utiliza etiquetas en lugar de direcciones IP para enrutar paquetes,
lo que simplifica y acelera el enrutamiento, podrian llegar paquetes con labels invalidos
y estos podrian ser descartados, pero es ajeno al manejo/control de errores.

---

# ATM: Capa de adaptacion

La capa de adaptacion de ATM, conocida como `AAL`(ATM Adaptation layer) es una capa
diseniada para permitir que los diferentes tipos de trafico (voz,video,texto,etc,) se
transmitan eficientemente sobre la red.

> Se le llaman `celdas` a los `paquetes` de ATM porque estas son de tamanio fijo.

**El proposito principal?**: adaptar los datos de las aplicaciones o protocolos superiores
a los `53bytes` que usa ATM, basicamente esta capa "traduce" o "adapta" los datos de las capas
superiores para que puedan transportarse en celdas ATM de menor tramanio (`53bytes`)

y como funciona?:

- **fragmentacion y ensamblado**: divide todos los datos en fragmentos de `48` bytes para que
  entren en el payload de los paquetes ATM, (el header se mantiene igual).
  el receptor luego re-ensambla los datos en su formato original.

## Variantes

las variantes , o distintos tipos de capas de adaptacion `AAL`, surgen por los requisitos y tipos
de trafico:

- `AAL1` (trafico en tiempo real de voz/video sin compresion)
- `AAL2` (trafico en tiempo real variable: voz comprimida)
- `AAL3/4` (datos con errores manejables: orientados o no a conexion)

---

# Protocolos de ruteo
El proceso de ruteo o enrutamiento ocurre en la capa 3 (Network) del model OSI.

los protocolos de ruteo o "enrutamiento" tienen como función principal el determinar el mejor camino para que los paquetes de datos viajen a través de la red, desde el origen hasta el destino, pasando por varios dispositivos intermedios (como routers o conmutadores). 
Estos protocolos permiten que los routers intercambien información sobre las redes que conocen, de modo que puedan tomar decisiones informadas acerca de cómo enviar los paquetes de datos.

**Toma de Decisiones de Enrutamiento**
los protocolos de enrutamiento determinan cuál es el mejor camino para un paquete basándose en diversos factores como:

- `HOPS`: El número de saltos entre los routers.
- `Costo`: El costo asociado con la transmisión (por ejemplo, ancho de banda, latencia).
- `Métricas específicas`: Como el ancho de banda, el retardo o la fiabilidad de la ruta.

en la tabla de ruteo es donde se almacena la informacion de topologia, o mejor dihco
, la informacion que tiene el HOST dispositivo acerca de la red.

```shell
┌──(guido㉿kali)-[~]
└─$ route -e
Kernel IP routing table

Destination     Gateway         Genmask         Flags   MSS Window  irtt Iface
default         _gateway        0.0.0.0         UG        0 0          0 eth0
default         _gateway        0.0.0.0         UG        0 0          0 wlan0
172.17.0.0      0.0.0.0         255.255.0.0     U         0 0          0 docker0
192.168.0.0     0.0.0.0         255.255.255.0   U         0 0          0 eth0
192.168.0.0     0.0.0.0         255.255.255.0   U         0 0          0 wlan0
```

## Tipos de Protocolos de Enrutamiento:
- Protocolos de Enrutamiento Interior (IGP, Interior Gateway Protocol):
Se usan dentro de una misma red o dominio de enrutamiento.
    - RIP (Routing Information Protocol): Protocolo de distancia-vector.
    - OSPF (Open Shortest Path First): Protocolo de estado de enlace.
    - EIGRP (Enhanced Interior Gateway Routing Protocol): Protocolo híbrido (combinación de distancia-vector y estado de enlace).

- Protocolos de Enrutamiento Exterior (EGP, Exterior Gateway Protocol):
Se utilizan para intercambiar información de enrutamiento entre diferentes dominios de enrutamiento (por ejemplo, entre diferentes ISPs).

- BGP (Border Gateway Protocol): El protocolo utilizado para el enrutamiento entre sistemas autónomos (AS), esencial para el enrutamiento en Internet.


# CSMA/CD (en ethernet) - (Carrier-Sense Multiple-Access / Colission Detection)

- Existe `CSMA/CD` , que basicamente
  es un mecanismo de control que usa ethernet para detectar colisiones en un dado medio para asi despues descartarlas.
  Carrier sense viene porque se escucha el medio (cable ethernet), si esta libre, se puede
  transmitir, si no, esperamos.
  Multiple access porque todos los dipositivos en la red comparten el mismo cable, lo cual
  genera que varios puedan intentar transmitir al mismo tiempo (aca se generan colisiones)
  Las colisiones se detectan cuando se ve un cambio abrupto en el voltaje del medio.

### que pasa si hay una colision?

=> cuando una colision es detectada, todos los dispositivos involucrados dejan de transmitir
=> se envia un `JAM` para garantizar que todos detecten la colision
=> se espera un X tiempo aleatorio (backoff algoritmo) antes de intentar a transmitir

