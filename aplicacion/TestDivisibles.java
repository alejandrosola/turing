package aplicacion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Estado;
import model.MaquinaTuring;

public class TestDivisibles {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Esta máquina de Turing lee un número binario e indica si es divisible por 8 o no");
        System.out.println("Ingrese la cadena: ");
        String cadena = scanner.next();

        System.out.println("1. PAUSADO\n2. RAPIDO\n3. LENTO");
        int opcion = scanner.nextInt();

        boolean pausado = opcion == 1 ? true : false;
        int velocidad = opcion == 2 ? Constantes.RAPIDO : Constantes.LENTO;

        MaquinaTuring maquina = new MaquinaTuring();

        Map<String, Estado> estados = new HashMap<>();

        for (int i = 1; i <= 4; i++) {
            estados.put(String.format("q%d", i), new Estado(String.format("q%d", i)));
        }

        estados.put("qa", new Estado("qa"));

        estados.get("q1").agregarTransicion('1', '1', true, estados.get("q1"));
        estados.get("q1").agregarTransicion('0', '0', true, estados.get("q2"));

        estados.get("q2").agregarTransicion('0', '0', true, estados.get("q3"));
        estados.get("q2").agregarTransicion('1', '1', true, estados.get("q1"));

        estados.get("q3").agregarTransicion('0', '0', true, estados.get("q4"));
        estados.get("q3").agregarTransicion('1', '1', true, estados.get("q1"));

        estados.get("q4").agregarTransicion('1', '1', true, estados.get("q1"));
        estados.get("q4").agregarTransicion(Constantes.ESPACIO, Constantes.ESPACIO, true, estados.get("qa"));

        maquina.setEstados(estados);
        maquina.setEstadoAceptador(estados.get("qa"));
        maquina.run(cadena, estados.get("q1"), pausado, velocidad);
    }
}
