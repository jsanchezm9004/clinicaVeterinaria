public abstract class Paciente {
    private String nombre;
    private double peso; // en kg
    private int edad; // en años
    private String estado; // "INGRESADO", "CUARENTENA", "EN_TRATAMIENTO"
    private double factorDosis; // en mg/kg
    private double dosisAcumulada; // en mg
    private Jaula jaulaAsignada;

    public Paciente(String nombre, double peso, int edad, double factorDosis, String estadoInicial) {
        setNombre(nombre);
        setPeso(peso);
        setEdad(edad);
        this.factorDosis = factorDosis;
        this.estado = estadoInicial;
        this.dosisAcumulada = 0.0;
        this.jaulaAsignada = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: datos invalidos");
        }
        this.nombre = nombre.trim();
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("ERROR: datos invalidos");
        }
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0 || edad > 30) {
            throw new IllegalArgumentException("ERROR: datos invalidos");
        }
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getFactorDosis() {
        return factorDosis;
    }

    public double getDosisAcumulada() {
        return dosisAcumulada;
    }

    public void setDosisAcumulada(double dosisAcumulada) {
        this.dosisAcumulada = dosisAcumulada;
    }

    public Jaula getJaulaAsignada() {
        return jaulaAsignada;
    }

    public void setJaulaAsignada(Jaula jaulaAsignada) {
        this.jaulaAsignada = jaulaAsignada;
    }

    public abstract String getTipoString();
}
