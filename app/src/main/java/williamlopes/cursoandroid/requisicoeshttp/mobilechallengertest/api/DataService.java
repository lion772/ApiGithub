package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Repositorio;


public interface DataService {

    @GET("/users/{username}/repos")
    Call<List<Repositorio>> recuperarRepositorio(@Path("username") String username);

}
