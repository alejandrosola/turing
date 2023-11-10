package model;

public class Cabezal {
    private int position;
    private Cinta cinta;

    public Cabezal() {
        position = 0;
        this.cinta = new Cinta();
    }

    public int getPosition() {
        return position;
    }

    public void moverDerecha() {
        position++;
    }

    public Cinta getCinta() {
        return this.cinta;
    }

    public void moverIzquierda() {
        // Qué pasa si quiero ir más a la izquierda?
        // Capaz puedo hacer un desplazamiento de la cinta a la derecha para tener un
        // lugar a la izquierda
        if (position > 0) {
            position--;
        }
    }

    public char leerCinta() {
        return cinta.leer(position);
    }

    public void escribirCinta(char simbolo) {
        cinta.escribir(position, simbolo);
    }
}