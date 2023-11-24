package aplicacion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Estado;
import model.MaquinaTuring;

public class TestPractico {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Esta máquina de Turing lee un número binario e indica si está formado por el patrón 01 o 10");
        System.out.println("Ingrese la cadena: ");
        String cadena = scanner.next();

        System.out.println("1. PAUSADO\n2. RAPIDO\n3. LENTO");
        int opcion = scanner.nextInt();

        boolean pausado = opcion == 1 ? true : false;
        int velocidad = opcion == 2 ? Constantes.RAPIDO : Constantes.LENTO;

        MaquinaTuring maquina = new MaquinaTuring();

        Map<String, Estado> estados = new HashMap<>();

        for (int i = 1; i <= 6; i++) {
            estados.put(String.format("q%d", i), new Estado(String.format("q%d", i)));
        }

        estados.get("q1").agregarTransicion('0', '0', true, estados.get("q2"));
        estados.get("q1").agregarTransicion('1', '1', true, estados.get("q5"));

        estados.get("q2").agregarTransicion('1', '1', true, estados.get("q3"));

        estados.get("q3").agregarTransicion(Constantes.ESPACIO, Constantes.ESPACIO, true, estados.get("q4"));
        estados.get("q3").agregarTransicion('0', '0', true, estados.get("q2"));

        estados.get("q5").agregarTransicion('0', '0', true, estados.get("q6"));

        estados.get("q6").agregarTransicion('1', '1', true, estados.get("q5"));
        estados.get("q6").agregarTransicion(Constantes.ESPACIO, Constantes.ESPACIO, true, estados.get("q4"));

        maquina.setEstados(estados);
        maquina.setEstadoAceptador(estados.get("q4"));
        maquina.run(cadena, estados.get("q1"), pausado, velocidad);

    }

}
