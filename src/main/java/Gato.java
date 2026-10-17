public class Gato extends Paciente {
    private int nivelEstres; // 0 a 10

    public Gato(String nombre, double peso, int edad, int nivelEstres) {
        super(nombre, peso, edad, 6.0, "INGRESADO");
        setPeso(peso);
        setNivelEstres(nivelEstres);
    }

    @Override
    public void setPeso(double peso) {
        if (peso > 15) {
            throw new IllegalArgumentException("ERROR: datos invalidos");
        }
        super.setPeso(peso);
    }

    public int getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(int nivelEstres) {
        if (nivelEstres < 0 || nivelEstres > 10) {
            throw new IllegalArgumentException("ERROR: datos invalidos");
        }
        this.nivelEstres = nivelEstres;
    }

    @Override
    public String getTipoString() {
        return "Gato";
    }
}
