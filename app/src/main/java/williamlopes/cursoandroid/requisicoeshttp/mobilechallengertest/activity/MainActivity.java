package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.AdapterRepositorios.AdapterRepositorios;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.R;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.api.DataService;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.listener.RecyclerItemClickListener;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Repositorio;

public class MainActivity extends AppCompatActivity {

    private Button botaoBuscar;
    private EditText textoResultado;

    private Retrofit retrofit;
    private List<Repositorio> listaRepositorios = new ArrayList<>();
    private RecyclerView recyclerRepositorios;
    private AdapterRepositorios adapterRepositorios;
    private ActivityRepositorios activityRepositorios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoBuscar = findViewById(R.id.botaoBuscar);
        textoResultado = findViewById(R.id.textoResultado);
        recyclerRepositorios = findViewById(R.id.recyclerRepositorios);

        /*try {
            //Criar banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            //Criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS usuarios ( id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, repositorio INT(3) )");
        }catch (Exception e){
            e.printStackTrace();
        }*/


        /* Configurando o Retrofit */
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create()) //Escolher o conversor a ser utilizado
                .build();

        botaoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarRepositorio();
            }
        });

    }

    private void recuperarRepositorio() {

        DataService service = retrofit.create(DataService.class);
        Call<List<Repositorio>> call = service.recuperarRepositorio( String.valueOf(textoResultado.getText()) );

        call.enqueue(new Callback<List<Repositorio>>() {
            @Override
            public void onResponse(Call<List<Repositorio>> call, Response<List<Repositorio>> response) {

                if (response.isSuccessful()){

                    listaRepositorios = response.body();

                    Intent intent = new Intent(MainActivity.this, ActivityRepositorios.class);
                    intent.putExtra("ListaRepositório", (Serializable) listaRepositorios);
                    startActivity(intent);


                    //for (int i = 0; i < listaRepositorios.size(); i++){}
                }
            }

            @Override
            public void onFailure(Call<List<Repositorio>> call, Throwable t) {
                Log.i("Erro", "onFailure:" + t.getMessage());
            }
        });
    }
}
