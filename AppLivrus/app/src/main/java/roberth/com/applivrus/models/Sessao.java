package roberth.com.applivrus.models;

import com.orm.SugarRecord;

/**
 * Created by Roberth Santos on 06/03/2018.
 */

public class Sessao extends SugarRecord {

    private long idUsuario;

    public Sessao() {

    }



    public Sessao(long idUsuario) {
        this.idUsuario = idUsuario;

    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }


}
