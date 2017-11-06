package id.co.imastudio.realmapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSiswaActivity extends AppCompatActivity {

    private RealmHelper helper;
    private EditText inAlamat, inNama;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_siswa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new RealmHelper(AddSiswaActivity.this);
        inNama = (EditText) findViewById(R.id.nama);
        inAlamat = (EditText) findViewById(R.id.alamat);
        addButton = (Button) findViewById(R.id.simpan);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama, alamat;
                nama = inNama.getText().toString();
                alamat = inAlamat.getText().toString();
                helper.addSiswa(nama, alamat);

                finish();
            }
        });
    }
}
