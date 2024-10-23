package sistemaAutogestion;

public class JavaApplication3 {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Estudiante e = new Estudiante("Juan", "Sanchez", 99);
        System.out.println(e.toString());
    }
    
}
