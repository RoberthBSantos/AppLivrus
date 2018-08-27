package roberth.com.applivrus.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import roberth.com.applivrus.R;
import roberth.com.applivrus.app.FormularioLivroActivity;
import roberth.com.applivrus.app.ListaLivrosPdfActivity;
import roberth.com.applivrus.models.Livro;
import roberth.com.applivrus.models.PDF;

/**
 * Created by Roberth Santos on 17/03/2018.
 */

public class ListaPdfAdapter extends BaseAdapter {

    Context context;
    ArrayList<PDF> pdfs;

    public ListaPdfAdapter(Context context, ArrayList<PDF> pdfs) {
        this.context = context;
        this.pdfs = pdfs;
    }

    @Override
    public int getCount() {
        return pdfs.size();
    }

    @Override
    public Object getItem(int position) {
        return pdfs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){

            //inflar um layout
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pdf,parent,false);
        }

        final PDF pdf = (PDF) this.getItem(position);

        TextView nomePdf = (TextView) convertView.findViewById(R.id.nome_pdf);
        ImageView imagemPdf = (ImageView) convertView.findViewById(R.id.imagem_pfd);

        nomePdf.setText(pdf.getNome());


        convertView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view){
            passarPdf(pdf.getCaminho(),pdf.getNome());
            ((Activity)context).finish();
        }});
        return convertView;
    }


    private void passarPdf(String caminho, String nome){

        Intent intent = new Intent(context, FormularioLivroActivity.class);
        intent.putExtra("caminho",caminho);
        intent.putExtra("pdfNome",nome);
        context.startActivity(intent);


    }
}
