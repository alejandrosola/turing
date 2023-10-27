package model;

import java.util.List;

public class Cinta {
    private List<Character> celdas;

    public char leer(int posicion) {
        if (posicion >= 0 && posicion < celdas.size()) {
            return celdas.get(posicion);
        } else {
            return ' ';
        }
    }

    public void escribir(int posicion, Character caracter) {
        if (posicion >= 0 && posicion < celdas.size()) {
            celdas.set(posicion, caracter);
        } else {
            while (celdas.size() <= posicion) {
                celdas.add(' ');
            }
            celdas.set(posicion, caracter);
        }
    }

}