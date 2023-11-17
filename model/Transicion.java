package model;

public class Transicion {
    private char simboloLectura;
    private char simboloEscritura;
    private boolean direccionMovimiento; // true -> derecha. false -> izquierda
    private Estado estadoSiguiente;
    private MaquinaTuring accion;

    public Transicion(char simboloLectura, char simboloEscritura, boolean direccionMovimiento,
            Estado estadoSiguiente, MaquinaTuring accion) {
        this.simboloLectura = simboloLectura;
        this.simboloEscritura = simboloEscritura;
        this.direccionMovimiento = direccionMovimiento;
        this.estadoSiguiente = estadoSiguiente;
        this.accion = accion;
    }

    public MaquinaTuring getAccion() {
        return this.accion;
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