package persistencia.ReposMemoria;

import domain.Actividad.FactorEmision;

import java.util.ArrayList;
import java.util.List;

public class RepoFactorEmision {

    List<FactorEmision> factorEmisionList = new ArrayList<FactorEmision>();

    public FactorEmision search(int id) //TODO: Ver en base a que compara, los FE solo tienen 2 campos en la clase
    {
        return new FactorEmision();
    }

    public void update(FactorEmision factorEmision)
    {
        /*FactorEmision feBuscado = this.search(factorEmision.getNombre());
        this.delete(feBuscado);
        this.add(feBuscado);
         */
    }

    //TODO: Implementar delete y add
}
