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

    // C3 Nota ascendente, sin desempate

    public static Comparator<Estudiante> c3() {
        return Comparator.comparingDouble(Estudiante::getNota);
    }

    // C4 Nota descendente; nombre ascendente
    //reversed y thenComparing.

    public static Comparator<Estudiante> c4() {
        return Comparator.comparingDouble(Estudiante::getNota)
                .reversed()
                .thenComparing(Estudiante::getNombre);
    }

    //C5 Edad ascendente; nombre descendente.
    //comparingInt y sentido independiente para el criterio secundario.
    public static Comparator<Estudiante> c5() {
        return Comparator.comparingInt(Estudiante::getEdad)
                .thenComparing(Estudiante::getNombre, Comparator.reverseOrder());
    }

    //C6 Carrera ascendente; nota descendente; nombre ascendente; código ascendente.
    //Composición de cuatro criterios.
    public static Comparator<Estudiante> c6() {
        return Comparator.comparing(Estudiante::getCarrera)
                .thenComparing(Comparator.comparingDouble(Estudiante::getNota).reversed())
                .thenComparing(Estudiante::getNombre)
                .thenComparing(Estudiante::getCodigo);
    }
    // Lambda que calcule Math.min(10.0, nota + 0.5) para estudiantes de Computacion y conserve la nota de las demás carreras.

    public static double notaAjustada(Estudiante e) {
        if (e.getCarrera().equals("Computacion")) {
            return Math.min(10.0, e.getNota() + 0.5);
        }
        return e.getNota();
    }

    //C7 Nota ajustada descendente; nombre ascendente; código ascendente.
    
     public static Comparator<Estudiante> c7() {
        return (a, b) -> {
            int resultado = Double.compare(notaAjustada(b), notaAjustada(a));
            if (resultado != 0) {
                return resultado;
            }
            resultado = a.getNombre().compareTo(b.getNombre());
            if (resultado != 0) {
                return resultado;
            }
            return a.getCodigo().compareTo(b.getCodigo());
        };
    } 



}
