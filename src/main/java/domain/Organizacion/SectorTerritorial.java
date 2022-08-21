package domain.Organizacion;


public class SectorTerritorial {
    private String nombre;
    TipoSectorTerritorial tipoSectorTerritorial;

    public SectorTerritorial(String nombre, TipoSectorTerritorial tipoSectorTerritorial) {
        this.nombre = nombre;
        this.tipoSectorTerritorial = tipoSectorTerritorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoSectorTerritorial getTipoSectorTerritorial() {
        return tipoSectorTerritorial;
    }

    public void setTipoSectorTerritorial(TipoSectorTerritorial tipoSectorTerritorial) {
        this.tipoSectorTerritorial = tipoSectorTerritorial;
    }
}
