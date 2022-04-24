# **Corriente Alterna**
### (referencia al generador que vimos con la ley de faraday)
![](images/2022-01-24-10-11-40.png)
![](images/2022-01-24-10-19-28.png)
```python
Frecuencia: cada cuantos segundos se repitio
la signal
```
## Generador aplicado a un resistor
```python
- no existe desfasaje relativo
- lo de la derecha es un diagrama fasorial
```
![](images/2022-01-24-10-29-22.png)

---
## Generador aplicado a un inductor
![](images/2022-01-24-10-34-43.png)

---
## **Generador [COMPARACION corriente-tension]**
![](images/2022-01-24-10-41-16.png)
![](images/2022-01-24-10-44-56.png)
![](images/2022-01-24-10-46-46.png)

---

## Generador aplicado a un capacitor
![](images/2022-01-24-11-03-23.png)
![](images/2022-01-24-11-05-47.png)

## Regla **CIVIL**
```python
significa:
"CIVIL" -> que en un "[C]apacitor" 
           la corriente "[I]" siempre
           viene primero que la 
           tension "[V]" 

        -> la tension "[V]" viene 
           primero que la corriente "[I]"
           en una inductancia
```
![](images/2022-01-24-11-09-53.png)

---

## **Valor MEDIO de una señal**
```python
"VALOR MEDIO": dado una senial de alterna
buscarme un valor de continua, tal que 
implique el mismo transporte neto de carga
```
![](images/2022-01-24-11-12-44.png)

---
## Valor eficaz o **RMS** asociado a una senial de alterna
```python
'RMS': root medium square, esta asociado
a la energia, con el transporte neto de
energia.
basicamente es el valor de corriente continua (pila)
que habria que aplicar a identico resistor para
que en el mismo periodo de tiempo que se aplica
la senial de alterna, disipe la misma cantidad
de energia como hace la senial de alterna
```
![](images/2022-01-24-11-19-53.png)
```python
- justamente esto es ROOT MEDIUM SQUARE
osea, la raiz del valor medio elevado al
cuadrado
```
![](images/2022-01-24-11-20-35.png)
```python
- en un toma de una casa
el valor pico es 311[V] 
el valor de pico a pico es 622[V]
el valor 'RMS' es 220[V]
```
![](images/2022-01-24-11-23-17.png)
![](images/2022-01-24-11-27-10.png)
## y de donde salen los 220V entonces?
![](images/2022-01-24-11-29-47.png)
## y de donde sale ese 314 t?
```python
-ES LA FRECUENCIA 50[Hz] de la linea (argentina)

ojo que en usa la frecuenca es 60Hz, y entonces
el valor RMS de 110[V]
```
![](images/2022-01-24-11-31-17.png)

---

## **Circuito RLC serie**
### (NO VALE LA LEY DE KIRCHOFF aca porque es alterna)
```python
- ese angulo phi, lo que hace es separar 
la tension de la corriente

- (Vl - Vc) es la suma del vector
  del capacitor y del inductor, 
  por eso te queda arriba uno 
  mas chico del original que era 
  mas grande

- dependiendo quien gane, el circuito
  cambia el nombre, sea inductivo, 
  capacitivo o resistivo

'Vg': generador
'Vl': inductor
'Vc': capacitor
'Vr': resistor
```
![](images/2022-01-24-11-53-41.png)
![](images/2022-01-24-11-58-38.png)
![](images/2022-01-24-12-14-56.png)
![](images/2022-01-24-12-15-53.png)

## Condicion de resonancia: Z = R
![](images/2022-01-24-12-19-30.png)
![](images/2022-01-24-12-21-17.png)

## Potencia Instantanea
![](images/2022-01-24-12-21-58.png)
![](images/2022-01-24-12-22-25.png)

---
## ejercicio 5
![](images/2022-01-24-12-24-44.png)
```python
- en el de continua, el L se comporta
como un cable acordate
```
![](images/2022-01-24-12-28-50.png)
![](images/2022-01-24-12-29-26.png)

---
## Ejercicio 1
```python
-notar siempre que si no te dicen
que es una bobina ideal, tiene una
resistencia interna dada por el cobre

- aplicas trigonometria al diagrama
de potencias de abajo fijate, que se 
parte del RLC serie normal
```

![](images/2022-01-25-15-19-21.png)
![](images/2022-01-25-15-24-48.png)
![](images/2022-01-25-15-29-31.png)

---
## Ejercicio 4 
![](images/2022-01-25-15-30-27.png)
```python
- notar que este es un circutio RC, no 
hay L

-podes aplicar todo lo que es trigonometria
a los diagramas fasoriales y trabajar mecha asi
fijate como hace el profe 

- fijate el diagrama fasorial
usando la regla civil 
" en un capacitor la corriente I viene primero que V"
o adelanta en 90 grados la corriente al vc
```
![](images/2022-01-25-15-34-44.png)
Otra forma: \
![](images/2022-01-25-15-36-30.png)

---

## Ejercicio 6
![](images/2022-01-25-15-36-43.png)
![](images/2022-01-25-15-39-16.png)
![](images/2022-01-25-15-40-23.png)  

---
## Ejercicio 8 
![](images/2022-01-25-15-40-52.png)
![](images/2022-01-25-15-45-31.png)
```python
-notar que cuando abris el modulo
tenes que fijarte con cual de las dos
partes del modulo quedarte
en este caso te quedaste con que
'Xl < Xc' porque XC va adelante


```
![](images/2022-01-25-15-46-33.png)

---
## Ejercicio final
```python
dice:
"""
a)
Un circuito en serie RLC esta conectado
a un generador de Vef = 15V
el w=250 radianes/segundos
la bobina es ideal y su inductancia es 20[mHenrios]
y la C del capacitor de 2000[uF]
calcular los valores posibles de la resistencia R para
que lograr que la potencia activa o media sea de 36[W]

b)
determine que capacidad C2 deberia tener
y de que forma deberia conectar un segundo
capacitor para que el circuito resuene con el 
mismo generador, la misma bobina y ambos 
capacitores juntos conectados de la manera
que usted considere adecuada (serie o paralelo)
"""

```
![](images/2022-01-25-15-50-50.png)
**NOTAR**: \
```python
INDUCTIVO: Xl > Xc > Xr
RESISTIVO: Xr > Xl > Xc
CAPACITIVO: Xc > ..
```
![](images/2022-01-25-15-57-38.png)
![](images/2022-01-25-15-58-24.png)
![](images/2022-01-25-16-07-38.png)
```python
sarpado, cuando pensas la resonancia
planteas que Xc = Xl , entonces te fijas
que tenes que hacer con el capacitor que agregas
para que el circuito resuene (Xc = Xl)
entonces para eso lo planteas en serie, dado que
la capacidad 'equivalente' termina siendo menor
```
![](images/2022-01-25-16-07-59.png)

---

## Ejercicio final
![](images/2022-02-01-16-53-39.png)
```python
lo hiciste en la hoja
```

---

## Otro ejercicio final
![](images/2022-02-01-17-20-50.png)
![](images/2022-02-01-17-31-42.png)
![](images/2022-02-01-17-34-55.png)