package model;

public class Transicion {
    private char simboloLectura;
    private char simboloEscritura;
    private boolean direccionMovimiento; // true -> derecha. false -> izquierda
    private Estado estadoSiguiente;

    public Transicion(char simboloLectura, char simboloEscritura, boolean direccionMovimiento,
            Estado estadoSiguiente) {
        this.simboloLectura = simboloLectura;
        this.simboloEscritura = simboloEscritura;
        this.direccionMovimiento = direccionMovimiento;
        this.estadoSiguiente = estadoSiguiente;
    }

    public char getSimboloLectura() {
        return simboloLectura;
    }

    public char getSimboloEscritura() {
        return simboloEscritura;
    }

    public boolean getDireccionMovimiento() {
        return direccionMovimiento;
    }

    public Estado getEstadoSiguiente() {
        return estadoSiguiente;
    }

    @Override
    public String toString() {
        return String.format("Leo %s. Escribo %s. Voy a %s", this.simboloLectura, this.simboloEscritura,
                this.estadoSiguiente);
    }

}