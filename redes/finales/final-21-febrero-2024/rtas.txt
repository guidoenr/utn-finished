# Como hace TCP para:
## Detectar errores?
el gran Transmission Control Protocol no corrige errores directamente, sino que los maneja solicitando retransmisiones.

- Cada paquete TCP tiene un campo `Checksum` en el header, que se calcula sumando los valores binarios de **todas las partes del segmento**, incluyendo datos y header, y tomando el `+1` de ese valor, como hace ip?
es como una funcion de hash, se genera un checksum antes de enviarlo y el receptor lo vuelve a hacer para descubrir si estos coinciden
if coinciden {
    todo okey
} else {
    el paquete tiene errores y es descartado
}


- TCP usa los famosos `Sequence Number` para identificar los datos que se van transmitiendo, si el receptor nota saltos sin sentidos entre los numeros de secuencia, se han perdido segmentos.
- TCP tambien recibe el famoso `ACK`, si esto no se recibe dentro de un timeout, se asume que hubo un error (perdida/corrupcion).

## corregir errores? 
TCP no corrije errores

## que hace el RTO
El RTO (Retransmission Timeout) es el tiempo que el protocolo espera antes de retransmitir un segmento que no ha sido confirmado por el receptor.

este valor es calculado en funcion del Round-Trip Time o "RTT" que es el tiempo del paquete en ir y volver (paquete + recepecion del ACK).

su principal funcion es garantizar la fiabilidad en la entrega de datos al manejar la perdida de paquetes, lo hace determinando cuanto tiempo debe esperar el emisor antes de retransmitir un segmento no confirmado. 

=> Uso del RTO:
Cuando TCP envía un segmento, inicia un temporizador con el valor calculado de `RTO`.
Si recibe el ACK dentro del tiempo establecido, el temporizador se reinicia para el siguiente segmento.

=> Adaptación dinámica:
Si la red tiene más latencia (por ejemplo, por congestión), el RTT aumenta y el RTO se recalcula al alza.

# opción WS (Window Scale Factor)- se negocia en el three-way-handshake
es una extension en realidad, cuyo objetivo principal es aumentar el tamaño maximo
de la ventana de recepcion (receive window) mas halla del limite original de 65535 bytes de TCP.
- La opción WS se negocia durante la conexión TCP, en el proceso `three-way handshake`
- Ambas partes (emisor y receptor) deben incluir la opción Window Scale en los segmentos SYN y SYN-ACK, indicando el número de bits de desplazamiento (factor de escala) que usarán.

---

# PROTOCOLO ICMP
> esta integrado en la logica de IP, y viaja dentro de un paquete IP.
ICMP es un protocolo de capa de red (en el modelo OSI, capa 3) utilizado para **diagnosticar problemas de conectividad, reportar errores y proporcionar información sobre el estado de una red** -- es parte integral de la suite de protocolos TCP/IP y es esencial para el funcionamiento y la gestión eficiente de las redes, es como el `logger`.

ICMP suele informar problemas con el envio y entrega de paquetes IP
-> `NO TRANSPORTA DATOS DE USUARIO, solo mensajes de control/error`

tenes dos tipos de mensajes ICMP:
1) **"Reportes de error"**
- "Destino Incalanzable": cuando un paquete no puede llegara destino, como por ej, `host unreachable`/ `network unreacheable`/`protocol not allowed`
- "Tiempo de espera agotado": se genera cuando el TTL de un paquete llega a `0`, indicando un bucle en la red.

2) consulta/respuesta
- "Echo request/reply": basicamente son `pings` para ver si hay conectividad
- "Router advertisment/RouterS Solicitacion": ayuda para descubrir routers en la red.

`ping` / `traceroute` son herramientas que sin ICMP no funcionarian.

## "`Se necesita fragmentar pero DF(don't fragment) esta activado`"
esto sucede cuando un paquete IP es demasiado grande para ser transmitido por la red, y pero el campo que dice DONT FRAGMENT esta activado... entonces, que paso?

- el `MTU` (maximum transfer unit) puede haber sido de 1400 bytes y el paquete era de 3000 bytes, entonces no entraba.
- que DF este activado va en el IP header (FLAGS field)

entonces lo que pasa es que se envia una notificacion o mensaje ICMP al host con el tipo
- 3:( Destino Inalcanzavble) 
y el 
- codigo 4: (Necesita fragmentacion pero DF esta activado)

# Protocolo MPLS, que es un LSR y que campos tiene una etiqueta.
MPLS (Multiprotocol Label Switching) es una tecnología de conmutación de paquetes que se utiliza para mejorar la eficiencia del enrutamiento de tráfico en redes de área amplia (WAN). En lugar de realizar el enrutamiento a través de la inspección del encabezado IP de cada paquete, como sucede en las redes tradicionales, MPLS utiliza etiquetas (labels) para tomar decisiones de conmutación, lo que permite una conmutación más rápida y flexible de los paquetes.

Un `LSR (Label Switch Router)` es un dispositivo dentro de una red MPLS que realiza el trabajo principal de conmutación de etiquetas. Los LSRs operan en el camino de datos de MPLS y se encargan de encaminar los paquetes en función de las etiquetas MPLS en lugar de las direcciones IP tradicionales.

## ¿Qué contiene una etiqueta MPLS?
Una etiqueta MPLS es una pequeña secuencia de bits que se utiliza para identificar y enrutar los paquetes dentro de la red MPLS. La etiqueta se inserta entre la capa de enlace de datos (por ejemplo, Ethernet) y la capa de red (por ejemplo, IP) en el paquete. El encabezado de la etiqueta MPLS contiene varios campos importantes:

```shell
+---------+-----------+--------+---------+-----------+
|  Label  |  Exp (1)  |  S (1) |  TTL (8)| Reserved  |
+---------+-----------+--------+---------+-----------+
|  (20)   |   (1)     |  (1)   |  (8)    |   (2)     |
+---------+-----------+--------+---------+-----------+
```

- `Label (20 bits)`:
Este campo contiene el valor de la etiqueta MPLS, que es utilizado por los routers LSR para tomar decisiones de conmutación.

- `Exp (1 bit)`:
Este bit está reservado para Expedited Forwarding (EF), y se usa en la gestión de calidad de servicio (QoS) para indicar si el paquete tiene prioridad.

- `S (1 bit)`:
El bit S indica si esta es la última etiqueta en la pila de etiquetas. Si está a 1, es la última etiqueta. Si está a 0, hay más etiquetas en la pila.

- `TTL (8 bits)`:
Similar al TTL de los paquetes IP, el TTL en MPLS se utiliza para evitar que los paquetes circulen indefinidamente. Cada vez que un paquete pasa por un LSR, el valor de TTL se decrementa. Si el TTL llega a cero, el paquete es descartado.

- `Reserved (2 bits)`:
Este campo está reservado para futuros usos o extensiones de MPLS y actualmente no se utiliza.
