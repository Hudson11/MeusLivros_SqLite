package br.edu.ufrn.meuslivros_part1.Activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.edu.ufrn.meuslivros_part1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v){
        Intent t = new Intent(this, CadastraActivity.class);
        startActivityForResult(t, 1);
    }

    public void click2(View v){
        Intent t = new Intent(this, VisualizaActivity.class);
        startActivity(t);
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent i){

        String label;

        if(resultcode == RESULT_OK)
            label = "Livro Registrado Com Sucesso";
        else
            label = "Operação Cancelada";

        View v = findViewById(R.id.constraintLayout);

        Snackbar snackbar = Snackbar.make((View) v.getParent(), ""+label, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

}
