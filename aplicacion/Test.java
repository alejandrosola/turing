package aplicacion;

import java.util.HashMap;
import java.util.Map;

import model.Estado;
import model.MaquinaTuring;

public class Test {
    public static void main(String args[]) {

        MaquinaTuring maquina = new MaquinaTuring();
        MaquinaTuring maquinita = new MaquinaTuring();
        Map<String, Estado> estados = new HashMap<>();
        Map<String, Estado> estadosMaquinita = new HashMap<>();

        for (int i = 1; i <= 32; i++) {
            estadosMaquinita.put(String.format("q%d", i), new Estado(String.format("q%d", i)));
        }

        estadosMaquinita.get("q1").agregarTransicion('a', 'a', true, estadosMaquinita.get("q1"), null);
        estadosMaquinita.get("q1").agregarTransicion(Constantes.ESPACIO, Constantes.ESPACIO, true,
                estadosMaquinita.get("q2"), null);

        maquinita.setEstados(estadosMaquinita);
        maquinita.setEstadoAceptador(estadosMaquinita.get("q2"));
        maquinita.setEstadoInicial(estadosMaquinita.get("q1"));
        maquinita.setSubMaquina(true);

        for (int i = 1; i <= 3; i++) {
            estados.put(String.format("q%d", i), new Estado(String.format("q%d", i)));
        }

        estados.get("q1").agregarTransicion('a', 'a', true, estados.get("q1"), maquinita);
        estados.get("q1").agregarTransicion('c', 'c', true, estados.get("q1"));
        estados.get("q1").agregarTransicion(Constantes.ESPACIO, Constantes.ESPACIO, true, estados.get("q1"));
        estados.get("q1").agregarTransicion('b', 'c', true, estados.get("q2"));
        estados.get("q2").agregarTransicion(Constantes.ESPACIO, Constantes.ESPACIO, false, estados.get("q2"));
        estados.get("q2").agregarTransicion('c', 'c', false, estados.get("q3"));

        maquina.setEstados(estados);
        maquina.setEstadoAceptador(estados.get("q3"));

        maquina.run("caaaaaaΔΔΔb", estados.get("q1"), true);
    }

}
