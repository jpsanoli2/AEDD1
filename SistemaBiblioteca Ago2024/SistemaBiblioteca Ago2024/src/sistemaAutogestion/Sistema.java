package sistemaAutogestion;

public class Sistema implements IObligatorio {
    private ListaEstudiante listaEstudiante;
    private ListaLibro listaLibro;
    private ListaPrestamo listaPrestamo;
    private ColaReserva colaReserva;
    
    @Override
    public Retorno crearSistemaDeGestion() {
        listaEstudiante = new ListaEstudiante();
        listaLibro = new ListaLibro();
        listaPrestamo = new ListaPrestamo();
        colaReserva = new ColaReserva();
        return Retorno.ok();
    }

    @Override
    public Retorno agregarEstudiante(String nombre, String apellido, int numero) {
        Estudiante e = new Estudiante(nombre, apellido, numero);
        if(!e.ValidarNombre())return Retorno.error1();
            
        if(!e.ValidarNumero())return Retorno.error2();
            
        if(listaEstudiante.Get(numero) != null) return Retorno.error3();
            
        listaEstudiante.Add(e);
        return Retorno.ok();
    }

    @Override
    public Retorno obtenerEstudiante(int numero) {
        if(numero <= 0 || numero > 500000) return Retorno.error1();
        
        Estudiante e = listaEstudiante.Get(numero);
        if(e == null)return Retorno.error2();
        
        return Retorno.ok(e.toString());
    }

    @Override
    public Retorno eliminarEstudiante(int numero) {
        if(numero <= 0 || numero > 500000) return Retorno.error1();
        
        if(listaEstudiante.Get(numero) == null) return Retorno.error2();
            
        listaEstudiante.Remove(numero);
        return Retorno.ok();
    }

    @Override
    public Retorno agregarLibro(String nombre, String ISBN, String categoria, int cantidad) {
        Libro l = new Libro(nombre, ISBN, categoria, cantidad);
        if(!l.Validar()) return Retorno.error1();
        
        if(listaLibro.Get(ISBN) != null) return Retorno.error2();
        
        if(!l.ValidarCantidad()) return Retorno.error3();
        
        listaLibro.Add(l);
        return Retorno.ok();
    }
    
    @Override
    public Retorno eliminarLibro(String ISBN) {
        if(ISBN == null || ISBN.trim().isBlank()) return Retorno.error1();
        
        if(listaPrestamo.GetXISBN(ISBN) != null) return Retorno.error2();
        
        listaLibro.Remove(ISBN);
        return Retorno.ok();
    }

    @Override
    public Retorno prestarLibro(String ISBN, int numero) {
        if(ISBN == null || ISBN.trim().isBlank()) return Retorno.error1();
         
        if(listaLibro.Get(ISBN) == null) return Retorno.error2();
         
        if(listaEstudiante.Get(numero) == null) return Retorno.error3();
         
        if(listaLibro.Get(ISBN).GetCantidad() == 0) return Retorno.error4();
        
        Prestamo prestamo = listaPrestamo.Get(ISBN, numero);
        if(prestamo != null && prestamo.GetEstado().equals("activo") 
        || listaPrestamo.PrestamosActivos(numero) >= 8) return Retorno.error5();
         
        Prestamo p = new Prestamo(ISBN, numero);
        listaPrestamo.Add(p);
        Libro l = listaLibro.Get(ISBN);
        l.Prestar();
        return Retorno.ok();
    }
    

   @Override
    public Retorno reservarLibro(String ISBN, int numero) {
        if(ISBN == null || ISBN.trim().isBlank()) return Retorno.error1();
        
        Libro l = listaLibro.Get(ISBN);
        Estudiante e = listaEstudiante.Get(numero);
        if(l == null) return Retorno.error2();
        
        if(e == null) return Retorno.error3();
        
        if(l.GetCantidad() > 0) return Retorno.error4();
        
        Reserva r = new Reserva(ISBN, numero);
        colaReserva.Add(r);
        return Retorno.ok();
    }
    
    @Override
    public Retorno devolverLibro(String ISBN, int numero) {
        if(ISBN == null || ISBN.trim().isBlank()) return Retorno.error1();
        
        Libro l = listaLibro.Get(ISBN);
        Estudiante e = listaEstudiante.Get(numero);
        Prestamo p = listaPrestamo.Get(ISBN, numero);
        if(l == null) return Retorno.error2();
        
        if(e == null) return Retorno.error3();
        
        if(p == null) return Retorno.error4();
        
        Reserva res = colaReserva.obtenerPrimeraReservaPorISBN(ISBN);
        if(res != null){
            Prestamo nuevo = new Prestamo(ISBN, res.GetNumero());
            listaPrestamo.Add(nuevo);
        }
        else{
            p.ToFinalizado();
            l.Devolver();
        }
        return Retorno.ok();
    }

    @Override
    public Retorno listarEstudiantes() {
        String resultado = listaEstudiante.listarEstudiantes();
        return Retorno.ok(resultado);
    }

    

    @Override
    public Retorno listarLibros() {
        String resultado = listaLibro.listarLibros();
        return Retorno.ok(resultado);
    }

    @Override
    public Retorno listarLibros(String categoria) {
        String resultado = listaLibro.listarLibros(categoria);
        return Retorno.ok(resultado);
    }

    @Override
    public Retorno listarPrestamos(int numero) {
           return Retorno.noImplementada();
    }

    @Override
    public Retorno librosMasPrestados() {
             return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerEliminaciones(int n) {
             return Retorno.noImplementada();
    }

    @Override
    public Retorno cantidadPrestamosActivos(String ISBN) {
           return Retorno.noImplementada();
    }

    @Override
    public Retorno prestamosXCategoría() {
           return Retorno.noImplementada();
    }

}
