package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.AdapterRepositorios.AdapterRepositorios;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados.GithubContract;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.BancoDeDados.SQLite;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.R;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.api.DataService;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.DadosUsuario;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Items;

import static williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.api.DataService.retrofit;

public class MainActivity extends AppCompatActivity {

    private Button botaoBuscar;
    private EditText textoResultado;
    private String palavraRecuperada = "";

    private List<Items>listaItems = new ArrayList<>();
    private RecyclerView recyclerRepositorios;
    private AdapterRepositorios adapterRepositorios;
    private ActivityRepositorios activityRepositorios;

    private SQLiteDatabase mDb;
    private Integer idUsuario = -1; //se o usuário não existir


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SQLite dbHelper = new SQLite(this);
        mDb = dbHelper.getWritableDatabase(); //Reinstancia o banco de dados se tiver uma instância já pré-definida


        botaoBuscar = findViewById(R.id.botaoBuscar);
        textoResultado = findViewById(R.id.textoResultado);
        recyclerRepositorios = findViewById(R.id.recyclerRepositorios);



        botaoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recuperarRepositorio();
            }
        });

    }



    private void recuperarRepositorio() {
        palavraRecuperada = textoResultado.getText().toString();

        DataService service = retrofit.create(DataService.class);
        Call<List<Items>> call = service.recuperarRepositorio( palavraRecuperada );

        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {

                if (response.code() == 200){ //Requisição OK

                    listaItems = response.body();
                   for (Integer i = 0; i < listaItems.size(); i++){

                       Items rep = listaItems.get(i);
                       DadosUsuario dadosUsuario = rep.getOwner();
                       idUsuario = dadosUsuario.getId();

                       ContentValues contentValuesRepositorios = new ContentValues();
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaId, rep.getId());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaName, rep.getName());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaDescription, rep.getDescription());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaIdOwner, dadosUsuario.getId());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaLanguage, rep.getLanguage());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaOpenIssues, rep.getOpen_issues());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaCreatedAt, rep.getCreated_at());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaStars, rep.getStargazers_count());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaForks, rep.getForks());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaClosedIssues, rep.getClosed_issues());
                       contentValuesRepositorios.put(GithubContract.ItemsEntry.colunaHtmlUrl, rep.getHtml_url());
                       mDb.insert(GithubContract.ItemsEntry.tabelaNome, null, contentValuesRepositorios);

                       ContentValues contentValuesUsuarios = new ContentValues();
                       contentValuesUsuarios.put(GithubContract.OwnerEntry.colunaAvatar, dadosUsuario.getAvatar_url());
                       contentValuesUsuarios.put(GithubContract.OwnerEntry.colunaId, dadosUsuario.getId());
                       contentValuesUsuarios.put(GithubContract.OwnerEntry.colunaLogin, dadosUsuario.getLogin());
                       Log.i("avatar", "onResponse: " + dadosUsuario.getAvatar_url());
                       mDb.insert(GithubContract.OwnerEntry.tabelaNome, null, contentValuesUsuarios);
                   }

                    if (idUsuario != -1){

                        Intent intent = new Intent(MainActivity.this, ActivityRepositorios.class);
                        intent.putExtra(GithubContract.OwnerEntry.colunaId, idUsuario);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                    }




                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Log.i("Erro", "onFailure:" + t.getMessage());
            }
        });
    }
}
