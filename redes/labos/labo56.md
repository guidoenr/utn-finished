
# TCP/IP MODEL VS OSI MODEL
![](images/2022-06-23-10-55-10.png) 
![](images/2022-06-23-10-55-37.png) 
![](images/2022-06-23-10-57-20.png) 

---
# RELACION CON NIVEL 2 (TCP/IP)
## Determinar si una trama es unicast, multicast o broadcast
- Viendo la direccion DESTINO
    - **BROADCAST:**
    - si es a nivel de mac-address es todo ff.ff.ff.ff
    - si es a nivel IP seria 255.255.255.255

## Como determinar a una VLAN por defecto o una VLAN por negocios
![](images/2022-06-23-15-31-11.png) 
- Tenes que ver el etiquetado que le da el 801Q (vlan tag)
- Puede ser que esta trama no tenga nada (seguramente es este caso)

## Identificar el grupo hexa que indica el inicio de los datos de la trama y su longitud
- recordar que el header/cabecera TCP ocupa 20 bytes
- Te fijas el **HEADER_LENGTH** dentro de -> Transmission Control Protocol 
- Despues de eso son todos datos
![](images/2022-06-23-11-17-45.png) 
![](images/2022-06-23-11-11-23.png) 

## ¿Este campo de datos será del mismo tamaño en otra trama que tenga los mismos hosts origen y destino? ¿Qué longitud deberá tener?
- No necesariamente, puede llegar hasta los 1460 bytes por paquete

## Encabezado PDU?
![](images/2022-06-23-11-24-24.png) 

## Protocolo encapsulado? bueno , depende de que trama
- Esta es la Internet Protocol Version 4
![](images/2022-06-23-11-26-25.png) 

---
# Rleacion con nivel 3 (TCP/ip)

## ¿Esta captura representa una PDU única, un fragmento intermedio o el último fragmento?, en cualquier caso ¿qué valor tiene el grupo HEXA del campo que identifica el paquete
- PDU UNICA, Fragment Offset:0, Flags: 0x00