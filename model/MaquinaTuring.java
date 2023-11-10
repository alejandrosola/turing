package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaquinaTuring {
    private Cabezal cabezal;
    private Map<String, Estado> estados;
    private Estado estadoActual, estadoAceptador, estadoNoAceptador;
    /* private int posicionActual; */

    public MaquinaTuring(String estadoAceptador, String estadoNoAceptador) {
        this.estados = new HashMap<>();
        this.estadoAceptador = this.estados.get(estadoAceptador);
        this.estadoNoAceptador = this.estados.get(estadoNoAceptador);
        this.cabezal = new Cabezal();
    }

    public MaquinaTuring() {
        this.estados = new HashMap<>();
        this.cabezal = new Cabezal();
    }

    public void setEstadoAceptador(Estado e) {
        this.estadoAceptador = e;
    }

    public Estado getEstadoNoAceptador() {
        return this.estadoNoAceptador;
    }

    public Estado getEstadoAceptador() {
        return this.estadoAceptador;
    }

    public void setEstadoNoAceptador(Estado e) {
        this.estadoNoAceptador = e;
    }

    public void setEstados(Map<String, Estado> estados) {
        this.estados = estados;
    }

    public void run(String input, Estado estadoInicial, boolean pausado) {

        Scanner scanner = new Scanner(System.in);
        this.estadoActual = estadoInicial;
        this.cabezal.getCinta().setCeldas(input);
        System.out.println(this.estadoActual + " ACTUAL");
        System.out.println(this.estadoAceptador + " ACEPTADOR");
        System.out.println(this.estadoNoAceptador + " NO ACEPTADOR");
        while (!this.estadoActual.equals(this.estadoAceptador) && !this.estadoActual.equals(this.estadoNoAceptador)) {
            System.out.println(this.cabezal.getCinta() + "Cinta");
            char caracterLeido = this.cabezal.leerCinta();

            Transicion transicion = this.estadoActual.getTransicion(caracterLeido);
            if (transicion == null) {
                throw new IllegalArgumentException(
                        String.format("No hay transición definida para el caracter %s en el estado %s", caracterLeido,
                                this.estadoActual.getNombre()));
            }

            this.cabezal.escribirCinta(transicion.getSimboloEscritura());
            this.estadoActual = transicion.getEstadoSiguiente();
            if (transicion.getDireccionMovimiento()) {
                this.cabezal.moverDerecha();
            } else {
                this.cabezal.moverIzquierda();
            }

            if (pausado) {
                scanner.nextLine();
            }
        }
        System.out.println(this.cabezal.getCinta() + "Cinta");
        System.out.println(this.estadoActual.equals(this.estadoAceptador));
    }

}
