package it.localhost.app.mobile.learningrecyclercardview.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import it.localhost.app.mobile.learningrecyclercardview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRecyclerLinear = (Button) findViewById(R.id.btnRecyclerLinear);
        btnRecyclerLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
            }
        });
        Button btnRecyclerGrid = (Button) findViewById(R.id.btnRecyclerGrid);
        btnRecyclerGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecyclerPhotoActivity.class));
            }
        });
        Button btnRecyclerCard = (Button) findViewById(R.id.btnRecyclerCard);
        btnRecyclerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecyclerCardActivity.class));
            }
        });
    }
}
