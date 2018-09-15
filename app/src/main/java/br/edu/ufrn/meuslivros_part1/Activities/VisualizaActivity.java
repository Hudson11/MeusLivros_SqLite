package br.edu.ufrn.meuslivros_part1.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufrn.meuslivros_part1.BancoSqLite.BancoHelper;
import br.edu.ufrn.meuslivros_part1.R;
import br.edu.ufrn.meuslivros_part1.classes.Livro;

public class VisualizaActivity extends AppCompatActivity {

    private int cont = 0;

    TextView autor_livro;
    TextView titulo_livro;
    TextView ano_livro;
    TextView nota_livro;
    BancoHelper db;
    List<Livro> livros = new ArrayList<>();
    Livro v = new Livro();
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        autor_livro = findViewById(R.id.autor_livro);
        titulo_livro = findViewById(R.id.titulo_livro);
        ano_livro = findViewById(R.id.ano_livro);
        nota_livro = findViewById(R.id.nota_livro);

        db = new BancoHelper(this);

        livros = db.findAll();
        index = livros.size();
        v = livros.get(0);

        setTextView(v);

    }

    public void proximo_rg(View view){
        cont++;

        if(cont == index) {
            cont = index - 1;
            return;
        }

        v = livros.get(cont);

        setTextView(v);
    }

    public void anterior_rg(View view){
        cont--;

        if(cont == -1){
            cont = 0;
            return;
        }

        v = livros.get(cont);
        setTextView(v);
    }


    public void setTextView(Livro v){
        autor_livro.setText(v.getAutor().toString());
        titulo_livro.setText(v.getTitulo().toString());
        ano_livro.setText(String.valueOf(v.getAno()).toString());
        nota_livro.setText(String.valueOf(v.getNota()).toString());
    }

}
