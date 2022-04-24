# **Capacidad** [C/Voltz] = Faradios(F)
## la capacidad es la propiedad que tiene un sistema de almacenar energia en el **campo electrico**
![](images/2022-01-28-11-17-54.png)
```python
aca 'V' se refiere a la diferencia de potencial
y no a el voltaje

- la carga se tomara siempre igual
  sea negativa o positiva
```

---
### Calculo de la capacidad de un capacitor esferico
![](images/2022-01-28-13-20-06.png)
![](images/2022-01-28-13-21-35.png)
![](images/2022-01-28-13-21-42.png)

---
### Calculo de la capacidad de un capacitor cilindrico
```python
- imaginate todo con placas, el de arriba tmb..
el cilindro de adentro esta cargado positivamente
y el que lo rodea negativamente, en el medio? 
nada, aire, mas adelante vas a ver dielectricos
```
![](images/2022-01-28-13-25-47.png)
![](images/2022-01-28-13-26-03.png)
```pyhton
- OBSERVAR: la capacidad depende
totalmente de la geometria del dispositivo
(fijate la formula)
```

---
### Calculo de la capacidad de un capacitor placas planas
![](images/2022-01-28-13-30-16.png)
![](images/2022-01-28-13-31-42.png)
```python
- aca depende del AREA (A) y la 
distancia entre las placas
mientras mas juntas estan las placas
mas capacidad tienen
```

---
## **<ins>Energia almacenada en un capacitor</ins>**
### (3 EXPRESIONES)
![](images/2022-01-28-13-36-42.png)

---

## **<ins>Capacitores en SERIE</ins>**
```python
- al cerrar el switch se induce carga positiva
en la primer placa, y negativa en la segunda
"recordar que en la rama serie, a corriente es constante"

- se induce el mismo tipo de carga entre las islas
 es decir +- +- +-

- NO PUEDE HABER MAS CARGA INDUCIDA 
  EN UN "CAPACITOR QUE EN OTRO", SINO   
  NO SE CUMPLIRIAN LAS LEYES DE KIRCHOFF
```
![](images/2022-01-28-13-43-04.png)

## **<ins>Capacitores en PARALELO</ins>**
```python
- suponer que C2 > C1, aca si el C2 
  va a tener mucha mas carga que el C1

- aca la corriente no es la misma 
  (ley de nodos de kirchoff)

```
![](images/2022-01-28-13-47-24.png)

---

### **Ejercicio 99**
![](images/2022-01-28-13-48-04.png)
```python
"ESCNEARIO 1": fijate que cerras el switch
y la fuente te va a cargar los capacitores
con la misma carga(estan en serie)
entonces q1 = q2
y el sentido de las placas queda +- +-

- la carga en cada capacitor te da '15uC'
esto quiere decir que la placa positiva tiene
'+15uC' y la negativa '-15uC' en cada capacitor

- la carga es la misma pero la diferencia
de potencial NO (xq depende de la capacidad)

```
![](images/2022-01-28-13-58-35.png)
```python
"ESCENARIO 2": 
al provenir estos dos capacitores de una conexion
serie terminan teniendo la misma carga
entonces terminan quedando los dos capacitores
en "paralelo" distribuyendose las cargas
en ambos capacitores
(si fuesen iguales no pasaria nada)
porque la "diferencia de potencial en ramas paralelas
"debe ser la misma"
entonces vos tenes que hallar la
V// que va a ser la diferencia de potencial
del paralelo 

```
![](images/2022-01-28-14-00-26.png)
![](images/2022-01-28-14-04-20.png)
```python
- la carga final esta ahi fijate
y no se puede ir para cualquier lado
entonces tiene que darte todo 30[uC]

finales:
q1' = 7,5 [uC]
q2' = 22,5 [uC]

```

---

### **ejercicio 100** : arrancan en paralelo
![](images/2022-01-28-14-05-53.png)
```python
- EL TRUCO esta en enfocarse en las islas
es decir, como queda el cable que une las placas
si quedan todas positivas, negativas o enfrentadas 
negativamente (++ , --,  +-, -+)
```
Escenario 1: \
![](images/2022-01-28-14-09-29.png)

Escenario 2: \
```python
- notar que pasa algo parecido,
si te fijas las caidas de potencial de los 
capacitores te dan 10V cada uno
y sumandolos al estar en serie estaria mal
porque daria 20V, entonces, es una conexion
en paralelo

- aca las islas quedan opuestas
entonces tenes que restar las cargas
```
![](images/2022-01-28-14-12-50.png)
![](images/2022-01-28-14-14-28.png)


---
### Ejercicio 105
![](images/2022-01-28-14-34-41.png)
```python
- el capacitor actua como fuente, es decir
  da energia pero no constante como una fuente
  y la carga que tiene se va a inducir en ambos
  capacitores
```
![](images/2022-01-28-14-39-11.png)
![](images/2022-01-28-14-40-34.png)
![](images/2022-01-28-14-41-44.png)
![](images/2022-01-28-14-43-29.png)
![](images/2022-01-28-14-44-45.png)

---

## <ins>**Transitorio de conexion de una rama RC**</ins>
![](images/2022-01-28-14-47-36.png)
![](images/2022-01-28-14-49-44.png)
![](images/2022-01-28-14-50-11.png)
![](images/2022-01-28-14-50-28.png)
![](images/2022-01-28-14-50-42.png)
**AHORA ANALIZAMOS QUE PASA EN DETERMINADOS SEGUNDOS**
![](images/2022-01-28-14-53-07.png)
```python
y en i(inf) -> se comporta como un circuito abierto
```
![](images/2022-01-28-14-58-43.png)
![](images/2022-01-28-15-00-10.png)
![](images/2022-01-28-15-05-39.png)

---
---
## <ins>**Transitorio de DES-conexion de una rama RC**</ins>
### (aca el capacitor ya esta cargado totalmente)
![](images/2022-01-28-15-08-59.png)
```python
- NOTAR que la corriente se invierte aca, es decir
  va a ir en el sentido opuesto al que se cargo
  (obviamente variable acordate, pero siempre en 
  ese sentido, porque el capacitor no es una bateria)
```
![](images/2022-01-28-15-11-27.png)
**y aca que pasa con la corriente y la tension?:** \
![](images/2022-01-28-15-12-56.png)
![](images/2022-01-28-15-14-18.png)
![](images/2022-01-28-15-13-50.png)
![](images/2022-01-28-15-13-59.png)

---

## Ejercicio 112
![](images/2022-01-28-15-15-51.png)
```python
- al decirnos "TRANSCURRIDO el REGIMEN TRANSITORIO"
  quiere decir que la corriente no existe, es decir
  que no va a circular corirente por las ramas
  que tienen capacitores, porque se comportan
  como circuitos abiertos
```
![](images/2022-01-28-15-21-29.png)
![](images/2022-01-28-15-23-02.png)
![](images/2022-01-28-15-25-45.png)

vos hallas entonces la dif de potencial entre 
V y P, y vas por el camino de afuera, porque
acordate que adentro no hay corrriente \
![](images/2022-01-28-15-24-33.png)
![](images/2022-01-28-15-24-43.png)
![](images/2022-01-28-15-26-24.png)

---
# **DIELECTRICOS**: materiales que no conducen la electricidad
```python
'Sigma': conductividad [siemens/metros]
```
![](images/2022-02-02-16-21-27.png)
![](images/2022-02-02-16-27-15.png)
```python
- notar que el campo original va del + al -
y se ve debilitado por el campo de polarizacion
del dielectrico que se opone

- en los extremos se generan cargas ligadas
  (qb - q bounded)
  en conjunto forman la sigma de polarizacion

- tambien existe la sigma free, que es la del campo
original

" SE OPONEN LOS SIGNOS DE LOS SIGMAS DE POLARIZACION vs campo E"
```

## Experiencia de faraday: inicialmente un C cargado sin dielectrico y despues le meto un dielectrico
![](images/2022-02-02-16-31-21.png)
```python
- obviamente, luego de ponerle el dielectrico
la diferencia de potencial en el capacitor
va a bajar porque como viste arriba, el campo
de polarizacion le saca fuerza al campo electrico
original
```

![](images/2022-02-02-16-32-04.png)
```python
- notar que la CAPACIDAD aumenta K veces
```
---

### Valores de K + propiedades de los capacitores 
![](images/2022-02-02-16-35-17.png)

---
## Ejercicio 113
![](images/2022-02-02-16-36-45.png)
![](images/2022-02-02-16-38-02.png)

---
## Ejercicio 132
![](images/2022-02-02-16-38-36.png)
![](images/2022-02-02-16-43-19.png)
```python
- recordar que la diferencia de potencial
entre placas de un capacitor de placas
planas->
  V = E . d (distancia de separacion)
```
![](images/2022-02-02-16-47-14.png)
```python
- notar que hay que hacer una fuerza
para mover el dielectrico afuera del capacitor

- tener las placas asi, es como tener 
un C con dielectrico + un C sin dielectrico en PARALELO
```
![](images/2022-02-02-16-49-22.png)

---

## Campo/sigma/carga de polarizacion/libre
![](images/2022-02-02-16-55-53.png)

---
![](images/2022-02-02-16-58-44.png)
![](images/2022-02-02-17-00-58.png)
![](images/2022-02-02-17-02-23.png)
![](images/2022-02-02-17-03-33.png)

## Ejercicio 114
![](images/2022-02-03-09-57-44.png)
```python
- encierro una SG gaussiana justo en 
los puntos que me piden y listo
```
![](images/2022-02-03-10-03-56.png)
```python
- recordar 
"""
(pide todo dentro del conductor)
POR LEY DE GAUSS, en un conductor
el campo E=0, por lo tanto  D=0
"""
```
![](images/2022-02-03-10-05-28.png)
![](images/2022-02-03-10-07-10.png)
![](images/2022-02-03-10-07-36.png)
![](images/2022-02-03-10-08-58.png)

---

## E y D en un cambio de medio material (falta toda esta teoria)

## ejercicio 120/122 ( falta tambien)
![](images/2022-02-03-10-28-01.png)
![](images/2022-02-03-10-28-13.png)

---
## ejercicio 123(piola)
![](images/2022-02-03-10-31-42.png)
![](images/2022-02-03-10-33-23.png)
![](images/2022-02-03-10-34-13.png)
![](images/2022-02-03-10-35-02.png)

## ejercicio 131
![](images/2022-02-03-10-35-25.png)
![](images/2022-02-03-10-36-23.png)
![](images/2022-02-03-10-38-19.png)
![](images/2022-02-03-10-38-35.png)
![](images/2022-02-03-10-39-23.png)
