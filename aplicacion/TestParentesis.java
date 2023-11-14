package aplicacion;

import java.util.HashMap;
import java.util.Map;

import model.Estado;
import model.MaquinaTuring;

public class TestParentesis {
    public static void main(String args[]) {

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
        /*
         * estados.get("q1").agregarTransicion('0', '0', true, estados.get("q2"));
         * estados.get("q1").agregarTransicion('1', '1', true, estados.get("q5"));
         * 
         * estados.get("q2").agregarTransicion('1', '1', true, estados.get("q3"));
         * 
         * estados.get("q3").agregarTransicion(Constantes.ESPACIO, Constantes.ESPACIO,
         * true, estados.get("q4"));
         * estados.get("q3").agregarTransicion('0', '0', true, estados.get("q2"));
         * 
         * estados.get("q5").agregarTransicion('0', '0', true, estados.get("q6"));
         * 
         * estados.get("q6").agregarTransicion('1', '1', true, estados.get("q5"));
         * estados.get("q6").agregarTransicion(Constantes.ESPACIO, Constantes.ESPACIO,
         * true, estados.get("q4"));
         */
        maquina.setEstados(estados);
        maquina.setEstadoAceptador(estados.get("q8"));
        maquina.run("(((a)))", estados.get("q1"), true);

    }

}
