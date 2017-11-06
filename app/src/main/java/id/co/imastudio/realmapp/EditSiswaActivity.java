package id.co.imastudio.realmapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EditSiswaActivity extends AppCompatActivity {

    private int posisi;
    private Button hapus, edit;
    private EditText inama, ialamat;
    private RealmHelper realmHelper;
    private String nama, alamat, intentNama, intentAlamat;;
    private ArrayList<ModelSiswa> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_siswa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        realmHelper = new RealmHelper(this);
        data = new ArrayList<>();
        posisi = getIntent().getIntExtra("id", 0);
        intentNama = getIntent().getStringExtra("nama");
        intentAlamat = getIntent().getStringExtra("alamat");

        hapus = (Button) findViewById(R.id.hapuse);
        edit = (Button) findViewById(R.id.simpane);

        inama = (EditText) findViewById(R.id.namae);
        ialamat = (EditText) findViewById(R.id.alamate);
        inama.setText(intentNama);
        ialamat.setText(intentAlamat);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realmHelper.deleteSiswa(posisi);
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = inama.getText().toString();
                alamat = ialamat.getText().toString();

                //melakukan update artikel
                realmHelper.updateSiswa(posisi, nama, alamat);
                finish();

            }
        });

    }
}
