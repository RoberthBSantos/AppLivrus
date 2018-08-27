package roberth.com.applivrus.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

import roberth.com.applivrus.R;
import roberth.com.applivrus.models.Livro;

public class AnotacoesActivity extends AppCompatActivity {
    private Livro livro;
    private EditText etComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacoes);
        etComentario = (EditText) findViewById(R.id.et2);

        etComentario.setScroller(new Scroller(getApplicationContext()));
        etComentario.setVerticalScrollBarEnabled(true);
        etComentario.setMinLines(5);
        etComentario.setMaxLines(5);

        Intent intent = getIntent();

        long id = intent.getLongExtra("livro",0);
        livro = new Livro();
        livro = Livro.findById(Livro.class,id);
        etComentario.setText(livro.getComentario());

    }

    public void salvar(View view) {
        livro.setComentario(etComentario.getText().toString());
        livro.save();
        Toast.makeText(this,"Anotação Salva!",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void limparTexto(View view) {
        etComentario.setText("");
    }
}
