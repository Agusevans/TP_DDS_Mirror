package domain.Trayecto;

import java.util.List;

public class TransportePublico extends MedioTransporte {
    String linea;
    TipoPublico tipo;
    List<Parada> parada;
}
