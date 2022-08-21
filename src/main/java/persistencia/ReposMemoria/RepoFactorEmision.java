package persistencia.ReposMemoria;

import domain.Actividad.FactorEmision;

import java.util.ArrayList;
import java.util.List;

public class RepoFactorEmision {

    List<FactorEmision> factorEmisionList = new ArrayList<FactorEmision>();

    public FactorEmision search(String id)
    {
        for( FactorEmision fe : factorEmisionList)
        {
            if(fe.getId() == Integer.parseInt(id))
            {
                return fe;
            }
        }
        return null;
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
