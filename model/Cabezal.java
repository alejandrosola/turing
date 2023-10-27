package model;

public class Cabezal {
    private int position;

    public Cabezal() {
        position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void moverDerecha() {
        position++;
    }

    public void moverIzquierda() {
        // Qué pasa si quiero ir más a la izquierda?
        // Capaz puedo hacer un desplazamiento de la cinta a la derecha para tener un
        // lugar a la izquierda
        if (position > 0) {
            position--;
        }
    }

    public char leerCinta(Cinta cinta) {
        return cinta.leer(position);
    }

    public void escribirCinta(Cinta cinta, char simbolo) {
        cinta.escribir(position, simbolo);
    }
}