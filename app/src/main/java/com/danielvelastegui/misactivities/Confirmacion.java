package com.danielvelastegui.misactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirmacion extends AppCompatActivity {

    private String nombre;
    private String telefono;
    private String fecha;
    private String email;
    private String descripcion;
    private TextView tvInfoNombre;
    private TextView tvInfoFecha;
    private TextView tvInfoEmail;
    private TextView tvInfoDescripcion;
    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        recolectarInfo();
        mostrarInfo();

    }

    private void recolectarInfo(){
        Bundle info = getIntent().getExtras();

        nombre = info.get("pnombre").toString();
        telefono = info.get("ptelefono").toString();
        fecha = info.getString("pfecha").toString();
        email = info.getString("pemail").toString();
        descripcion = info.get("pdescripcion").toString();
    }

    private void mostrarInfo(){
        tvInfoNombre = findViewById(R.id.tvInfoNombre);
        tvInfoFecha = findViewById(R.id.tvInfoFecha);
        tvInfoEmail = findViewById(R.id.tvInfoEmail);
        tvInfoDescripcion = findViewById(R.id.tvInfoDescripcion);
        btnEditar = findViewById(R.id.btnEditar);

        tvInfoNombre.setText(nombre);
        tvInfoFecha.setText(fecha);
        tvInfoEmail.setText(email);
        tvInfoDescripcion.setText(descripcion);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Confirmacion.this, MainActivity.class);
                intent.putExtra("pnombre", nombre);
                intent.putExtra("pfecha", fecha);
                intent.putExtra("ptelefono", telefono);
                intent.putExtra("pemail", email);
                intent.putExtra("pdescripcion", descripcion);
                startActivity(intent);
                finish();
            }
        });
    }
}