package roberth.com.applivrus.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import roberth.com.applivrus.models.Livro;
import roberth.com.applivrus.R;

public class LivrosStatus extends AppCompatActivity {

    private TextView titulo;
    private TextView autor;
    private TextView genero;
    private TextView estado;
    private ProgressBar barraProgresso;
    private TextView progresso;
    private RatingBar avaliacao;
    private TextView resultadoAvaliacao;
    private float calculoProgresso;
    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros_status);

        titulo = (TextView) findViewById(R.id.tv_status_titulo);
        autor = (TextView) findViewById(R.id.tv_status_autor);
        genero = (TextView) findViewById(R.id.tv_status_genero);
        estado = (TextView) findViewById(R.id.tv_status_estado);
        barraProgresso = (ProgressBar) findViewById(R.id.pb_status);
        progresso = (TextView) findViewById(R.id.tv_status_progresso);
        avaliacao = (RatingBar) findViewById(R.id.rb_status);
        resultadoAvaliacao = (TextView) findViewById(R.id.tv_status_avaliacao);

        buscaDadosLivro();

    }

    public void buscaDadosLivro(){
        //pegar o id do livro pela intent
        livro = new Livro();
        Intent intent = getIntent();
        long id = intent.getLongExtra("livro",0);
        livro = Livro.findById(Livro.class,id);

        //passando os dados do livro para os campos
        titulo.setText(livro.getTitulo().toString());
        textoAvaliacao(livro.getAvaliacao());


        //caso autor não existir tratar o NullPointer
        try {
            autor.setText("Autor: " + livro.getAutor().toString());
        }catch (NullPointerException npe){
            autor.setText("Autor: Desconhecido");
        }
        genero.setText("Gênero: " + livro.getGenero().toString());



        //Setando o estado de leitura de acordo com a pagina de leitura...
        try {
            if(livro.getPaginas() == livro.getPaginaAtual()){
                livro.setEstado("3");
                estado.setText("Estado: Concluído");
            }

            if(livro.getPaginaAtual() < livro.getPaginas()){
                livro.setEstado("1");
                estado.setText("Estado: Em leitura");
            }

            if (livro.getPaginaAtual() == 1){
                livro.setEstado("0");
                estado.setText("Estado: Na fila");
            }
        }catch(NumberFormatException nfe){
            estado.setText("Estado: Na fila");
        }
        livro.save();


        //AJUSTANDO A BARRA DE PROGRESSO.

        try {
            calculoProgresso = (Integer.valueOf(livro.getPaginaAtual() * 100) / Integer.valueOf(livro.getPaginas()));
        }catch (Exception e){
            calculoProgresso = 0;
        }
        barraProgresso.setProgress((int) calculoProgresso);
        progresso.setText(String.valueOf(livro.getPaginaAtual()) + "/" + String.valueOf(livro.getPaginas()));


        //AJUSTANDO A RATING BAR.

        avaliacao.setRating(livro.getAvaliacao());
        avaliacao.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                livro.setAvaliacao((int) avaliacao.getRating());
                textoAvaliacao((int) avaliacao.getRating());
                livro.save();
            }
        });

    }

    //METODO PARA COLOCAR O TEXTO DA AVALIAÇÃO DE ACORDO COM A NOTA.
    public void textoAvaliacao(int avaliacao){

        if(avaliacao == 1){
            resultadoAvaliacao.setText("Muito Ruim");
        }
        if(avaliacao == 2){
            resultadoAvaliacao.setText("Ruim");
        }
        if(avaliacao == 3){
            resultadoAvaliacao.setText("Regular");
        }
        if(avaliacao == 4){
            resultadoAvaliacao.setText("Bom");
        }
        if(avaliacao == 5){
            resultadoAvaliacao.setText("Excelente");
        }
        if(avaliacao == 0){
            resultadoAvaliacao.setText("");
        }
    }


    //CHAMAR A PÁGINA DE ANOTAÇÕES.
    public void anotacoes(View view) {
        Intent intent = new Intent(LivrosStatus.this, AnotacoesActivity.class);
        intent.putExtra("livro",livro.getId());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        buscaDadosLivro();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        buscaDadosLivro();
        super.onRestart();
    }

    //ABRIR O LIVRO
    public void lerLivro(View view) {
        Intent intent =new Intent(this,LeitorPdfActivity.class);
        intent.putExtra("livro",livro.getId());
        startActivity(intent);
    }

}
