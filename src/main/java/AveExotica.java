public class AveExotica extends Paciente {
    private double envergadura; // en cm

    public AveExotica(String nombre, double peso, int edad, double envergadura) {
        super(nombre, peso, edad, 3.0, "INGRESADO");
        setPeso(peso);
        setEnvergadura(envergadura);
    }

    @Override
    public void setPeso(double peso) {
        if (peso > 15) {
            throw new IllegalArgumentException("ERROR: datos invalidos");
        }
        super.setPeso(peso);
    }

    public double getEnvergadura() {
        return envergadura;
    }

    public void setEnvergadura(double envergadura) {
        if (envergadura <= 0) {
            throw new IllegalArgumentException("ERROR: datos invalidos");
        }
        this.envergadura = envergadura;
    }

    @Override
    public String getTipoString() {
        return "Ave";
    }
}
