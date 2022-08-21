package domain.Trayecto.Distancia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class DistanciaAPI {
    private static DistanciaAPI instancia = null;
    private static final String urlAPI = "https://api.refugiosdds.com.ar/api/";
    private Retrofit retrofit;

    private DistanciaAPI(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static DistanciaAPI getInstancia(){
        if(instancia == null){
            instancia = new DistanciaAPI();
        }
        return instancia;
    }

    public ListadoDeHogares listadoDeHogares() throws IOException {
        ServicesHogares servicesHogares = this.retrofit.create(ServicesHogares.class);
        Call<ListadoDeHogares> requestHogares = servicesHogares.hogares();
        Response<ListadoDeHogares> responseHogares = requestHogares.execute();
        return responseHogares.body();
    }
}