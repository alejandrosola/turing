package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import aplicacion.Constantes;

public class MaquinaTuring {
    private Cabezal cabezal;
    private Map<String, Estado> estados;
    private Estado estadoActual, estadoAceptador;
    private Estado estadoInicial;
    private boolean subMaquina;
    /* private int posicionActual; */

    public MaquinaTuring(Estado estadoInicial) {
        this.estados = new HashMap<>();
        this.estadoAceptador = this.estados.get(estadoAceptador);
        this.cabezal = new Cabezal();
        this.subMaquina = false;
    }

    public MaquinaTuring() {
        this.estados = new HashMap<>();
        this.cabezal = new Cabezal();
        this.subMaquina = false;
    }

    public MaquinaTuring(Cabezal cabezal) {
        this.cabezal = cabezal;
        this.subMaquina = false;
    }

    public void setSubMaquina(boolean s) {
        this.subMaquina = s;
    }

    public void setEstadoInicial(Estado e) {
        this.estadoInicial = e;
    }

    public void setEstadoAceptador(Estado e) {
        this.estadoAceptador = e;
    }

    public Estado getEstadoAceptador() {
        return this.estadoAceptador;
    }

    public void setEstados(Map<String, Estado> estados) {
        this.estados = estados;
    }

    public void run(String input, Estado estadoInicial, boolean pausado) {
        this.run(input, estadoInicial, pausado, Constantes.RAPIDO, null);
    }

    public void run(List<Character> input, Estado estadoInicial, boolean pausado, int velocidad) {
        String i = "";
        for (Character c : input) {
            i += c;
        }
        this.run(i, estadoInicial, pausado, velocidad, null);
    }

    public void run(String input, Estado estadoInicial, boolean pausado, int velocidad, Cabezal cabezal) {

        if (estadoInicial != null) {
            this.estadoActual = estadoInicial;
        } else {
            this.estadoActual = this.estadoInicial;
        }

        if (cabezal != null) {
            this.cabezal = cabezal;
        } else {
            this.cabezal.getCinta().setCeldas(input);
        }

        Scanner scanner = new Scanner(System.in);
        while (!this.estadoActual.equals(this.estadoAceptador)) {

            char caracterLeido = this.cabezal.leerCinta();
            this.imprimirCinta();

            Transicion transicion = this.estadoActual.getTransicion(caracterLeido);
            if (transicion == null) {
                System.out.println("Cadena rechazada");
                return;
            }

            this.cabezal.escribirCinta(transicion.getSimboloEscritura());
            this.estadoActual = transicion.getEstadoSiguiente();

            if (transicion.getAccion() != null) {
                transicion.getAccion().run(input, null, false, Constantes.RAPIDO,
                        this.cabezal);
            } else {
                if (transicion.getDireccionMovimiento()) {
                    this.cabezal.moverDerecha();
                } else {
                    this.cabezal.moverIzquierda();
                }
            }

            if (pausado) {
                scanner.nextLine();
            } else {
                if (velocidad == Constantes.RAPIDO) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        String mensaje = this.estadoActual.equals(this.estadoAceptador) ? "Cadena aceptada" : "Cadena rechazada";
        if (!this.subMaquina) {
            System.out.println(mensaje);
            scanner.close();
        }
        return;
    }

    public void imprimirCinta() {
        System.out.println(this.cabezal.getCinta());
        StringBuilder cabezalPosicion = new StringBuilder("");
        for (int i = 0; i < this.cabezal.getCinta().getCeldas().size(); i++) {
            cabezalPosicion.append(" ");
        }

        while (cabezalPosicion.length() <= this.cabezal.getPosition()) {
            cabezalPosicion.append(" ");
        }

        cabezalPosicion.setCharAt(this.cabezal.getPosition(), '^');
        System.out.println(cabezalPosicion);
    }

}
