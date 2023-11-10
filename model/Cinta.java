package model;

import java.util.ArrayList;
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

    public void setCeldas(String input) {
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            chars.add(input.charAt(i));
        }
        this.celdas = chars;
    }

    @Override
    public String toString() {
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < this.celdas.size(); i++) {
            chars.add(this.celdas.get(i));
        }

        return chars.toString();
    }

}