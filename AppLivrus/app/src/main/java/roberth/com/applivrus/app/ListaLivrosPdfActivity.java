package roberth.com.applivrus.app;

import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import roberth.com.applivrus.R;
import roberth.com.applivrus.adapters.ListaPdfAdapter;
import roberth.com.applivrus.models.PDF;

public class ListaLivrosPdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros_pdf);
        final ListView listaPdf = (ListView) findViewById(R.id.lista_pdfs);
        FloatingActionButton atualizar = (FloatingActionButton) findViewById(R.id.atualizar_pdfs);
        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaPdf.setAdapter(new ListaPdfAdapter(ListaLivrosPdfActivity.this,atualizarPDFS()));
            }
        });

    }

    private ArrayList<PDF> atualizarPDFS() {

        ArrayList<PDF> pdfs = new ArrayList<>();
        //PASTA ALVO
        File pastaDownloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        PDF pdf;

        if(pastaDownloads.exists()){

            //PEGA TODOS OS ARQUIVOS DA PASTA DOWNLOADS
            File[] files = pastaDownloads.listFiles();

            //PERCORRE OS ARQUIVOS PEGANDO O NOME E O CAMINHO
            for (int i = 0; i < files.length;i++){

                File file = files[i];

                if (file.getPath().endsWith("pdf")){
                    pdf = new PDF();
                    pdf.setNome(file.getName());
                    pdf.setCaminho(file.getAbsolutePath());

                    pdfs.add(pdf);

                }
            }
        }

        return pdfs;
    }
}
