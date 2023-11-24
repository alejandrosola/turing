package aplicacion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Estado;
import model.MaquinaTuring;

public class TestBinarios {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Esta máquina de Turing acepta dos números binarios separados por un # e indica si son iguales o no");
        System.out.println("Ingrese la cadena: ");
        String cadena = scanner.next();

        System.out.println("1. PAUSADO\n2. RAPIDO\n3. LENTO");
        int opcion = scanner.nextInt();

        boolean pausado = opcion == 1 ? true : false;
        int velocidad = opcion == 2 ? Constantes.RAPIDO : Constantes.LENTO;

        MaquinaTuring maquina = new MaquinaTuring();

        Map<String, Estado> estados = new HashMap<>();

        for (int i = 1; i <= 8; i++) {
            estados.put(String.format("q%d", i), new Estado(String.format("q%d", i)));
        }

        estados.put("qa", new Estado("qa"));
        estados.put("qr", new Estado("qr"));

        estados.get("q1").agregarTransicion('1', 'x', true, estados.get("q3"));
        estados.get("q1").agregarTransicion('0', 'x', true, estados.get("q2"));
        estados.get("q1").agregarTransicion('#', '#', true, estados.get("q8"));

        estados.get("q2").agregarTransicion('0', '0', true, estados.get("q2"));
        estados.get("q2").agregarTransicion('1', '1', true, estados.get("q2"));
        estados.get("q2").agregarTransicion('#', '#', true, estados.get("q4"));

        estados.get("q3").agregarTransicion('0', '0', true, estados.get("q3"));
        estados.get("q3").agregarTransicion('1', '1', true, estados.get("q3"));
        estados.get("q3").agregarTransicion('#', '#', true, estados.get("q5"));

        estados.get("q4").agregarTransicion('x', 'x', true, estados.get("q4"));
        estados.get("q4").agregarTransicion('0', 'x', false, estados.get("q6"));

        estados.get("q5").agregarTransicion('x', 'x', true, estados.get("q5"));
        estados.get("q5").agregarTransicion('1', 'x', false, estados.get("q6"));

        estados.get("q6").agregarTransicion('0', '0', false, estados.get("q6"));
        estados.get("q6").agregarTransicion('1', '1', false, estados.get("q6"));
        estados.get("q6").agregarTransicion('x', 'x', false, estados.get("q6"));
        estados.get("q6").agregarTransicion('#', '#', false, estados.get("q7"));

        estados.get("q7").agregarTransicion('0', '0', false, estados.get("q7"));
        estados.get("q7").agregarTransicion('1', '1', false, estados.get("q7"));
        estados.get("q7").agregarTransicion('x', 'x', true, estados.get("q1"));

        estados.get("q8").agregarTransicion('x', 'x', true, estados.get("q8"));
        estados.get("q8").agregarTransicion(Constantes.ESPACIO, Constantes.ESPACIO, true, estados.get("qa"));

        maquina.setEstados(estados);
        maquina.setEstadoAceptador(estados.get("qa"));
        maquina.setEstadoNoAceptador(estados.get("qr"));

        maquina.run(cadena, estados.get("q1"), pausado, velocidad);
    }
}
