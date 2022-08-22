package persistencia.ReposMemoria;

import domain.Actividad.FactorEmision;
import domain.Organizacion.AgenteSectorial;

import java.util.ArrayList;
import java.util.List;

public class RepoFactorEmision {

    List<FactorEmision> factorEmisionList = new ArrayList<FactorEmision>();

    public FactorEmision search(Integer id) {
        for( FactorEmision fe : factorEmisionList) {
            if(fe.getId() == id) {
                return fe;
            }
        }
        return null;
    }

    public void update(FactorEmision factorEmision) {
        FactorEmision feBuscado = this.search(factorEmision.getId());
        this.delete(feBuscado);
        this.add(factorEmision);
    }

    public void delete(FactorEmision fe) {
        factorEmisionList.remove(fe);
    }

    public void add(FactorEmision fe){
        this.factorEmisionList.add(fe);
    }
}
