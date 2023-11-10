package aplicacion;

import java.util.HashMap;
import java.util.Map;

import model.Estado;
import model.MaquinaTuring;

public class Test {
    public static void main(String args[]) {

        MaquinaTuring maquina = new MaquinaTuring();
        Map<String, Estado> estados = new HashMap<>();

        for (int i = 1; i <= 3; i++) {
            estados.put(String.format("q%d", i), new Estado(String.format("q%d", i)));
        }

        estados.get("q1").agregarTransicion('a', 'b', true, estados.get("q2"));

        maquina.setEstados(estados);
        maquina.setEstadoAceptador(estados.get("q2"));
        maquina.setEstadoNoAceptador(estados.get("q3"));
        /*
         * System.out.println(estados.get("q1").getTransicion('a'));
         */
        /*
         * System.out.println(estados.values());
         */ maquina.run("ab", estados.get("q1"), true);
    }

}
