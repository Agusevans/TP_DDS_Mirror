package persistencia.repositorio;

import domain.Organizacion.AgenteSectorial;
import domain.Organizacion.Miembro;
import persistencia.BusquedaCondicional;
import persistencia.EntityManagerHelper;
import persistencia.daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepoMiembro extends Repositorio<Miembro>{

    public RepoMiembro(DAO<Miembro> dao) {
        super(dao);
    }

    public Miembro buscarMiembro(int nro_doc) {return this.dao.buscar(miembro(nro_doc));}

    BusquedaCondicional miembro(int nro_doc){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Miembro> agenteQuery = criteriaBuilder.createQuery(Miembro.class);

        Root<Miembro> raiz = agenteQuery.from(Miembro.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("nro_doc"), nro_doc);

        agenteQuery.where(predicado);

        return new BusquedaCondicional(null, agenteQuery);
    }

}

