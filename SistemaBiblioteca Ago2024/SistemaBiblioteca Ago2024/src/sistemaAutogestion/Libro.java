package sistemaAutogestion;

public class Libro {
    private String nombre;
    private String ISBN;
    private String categoria;
    private int cantidad;
    
    public Libro(String nombre, String ISBN, String categoria, int cantidad){
        this.nombre = nombre;
        this.ISBN = ISBN;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }
    public boolean Validar(){
        return !(nombre == null || nombre.trim().isBlank() || ISBN == null || ISBN.trim().isBlank() ||
                categoria == null || categoria.trim().isBlank()
                );
    }
    public boolean ValidarCantidad(){
        return !(cantidad <= 0);
    }
    public int GetCantidad(){
        return this.cantidad;
    }
    public void Prestar(){
        cantidad -= 1;
    }
    public void Devolver(){
        cantidad += 1;
    }
    public String GetISBN(){
        return this.ISBN;
    }
    @Override
    public String toString() {
        return nombre + "#" + ISBN + "#" + categoria;
    }
    public String GetCategoria(){
        return this.categoria;
    }
    public String GetNombre(){
        return this.nombre;
    }
}
