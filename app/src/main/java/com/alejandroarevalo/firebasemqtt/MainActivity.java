package com.alejandroarevalo.firebasemqtt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alejandroarevalo.firebasemqtt.model.Sensor;
import com.google.firebase.FirebaseApp;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private List<Sensor> listSensor = new ArrayList<Sensor>();
    ArrayAdapter<Sensor> arrayAdapterSensor;


    EditText Nombre,Observacion,Tipo,Valor,Ubicacion,Fecha,Hora;
    ListView lv_sensor;



    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Sensor sensorSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nombre = findViewById(R.id.txt_nombre);
        Observacion = findViewById(R.id.txt_observacion);
        Tipo = findViewById(R.id.txt_tipo);
        Valor = findViewById(R.id.txt_valor);
        Ubicacion = findViewById(R.id.txt_ubicacion);
        Fecha = findViewById(R.id.txt_date);
        Hora = findViewById(R.id.txt_hora);



        lv_sensor = findViewById(R.id.lv_datosSensor);
        inicializarFirebase();
        listarDatos();

        lv_sensor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sensorSelected = (Sensor) parent.getItemAtPosition(position);
                Observacion.setText(sensorSelected.getObservacion());
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("Sensor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSensor.clear();

                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Sensor s = objSnaptshot.getValue(Sensor.class);
                    listSensor.add(s);
                }


                arrayAdapterSensor = new ArrayAdapter<Sensor>(MainActivity.this, android.R.layout.simple_list_item_1, listSensor);
                lv_sensor.setAdapter(arrayAdapterSensor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //MENU
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        String nombre = Nombre.getText().toString();
        String observacion = Observacion.getText().toString();
        String tipo = Tipo.getText().toString();
        String valor = Valor.getText().toString();
        String ubicacion = Ubicacion.getText().toString();
        String fecha = Fecha.getText().toString();
        String hora = Hora.getText().toString();

        //Switch
        switch (item.getItemId()){
            case R.id.icon_add:{
                    if(nombre.equals("") || observacion.equals("") || tipo.equals("") || valor.equals("") || ubicacion.equals("")|| fecha.equals("")||hora.equals("")){
                        validacion();
                    }
                    else{
                        Sensor s = new Sensor();
                        s.setUid(UUID.randomUUID().toString());
                        s.setNombre(nombre);
                        s.setObservacion(observacion);
                        s.setTipo(tipo);
                        s.setValor(valor);
                        s.setUbicación(ubicacion);
                        s.setFecha(fecha);
                        s.setHora(hora);
                        databaseReference.child("Sensor").child(s.getUid()).setValue(s);
                        Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
                        limpiarCajas();
                    }
                    break;
            }
            case R.id.icon_save:{
                Sensor s = new Sensor();
                s.setUid(sensorSelected.getUid());
                s.setObservacion(sensorSelected.getObservacion().toString().trim());
                s.setNombre(sensorSelected.getNombre().toString().trim());
                s.setTipo(sensorSelected.getTipo().toString().trim());
                s.setValor(sensorSelected.getValor().toString().trim());
                s.setUbicación(sensorSelected.getUbicación().toString().trim());
                s.setFecha(sensorSelected.getFecha().toString().trim());
                s.setHora(sensorSelected.getHora().toString().trim());

                databaseReference.child("Sensor").child(s.getUid()).setValue(s);

                Toast.makeText(this,"Actualizado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete:{

                Sensor s = new Sensor();
                s.setUid(sensorSelected.getUid());
                databaseReference.child("Sensor").child(s.getUid()).removeValue();
                Toast.makeText(this,"Eliminado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
        Nombre.setText("");
        Observacion.setText("");
        Tipo.setText("");
        Valor.setText("");
        Ubicacion.setText("");
        Fecha.setText("");
        Hora.setText("");
    }

    private void validacion() {
        String NombreSensor = Nombre.getText().toString();
        String ObservacionSensor = Observacion.getText().toString();
        if (NombreSensor.equals("")){
            Nombre.setError("Required");
        }
        else if (ObservacionSensor.equals("")){
            Observacion.setError("Required");
        }
        else if (ObservacionSensor.equals("")){
            Tipo.setError("Required");
        }
        else if (ObservacionSensor.equals("")){
            Valor.setError("Required");
        }
        else if (ObservacionSensor.equals("")){
            Ubicacion.setError("Required");
        }
        else if (ObservacionSensor.equals("")){
            Fecha.setError("Required");
        }
        else if (ObservacionSensor.equals("")){
            Hora.setError("Required");
        }
      }
}