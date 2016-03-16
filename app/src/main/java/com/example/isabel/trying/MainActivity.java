package com.example.isabel.trying;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton siguiente = (ImageButton) findViewById(R.id.Siguiente);
        final EditText usuario = (EditText) findViewById(R.id.Usuario);
        final EditText passw = (EditText) findViewById(R.id.Password);


        ManagmentSqlite BD2 = new ManagmentSqlite(this, "User", null, 1);

        final SQLiteDatabase Base2 = BD2.getWritableDatabase();


        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] campos = new String[]{"nombre", "password"};
                final String values = usuario.getText().toString();
                final String[] nombre = new String[]{values};
                String nombres = "";
                String passd = "";
                Cursor query = Base2.query("User", campos, "nombre=?", nombre, null, null, null);


                if (query.moveToFirst()) {

                    do {
                        nombres = query.getString(0);
                        passd = query.getString(1);

                    } while (query.moveToNext());

                }


                if ((usuario.getText().toString().equals(nombres)) && (passw.getText().toString().equals(passd))) {
                    Intent intent = new Intent(MainActivity.this, Segunda_Actividad.class);
//                    Bundle datos = new Bundle();
//                    datos.putString("elusuario",usuario.getText().toString());
//                    intent.putExtras(datos);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Usuario o Password incorrecto", Toast.LENGTH_SHORT).show();

                }

            }


        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
