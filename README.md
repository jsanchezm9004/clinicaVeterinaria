Aplicacion en Intellij IDEA usando java para una clínica veterinaria para administrar los pacientes y jaulas, con el apoyo de la IA Gemini, usando POO aplicando encapsulamiento, herencia, polimorfismo y separacion de responsabilidades. Se siguen estrictas reglas de negocio y validaciones de datos de ingreso.

La interfaz del usuario solicita que escriba una de las opciones del menú, donde las palabras van separadas por espacios y los decimales por punto:

Ingresar PERRO <nombre> <peso> <edad> <raza> <si/no>
Ingresar GATO <nombre> <peso> <edad>
Ingresar AVE <nombre> <peso> <edad> <envergadura>
Vacunar <nombre>
Asignar <nombre>
Medicar <nombre> <dosis>
Alta <nombre>
Reporte
Salir

Características de la app:
- Uso exclusivo de arreglos/vectores de capacidad fija (Paciente[10] y Jaula[6]).
- Jerarquia de Clases
  Paciente (Clase Base Abstracta)
  Perro: Hereda de Paciente (Factor de dosis: 10, Zona: A, Estado inicial según vacunación)
  Gato: Hereda de Paciente (Factor de dosis: 6, Zona: A, Control de nivel de estrés)
  AveExotica: Hereda de Paciente (Factor de dosis: 3, Zona: B, Requisito de jaula por envergadura)
- Gestión de Jaulas: Asignación automática por compatibilidad de peso y envergadura (S, M, L) y zona (A o B).
- Control Médico: Cálculo de dosis máxima, registro de dosis acumulada y actualización automática de estados (EN_TRATAMIENTO).
- Búsqueda en arreglos, compactación física de arreglos al dar altas.

Estructura del proyecto:
src/
├── Paciente.java     # Clase base abstracta
├── Perro.java        # Subclase especifica para perros
├── Gato.java         # Subclase especifica para gatos
├── AveExotica.java   # Subclase especifica para aves
├── Jaula.java        # Entidad para la gestión de espacio
├── Hospital.java     # Controlador y lógica de negocio
└── Main.java         # Interfaz de usuario (Scanner / System.out)
