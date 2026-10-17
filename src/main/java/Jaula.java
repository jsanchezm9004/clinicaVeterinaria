public class Jaula {
    private int numero;
    private char tamano; // 'S', 'M', 'L'
    private char zona; // 'A', 'B'
    private Paciente ocupante; // referencia o null

    public Jaula(int numero, char tamano, char zona) {
        this.numero = numero;
        this.tamano = Character.toUpperCase(tamano);
        this.zona = Character.toUpperCase(zona);
        this.ocupante = null;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public char getTamano() {
        return tamano;
    }

    public void setTamano(char tamano) {
        this.tamano = Character.toUpperCase(tamano);
    }

    public char getZona() {
        return zona;
    }

    public void setZona(char zona) {
        this.zona = Character.toUpperCase(zona);
    }

    public Paciente getOcupante() {
        return ocupante;
    }

    public void setOcupante(Paciente ocupante) {
        this.ocupante = ocupante;
    }

    public boolean estaLibre() {
        return ocupante == null;
    }
}
