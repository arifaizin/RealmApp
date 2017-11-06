package id.co.imastudio.realmapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by idn on 9/25/2017.
 */

public class RealmHelper {
    private Context context;
    private Realm realm;
    private RealmResults<ModelSiswa> realmResults;
    private static final String TAG = "RealmHelper";

    public RealmHelper(Context context) {
        this.context = context;
        realm = Realm.getInstance(context);
    }

    public void addSiswa(String nama, String alamat){
        ModelSiswa siswa = new ModelSiswa();
        siswa.setId((int)
                (System.currentTimeMillis()/1000));
        siswa.setNama(nama);
        siswa.setAlamat(alamat);

        realm.beginTransaction();
        realm.copyToRealm(siswa);
        realm.commitTransaction();

        ShowLog("data " + nama + " berhasil disimpan");
    }


    public void deleteSiswa (int id){
        RealmResults<ModelSiswa> dataResult = realm.
                where(ModelSiswa.class).equalTo("id",
                id).findAll();

        realm.beginTransaction();
        dataResult.remove(0);
        dataResult.removeLast();
        dataResult.clear();
        realm.commitTransaction();

        ShowLog("Data berhasil dihapus");
    }

    public void updateSiswa (int id, String nama,
                             String alamat){
        realm.beginTransaction();
        ModelSiswa siswa = realm.where(ModelSiswa.class).
                equalTo("id", id).findFirst();
        siswa.setNama(nama);
        siswa.setAlamat(alamat);
        realm.commitTransaction();

        ShowLog("Data berhasil di update");
    }

    public ArrayList<ModelSiswa> findAllSiswa(){
        ArrayList<ModelSiswa> data = new ArrayList<>();
        realmResults = realm.where(ModelSiswa.class)
                .findAll();
        realmResults.sort("id", Sort.DESCENDING);
        if (realmResults.size() > 0){
            ShowLog("Size : "+realmResults.size());
            for (int i =0; i < realmResults.size() ;
                 i++){
                String nama, alamat;
                int id = realmResults.get(i).getId();
                nama = realmResults.get(i).getNama();
                alamat = realmResults.get(i).getAlamat();
                data.add(new ModelSiswa(id, nama, alamat));
            }
        }else {
            ShowLog("size = 0");
        }
        return  data;
    }

    private void ShowLog(String s){
        Log.d(TAG, s);
        Toast.makeText(context, s , Toast.LENGTH_LONG).show();
    }
}
