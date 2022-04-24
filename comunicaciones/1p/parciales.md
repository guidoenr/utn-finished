![](images/2021-10-05-12-21-23.png)
```python
Ptx - Perdidas(alf conec + alf vinc + alf empalmes + FD) + G  = Srx

ENLACE = 500 Metros
Ptx = 1[W]
Srx = 0,5[W]

coaxil fino con atenuacion = 5dB/100m
coaxil grueso con atenuacion = 0,8 dB/100m

'punto a)'
Alf conectores = 500[m] * 5[db]/100[m]
Alf conectores = 25 dB

paso a dBm la potencia en Watz
1[W] = 1000[mW]
Ptx = 10 log (1000mW/1mW) 
Ptx = 30 dBm

Srx = 0,5 [W] ->  10log (500mW/1mw) 
Srx = 26,98 dBm


30dBm - (25dB) + G = 26,98 dBm
G = 26,98dBm - 30dBm + 25dBm
G = 21,99 dBm

'punto b)'

Alf conextores = 500[m] * 0,8 dB/100m
Alf conectores = 4 dB

30 dBm - 4dB + G = 26,98 dBm
G = 0,98 dBm

```
---
![](images/2021-10-05-12-57-40.png)
![](images/2021-10-05-13-54-38.png)
```python
rta: HDB -3 
tiene el menor ancho de banda
y no tiene componente continua
```

---
![](images/2021-10-05-18-17-52.png)
```python
tiene una ganancia de 20db
puesto que el amplificador es la relacion entre
potencias p1/p2 
```

---
![](images/2021-10-05-18-18-43.png)
```python
se la capacidad de un canal REAL:
obs: se trabaja todo en Khz, Kbps, etc

C = AB log2(1+S/N)

si AB = 4Khz
y C = 16,63 Kbps


S/N = 2^(C/AB) -1
S/N = 16.84


rta: e. 168,5[W] y 10w de N
```


---
![](images/2021-10-05-18-39-02.png)
```python

correcta: 
e: (esta en tu teoria fijate)
```
---
![](images/2021-10-05-18-42-12.png)
```python
rta:
c. CLEAR TO SEND o CTS
luego de enviar el CTS, que es en la etapa
donde el ETD manda una peticion para poder 
enviar, 
el ECD debe responder un 'clear to send' , que 
es como una confirmacion para poder mandar
```
---
![](images/2021-10-05-18-43-51.png)
```python
ningunba es correcta, es todo lo contrario 
de lo que dicen aca y muchas opciones 
hablan del ruido en si, fijate la teoria.md tambien

```

---
![](images/2021-10-05-18-46-23.png)
```python
1. primero hallo la informacion de cada mensaje
I(F) = log2 1/(0.125) = 3
I(E) = log2 1/(0.25) = 2
I(A) = log2 1/(0.25) = 2
I(B) = log2 1/(0.25) = 2
I(C) = log2 1/(0.125) = 3

I(FEA) = I(F) + I(E) + I(A)
I(FEA) = 3    + 2    + 2 
I(FEA) = 7 [Sh]

2. entropia de la fuente: sumar cada producto de I(Xi) * P(xi)
=>
H = (0.125)*3 + (0.125)*3 + (0.25)*2 + (0.25)*2 + (0.25)*2
H = 2.25

3. para que esos 5 simbolos sean equiprobables, todos deberian tener
0.20 de probabilidad de salir, para que su suma de uno..
de ser asi
I(xi) = log2 1/(0.2) = 2.32

```

---
## EJERCICIO PERRO SAN BERNANRDO
![](images/2021-10-05-19-13-13.png)


```python
el ruido blanco depende de 
TEMEPRATURA Y ANCHO DE BANDA

porque No = k T B 
donde T, es la temperatuira medida en Kelvin
y B es el ancho de banda medido en Hz
```

---
# **---------------------------------**
# **Parcial año anterior**
![](images/2021-10-06-09-47-53.png)

![](images/2021-10-06-10-57-42.png)
```python
S/N = 100
AB = 4Khz

C = AB log2(1+S/N)

C = 4Khz log2(1 + 100)
C = 26,63 Kbps

rta: ninguna de las anteriories
```

---
![](images/2021-10-06-09-50-20.png)
```python
FRP = 100 pps
Vm = 1000 baudios
A(amplitud del pulso) = 1 V

Donde Cn = A * d/T


FRP = 1/t = 100pps
T = 1/100 = 0,01 seg

d = t = 1/Vm = 0,001 seg

n = T/d => n = 0,01 seg / 0,001 seg = 10 armonicos

AB = n * f0 => AB = 100 * 100 = 1000 Hz

Cn = 1 V * 0,001seg / 0,01seg = 0,1 V

```
![](images/2021-10-06-09-59-54.png)
---
![](images/2021-10-06-10-06-31.png)
```python
antes que machetearte aca, lo podes pensar si no lo encontras
fijate que el ruido deberia ser el mismo en la entrada tanto
como en la salida para que no exista ruido
de todas formas , lo tenes en la ppt tambien
```
---
![](images/2021-10-06-10-08-02.png)
```python
julia te paso todo

```

---
![](images/2021-10-06-10-13-46.png)
```python
ya lo hiciste a este
```

---
![](images/2021-10-06-10-12-12.png)
![](images/2021-10-06-10-56-03.png)

---
# **---------------------------------**
![](images/2021-10-06-11-17-10.png)
![](images/2021-10-06-11-17-30.png)
![](images/2021-10-06-11-17-38.png)
![](images/2021-10-06-11-33-11.png)
![](images/2021-10-06-11-17-47.png)
![](images/2021-10-06-11-32-43.png)
# **---------------------------------**
---
![](images/2021-10-06-14-15-10.png)
![](images/2021-10-06-14-15-41.png)