package sistemaAutogestion;

public class ColaReserva {
     private Nodo frente;
    private Nodo fondo;

    private class Nodo {
        Reserva reserva;
        Nodo siguiente;

        Nodo(Reserva reserva) {
            this.reserva = reserva;
            this.siguiente = null;
        }
    }

    public ColaReserva() {
        this.frente = null;
        this.fondo = null;
    }
    public void Add(Reserva reserva) {
        Nodo nuevoNodo = new Nodo(reserva);
        if (Empty()) {
            frente = nuevoNodo;
            fondo = nuevoNodo;
        } else {
            fondo.siguiente = nuevoNodo;
            fondo = nuevoNodo;
        }
    }
    public Reserva obtenerPrimeraReservaPorISBN(String ISBN) {
    Nodo actual = frente;
    while (actual != null) {
        if (actual.reserva.GetISBN().equals(ISBN)) {
            return actual.reserva;
        }
        actual = actual.siguiente;
    }
    return null;
    }

    public boolean Empty() {
        return frente == null;
    }
    public Reserva GetReserva(String ISBN, int numero) {
    Nodo actual = frente;

    while (actual != null) {
        if (actual.reserva.GetISBN().equals(ISBN) && actual.reserva.GetNumero() == numero) {
            return actual.reserva;
        }
        actual = actual.siguiente;
    }
    return null;
    }
}
