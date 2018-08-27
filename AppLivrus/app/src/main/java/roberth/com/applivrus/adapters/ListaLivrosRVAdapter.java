package roberth.com.applivrus.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import roberth.com.applivrus.app.FormularioLivroActivity;
import roberth.com.applivrus.models.Livro;
import roberth.com.applivrus.app.LivrosStatus;
import roberth.com.applivrus.R;

/**
 * Created by Roberth Santos on 17/02/2018.
 */

public class ListaLivrosRVAdapter extends RecyclerView.Adapter<ListaLivrosRVAdapter.ViewHolder> {

    private final Context context;
    private final List<Livro> livros;

    @Override
    public ListaLivrosRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_livros,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Livro livro = this.livros.get(position);
        holder.tvLivroNome.setText(livro.getTitulo());
        /*try {
            holder.tvLivroAutor.setText(livro.getAutor().getNome());
        }catch (NullPointerException npe){
            holder.tvLivroAutor.setText("Desconhecido");
        }*/
        try {
            holder.pbLivroProgresso.setProgress((Integer.valueOf(livro.getPaginaAtual() * 100) / Integer.valueOf(livro.getPaginas())));
        }catch (Exception npe){
            holder.pbLivroProgresso.setProgress(0);
        }
        //holder.tvLivroGenero.setText(livro.getGenero());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Snackbar.make(v, "Livro: " + livro.getTitulo(), Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(context, LivrosStatus.class);
                intent.putExtra("livro",livro.getId());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                PopupMenu pop = new PopupMenu(context, v);
                MenuInflater menuInflater = pop.getMenuInflater();

                menuInflater.inflate(R.menu.popup_menu_lista_livros, pop.getMenu());

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        //Deletar livro
                        if (item.getItemId() == R.id.item_remover_livro){
                            livro.delete();
                            ListaLivrosRVAdapter.this.livros.remove(position);
                            ListaLivrosRVAdapter.this.notifyItemRemoved(position);
                            ListaLivrosRVAdapter.this.notifyItemChanged(position,ListaLivrosRVAdapter.this.livros.size());
                            Toast.makeText(context,"Removido",Toast.LENGTH_SHORT).show();
                        }

                        //Editar livro
                        if (item.getItemId() == R.id.item_editar_livro){
                            Intent intent = new Intent(context, FormularioLivroActivity.class);
                            intent.putExtra("livro",livro.getId());
                            context.startActivity(intent);
                        }

                        return true;
                    }
                });

                pop.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        protected TextView tvLivroNome;
        protected TextView tvLivroGenero;
        //protected TextView tvLivroAutor;
        protected ProgressBar pbLivroProgresso;

        public ViewHolder(View itemView){
            super(itemView);

            tvLivroNome = (TextView) itemView.findViewById(R.id.tv_livro_nome);
            //tvLivroGenero = (TextView) itemView.findViewById(R.id.tv_livro_genero);
           // tvLivroAutor = (TextView) itemView.findViewById(R.id.tv_livro_autor);
            pbLivroProgresso = (ProgressBar) itemView.findViewById(R.id.pb_livo);
        }
    }
    public ListaLivrosRVAdapter(Context context, List<Livro> livros){

        this.context = context;
        this.livros = livros;
    }
}
