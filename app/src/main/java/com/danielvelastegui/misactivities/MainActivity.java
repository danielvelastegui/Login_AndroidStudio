package com.danielvelastegui.misactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnshowDatePicker;
    private Button btnSiguiente;
    private TextInputLayout txtNombre;
    private TextInputLayout txtTelefono;
    private TextInputLayout txtDescripcion;
    private TextInputLayout txtEmail;
    private String nombre;
    private String telefono;
    private String fecha;
    private String email;
    private String descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            recolectarInfo();
        }catch (Exception e){

        }

        btnshowDatePicker = findViewById(R.id.btnShowDatePicker);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtEmail = findViewById(R.id.txtMail);

        //CalendarConstraints
        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder();
        constraints.setValidator(DateValidatorPointBackward.now());

        //MaterialDatePicker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText(R.string.botonCalendario);
        builder.setCalendarConstraints(constraints.build());
        final MaterialDatePicker materialDatePicker = builder.build();

        btnshowDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");

            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                fecha = materialDatePicker.getHeaderText();
                btnshowDatePicker.setText(fecha);
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = txtNombre.getEditText().getText().toString().trim();
                telefono = txtTelefono.getEditText().getText().toString().trim();
                email = txtEmail.getEditText().getText().toString().trim();
                descripcion = txtDescripcion.getEditText().getText().toString().trim();

                boolean entradaValida = validarNombre() && validarTelefono() && validarEmail();

                if(entradaValida){
                    Intent intent = new Intent(MainActivity.this, Confirmacion.class);
                    intent.putExtra("pnombre", nombre);
                    intent.putExtra("pfecha", fecha);
                    intent.putExtra("ptelefono", telefono);
                    intent.putExtra("pemail", email);
                    intent.putExtra("pdescripcion", descripcion);

                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    private void recolectarInfo(){
        Bundle info = getIntent().getExtras();

        nombre = info.get("pnombre").toString();
        telefono = info.get("ptelefono").toString();
        fecha = info.getString("pfecha").toString();
        email = info.getString("pemail").toString();
        descripcion = info.get("pdescripcion").toString();

        txtNombre.getEditText().setText(nombre);
        btnshowDatePicker.setText(fecha);
        txtTelefono.getEditText().setText(telefono);
        txtEmail.getEditText().setText(email);
        txtDescripcion.getEditText().setText(descripcion);
    }

    private boolean validarNombre(){
        if(nombre.isEmpty()){
            txtNombre.setError(getResources().getString(R.string.error));
            return false;
        }else{
            txtNombre.setError(null);
            return true;
        }
    }

    private boolean validarTelefono(){

        boolean telefonoValido = telefono.matches("[0-9-+]+");
        if(telefono.isEmpty()){
            txtTelefono.setError(getResources().getString(R.string.error));
            return false;
        }else if(!telefonoValido){
            txtTelefono.setError(getResources().getString(R.string.telefonoInvalido));
            return false;
        }else{
            txtTelefono.setError(null);
            return true;
        }
    }

    private boolean validarEmail(){

        boolean correoValido = email.matches("[A-za-z-_0-9.]+[@][a-z]+[.][a-z]+");

        if(email.isEmpty()){
            txtEmail.setError(getResources().getString(R.string.error));
            return false;
        }else if(!correoValido) {
            txtEmail.setError(getResources().getString(R.string.emailInvalido));
            return false;
        }else{
            txtEmail.setError(null);
            return true;
        }
    }

}