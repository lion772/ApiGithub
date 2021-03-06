package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Items;


public interface DataService {

    @GET("/users/{username}/repos")
    Call<List<Items>> recuperarRepositorio(@Path("username") String username);

    /* Configurando o Retrofit */
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create()) //Escolher o conversor a ser utilizado
            .build();

}
