package persistencia.factories;

import config.Config;
import persistencia.daos.DAOHibernate;
import persistencia.daos.DAOMemoria;
import persistencia.testMemoData.Data;

public class FactoryRepositorioMascotas {
    private static RepositorioDeMascotas repo;

    static {
        repo = null;
    }

    public static RepositorioDeMascotas get() {
        if (repo == null) {
            if (Config.useDataBase) {
                DAO<Mascota> dao = new DAOHibernate<>(Mascota.class);
                repo = new RepositorioDeMascotas(dao);
            } else {
                repo = new RepositorioDeMascotas(new DAOMemoria<>(Data.getData(Mascota.class)));
            }
        }
        return repo;
    }
}
