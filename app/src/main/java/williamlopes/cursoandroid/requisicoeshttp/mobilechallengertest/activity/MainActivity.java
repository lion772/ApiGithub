package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

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

public class MainActivity extends AppCompatActivity {

    private Button botaoBuscar;
    private EditText textoResultado;

    private Retrofit retrofit;
    private List<Repositorio> listaRepositorios = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterRepositorios adapterRepositorios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoBuscar = findViewById(R.id.botaoBuscar);
        textoResultado = findViewById(R.id.textoResultado);

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
                    //Configurando o adapter
                    adapterRepositorios = new AdapterRepositorios(listaRepositorios, MainActivity.this);
                    recyclerView.setAdapter(adapterRepositorios);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    //Configurando o recycler



                    for (int i = 0; i < listaRepositorios.size(); i++){

                        Repositorio repositorio = listaRepositorios.get(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Repositorio>> call, Throwable t) {

            }
        });
    }
}
