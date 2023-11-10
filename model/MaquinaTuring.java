package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import aplicacion.Constantes;

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
        this.run(input, estadoInicial, pausado, Constantes.RAPIDO);
    }

    public void run(String input, Estado estadoInicial, boolean pausado, int velocidad) {

        this.estadoActual = estadoInicial;
        this.cabezal.getCinta().setCeldas(input);
        Scanner scanner = new Scanner(System.in);
        while (!this.estadoActual.equals(this.estadoAceptador)) {

            char caracterLeido = this.cabezal.leerCinta();
            System.out.println(this.cabezal.getCinta());
            this.imprimirCinta();

            Transicion transicion = this.estadoActual.getTransicion(caracterLeido);
            if (transicion == null) {
                System.out.println("Cadena rechazada");
                return;
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
        System.out.println(mensaje);
        scanner.close();
    }

    public void imprimirCinta() {
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
