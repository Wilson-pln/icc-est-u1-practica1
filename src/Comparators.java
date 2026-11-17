import java.util.Comparator;

public class Comparators {

    //C2 Nombre ascendente; código ascendente.
    
    public static Comparator<Estudiante> c2lamda() {
        return (a, b) -> {
            int resultado = a.getNombre().compareTo(b.getNombre());
            if (resultado != 0) {
                return resultado;
            }
            return a.getCodigo().compareTo(b.getCodigo());
        };
    }

    // C2 con comparing(), thenComparing()

    public static Comparator<Estudiante> c2Comparing() {
        return Comparator.comparing(Estudiante::getNombre)
                .thenComparing(Estudiante::getCodigo);
    }

    

}
