package domain.Organizacion;

import java.util.List;

public class AgenteSectorial {
    String nombre;
    TipoTerritorio tipoTerritorio;
    List<Organizacion> organizaciones;
    String email;
    String periodo;

    public void obtenerHCTerritorial(){};

    public void agregarOrganizacion(Organizacion organizacion){
        this.organizaciones.add(organizacion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoTerritorio getTipoTerritorio() {
        return tipoTerritorio;
    }

    public void setTipoTerritorio(TipoTerritorio tipoTerritorio) {
        this.tipoTerritorio = tipoTerritorio;
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
