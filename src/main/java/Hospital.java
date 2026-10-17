import java.util.Locale;

public class Hospital {
    private Paciente[] pacientes;
    private int contadorPacientes;
    private Jaula[] jaulas;

    public Hospital() {
        pacientes = new Paciente[10];
        contadorPacientes = 0;
        jaulas = new Jaula[6];
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        try {
            // Jaulas: 1 (A, S), 2 (A, M), 3 (A, M), 4 (A, L), 5 (B, S), 6 (B, M)
            jaulas[0] = new Jaula(1, 'S', 'A');
            jaulas[1] = new Jaula(2, 'M', 'A');
            jaulas[2] = new Jaula(3, 'M', 'A');
            jaulas[3] = new Jaula(4, 'L', 'A');
            jaulas[4] = new Jaula(5, 'S', 'B');
            jaulas[5] = new Jaula(6, 'M', 'B');

            // Pacientes iniciales (todos sin jaula asignada)
            pacientes[contadorPacientes++] = new Perro("Rocky", 30.0, 4, "labrador", true);
            pacientes[contadorPacientes++] = new Gato("Misifu", 4.0, 3, 0);
            pacientes[contadorPacientes++] = new AveExotica("Kiwi", 0.8, 2, 70.0);
        } catch (Exception e) {
            // Excepción no esperada en datos iniciales
        }
    }

    public Paciente buscarPaciente(String nombre) {
        if (nombre == null) return null;
        for (int i = 0; i < contadorPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                return pacientes[i];
            }
        }
        return null;
    }

    public String ingresarPerro(String nombre, double peso, int edad, String raza, String vacunadoStr) {
        boolean vacunado;
        if (vacunadoStr.equalsIgnoreCase("si")) {
            vacunado = true;
        } else if (vacunadoStr.equalsIgnoreCase("no")) {
            vacunado = false;
        } else {
            return "ERROR: datos invalidos";
        }

        Perro p;
        try {
            p = new Perro(nombre, peso, edad, raza, vacunado);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }

        if (contadorPacientes >= 10) {
            return "ERROR: hospital lleno";
        }

        pacientes[contadorPacientes++] = p;

        if (!vacunado) {
            return "OK: " + p.getNombre() + " ingresado en CUARENTENA";
        } else {
            return "OK: " + p.getNombre() + " ingresado";
        }
    }

    public String ingresarGato(String nombre, double peso, int edad) {
        Gato g;
        try {
            g = new Gato(nombre, peso, edad, 0);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }

        if (contadorPacientes >= 10) {
            return "ERROR: hospital lleno";
        }

        pacientes[contadorPacientes++] = g;
        return "OK: " + g.getNombre() + " ingresado";
    }

    public String ingresarAve(String nombre, double peso, int edad, double envergadura) {
        AveExotica a;
        try {
            a = new AveExotica(nombre, peso, edad, envergadura);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }

        if (contadorPacientes >= 10) {
            return "ERROR: hospital lleno";
        }

        pacientes[contadorPacientes++] = a;
        return "OK: " + a.getNombre() + " ingresado";
    }

    public String vacunar(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (!(p instanceof Perro)) {
            return "ERROR: solo aplica a perros";
        }
        Perro perro = (Perro) p;
        if (perro.isVacunado()) {
            return "ERROR: ya vacunado";
        }

        perro.setVacunado(true);
        perro.setEstado("INGRESADO");
        return "OK: " + perro.getNombre() + " vacunado (pasa a INGRESADO)";
    }

    public String asignar(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (p.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }
        if (p.getJaulaAsignada() != null) {
            return "ERROR: paciente ya tiene jaula";
        }

        char tamanoReq = determinarTamanoRequerido(p);
        char zonaReq = determinarZonaRequerida(p);

        Jaula jaulaLibre = buscarJaulaCompatible(tamanoReq, zonaReq);
        if (jaulaLibre == null) {
            return "ERROR: no hay jaula compatible";
        }

        jaulaLibre.setOcupante(p);
        p.setJaulaAsignada(jaulaLibre);

        return "OK: " + p.getNombre() + " asignado a jaula " + jaulaLibre.getNumero();
    }

    private char determinarTamanoRequerido(Paciente p) {
        double peso = p.getPeso();
        char tamano;
        if (peso < 5.0) {
            tamano = 'S';
        } else if (peso <= 25.0) {
            tamano = 'M';
        } else {
            tamano = 'L';
        }

        if (p instanceof AveExotica) {
            AveExotica ave = (AveExotica) p;
            if (ave.getEnvergadura() > 60.0 && tamano == 'S') {
                tamano = 'M';
            }
        }
        return tamano;
    }

    private char determinarZonaRequerida(Paciente p) {
        if (p instanceof Perro || p instanceof Gato) {
            return 'A';
        } else {
            return 'B';
        }
    }

    private Jaula buscarJaulaCompatible(char tamanoReq, char zonaReq) {
        int valReq = valorTamano(tamanoReq);
        for (int i = 0; i < jaulas.length; i++) {
            Jaula j = jaulas[i];
            if (j.estaLibre() && j.getZona() == zonaReq) {
                if (valorTamano(j.getTamano()) >= valReq) {
                    return j;
                }
            }
        }
        return null;
    }

    private int valorTamano(char t) {
        switch (Character.toUpperCase(t)) {
            case 'S': return 1;
            case 'M': return 2;
            case 'L': return 3;
            default: return 0;
        }
    }

    public String medicar(String nombre, double mg) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (mg <= 0) {
            return "ERROR: datos invalidos";
        }
        if (p.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }
        if (p.getJaulaAsignada() == null) {
            return "ERROR: paciente sin jaula";
        }
        if (p instanceof Gato) {
            Gato gato = (Gato) p;
            if (gato.getNivelEstres() >= 8) {
                return "ERROR: gato con estres alto";
            }
        }

        double dosisMax = p.getPeso() * p.getFactorDosis();
        if (mg > dosisMax) {
            return "ERROR: dosis excede el maximo";
        }

        double topeAcumulado = 3.0 * dosisMax;
        if (p.getDosisAcumulada() + mg > topeAcumulado) {
            return "ERROR: tope acumulado superado";
        }

        p.setDosisAcumulada(p.getDosisAcumulada() + mg);
        p.setEstado("EN_TRATAMIENTO");

        if (p instanceof Gato) {
            Gato gato = (Gato) p;
            int nuevoEstres = Math.min(10, gato.getNivelEstres() + 2);
            gato.setNivelEstres(nuevoEstres);
        }

        String mgStr = (mg == (int) mg) ? String.valueOf((int) mg) : String.valueOf(mg);
        return "OK: " + p.getNombre() + " medicado con " + mgStr + " mg";
    }

    public String alta(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (!p.getEstado().equals("EN_TRATAMIENTO")) {
            return "ERROR: paciente no apto para alta";
        }

        if (p.getJaulaAsignada() != null) {
            p.getJaulaAsignada().setOcupante(null);
            p.setJaulaAsignada(null);
        }

        int index = -1;
        for (int i = 0; i < contadorPacientes; i++) {
            if (pacientes[i] == p) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < contadorPacientes - 1; i++) {
                pacientes[i] = pacientes[i + 1];
            }
            pacientes[contadorPacientes - 1] = null;
            contadorPacientes--;
        }

        return "OK: " + p.getNombre() + " dado de alta";
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE\n");
        sb.append("Pacientes: ").append(contadorPacientes).append("/10\n");
        for (int i = 0; i < contadorPacientes; i++) {
            Paciente p = pacientes[i];
            String jaulaStr = (p.getJaulaAsignada() != null) ? String.valueOf(p.getJaulaAsignada().getNumero()) : "-";
            sb.append(p.getNombre()).append(" | ")
                    .append(p.getTipoString()).append(" | ")
                    .append(String.format(Locale.US, "%.1f", p.getPeso())).append(" kg | ")
                    .append(p.getEstado()).append(" | ")
                    .append("jaula: ").append(jaulaStr).append("\n");
        }

        int jaulasLibres = 0;
        for (int i = 0; i < jaulas.length; i++) {
            if (jaulas[i].estaLibre()) {
                jaulasLibres++;
            }
        }
        sb.append("Jaulas libres: ").append(jaulasLibres);
        return sb.toString();
    }
}
