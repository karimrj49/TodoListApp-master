package com.example.todolist1.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Status;

import java.util.List;

@Dao
public interface ActionDao {

    @Insert
    void insert(Action action);

    @Delete
    void delete(Action action);
    @Update
    void update(Action action);

    @Query("SELECT * FROM action_table")
    LiveData<List<Action>> getAllAction();

    @Query("SELECT * FROM action_table WHERE idstatus = :id")
    LiveData<List<Action>>getActionsS(int id);

}
