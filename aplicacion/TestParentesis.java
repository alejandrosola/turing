package aplicacion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Estado;
import model.MaquinaTuring;

public class TestParentesis {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Esta máquina de Turing recibe una cadena de paréntesis y 'a', e indica si para cada paréntesis que abre hay un paréntesis que cierra, y si en el medio hay una sola 'a'");
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

        estados.get("q1").agregarTransicion('(', 'x', true, estados.get("q2"));
        estados.get("q2").agregarTransicion('(', '(', true, estados.get("q2"));
        estados.get("q2").agregarTransicion('a', 'a', true, estados.get("q2"));
        estados.get("q2").agregarTransicion(')', ')', true, estados.get("q2"));
        estados.get("q2").agregarTransicion(Constantes.ESPACIO, ' ', false, estados.get("q3"));

        estados.get("q3").agregarTransicion(')', 'x', false, estados.get("q4"));

        estados.get("q4").agregarTransicion(')', ')', false, estados.get("q4"));
        estados.get("q4").agregarTransicion('a', 'a', false, estados.get("q4"));
        estados.get("q4").agregarTransicion('(', '(', false, estados.get("q4"));
        estados.get("q4").agregarTransicion('x', 'x', true, estados.get("q5"));

        estados.get("q5").agregarTransicion('(', 'x', true, estados.get("q6"));
        estados.get("q5").agregarTransicion('a', 'a', true, estados.get("q7"));

        estados.get("q7").agregarTransicion('x', 'x', true, estados.get("q8"));

        estados.get("q6").agregarTransicion('(', '(', true, estados.get("q6"));
        estados.get("q6").agregarTransicion('a', 'a', true, estados.get("q6"));
        estados.get("q6").agregarTransicion(')', ')', true, estados.get("q6"));
        estados.get("q6").agregarTransicion('x', 'x', false, estados.get("q3"));

        maquina.setEstados(estados);
        maquina.setEstadoAceptador(estados.get("q8"));
        maquina.run(cadena, estados.get("q1"), pausado, velocidad);

    }

}
