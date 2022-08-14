package com.techwiz.smartstudy.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.techwiz.smartstudy.model.Resources;
import com.techwiz.smartstudy.model.Revision;
import com.techwiz.smartstudy.model.ScoreDetails;
import com.techwiz.smartstudy.model.Test;
import com.techwiz.smartstudy.sql.DatabaseHelper;

public class TeacherService extends DatabaseHelper {
    /**
     * Constructor
     *
     * @param context
     */
    public TeacherService(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void updateScore(ScoreDetails scoreDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("test_id", scoreDetails.getTestId());
        values.put("user_id", scoreDetails.getUserId());
        values.put("score_received", scoreDetails.getScore());
        values.put("description", scoreDetails.getDescription());
        values.put("date", scoreDetails.getDate());
        db.insert(TABLE_SCORE_DETAILS, null, values);
        db.close();
    }

    public void updateAcademicProgress(int testId, Test test) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("is_taken", test.getIsTaken());
        db.update(TABLE_TESTS, values, "id" + " = " + testId, null);
        db.close();
    }

    public void addStudyResources(Resources resources) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", resources.getTitle());
        values.put("link", resources.getLink());
        db.insert(TABLE_RESOURCES, null, values);
        db.close();
    }

    public void addRevisionClasses(Revision revision) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", revision.getName());
        values.put("date", revision.getDate());
        values.put("time", revision.getTime());
        db.insert(TABLE_REVISIONS, null, values);
        db.close();
    }
}
