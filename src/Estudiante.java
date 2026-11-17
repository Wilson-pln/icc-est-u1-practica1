public class Estudiante implements Comparable<Estudiante> {

    private final String codigo;
    private final String nombre;
    private final int edad;
    private final double nota;
    private final String carrera;

    public Estudiante(String codigo, String nombre, int edad, double nota, String carrera) {
        if (codigo == null || nombre == null || carrera == null) {
            throw new IllegalArgumentException("Los textos no pueden ser nulos");
        }
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser positiva");
        }
        if (Double.isNaN(nota) || Double.isInfinite(nota) || nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe ser finita y estar entre 0 y 10");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.nota = nota;
        this.carrera = carrera;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getNota() {
        return nota;
    }

    public String getCarrera() {
        return carrera;
    }
    @Override
    public int compareTo(Estudiante otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public String toString() {
        return "Estudiante{codigo=" + codigo + ", nombre=" + nombre + ", edad=" + edad
                + ", nota=" + nota + ", carrera=" + carrera + "}";
    }
}
