package com.example.mye_lapor.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mye_lapor.dao.DatabaseDao;
import com.example.mye_lapor.database.DatabaseClient;
import com.example.mye_lapor.model.ModelDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class InputDataViewModel extends AndroidViewModel {

    DatabaseDao databaseDao;

    public InputDataViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    public void addLaporan(final String kategori, final String image,
                              final String nama, final String lokasi, final String tanggal,
                              final String isi_laporan, final String telepon) {
        Completable.fromAction(() -> {
            ModelDatabase modelDatabase = new ModelDatabase();
            modelDatabase.kategori = kategori;
            modelDatabase.image = image;
            modelDatabase.nama = nama;
            modelDatabase.lokasi = lokasi;
            modelDatabase.tanggal = tanggal;
            modelDatabase.isi_laporan = isi_laporan;
            modelDatabase.telepon = telepon;
            databaseDao.insertData(modelDatabase);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
