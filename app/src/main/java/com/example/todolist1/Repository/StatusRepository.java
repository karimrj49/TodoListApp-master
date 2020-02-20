package com.example.todolist1.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todolist1.ActionDatabase;
import com.example.todolist1.DAO.StatusDao;
import com.example.todolist1.Entities.Status;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StatusRepository {

    private static StatusDao statusDao;
    private LiveData<List<Status>> allStatus;
    private LiveData<List<String>> allStatustitle;
    public StatusRepository(Application application){
        ActionDatabase database = ActionDatabase.getInstance(application);
        statusDao = database.statusDao();
        allStatus = statusDao.getAllStatus();
        allStatustitle = statusDao.getAllStatustitle();
    }

    public void inserts(Status status){

        new InsertAsyncTask(statusDao).execute(status);
    }

    public static String getStatusById(int id) throws ExecutionException, InterruptedException {

        String s = new GetAsyncTask(statusDao).execute(id).get();
        return s;
    }
    public LiveData<List<Status>> getAllStatus() {

        return allStatus;
    }

    public LiveData<List<String>> getAllStatustitle() {

        return allStatustitle;
    }

    private static class GetAsyncTask extends AsyncTask<Integer , Void , String > {

        private StatusDao statusDao;
        Application application;
        ActionDatabase actionDatabase = ActionDatabase.getInstance(application);
        private GetAsyncTask (StatusDao statusDao){
            this.statusDao = statusDao;
        }

        @Override
        protected String doInBackground(Integer... integers) {
            statusDao = actionDatabase.statusDao();
            String status = statusDao.getStatusById(integers[0]);
            return status;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Status , Void , Void >{

        private StatusDao statusDao;
        private InsertAsyncTask (StatusDao statusDao){
            this.statusDao = statusDao;
        }

        @Override
        protected Void doInBackground(com.example.todolist1.Entities.Status... statuses) {
            statusDao.inserts(statuses[0]);
            return null;
        }
    }
}
