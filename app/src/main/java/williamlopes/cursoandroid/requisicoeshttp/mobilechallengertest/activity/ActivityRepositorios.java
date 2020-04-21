package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.AdapterRepositorios.AdapterRepositorios;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.R;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.listener.RecyclerItemClickListener;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Items;

public class ActivityRepositorios extends AppCompatActivity {

    private TextView nome;
    private List<Items> listaItems = new ArrayList<>();
    private CircleImageView avatar;
    private RecyclerView recyclerRepositorios;

    private AdapterRepositorios adapterRepositorios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorios);


        //Recuperar lista de repositórios
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ){

            listaItems = (List<Items>) bundle.getSerializable("ListaItems");
        }

        //Configurando o adapter
        adapterRepositorios = new AdapterRepositorios(listaItems, this);
        recyclerRepositorios.setAdapter(adapterRepositorios);
        recyclerRepositorios.setHasFixedSize(true);
        recyclerRepositorios.setLayoutManager(new LinearLayoutManager(this));

        //Configurando o recycler
        recyclerRepositorios.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerRepositorios,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Items itemSelecionado = listaItems.get(position);
                        Intent i = new Intent(ActivityRepositorios.this, ActivityRepositorioDados.class);
                        i.putExtra("ItemSelecionado", itemSelecionado);
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }

        ));


        //Recuperar empresa selecionada
        /*Bundle bundle = getIntent().getExtras();
        if (  bundle != null ){

            empresaSelecionada = (Empresa) bundle.getSerializable("empresa");

            textNomeEmpresaCardapio.setText(empresaSelecionada.getNome());
            idEmpresa = empresaSelecionada.getIdUsuario();

            String urlImagem = empresaSelecionada.getUrlImagem();
            Picasso.get().load( urlImagem ).into( imageEmpresaCardapio );
        }*/
    }
}
