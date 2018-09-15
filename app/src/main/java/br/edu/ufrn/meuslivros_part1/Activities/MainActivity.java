package br.edu.ufrn.meuslivros_part1.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ufrn.meuslivros_part1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v){
        Button button1 = findViewById(R.id.cadastrar);
        Button button2 = findViewById(R.id.visualizar);

        Intent intent;

        if(v.getId() == R.id.cadastrar){
            intent = new Intent(this, CadastraActivity.class);
        }else{
            intent = new Intent(this, VisualizaActivity.class);
        }

        startActivity(intent);
    }

}
