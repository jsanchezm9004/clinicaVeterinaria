public class Perro extends Paciente {
    private String raza;
    private boolean vacunado;

    public Perro(String nombre, double peso, int edad, String raza, boolean vacunado) {
        super(nombre, peso, edad, 10.0, vacunado ? "INGRESADO" : "CUARENTENA");
        if (peso > 80) {
            throw new IllegalArgumentException("ERROR: datos invalidos");
        }
        this.raza = raza;
        this.vacunado = vacunado;
    }

    @Override
    public void setPeso(double peso) {
        if (peso > 80) {
            throw new IllegalArgumentException("ERROR: datos invalidos");
        }
        super.setPeso(peso);
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean isVacunado() {
        return vacunado;
    }

    public void setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
    }

    @Override
    public String getTipoString() {
        return "Perro";
    }
}
