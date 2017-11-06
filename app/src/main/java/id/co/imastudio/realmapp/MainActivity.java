package id.co.imastudio.realmapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<ModelSiswa> dataSiswa = new ArrayList<>();
    RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddSiswaActivity.class));

            }
        });

        helper = new RealmHelper(MainActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.RVSiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        setRecycleView();
    }

    private void setRecycleView() {

//        ModelSiswa siswa1 = new ModelSiswa();
//        siswa1.setId(1);
//        siswa1.setNama("Arif");
//        siswa1.setAlamat("Ambarawa");
//        for (int i = 0; i < 10; i++) {
//            dataSiswa.add(siswa1);
//        }
        try {
            dataSiswa =
                    helper.findAllSiswa();
        }catch (Exception e){
            e.printStackTrace();
        }

        AdapterSiswa adapterSiswa = new AdapterSiswa(dataSiswa, new AdapterSiswa.OnItemClickListener() {
            @Override
            public void onClick(ModelSiswa item) {
                Intent i = new Intent(getApplicationContext(), EditSiswaActivity.class);
                i.putExtra("id", item.getId());
                i.putExtra("nama", item.getNama());
                i.putExtra("alamat", item.getAlamat());
                startActivity(i);
            }
        });
        recyclerView.setAdapter(adapterSiswa);
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

    @Override
    protected void onResume() {
        super.onResume();
        try {
            dataSiswa = helper.findAllSiswa();
        }catch (Exception e){
            e.printStackTrace();
        }
        setRecycleView();
    }
}
