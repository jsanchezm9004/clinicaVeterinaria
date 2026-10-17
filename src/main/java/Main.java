import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nESCRIBA UNA OPCIÓN DEL MENÚ:");
        System.out.print("Ingresar PERRO <nombre> <peso> <edad> <raza> <si/no>\n");
        System.out.print("Ingresar GATO <nombre> <peso> <edad>\n");
        System.out.print("Ingresar AVE <nombre> <peso> <edad> <envergadura>\n");
        System.out.print("Vacunar <nombre>\n");
        System.out.print("Asignar <nombre>\n");
        System.out.print("Medicar <nombre> <dosis>\n");
        System.out.print("Alta <nombre>\n");
        System.out.print("Reporte\n");
        System.out.print("Salir\n\n");

        while (true) {
            if (!scanner.hasNextLine()) break;
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) {
                continue;
            }

            if (linea.equalsIgnoreCase("salir")) {
                break;
            }

            if (linea.equalsIgnoreCase("reporte")) {
                System.out.println(hospital.generarReporte());
                continue;
            }

            String[] partes = linea.split("\\s+");
            String comando = partes[0].toLowerCase();

            if (comando.equals("vacunar")) {
                if (partes.length < 2) {
                    System.out.println("ERROR: comando desconocido.");
                    continue;
                }
                System.out.println(hospital.vacunar(partes[1]));
            } else if (comando.equals("asignar")) {
                if (partes.length < 2) {
                    System.out.println("ERROR: comando desconocido.");
                    continue;
                }
                System.out.println(hospital.asignar(partes[1]));
            } else if (comando.equals("medicar")) {
                if (partes.length < 3) {
                    System.out.println("ERROR: comando desconocido.");
                    continue;
                }
                double mg;
                try {
                    mg = Double.parseDouble(partes[2]);
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: datos invalidos");
                    continue;
                }
                System.out.println(hospital.medicar(partes[1], mg));
            } else if (comando.equals("alta")) {
                if (partes.length < 2) {
                    System.out.println("ERROR: comando desconocido.");
                    continue;
                }
                System.out.println(hospital.alta(partes[1]));
            } else if (comando.equals("ingresar")) {
                if (partes.length < 2) {
                    System.out.println("ERROR: comando desconocido.");
                    continue;
                }
                String tipoAnimal = partes[1].toLowerCase();
                if (tipoAnimal.equals("perro")) {
                    if (partes.length < 7) {
                        System.out.println("ERROR: datos invalidos");
                        continue;
                    }
                    double peso;
                    int edad;
                    try {
                        peso = Double.parseDouble(partes[3]);
                        edad = Integer.parseInt(partes[4]);
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: datos invalidos");
                        continue;
                    }
                    System.out.println(hospital.ingresarPerro(partes[2], peso, edad, partes[5], partes[6]));
                } else if (tipoAnimal.equals("gato")) {
                    if (partes.length < 5) {
                        System.out.println("ERROR: datos invalidos");
                        continue;
                    }
                    double peso;
                    int edad;
                    try {
                        peso = Double.parseDouble(partes[3]);
                        edad = Integer.parseInt(partes[4]);
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: datos invalidos");
                        continue;
                    }
                    System.out.println(hospital.ingresarGato(partes[2], peso, edad));
                } else if (tipoAnimal.equals("ave")) {
                    if (partes.length < 6) {
                        System.out.println("ERROR: datos invalidos");
                        continue;
                    }
                    double peso;
                    int edad;
                    double envergadura;
                    try {
                        peso = Double.parseDouble(partes[3]);
                        edad = Integer.parseInt(partes[4]);
                        envergadura = Double.parseDouble(partes[5]);
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: datos invalidos");
                        continue;
                    }
                    System.out.println(hospital.ingresarAve(partes[2], peso, edad, envergadura));
                } else {
                    System.out.println("Comando desconocido: ERROR: comando desconocido.");
                }
            } else {
                System.out.println("Comando desconocido: ERROR: comando desconocido.");
            }
        }

        scanner.close();
    }
}