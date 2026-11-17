import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {
    private static void consulta(String id, String criterio, List<Estudiante> original,
            Comparator<Estudiante> comparador) {
        List<Estudiante> copia = new ArrayList<>(original);
        copia.sort(comparador); 

        



