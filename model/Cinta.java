package model;

import java.util.ArrayList;
import java.util.List;

import aplicacion.Constantes;

public class Cinta {
    private List<Character> celdas;

    public char leer(int posicion) {
        if (posicion >= 0 && posicion < celdas.size()) {
            return celdas.get(posicion);
        } else {
            posicion = posicion < 0 ? posicion + 1 : posicion;
            celdas.add(posicion, Constantes.ESPACIO);
            return Constantes.ESPACIO;
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

    public void setCeldas(List<Character> input) {
        this.celdas = celdas;
    }

    public List<Character> getCeldas() {
        return this.celdas;
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < this.celdas.size(); i++) {
            ans += (this.celdas.get(i));
        }

        return ans;
    }

}