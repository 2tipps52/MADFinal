package com.example.a2tipps52.mad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ADDActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmaen);
        Button B = (Button)findViewById(R.id.b);
        B.setOnClickListener(this);
    }

    public void onClick(View view){
        EditText placen = (EditText)findViewById(R.id.naem);
        String nplace = placen.getText().toString();
        EditText placet = (EditText)findViewById(R.id.typtxt);
        String tplace = placet.getText().toString();
        EditText placed = (EditText)findViewById(R.id.destxt);
        String dplace = placed.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("com.example.naem", nplace);
        bundle.putString("com.example.typtxt", tplace);
        bundle.putString("com.example.destxt", dplace);

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();


    }
}
