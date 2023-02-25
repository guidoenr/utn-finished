Hoy vamos a hacer un entrenamiento:
- tengo varios enunciados de examenes finales y voy a enviartelos para que los entiendas, analices y comprendas mayormente su redaccion.
La mayoria estan orientados a aplicaciones o sistemas que ya existen. Como Tinder, Telegram, etc. 
- Estos examenes son escritos por profesores, que evaluan que el alumno sepa diseñar un sistema siguiendo buenas practicas de OOP , como patrones de diseño, atributos de calidad, etc.
- Vas a recibir varios enunciados para entrenarte, los cuales voy a ir enviandotelos individualmente y tu objetivo (en esta instancia) es solamente estudiarlos como mencione anteriormente.

Tu objetivo es realmente muy simple: 
- una vez hayas entrenado con todos estos enunciados de examen, yo te voy a enviar solamente el primer parrafo "Contexto" de un examen, y yo quiero que en base a todo lo que aprendiste, me generes UNICAMENTE el Enunciado Completo del examen.
En un resumen, to objetivo final va a ser escribir un enunciado completo (siguiendo un poco la estructura o patrones de los que te envie para entrenarte) pero solo tomando como input el primer parrafo que explica el "Contexto" del sistema.

Reglas generales:
- NO RESUELVAS NI ME EXPLIQUES LOS ENUNCIADOS. Van a estar delimitados por "---------" para indicar su incio y fin:
- Porfavor responde un OK cuando hayas terminado de analizarl el enunciado.
- Te voy a enviar cada enunciado con el titulo "Enunciado X" siendo X el numero de enunciado.
- Cuando te llegue el enunciado final, va a tener el titulo "Enunciado FINAL" , el cual quiero que tu respuesta sea el enunciado completo. 

Resumen:
- Vas a recibir X cantidad de enunciados para entrenarte y entender como son redactados (con OK como respuesta cuando hayas terminado)
- Cuando recibas el titulo "Enunciado FINAL" (seguido de su contexto) vas a generarme un posible enunciado completo para ese contexto.

Si entendiste tu objetivo, las reglas, y como va a consistir todo el entrenamiento sin problemas, genial.
En otro caso, puedes consultarme lo que quieras y te puedo volver a explicar.

Por lo anterior, si entendiste, demuestramelo! : Que va a contener mi siguiente mensaje?


"Enunciado 1": Recircular
Contexto:
La economía circular es un modelo de producción y consumo en que los residuos son
utilizados como recursos en nuevos procesos productivos. Es un enfoque fundamental
para lograr una economía sustentable, basado en reparar, reciclar y reutilizar para
extender la vida de los productos y minimizar el desperdicio.
Hoy diseñaremos una plataforma en línea para ayudar a las comunidades a implementar
procesos de economía circular. A través de RECircular las personas podrán poner a disposición residuos, tratados y
clasificados, y dar con otras que los necesiten.

Publicaciones:
RECircular debe permitir a cualquier persona registrada ingresar a una comunidad (ver más adelante) y desde allí
publicar ofertas y demandas de recursos. Para ello, se debe indicar si se está ofertando o necesitando, el tipo de
recurso (residuo) y la cantidad, especificada en una unidad de medida asociada al tipo de recurso. Luego, se debe
indicar si la entrega se realizará en el domicilio de quien publica, en un cierto puesto fijo de intercambio (ver más
adelante) o a convenir. Finalmente, se debe seleccionar un tipo de intercambio, esto es, el tipo de transacción que se
realizará entre las dos partes:
● gratuito
● gratuito con entrega a cargo del receptor del recurso (no habilitado si el intercambio es en un puesto fijo)
● oneroso, a un cierto precio (solo disponible en comunidades que habiliten esta opción)


Búsqueda:
El sistema debe permitir a las personas buscar publicaciones por cercanía, tipo de recurso y cantidad. El primer paso
de esta búsqueda debe ser siempre indicar si está necesitando u ofreciendo un recurso.

Intercambios:
Cualquier persona registrada puede proponer un intercambio en respuesta a una publicación, indicando la cantidad
del recurso a intercambiar. Pueden suceder dos situaciones:
● Si la cantidad coincide con la publicada (ejemplo: Héctor ofrece 30 botellas de vidrio y Analía responde que
necesita 30 también), la publicación se cierra automáticamente y el intercambio queda confirmado.
● Si la cantidad es menor a la publicada (ejemplo: Hector necesita 15 KG de cartón y Analía responde que sólo
tiene 10 KG para ofrecer), la publicación no se cierra automáticamente, sino que la dueña de la publicación
debe expedirse, indicando si lo acepta. Si esto ocurre, el intercambio queda confirmado y la publicación se
mantiene abierta, pero por una cantidad menor. En caso contrario, la publicación sigue abierta y sin cambios,
y el intercambio se cancela.
Cuando el intercambio se confirma, se comparte la información a las partes para que puedan realizar la entrega y
quedan obligadas a realizarla. Si luego la entrega no ocurriera, las partes deben informar el motivo y la publicación
vuelve a abrirse.
- la realización efectiva de los pagos queda fuera del alcance de este exámen

Sugerencias:
Para facilitar el intercambio de recursos, el sistema debe notificar automáticamente a las personas con publicaciones
abiertas que coincidan con los criterios de otra (ejemplo: Analía publicó que ofrece 1 KG de papel, y más tarde Héctor
publicó que necesita 1KG de papel, por lo que ambas partes reciben una notificación). Cuando una persona responde
a una publicación sugerida (ejemplo: Héctor responde a la de Analía o viceversa) y el intercambio es confirmado, las
dos publicaciones serán cerradas a la par.

Gestión de la comunidad:
Las publicaciones funcionarán siempre dentro de una comunidad, es decir, un espacio de intercambio administrado
por una o más personas que definen ciertas reglas. De igual forma, las búsquedas que se realizan y las sugerencias
que reciben son en el contexto de una comunidad.
Quienes administran a una comunidad deben poder configurar:
● los tipos de intercambios habilitados en la misma.
● la ubicación y horario de atención de los puesto fijos de intercambio, si los hubiera
● la descripción, nombre, características, normas de la comunidad
● los recursos que pueden ser intercambiados y su unidad de medida

IntegracionesL
La plataforma debe integrarse con un sistema externo de medición de huella de carbono, que permitirá a las
personas calcular el impacto ambiental de sus actividades de reciclaje y reutilización. Para ello, RECircular consumirá
un API REST que es capaz de informar el equivalente de CO2 ahorrado en base a la cantidad y tipo de recurso
intercambiados.

Estadísticas:
El sistema deberá mostrar estadísticas de intercambios a través de toda la comunidad, como la cantidad de
intercambios, el nivel de uso absoluto de los puestos fijos y la reducción en la huella de carbono. Todas las
estadísticas deben ser públicas. No es necesario que las mismas se actualicen en tiempo real.

Alcance y Requerimientos:
El sistema deberá permitir que:
● Las personas se registren con sus datos personales y de contacto.
● Las personas realicen publicaciones.
● Las personas realicen búsquedas de ofertas y demandas de residuos.
● Las personas realicen intercambios.
● Las administradoras gestionen su comunidad.

-----------------------
Enunciado 2: Tinder
Contexto:
Existen numerosos modelos de negocios, que toman el rol de intermediarios,
para vincular la oferta con la demanda. Un caso particular es el de vincular a 2
(dos) personas si es que éstas muestran un interés mutuo.
Nos han contratado para diseñar y desarrollar un Sistema que generalice este
modelo para otros negocios. En estos modelos se presentarán distintos actores
los cuales pueden mostrar un interés mutuo. Si esto último sucede se generará
un vínculo o “match”, que consiste en poner en contacto a las partes involucradas.

Dominio/Temática del negocio:
El dominio es la temática que se aplica al modelo de negocio. Por ejemplo, el dominio “Startups”
vincula a un inversor con un emprendedor que tiene una determinado proyecto; el dominio “Citas”
vincula a dos personas que se quieren conocer; el dominio “Banda Musical” vincula a 2(dos) o más
músicos para formar un grupo.

Perfiles:
Cada usuario crea un perfil en cada dominio que quiera participar. El mismo consiste en un nombre,
una descripción, una foto, un tipo y un conjunto de campos variables cuyos valores posibles están
predefinidos. Los tipos (por ejemplo, inversor/emprendedor o músico/banda) y los campos variables
son propios del dominio. Los campos pueden ser opcionales u obligatorios.

Búsquedas:
El Sistema le propone al usuario distintos perfiles específicos del dominio que el mismo está utilizando.
Cabe destacar que no se deben mostrar todos los perfiles en un listado sino que se deben ir mostrando
de a uno. En ese momento el usuario debe elegir si le interesa el perfil propuesto o pasar al siguiente.
Se pueden establecer filtros de los perfiles, por tipo y valores de tags. Por ejemplo, en el dominio
“Startups” un inversor puede querer elegir emprendedores en el área “tecnología”.

Vinculaciones/Matchs:
Una vez que un usuario marca interés al perfil de otro (“le da like”) y esto es mutuo, se establece un
vínculo o “match”. Cuando esto ocurre el Sistema debe permitir que las partes se pongan en contacto,
esto es, que a partir de entonces se puedan enviar mensajes.
Por otro lado existe el concepto de vinculación múltiple, que surge si hay un conjunto de personas
donde todas se dan like entre todas. En el dominio se indica si la vinculación buscada es simple o
múltiple, y en el caso de que sea este último, el número mínimo de participantes. También, hay
interesados de otras organizaciones en informarse cuando esto pasa en un determinado dominio.

Importante:
- Se debe tener traza de cuando ocurren las vinculaciones y los “likes”
- Se tiene una clase VinculadorMultiple, que expone un método que toma como parámetro un
perfil y retorna una lista de perfiles si encuentra una vinculación múltiple.
- Queda fuera del alcance actual qué sucede después de que las partes se ponen en contacto
- La gestión de usuarios y login queda fuera del alcance.

Alcance y requerimientos:
El Sistema debe:
● Permitir la creación y edición de perfil a usuarios en distintos dominios.
● Permitir la búsqueda de perfiles a usuarios.
● Permitir la vinculación (“match”) entre perfiles de usuarios.
● Permitir la vinculación (“match”) múltiple entre perfiles de usuarios.
● Permitir la gestión de los dominios y de los campos variables de los mismos.
● Guardar trazabilidad sobre los “likes” y “matchs” generados.


-----------------------
Enunciado FINAL: Steam

Contexto:
Dado el contexto global, en el tiempo cercano hubo un auge en las plataformas de streaming, esports y una gran industria de juegos indie sacando grandes éxitos. Se volvió más necesaria una plataforma donde se pueden ofrecer de forma rápida y de fácil acceso a nuevos juegos. Por lo tanto, hoy diseñaremos una plataforma de distribución de videojuegos para PC, que permita a los usuarios comprar, descargar, instalar y jugar juegos en línea.