package model;

import java.util.HashMap;
import java.util.Map;

public class Estado {
    private String nombre;
    private Map<Character, Transicion> transiciones;

    public Estado(String nombre) {
        this.nombre = nombre;
        this.transiciones = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarTransicion(char readSymbol, char writeSymbol, boolean moveDirection, Estado nextState) {
        Transicion transition = new Transicion(readSymbol, writeSymbol, moveDirection, nextState, null);
        transiciones.put(readSymbol, transition);
    }

    public void agregarTransicion(char readSymbol, char writeSymbol, boolean moveDirection, Estado nextState,
            MaquinaTuring accion) {
        Transicion transition = new Transicion(readSymbol, writeSymbol, moveDirection, nextState, accion);
        transiciones.put(readSymbol, transition);
    }

    public Transicion getTransicion(char caracterLeido) {
        return transiciones.get(caracterLeido);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return this == null;
        return this.nombre.equals(((Estado) obj).getNombre());
    }

    @Override
    public String toString() {
        return String.format("Estado %s", this.getNombre());
    }
}
