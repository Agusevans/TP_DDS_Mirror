package persistencia.factories;

import config.Config;
import persistencia.daos.*;
import persistencia.repositorio.Repositorio;
import persistencia.testMemoData.Data;

import java.util.HashMap;

public class FactoryRepositorio {
    private static HashMap<String, Repositorio> repos;

    static {
        repos = new HashMap<>();
    }

    public static <T> Repositorio<T> get(Class<T> type){
        Repositorio<T> repo;
        if(repos.containsKey(type.getName())){
            repo = repos.get(type.getName());
        }
        else{
            if(Config.useDataBase){
                DAO<T> dao = new DAOHibernate<>(type);
                repo = new Repositorio<>(dao);
            }
            else{
                repo = new Repositorio<>(new DAOMemoria<>(Data.getData(type)));
            }
            repos.put(type.toString(), repo);
        }
        return repo;
    }
}