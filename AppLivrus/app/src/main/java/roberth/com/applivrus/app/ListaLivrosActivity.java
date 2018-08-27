package roberth.com.applivrus.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import roberth.com.applivrus.models.Livro;
import roberth.com.applivrus.R;
import roberth.com.applivrus.adapters.ListaLivrosRVAdapter;
import roberth.com.applivrus.models.Sessao;

public class ListaLivrosActivity extends AppCompatActivity {

    RecyclerView rvLivros;
    private BottomNavigationView btBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros);


        rvLivros = (RecyclerView) findViewById(R.id.rv_livros);
        btBar = (BottomNavigationView) findViewById(R.id.btb_lista_livros);

        try {
            Activity self = this;
            btBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.bt_biblioteca:
                            setAdapterFilter(R.id.bt_biblioteca);
                            return true;
                        case R.id.bt_em_leitura:
                            setAdapterFilter(R.id.bt_em_leitura);
                            return true;
                        case R.id.bt_concluido:
                            setAdapterFilter(R.id.bt_concluido);
                            return  true;
                        case R.id.bt_em_espera:
                            setAdapterFilter(R.id.bt_em_espera);
                            return true;
                    }
                    return false;
                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void setAdapterFilter(int filterId){

        Sessao sessao = Sessao.listAll(Sessao.class).get(0);
        List<Livro> livros = Livro.listAll(Livro.class);
        List<Livro> tempLivro = new ArrayList<>();
        for (Livro livro:livros) {

            if (filterId == R.id.bt_biblioteca) {
                if (livro.getUsuario().getId().equals(sessao.getIdUsuario())) {
                    tempLivro.add(livro);
                }
            }
            if (filterId == R.id.bt_em_espera) {
                if (livro.getUsuario().getId().equals(sessao.getIdUsuario()) && livro.getEstado().equals("0")) {
                    tempLivro.add(livro);
                }
            }
            if (filterId == R.id.bt_concluido) {
                if (livro.getUsuario().getId().equals(sessao.getIdUsuario()) && livro.getEstado().equals("3")) {
                    tempLivro.add(livro);
                }
            }
            if (filterId == R.id.bt_em_leitura) {
                if (livro.getUsuario().getId().equals(sessao.getIdUsuario()) && livro.getEstado().equals("1")) {
                    tempLivro.add(livro);
                }
            }
        }
        ListaLivrosRVAdapter adapter = new ListaLivrosRVAdapter(ListaLivrosActivity.this, tempLivro);
        rvLivros.setAdapter(adapter);
        rvLivros.setLayoutManager(new LinearLayoutManager(ListaLivrosActivity.this));
        }



    @Override
    protected void onResume() {
        setAdapterFilter(R.id.bt_biblioteca);
        super.onResume();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onRestart() {
        setAdapterFilter(R.id.bt_biblioteca);
        super.onResume();
    }


    public void abrirFormulario(View view){
        Intent intent = new Intent(this, ListaLivrosPdfActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_lista_livros, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.item_logout){
            Sessao sessao = new Sessao();
            sessao = Sessao.listAll(Sessao.class).get(0);
            sessao.delete();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
