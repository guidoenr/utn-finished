class Vehiculo{
    Segmento segmento;
    Integer patente; // va a ser el id_ en la db
    Float kms;
    String color;
    caracteristicas// TODO caracteristicas?
}
    
class Segmento{ // full, highline, coupe, etc
    String nombre, descripcion;
    Integer precioMaximo;
    Integer cantidadDePlazas;
    Integer tamanio;
}

class Reserva{
    Period periodoTiempo;
    Ubicacion ubicacion; // TODO
    Segmento segmento;
    CicloDeVida ciclo;

    float valorFinalDeReserva(){
        // cuenta de ejemplo
        // TODO, tampoco me gusta esto
        return segmento.getPrecioMaximo() * 1 /
        RepositorioReservas.reservasEnPeriodo(this.periodoTiempo,
        RepositorioReservas.getReservas(this.ubicacion)) 
                
    }

    boolean estaEnPeriodo(){
        .. TODO
    }
}

class RepositorioReservas{
    void agregar, eliminar, etc.

    List<Reserva> getReservasEnUbicacion(Ubicacion ubicacion){
        return entityManager.query{
            from "Reserva r" where "r.ubicacion = ubicacion"
        }
    }

    // TODO, no parece bueno que esto sea responsabilidad
    // del repositorio
    List<Reserva> reservasEnPeriodo(Period period, List<Reserva> reservas){
        return reservas.stream.filter(reserva ->
        reserva.estaEnPeriodo(period))
    }
}

public enum CicloDeVida{
    GENERADA, CONFIRMADA, ASIGNADA, RETIrADA, FINALIZADA
}



class Agente{
    String nombre, dni, etc..
    MedioDePago medioDePago;

}

public interface MedioDePago{

}

class MedioPago1 extends MedioDePago{
    void realizarOperacion(){
        //code 1 // strategy
    }
}

class MedioPago2 extends MedioDePago{
    void realizarOperacion(){
        //code 2 // strategy
    }
}

class MedioPago3 extends MedioDePago{
    void realizarOperacion(){
        //code 3 //strategy
    }
