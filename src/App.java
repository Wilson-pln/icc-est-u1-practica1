import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {
    private static void consulta(String id, String criterio, List<Estudiante> original,
            Comparator<Estudiante> comparador) {
        List<Estudiante> copia = new ArrayList<>(original);
        copia.sort(comparador); 

        System.out.println("--- " + id + ": " + criterio + " ---");
        System.out.println("Codigos: " + codigos(copia));
        for (Estudiante e : copia) {
            if (id.equals("C7")) {
                System.out.println(e + " | nota ajustada=" + Comparators.notaAjustada(e));
            } else {
                System.out.println(e);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Estudiante> original = DatosEstudiante.crearEstudiantes();
        List<String> codigosOriginales = codigos(original);

        System.out.println("=== CONSULTAS ===");
        consulta("C1", "codigo ascendente (orden natural)", original, null);
        consulta("C2", "nombre asc; codigo asc (lambda)", original, Comparators.c2lamda());
        consulta("C3", "nota asc, sin desempate", original, Comparators.c3());
        consulta("C4", "nota desc; nombre asc", original, Comparators.c4());
        consulta("C5", "edad asc; nombre desc", original, Comparators.c5());
        consulta("C6", "carrera asc; nota desc; nombre asc; codigo asc", original, Comparators.c6());
        consulta("C7", "nota ajustada desc; nombre asc; codigo asc", original, Comparators.c7());

        ejecutar(original, codigosOriginales);
    }

    
    private static int total = 0;
    private static int aprobadas = 0;

    public static List<String> codigos(List<Estudiante> lista) {
        List<String> resultado = new ArrayList<>();
        for (Estudiante e : lista) {
            resultado.add(e.getCodigo());
        }
        return resultado;
    }
    // verificar q no haya algun elemento mayor q el siguiente elemento sefun el comparador

    public static boolean estaOrdenada(List<Estudiante> lista, Comparator<Estudiante> comp) {
        for (int i = 0; i < lista.size() - 1; i++) {
            if (comp.compare(lista.get(i), lista.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
    // no duplicados ni que se perdiera codigo
    public static boolean conservaRegistros(List<Estudiante> original, List<Estudiante> resultado) {
        List<String> a = codigos(original);
        List<String> b = codigos(resultado);
        Collections.sort(a);
        Collections.sort(b);
        return a.equals(b);
    }

    private static List<Estudiante> ordenar(List<Estudiante> original, Comparator<Estudiante> comp) {
        List<Estudiante> copia = new ArrayList<>(original);
        copia.sort(comp);
        return copia;
    }

    private static void registrar(String escenario, String esperado, String obtenido) {
        boolean pasa = esperado.equals(obtenido);
        total++;
        if (pasa) {
            aprobadas++;
        }
        System.out.println("Escenario: " + escenario);
        System.out.println("  Esperado: " + esperado);
        System.out.println("  Obtenido: " + obtenido);
        System.out.println("  Estado:   " + (pasa ? "PASA" : "FALLA"));
    }

    private static String primeros(List<Estudiante> lista, int n) {
        return codigos(lista).subList(0, n).toString();
    }

    public static void ejecutar(List<Estudiante> original, List<String> codigosOriginales) {
        System.out.println("=== PRUEBAS ===");

        //C1 completo

        List<Estudiante> c1 = new ArrayList<>(original);
        c1.sort(null);
        registrar("C1 con los 20 registros",
                "[E001, E002, E003, E004, E005, E006, E007, E008, E009, E010, "
                + "E011, E012, E013, E014, E015, E016, E017, E018, E019, E020]",
                codigos(c1).toString());

                // Aceptacion: prefijos
        List<Estudiante> c2 = ordenar(original, Comparators.c2lamda());
        registrar("C2 primeros 4", "[E005, E010, E014, E017]", primeros(c2, 4));

        List<Estudiante> c3 = ordenar(original, Comparators.c3());
        List<String> con85 = new ArrayList<>();
        for (Estudiante e : c3) {
            if (Double.compare(e.getNota(), 8.5) == 0) {
                con85.add(e.getCodigo());
            }
        }
        registrar("C3 estabilidad con nota 8.5",
                "[E014, E007, E005, E008, E010]", con85.toString());

        List<Estudiante> c4 = ordenar(original, Comparators.c4());
        registrar("C4 primeros 3", "[E017, E011, E001]", primeros(c4, 3));

        List<Estudiante> c5 = ordenar(original, Comparators.c5());
        registrar("C5 primeros 3", "[E001, E016, E004]", primeros(c5, 3));

        List<Estudiante> c6 = ordenar(original, Comparators.c6());
        registrar("C6 primeros 5", "[E001, E018, E005, E010, E014]", primeros(c6, 5));

        List<Estudiante> c7 = ordenar(original, Comparators.c7());
        registrar("C7 primero: E001 con ajustada 10.0",
                "E001 10.0 9.5",
                c7.get(0).getCodigo() + " " + Comparators.notaAjustada(c7.get(0)) + " " + c7.get(0).getNota());
        registrar("C7 segundo: E018 con ajustada 9.7",
                "E018 9.7 9.2",
                c7.get(1).getCodigo() + " " + Comparators.notaAjustada(c7.get(1)) + " " + c7.get(1).getNota());

                //Equivalencia del c2

                List<Estudiante> c2b = ordenar(original, Comparators.c2Comparing());
        registrar("C2 lambda y C2 comparing() dan la misma secuencia",
                codigos(c2).toString(), codigos(c2b).toString());

                //verificacion de registros y orden verificado

                List<List<Estudiante>> resultados = new ArrayList<>();
        resultados.add(c1);
        resultados.add(c2);
        resultados.add(c3);
        resultados.add(c4);
        resultados.add(c5);
        resultados.add(c6);
        resultados.add(c7);
        List<Comparator<Estudiante>> comparadores = new ArrayList<>();
        comparadores.add(Comparator.naturalOrder());
        comparadores.add(Comparators.c2lamda());
        comparadores.add(Comparators.c3());
        comparadores.add(Comparators.c4());
        comparadores.add(Comparators.c5());
        comparadores.add(Comparators.c6());
        comparadores.add(Comparators.c7());
        for (int i = 0; i < resultados.size(); i++) {
            String id = "C" + (i + 1);
            registrar(id + " conserva los 20 codigos sin perdidas ni duplicados",
                    "true", String.valueOf(conservaRegistros(original, resultados.get(i))
                            && resultados.get(i).size() == 20));
            registrar(id + " ordenado segun comparaciones consecutivas",
                    "true", String.valueOf(estaOrdenada(resultados.get(i), comparadores.get(i))));
        }

        List<Estudiante> empate = new ArrayList<>();
        empate.add(original.get(0));   // E014
        empate.add(original.get(6));   // E005
        empate.add(original.get(18));  // E010
        empate.sort(Comparators.c4());
        registrar("C4 sobre E014, E005, E010 (empate en nota y nombre)",
                "[E014, E005, E010]", codigos(empate).toString());


                Estudiante alto = new Estudiante("E021", "Zoe", 20, 9.8, "Computacion");
        registrar("Caso propio 1: nota 9.8 de Computacion se ajusta a 10.0 (maximo) y la original sigue en 9.8",
                "10.0 9.8",
                Comparators.notaAjustada(alto) + " " + alto.getNota());

                List<Estudiante> mayus = new ArrayList<>();
        mayus.add(new Estudiante("E031", "ana", 20, 8.0, "Computacion"));
        mayus.add(new Estudiante("E032", "Ana", 20, 8.0, "Computacion"));
        mayus.sort(Comparators.c2lamda());
        registrar("Caso propio 2: C2 con 'ana' y 'Ana' (sensible a mayusculas)",
                "[E032, E031]", codigos(mayus).toString());

                registrar("Original intacta despues de todas las consultas",
                codigosOriginales.toString(), codigos(original).toString());

        System.out.println();
        System.out.println("Resumen: " + aprobadas + " de " + total + " pruebas aprobadas");
    }
}



        



