package persistencia.repositorio;

import domain.Actividad.DatosActividad;
import persistencia.EntityManagerHelper;
import persistencia.daos.DAO;
import spark.Request;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class  RepoDatosActividad extends Repositorio<DatosActividad>{

    public RepoDatosActividad(DAO<DatosActividad> dao) {
        super(dao);
    }

    //Paginado
    public List<DatosActividad> list(int offset, int limit){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DatosActividad> criteriaQuery = criteriaBuilder.createQuery(DatosActividad.class);
        Root<DatosActividad> root = criteriaQuery.from(DatosActividad.class);
        criteriaQuery.select(root);

        List<DatosActividad> result =
                EntityManagerHelper.getEntityManager()
                        .createQuery(criteriaQuery)
                        .setMaxResults(limit)
                        .setFirstResult(offset)
                        .getResultList();

        return result;
    }

    //Base para filtro
    public List<DatosActividad> filter(Request request){
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DatosActividad> criteriaQuery = criteriaBuilder.createQuery(DatosActividad.class);
        Root<DatosActividad> root = criteriaQuery.from(DatosActividad.class);

        List<Predicate> predicatesList = new ArrayList<>();
        predicatesList.add(criteriaBuilder.equal(root.get("actividad_id"), Integer.parseInt(request.queryParams("actividad_id"))));
        predicatesList.add(criteriaBuilder.equal(root.get("medicion_id"), Integer.parseInt(request.queryParams("medicion_id"))));
        predicatesList.add(criteriaBuilder.equal(root.get("periodoDeImputacion"), Date.valueOf(request.queryParams("periodoDeImputacion"))));
        Predicate[] finalPredicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(finalPredicates);
        criteriaQuery.where(finalPredicates);


        List<DatosActividad> result = EntityManagerHelper.getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();

        return result;
    }

    public List<DatosActividad> filterDate(int año, int mes, int idOrg){ //TODO: Hacer bien el filtro
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DatosActividad> criteriaQuery = criteriaBuilder.createQuery(DatosActividad.class);
        Root<DatosActividad> root = criteriaQuery.from(DatosActividad.class);

        List<Predicate> predicatesList = new ArrayList<>();
        //filtro por fecha
        predicatesList.add(criteriaBuilder.between(root.get("periodoDeImputacion"), LocalDate.of(año,mes,1), LocalDate.of(año,mes,31)));
        Predicate[] finalPredicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(finalPredicates);
        criteriaQuery.where(finalPredicates);

        List<DatosActividad> result = EntityManagerHelper.getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();

        return result;
    }

}
