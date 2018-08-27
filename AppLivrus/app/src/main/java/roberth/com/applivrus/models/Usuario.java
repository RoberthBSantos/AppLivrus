package roberth.com.applivrus.models;

import com.orm.SugarRecord;

/**
 * Created by Roberth Santos on 05/03/2018.
 */

public class Usuario extends SugarRecord {
    private String email;
    private String senha;


    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String email, String senha) {
        this.email = email;

        this.senha = senha;
    }
}

