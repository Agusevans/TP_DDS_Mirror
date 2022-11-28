package persistencia.repositorio;

import domain.Organizacion.Miembro;
import domain.Organizacion.Organizacion;
import persistencia.BusquedaCondicional;
import persistencia.EntityManagerHelper;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepoMiembro extends Repositorio<Miembro>{

    public RepoMiembro(DAO<Miembro> dao) {
        super(dao);
    }

    public List<Miembro> buscarMiembrosDeOrg(int idOrg) {

        String query = "from Organizacion where id = " + idOrg;
        Organizacion org = EntityManagerHelper.getEntityManager().createQuery(query,Organizacion.class).getSingleResult();

        return org.obtenerMiembros();
    }

    //esta mal hecha esta busqueda, revisar
    public Miembro buscarMiembro(int nro_doc) {
        Miembro miembro = null;
        try{
            miembro = this.dao.buscar(miembro(nro_doc));
        }
        catch (Exception e){
            return null;
        }

        return miembro;
    }

    BusquedaCondicional miembro(int nro_doc){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Miembro> agenteQuery = criteriaBuilder.createQuery(Miembro.class);

        Root<Miembro> raiz = agenteQuery.from(Miembro.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("nroDocumento"), nro_doc);

        agenteQuery.where(predicado);

        return new BusquedaCondicional(null, agenteQuery);
    }

}

