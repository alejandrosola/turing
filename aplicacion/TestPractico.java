package aplicacion;

import java.util.HashMap;
import java.util.Map;

import model.Estado;
import model.MaquinaTuring;

public class TestPractico {
    public static void main(String args[]) {

        MaquinaTuring maquina = new MaquinaTuring();

        Map<String, Estado> estados = new HashMap<>();

        for (int i = 1; i <= 6; i++) {
            estados.put(String.format("q%d", i), new Estado(String.format("q%d", i)));
        }

        estados.get("q1").agregarTransicion('0', '0', true, estados.get("q2"));
        estados.get("q1").agregarTransicion('1', '1', true, estados.get("q5"));

        estados.get("q2").agregarTransicion('1', '1', true, estados.get("q3"));

        estados.get("q3").agregarTransicion('_', '_', true, estados.get("q4"));
        estados.get("q3").agregarTransicion('0', '0', true, estados.get("q2"));

        estados.get("q5").agregarTransicion('0', '0', true, estados.get("q6"));

        estados.get("q6").agregarTransicion('1', '1', true, estados.get("q5"));
        estados.get("q6").agregarTransicion('_', '_', true, estados.get("q4"));

        maquina.setEstados(estados);
        maquina.setEstadoAceptador(estados.get("q4"));
        maquina.run("1010101010", estados.get("q1"), true);

    }

}
