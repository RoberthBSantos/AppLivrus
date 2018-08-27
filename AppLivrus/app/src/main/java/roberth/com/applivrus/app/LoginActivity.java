package roberth.com.applivrus.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import roberth.com.applivrus.R;
import roberth.com.applivrus.models.Sessao;
import roberth.com.applivrus.models.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsuario;
    private EditText edSenha;
    private ImageView logar;
    private List<Usuario> usuarios;
    private List<Sessao> sessao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsuario = (EditText) findViewById(R.id.ed_login_usuario);
        edSenha = (EditText) findViewById(R.id.ed_login_senha);
        logar = (ImageView) findViewById(R.id.iv_login);
        this.usuarios =  Usuario.listAll(Usuario.class);
        this.sessao = Sessao.listAll(Sessao.class);
        if(sessao.size() == 0){

        logar.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v){

            int cont = 0;
            for (int i = 1; i <= usuarios.size(); i++) {
                Usuario usuario = Usuario.findById(Usuario.class, i);
                if (edUsuario.getText().toString().equals(usuario.getEmail().toString()) && edSenha.getText().toString().equals(usuario.getSenha())) {
                    Sessao sessao = new Sessao(usuario.getId());
                    sessao.save();
                    Intent intent = new Intent(LoginActivity.this, ListaLivrosActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    cont++;
                    if (cont >= usuarios.size()) {
                        Toast.makeText(LoginActivity.this, "Usuario ou senha incorretos!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            }
            });
        }else{
            Intent intent = new Intent(LoginActivity.this,ListaLivrosActivity.class);
            startActivity(intent);
            finish();
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarUsuarios();
    }

    private void carregarUsuarios() {

         this.usuarios = Usuario.listAll(Usuario.class);

    }


    public void cadastroUsuario(View view) {
        // TODO: 05/03/2018

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View viewDialog = getLayoutInflater().inflate(R.layout.cadastro_usuario, null);

        builder.setView(viewDialog)
                .setTitle("Novo usuario")
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText edCadastroUsuario = (EditText) viewDialog.findViewById(R.id.ed_cadastro_usuario);
                        EditText edCadastroSenha = (EditText) viewDialog.findViewById(R.id.ed_cadastro_senha);
                        EditText edCadastroConfirmarSenha = (EditText) viewDialog.findViewById(R.id.ed_cadastro_confirmar_senha);

                        String email = edCadastroUsuario.getText().toString();
                        String senha = edCadastroSenha.getText().toString();
                        String confirmaSenha = edCadastroConfirmarSenha.getText().toString();

                        if(senha.equals(confirmaSenha)) {
                            Usuario usuario = new Usuario(email, senha);
                            usuario.save();
                            carregarUsuarios();
                            Toast.makeText(LoginActivity.this, "Cadastrado.", Toast.LENGTH_LONG).show();

                        }else{

                            Toast.makeText(LoginActivity.this, "Senhas incompativeis.", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar",null)
                .show();

    }


}
