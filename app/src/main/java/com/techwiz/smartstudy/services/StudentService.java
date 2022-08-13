package com.techwiz.smartstudy.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.techwiz.smartstudy.model.Resources;
import com.techwiz.smartstudy.model.Revision;
import com.techwiz.smartstudy.model.ScoreDetails;
import com.techwiz.smartstudy.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentService extends DatabaseHelper {
    /**
     * Constructor
     *
     * @param context
     */
    public StudentService(Context context) {
        super(context);
    }

//    public List<ScoreDetails> getTestScores() {
//        // array of columns to fetch
//        String[] columns = {
//                COLUMN_SCORE_TEST_ID,
//                COLUMN_SCORE_DATE,
//                COLUMN_SCORE_DESCRIPTION,
//                COLUMN_SCORE_RECEIVED
//        };
//
//        List<ScoreDetails> testScores = new ArrayList<ScoreDetails>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_SCORE_DETAILS, //Table to query
//                columns,
//                null,
//                null,
//                null,
//                null,
//                null);
//
//        // Traversing through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                ScoreDetails scoreDetails = new ScoreDetails();
//                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
//                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
//                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
//                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
//                // Adding user record to list
//                testScores.add(user);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        // return test scores list
//        return testScores;
//    }

    public HashMap<String, Integer> getAcademicProgress() {

        HashMap<String, Integer> academicProgress = new HashMap<>();
        int totalTests = 0;
        int takenTests = 0;

        String[] columns = {
                COLUMN_PRIMARY_ID,
                COLUMN_TEST_STATUS
        };

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TESTS, columns,
                null,
                null,
                null,
                null,
                null);

        totalTests = cursor.getCount();
        cursor.close();
        db.close();
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_TEST_STATUS)) == 1){
                    takenTests++;
                }
            } while (cursor.moveToNext());
        }

        academicProgress.put("totalTests", totalTests);
        academicProgress.put("takenTests", takenTests);

        return academicProgress;

    }

    public HashMap<String, String> getStudyResources(Resources newResource) {
        HashMap<String, String> resources = new HashMap<>();
        String[] columns = {
                COLUMN_RESOURCE_TITLE,
                COLUMN_RESOURCE_TYPE,
                COLUMN_RESOURCE_LINK
        };

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RESOURCES, columns,
                null,
                null,
                null,
                null,
                null);
        cursor.close();
        db.close();
        if (cursor.moveToFirst()) {
            do {
                resources.put("title", cursor.getString(cursor.getColumnIndex(COLUMN_RESOURCE_TITLE)));
                resources.put("type", cursor.getString(cursor.getColumnIndex(COLUMN_RESOURCE_TYPE)));
                resources.put("link", cursor.getString(cursor.getColumnIndex(COLUMN_RESOURCE_LINK)));
            } while (cursor.moveToNext());
        }

        return resources;
    }

    public List<Revision> getRevisionClasses(Revision newRevision) {
        List<Revision> revisions = new ArrayList<>();
        String[] columns = {
                COLUMN_REVISION_NAME,
                COLUMN_REVISION_DATE,
                COLUMN_REVISION_TIME
        };

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TESTS, columns,
                null,
                null,
                null,
                null,
                null);
        cursor.close();
        db.close();
        if (cursor.moveToFirst()) {
            do {
                newRevision = new Revision(cursor.getString(cursor.getColumnIndex(COLUMN_REVISION_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_REVISION_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_REVISION_TIME))
                        );
                revisions.add(newRevision);
            } while (cursor.moveToNext());
        }

        return revisions;
    }
}
