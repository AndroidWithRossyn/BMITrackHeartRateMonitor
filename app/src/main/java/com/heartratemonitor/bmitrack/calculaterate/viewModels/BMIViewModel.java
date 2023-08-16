package com.heartratemonitor.bmitrack.calculaterate.viewModels;

import static com.heartratemonitor.bmitrack.calculaterate.db.HeartRateDatabase.heartRateWrite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.heartratemonitor.bmitrack.calculaterate.dao.HeartRateDao;
import com.heartratemonitor.bmitrack.calculaterate.db.HeartRateDatabase;
import com.heartratemonitor.bmitrack.calculaterate.models.DbModel;

import java.util.List;

public class BMIViewModel extends AndroidViewModel {

    HeartRateDao heartRateDao;

    public BMIViewModel(@NonNull Application application) {
        super(application);
        heartRateDao = HeartRateDatabase.getInstance(application.getApplicationContext()).dao();
    }

    public void addToDB(DbModel model) {
        heartRateWrite.execute(new Runnable() {
            @Override
            public void run() {
                heartRateDao.insert(model);
            }
        });
    }

    public LiveData<List<DbModel>> getAllData() {
        return heartRateDao.getData();
    }

    public LiveData<Integer> getMiniData() {
        return heartRateDao.getMinData();
    }

    public LiveData<Integer> getMaxiData() {
        return heartRateDao.getMaxData();
    }

    public LiveData<Integer> getAvgData() {
        return heartRateDao.getAVGData();
    }

}
