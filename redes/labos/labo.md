```bash
# [USER MODS COMMANDS]
- enable # privilegiado mod
- configure terminal # modo full config
- enable secret {password} # configura password para el modo privilegiado

# [SHOW/INFO COMMANDS]
- show interface {id} # info de interface especifica
- show interfaces # info interfaces
- show ip route # info sobre el routeo
- show mac-address-table # [dentro del switch] muestra la lista de macaddress
- show running-config # muestra todo
- show spanning-tree active # cual es el root , como estan los puertos
- show spanning-tree detail # + info 
- show spanning-tree summary # como funciona el spanning-tree
- show vlan # lista las vlans definidas y la asignacion de puertos

# [CONFIG COMMANDS]
- copy running-config startup-config # copia la config actual a la que se inicia el switch
- write # same as above

# [INTERFACE CONFIGURATION COMMANDS]
- interface f 0/x # ejemplo fastEthernet 0/1
- interface range f0/1-10 # ejecutar comandos en varias interfaces simultáneamente
- shutdown # apagas la interfaz
# como hacer sub-interfaces
- interface fastEthernet 0/0.10
- interface fastEthernet 0/0.20

# [VLANS] : notar que las vlans son interfaces tambien , pero logicas, no fisicas.
- interface vlan 1 # acceder a la vlan 1 
- vlan 10 # crear la vlan y su numero
- name nombreDeVlan # asignarle nombre a la vlan
# configurando un puerto para que se sume a una vlan especifica
- interface f0/1 # entras al puerto
- switchport access vlan 10 # y con esto haces que ese puerto pertenezca a esta vlan
# configurando puerto de switch para que este en modo trunk
- interface f0/24 
- switchport mode trunk

# [STP]
- hostname sw-nucleo 
- spanning-tree vlan 1,10,20 root primary # configuras el spanning tree, diciendole que use esas vlans y que el switch donde estas sea el primario
- port-channel load-balance $? # ese ? te va ayudar a elegir el tipo del load-balance
     
# [HOST COMMANDS]
- hostname {name} # cambia el hostname
- ip address 192.168.1.105 255.255.255.0 # setear IP address a cierto host, ej un switch

# [TELNET]
# ACTIVATE TELNET ACCESS
- line vty 0 3 # habilita el telnet hasta 3 conexiones simultaneas en la line vty 0
- login 
- password {password}
- exec-timeout {minutos} 
# DESACTIVATE TELNET ACCESS
- line vty 1 15
- no transport input
- transport input ssh

# [SSH] - CONFIGURE SSH CONNECTION: nos permite acceder al switch por IP
# estos van EN EL SWITCH, no en la interface
SSH (version 2)
-------
[modo config]

- ip domain-name <domain-name (ej: tl1.com)>
- crypto key generate rsa [usar 1024 bits]
- ip ssh version 2
- line vty 0 [line vty para un solo usuario]
  - transport input ssh
  - login local
- username <username> privilege <privilege (15 el más alto)> 
- password <password>
- enable secret <secret>

- line vty 1 15 [desactivar el resto de las lineas]
  - no transport input
  - transport input ssh

- ssh -L <username> <ip> [conectar ssh desde terminal]
- telnet <ip> [conectar telnet desde terminal]

# [PORTS]
- switchport mode access # modo acceso
- switchport port-security maximum 1 # maximo 1 mac address
- switchport port-security mac-address <MAC-ADDRESS-NUMBER> # seteas la mac address0001.6314.781C
- switchport port-security violation shutdown  # hace shutdown al puerto si entra un MAC no authorizada
# como hacer un PORT-CHANNEL: usar dos cables como uno
- interface f0/correspondiente
- channel-protocol lacp
- channel-group 1 mode active

# [EXTRAS]
- no {$command} # revert a command, example `no shutdown` means UP
- tracert {ip} # ya sabes lo q es


```

# WIRESHARK
- escucha todo lo que pasa por la placa de red, la tuya en debian se llama `enp5s0`

## filtros: (arriba a la izquierda)
- IP SOURCE:  `ip.src ==  192.168.0.188`
- IP DESTINATION: `ip.dst == 192.168.0.1`  -> tu gateway = router
  - se pueden concatenar los filtros con `&&`
- POR algun protocolo: `icmp` o `udp` o `tcp` etc..

<img src="2022-06-11-12-33-14.png" width="600" height="400">

![](images/2022-06-11-12-33-14.png) 
