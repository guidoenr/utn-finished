# CREANDO LAS VLANS EN LOS SWITCHS
+ **Vas al switch ACCESSO C1/ C2 y DISTRIBUCION**:-> (en todos se hace lo mismo)
    - `enable`
    - `config t`
    - `vlan 77` -> crea y entra a la vlan
    - `name alumnos`
    - `exit`
    - `vlan 88` -> crea y entra a la vlan
    - `name docentes`


+ **CONECTAR LOS SWITCHS Y LAS PCS** 
    - selecciones el cable cross-over
    - elegis los ultimos puertos de fastEthernet de cada switch y los conectas entre si
    - lo mismo con las pcs 
    ![](2022-06-02-11-48-01.png)

+ **CONFIGURAR LAS IPS**
El enunciado dice "La vlan 77 la cual pertenece a la maquina PC9 y PC11 tienen la IP 192.168.168.32" 
    + vas a la  PC9 -> desktop -> IP CONFIGURATION -> static
    + seteas (segun el enunciado)
        + ip : 192.168.168.34
        + netmask: 255.255.255.224 
        + gateway: 192.168.168.35
Lo mismo con la PC11:
    + vas a la  PC11 -> desktop -> IP CONFIGURATION -> static
    + seteas (segun el enunciado)
        + ip : 192.168.168.35
        + netmask: 255.255.255.224 
        + gatewa y: 192.168.168.33
    + vas a la  PC10 -> desktop -> IP CONFIGURATION -> static
    + seteas (segun el enunciado)
        + ip : 192.168.168.66
        + netmask: 255.255.255.224 
        + gateway: 192.168.168.65
    + vas a la  PC12 -> desktop -> IP CONFIGURATION -> static
    + seteas (segun el enunciado)
        + ip : 192.168.168.67
        + netmask: 255.255.255.224 
        + gateway: 192.168.168.65

+ **CONFIGURAR LOS PUERTOS DE LAS MAQUINAS PARA QUE ESTEN ASIGNADOS A UNA VLAN EN PARTICULAR** (hay que poner los que conectaste a los switches) - recordar el exit para salir de las interfaces
    - SWITCH ACCESO C1
        - `interface f0/1`
        - `switchport access vlan 77`
    - SWITCH ACCESO C1
        - `interface f0/2`
        - `switchport access vlan 88`
    - SWITCH ACCESO C2
        - `interface f0/1`
        - `switchport access vlan 77`
    - SWITCH ACCESO C2
        - `interface f0/2`
        - `switchport access vlan 88`
        
    **Configurando el puerto TRUNK entre los switches**
     - SWITCH ACCESO C1
        - `interface f0/24`
        - `switchport mode trunk`
    - SWITCH ACCESO C2
        - `interface f0/24`
        - `switchport mode trunk`
     - SWITCH ACCESO PT-Distribucion
        - `interface f1/1`
        - `switchport mode trunk`
        - `interface f2/1`
        - `switchport mode trunk`

---

# RED WIRELESS LAN
El enunciado dice que el WRT300N EXPOSITORES VA A ESTAR EN MODO BRIDGE y el INVITADOS EN MODO ROUTER \ 

- Con un cable cross-over conectamos expositores en un puerto ethernet al switch de distribucion (puertos ethernet)
- entramos a la `INTERFAZ CLI` del **EXPOSITORES**
- el enunciado dice poner una IP de la vlan 88
- entonces en IP ADDRESS ponemos
    - IP ADDRESS: 192.168.168.68
    - SM: 255.255.255.224
- guardas
- Configuras el DHCP:
    - start ip address: 192.168.168.69
    - maximum number of users: 10
    - guardas

**Wireless** -> basic
- SSSId: Exp0s1tor35
- save

**Wireless Security**
- WPA2 personal
- Encryption AES
- Passhpharese: M4sr3rstr1ctivA.-
- copiala asi te ayuda
** Conectados los equipos ahora(())

## CONECTAMOS LOS EQUIPOS
## **EN Expositor 1**
- Config ->> Wireless0
    - SSID: Exp0s1tor35
    - Passhprase: M4sr3rstr1ctivA.-
**IP CONFIGURATION**
- DHCP

![](images/2022-06-02-12-28-14.png) 
### **EN EXPOSITOR 2** (lo mismo)
- Config ->> Wireless0
    - SSID: Exp0s1tor35
    - Passhprase: M4sr3rstr1ctivA.-
**IP CONFIGURATION**
- DHCP

## EN EL SWITCH-PT distribucion 
Vamos a configurar que en el puerto 3/1 va a pasar la vlan 88, que el enunciado nos dice que las tablets esta en la misma vlans que las maquinas
- `configure terminal`
- `interface f3/1`
- `switchport access vlan 88`

--- 
# PARTE WAN UNIVERSITARIA
( el profe borro el WRT300N del smartphone PT y lo puso denuevo porque le andaba mal )
(recorda que tenes que conectar en el puerto **Internet** para que funcione como router, sino funciona como bridge)
- Conectas el Router de los smartphone con el de Router3 de la WAN universitaria
- Router de smartphones -> GUI -> InternetSetup-> Basic(el ejercicio dice que poner)
    - ip address:: 192.168.170.1
    - SM: 255.255.255.0
    - default gateway(el router 3 de la WAN): 192.168.170.2
    - save
- Mas abajo en la parte de network seup
    - IP ADDRESS: 192.168.180.1
    - SM: 255.255.255.0
    - start ip address: 192.168.180.2
    - maximum number of users: 100
    - save
- Wireless:
    - SSID: 1v1t4d0s
    - Wireless Security:
        - passhpharse: much0fu3rt3

## Conectando los smartphones al router
Invitado 3 1

![](images/2022-06-02-12-37-52.png) 

Invitado 4

![](images/2022-06-02-12-38-36.png) 

![](images/2022-06-02-12-39-15.png) 

## Router 3
- `conf t`
- `interface f0/1`
- `ip address 192.168.170.2 255.255.255.0`
- `no shutdown`
- `write (asi guardas la configuracion)`
- `exit`

Nota que si sacas el router y lo poens denuevo no tenes las interfaces seriales en la seccion de Config, entonces tenes que agregar esas interfaces:
    - Physical
    - Apagas el equipo
    - y arrastras las interfaces HWIC_2T a los dos slots 
    - prendes el equipo

--- 

# Conectando las seriales con el router
- Del router 2 al 3:
    - Cable Serial DCE en el puerto serial0/1/1 al serial 0/1/1
- Del router 3 al router 4:
    - Cable serial DCE en el puerto serial 0/1/0 al serial0/1/0
## En router 3
- CLI
- `interface serial0/1/0`
- `ip address 5.3.0.1 255.255.255.252`
- `encapsulation ppp`
- `no shutdown`
- `interface s0/1/1`
- `ip address 5.2.0.2 255.255.255.252`
- `encapsulation ppp`
- `no shutdown`
## En router 4
- `interface serial0/1/0`
- `encapsulation ppp`
## En router 2
- `interface serial0/1/1`
- `ip address 5.2.0.1 255.255.255.252`
- `encapsulation ppp`
- `clock rate 2000000`

---
## CONECTAR EL SWITCH CON EL ROUTER y el GATEWAY de ambas vlans 77 y 88
- Con un cable directo conectas el Router3 con el router EXPOSITORES
- Ahora tenes que configurarlo como trunk
    - en el fastEthernet 01
    - `interface f0/1`
    - `switchport mode trunk`
    - `no shutdown`

**dentro del router 3**: configurar las subinterfaces
- `conf t`
- `interface f0/0.77`
- `encapsulation dot1Q 77`
- `ip address 192.168.168.33 255.255.255.224`
- `no shutdown`
- `interface f0/0.88`
- `encapsulation dot1Q 88`
- `ip address 192.168.168.65 255.255.255.224`
- `no shutdown`

---
## Configurando ruta estatica del router 3 para que nos podamos comunicar dssde los smartphones al router 3
## Router 3
- `ip route` "#la ruta que cargamos como estatica"
- ip route 192.168.180.0 (que es la red que tienen todos los smartphones) 255.255.255.0 192.168.170.1 (este ultimo es el gateway)
- `ip route 192.168.180 255.255.255.0 192.168.170.1`

---

## Configurar protocolo de enrutamiento RIP para publicar las redes que tenemos conectadas
## Router 3
- `sh ip route` ( y te muestra las redes conectadas)
- `conf t`
- `router rip`
- `network 5.0.0.0 `(aca elegis las redes que queres publicar)
- `network 192.168.168.32`
- `network 192.168.168.64`
- `network 192.168.170.0`
- `redistribute static` (con este publicamos tambien las redes estaticas)
- `exit`

---
## Configurar el tunel desde el router 3 al router 4 de modo que quede conectada la VLAN 88 con el server de HTTPS, y que las demas sean por fuera de ese tunel, entonces desde una vlan tenes un salto menos
## Router 5
obs: las access list estan hechas para hacer una especie de whitelisteo, sirven para definir que redes van a poder participar del tunel.

- `conf t`
- `crypto isakmp key frba addresss 5.3.0.1`
- `crypto map mapa-docentes 111 ipsec-isakmp`
- `set peer 5.3.0.1`
- `access-list 101 permit ip host 192.168.111.111 192.168.168.64 0.0.0.31`
- `interface s0/1/0 `
- `crypto map mapa-docentes`

**ahora todo del otro extremo** ( y das vuelta la access list)
## Router 3
- `crypto isakmp policy 111`
- `encr aes 256`
- `authentication pre-share`
- `group 5`
- `lifetime 900`
- `crypto isakmp key frba address 5.4.0.2`
- `crypto ipsec transform-set 500 ah-sha-hmac esp-3des`
- `access-list 101 permit ip 192.168.168.64 0.0.0.31 host 192.168.111.111`
- `crypto map mapa-docentes 111 ipsec-isakmp`
- `set peer 5.4.0.2`
- `set security-association lifetime seconds 900`
- `set transform-set 500`
- `match address 101`
- `interface s0/1/0`
- `crypto map mapa-docentes`

# FINAL 
![](images/2022-06-02-16-10-02.png) 
