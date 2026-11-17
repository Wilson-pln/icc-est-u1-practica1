import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {
    private static void consulta(String id, String criterio, List<Estudiante> original,
            Comparator<Estudiante> comparador) {
        List<Estudiante> copia = new ArrayList<>(original);
        copia.sort(comparador);
        System.out.println("--- " + id + ": " + criterio + " ---");
        System.out.println("Codigos: " + Pruebas.codigos(copia));
        for (Estudiante e : copia) {
            if (id.equals("C7")) {
                System.out.println(e + " | nota ajustada=" + Comparators.notaAjustada(e));
            } else {
                System.out.println(e);
            }
        }
        System.out.println();
    }


            }
}


