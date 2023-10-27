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
        Transicion transition = new Transicion(readSymbol, writeSymbol, moveDirection, nextState);
        transiciones.put(readSymbol, transition);
    }

    public Transicion getTransition(char readSymbol) {
        return transiciones.get(readSymbol);
    }
}
