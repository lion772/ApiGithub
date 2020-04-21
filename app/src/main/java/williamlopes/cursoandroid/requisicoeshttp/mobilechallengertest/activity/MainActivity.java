package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
                       ContentValues contentValues = new ContentValues();
                       contentValues.put(GithubContract.ItemsEntry.colunaId, rep.getId());
                       contentValues.put(GithubContract.ItemsEntry.colunaName, rep.getName());
                       contentValues.put(GithubContract.ItemsEntry.colunaDescription, rep.getDescription());
                       Long numero = mDb.insert(GithubContract.ItemsEntry.tabelaNome, null, contentValues);
                       Toast.makeText(MainActivity.this, "numero: " + numero, Toast.LENGTH_SHORT).show();
                   }

                   Intent intent = new Intent(MainActivity.this, ActivityRepositorios.class);
                   startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Log.i("Erro", "onFailure:" + t.getMessage());
            }
        });
    }
}
