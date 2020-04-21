package williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.AdapterRepositorios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.bumptech.glide.Glide;

import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.R;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.DadosUsuario;
import williamlopes.cursoandroid.requisicoeshttp.mobilechallengertest.model.Items;

public class AdapterRepositorios extends RecyclerView.Adapter<AdapterRepositorios.MyViewHolder> {


    private List<Items> listaItems;
    private Context context;
    public AdapterRepositorios(List<Items> listaItems, Context context) {
        this.listaItems = listaItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_repositorios, parent, false);
        return new MyViewHolder( itemLista );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Items item = listaItems.get(position);

        holder.textoRepositorio.setText(item.getName());
        holder.textoDescricao.setText(item.getDescription());

        DadosUsuario dados = (DadosUsuario) item.getOwner();
        Glide.with(context).load( dados.getAvatar_url()).into(holder.imagem);


    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textoRepositorio;
        private TextView textoDescricao;
        private ImageView imagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textoRepositorio = itemView.findViewById(R.id.textRepositorio);
            textoDescricao = itemView.findViewById(R.id.textDescricao);
            imagem = itemView.findViewById(R.id.imagemUsuario);


        }
    }

}
