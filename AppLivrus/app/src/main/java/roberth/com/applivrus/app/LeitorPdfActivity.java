package roberth.com.applivrus.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

import roberth.com.applivrus.R;
import roberth.com.applivrus.models.Livro;

public class LeitorPdfActivity extends AppCompatActivity implements OnPageChangeListener {

    Livro livro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_pdf);
        //PDFVIEW DEVE MOSTRAR OS PDFS
        PDFView pdfView= (PDFView) findViewById(R.id.pdfView);
        //SCROLLBAR PARA HABILITAR O DESLIZE


        //SCROLLING VERTICAL

        //MELHOR QUALIDADE
        //pdfView.useBestQuality(true)

        //
        Intent intent =this.getIntent();

        long id = intent.getLongExtra("livro",0);

        livro = Livro.findById(Livro.class,id);


        //PEGAR O ARQUIVO
        File file=new File(livro.getCaminho());

        if(file.canRead())
        {
            //CARREGA O LIVRO
            pdfView.fromFile(file).defaultPage(livro.getPaginaAtual()-1).onLoad(new OnLoadCompleteListener() {

                //SOBREESCRITA PARA PODER PEGAR O NÚMERO TOTAL DE PÁGINAS
                @Override
                public void loadComplete(int nbPages) {
                    livro.setPaginas(nbPages);
                    livro.save();
                }
            }).enableSwipe(true)
                    .enableAnnotationRendering(true)
                    .onPageChange(this)
                    .swipeHorizontal(false)
                    .scrollHandle(new DefaultScrollHandle(this))
                    .load();
        }
    }

    //SOBREESCRITA PRA PODER SALVARA PÁGINA ATUAL DO LIVRO
    @Override
    public void onPageChanged(int page, int pageCount) {
        livro.setPaginaAtual(page+1);
        livro.save();
        setTitle(String.format("%s %s / %s", livro.getTitulo(), page + 1, pageCount));
    }
}