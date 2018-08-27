package roberth.com.applivrus.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import roberth.com.applivrus.models.Livro;
import roberth.com.applivrus.R;
import roberth.com.applivrus.models.Sessao;
import roberth.com.applivrus.models.Usuario;

public class FormularioLivroActivity extends AppCompatActivity {

    private EditText edLivroTitulo;
    private EditText edLivroGenero;
    private EditText edLivroAno;
    private EditText edLivroAutor;
    private String estado = "0";
    private String autor;
    private Livro livro;
    private Usuario usuario;
    private String caminho;
    private int paginas = 999;
    private int pgAtual = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_livro);

        livro = new Livro();
        Intent intent = getIntent();

        edLivroTitulo = (EditText) findViewById(R.id.ed_livro_titulo);
        edLivroGenero = (EditText) findViewById(R.id.ed_livro_genero);
        edLivroAno = (EditText) findViewById(R.id.ed_livro_ano);
        edLivroAutor = (EditText) findViewById(R.id.ed_livro_autor);
        Sessao sessao = Sessao.listAll(Sessao.class).get(0);
        this.usuario = Usuario.findById(Usuario.class,sessao.getIdUsuario());



        // se vier id no getIntent vc substitui o livro


        long id = intent.getLongExtra("livro",0);

        //pegar dados do pdf
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            caminho = extra.getString("caminho");
            edLivroTitulo.setText(extra.getString("pdfNome"));
        }


        if (id > 0){

            // Preencher os editext

            livro = Livro.findById(Livro.class,id);
            edLivroTitulo.setText(livro.getTitulo());
            edLivroGenero.setText(livro.getGenero());
            edLivroAno.setText(String.valueOf(livro.getAno()));
            edLivroAutor.setText(livro.getAutor());
            paginas = livro.getPaginas();
            pgAtual = livro.getPaginaAtual();
            estado = livro.getEstado();
            caminho = livro.getCaminho();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    public void salvarLivro(View view) {
        try {
            final String titulo = edLivroTitulo.getText().toString();
            final String genero = edLivroGenero.getText().toString();
            final String autor = edLivroAutor.getText().toString();
            final int ano = Integer.valueOf(edLivroAno.getText().toString());
            final int paginas = this.paginas;
            final int pgAtual = this.pgAtual;


            livro.setTitulo(titulo);
            livro.setGenero(genero);
            livro.setAno(ano);
            livro.setAutor(autor);
            livro.setPaginas(paginas);
            livro.setPaginaAtual(pgAtual);
            livro.setEstado(estado);
            livro.setUsuario(this.usuario);
            livro.setCaminho(caminho);
            livro.save();
            Toast.makeText(this, "Livro salvo.", Toast.LENGTH_LONG).show();
            finish();

        }catch (Exception e){
            Toast.makeText(this,"Preencha as informações corretamente.",Toast.LENGTH_SHORT).show();

        }
    }

}
