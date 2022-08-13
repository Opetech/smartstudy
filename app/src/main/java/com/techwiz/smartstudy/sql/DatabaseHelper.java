package com.techwiz.smartstudy.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.techwiz.smartstudy.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "SmartStudy.db";
    // User table name
    private static final String TABLE_USER = "users";
    private static final String TABLE_STUDENT_DATA = "students";
    private static final String TABLE_SCORE_DETAILS = "score_details";
    private static final String TABLE_PARENT_STUDENT = "parent_student";
    private static final String TABLE_REVISIONS = "revisions";
    private static final String TABLE_TESTS = "tests";
    private static final String TABLE_RESOURCES = "resources";

    private static final String COLUMN_PRIMARY_ID = "id";

    // User Table Columns names
    private static final String COLUMN_FIRST_NAME = "firstname";
    private static final String COLUMN_LAST_NAME = "lastname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_CATEGORY = "category";

    // Students Table Columns names
    private static final String COLUMN_STUDENT_USER_ID = "user_id";
    private static final String COLUMN_STUDENT_CLASS = "class";
    private static final String COLUMN_STUDENT_ENROLLMENT_DATE = "enrollment_date";

    // Revision Table Columns names
    private static final String COLUMN_REVISION_NAME = "name";
    private static final String COLUMN_REVISION_DATE = "revision_date";
    private static final String COLUMN_REVISION_TIME = "revision_time";

    // Test Table Columns names
    private static final String COLUMN_TEST_NAME = "name";
    private static final String COLUMN_TEST_STATUS = "is_taken";

    // Parent Student Table Columns names
    private static final String COLUMN_PARENT_ID = "parent_id";
    private static final String COLUMN_STUDENT_ID = "student_id";

    // Resources Table Columns names
    private static final String COLUMN_RESOURCE_TITLE = "title";
    private static final String COLUMN_RESOURCE_TYPE = "type";
    private static final String COLUMN_RESOURCE_LINK = "link";

    // Score Details Table Columns names
    private static final String COLUMN_SCORE_TEST_ID = "test_id";
    private static final String COLUMN_SCORE_USER_ID = "user_id";
    private static final String COLUMN_SCORE_DESCRIPTION = "description";
    private static final String COLUMN_SCORE_DATE = "date";
    private static final String COLUMN_SCORE_RECEIVED = "score_received";

    // create users table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_PRIMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRST_NAME + " VARCHAR(255)," + COLUMN_LAST_NAME + " VARCHAR(255),"
            + COLUMN_EMAIL + " VARCHAR(255)," + COLUMN_CONTACT + " VARCHAR(255)," + COLUMN_CATEGORY + " VARCHAR(255)," + COLUMN_PASSWORD + " TEXT" + ")";

    // create student data table sql query
    private String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT_DATA + "("
            + COLUMN_PRIMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_STUDENT_USER_ID + " INTEGER," + COLUMN_STUDENT_CLASS + " VARCHAR(255),"
            + COLUMN_STUDENT_ENROLLMENT_DATE + " TEXT, "
            + " FOREIGN KEY (" + COLUMN_STUDENT_USER_ID + ") REFERENCES " + TABLE_USER + "(" + COLUMN_PRIMARY_ID + ")" + ")";

    // create REVISIONS table sql query
    private String CREATE_REVISIONS_TABLE = "CREATE TABLE " + TABLE_REVISIONS + "("
            + COLUMN_PRIMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_REVISION_NAME + " VARCHAR (255), " +
            COLUMN_REVISION_DATE + " TEXT, " +
            COLUMN_REVISION_TIME + " TEXT " + ")";

    // create RESOURCES table sql query
    private String CREATE_RESOURCES_TABLE = "CREATE TABLE " + TABLE_RESOURCES + "("
            + COLUMN_PRIMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_RESOURCE_TITLE + " VARCHAR (255), " +
            COLUMN_RESOURCE_TYPE + " VARCHAR (255), " +
            COLUMN_RESOURCE_LINK + " TEXT " + ")";

    // create test table sql query
    private String CREATE_TEST_TABLE = "CREATE TABLE " + TABLE_TESTS + "("
            + COLUMN_PRIMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TEST_NAME + " TEXT, " + COLUMN_TEST_STATUS + " INTEGER " + ")";

    // create parent student table sql query
    private String CREATE_PARENT_STUDENT_TABLE = "CREATE TABLE " + TABLE_PARENT_STUDENT + "("
            + COLUMN_PRIMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PARENT_ID + " INTEGER," + COLUMN_STUDENT_ID + " INTEGER,"
            + " FOREIGN KEY (" + COLUMN_PARENT_ID + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_PRIMARY_ID + "),"
            + " FOREIGN KEY (" + COLUMN_STUDENT_ID + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_PRIMARY_ID + ")" + ")";

    // create score details table sql query
    private String CREATE_SCORE_DETAILS_TABLE = "CREATE TABLE " + TABLE_SCORE_DETAILS + "("
            + COLUMN_PRIMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  + COLUMN_SCORE_TEST_ID
            + " INTEGER," + COLUMN_SCORE_USER_ID + " INTEGER," + COLUMN_SCORE_DESCRIPTION + " TEXT,"
            + COLUMN_SCORE_DATE + "TEXT" + COLUMN_SCORE_RECEIVED + " INTEGER, "
            + " FOREIGN KEY (" + COLUMN_SCORE_TEST_ID + ") REFERENCES " + TABLE_TESTS + "(" + COLUMN_PRIMARY_ID + "),"
            + " FOREIGN KEY (" + COLUMN_SCORE_USER_ID + ") REFERENCES " + TABLE_USER + "(" + COLUMN_PRIMARY_ID + ")" + ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_STUDENT_DATA_TABLE = "DROP TABLE IF EXISTS " + TABLE_STUDENT_DATA;
    private String DROP_REVISION_TABLE = "DROP TABLE IF EXISTS " + TABLE_REVISIONS;
    private String DROP_RESOURCES_TABLE = "DROP TABLE IF EXISTS " + TABLE_RESOURCES;
    private String DROP_TEST_TABLE = "DROP TABLE IF EXISTS " + TABLE_TESTS;
    private String DROP_SCORE_DETAILS_TABLE = "DROP TABLE IF EXISTS " + TABLE_SCORE_DETAILS;
    private String DROP_PARENT_STUDENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_PARENT_STUDENT;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_REVISIONS_TABLE);
        db.execSQL(CREATE_RESOURCES_TABLE);
        db.execSQL(CREATE_TEST_TABLE);
        db.execSQL(CREATE_SCORE_DETAILS_TABLE);
        db.execSQL(CREATE_PARENT_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_STUDENT_DATA_TABLE);
        db.execSQL(DROP_REVISION_TABLE);
        db.execSQL(DROP_RESOURCES_TABLE);
        db.execSQL(DROP_TEST_TABLE);
        db.execSQL(DROP_SCORE_DETAILS_TABLE);
        db.execSQL(DROP_PARENT_STUDENT_TABLE);
        // Create tables again
        onCreate(db);
    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, user.getFirstname());
        values.put(COLUMN_LAST_NAME, user.getLastname());
        values.put(COLUMN_CONTACT, user.getContact());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_CATEGORY, user.getCategory());
        values.put(COLUMN_PASSWORD, user.getPassword());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkEmailExist(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_PRIMARY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public Object checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_PRIMARY_ID,
                COLUMN_FIRST_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        if (cursor.moveToFirst()) {
            //Update user entity
            User user = new User();
            user.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRIMARY_ID)));
            user.setFirstname(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME)));
            cursor.close();
            db.close();
            return true;
        }
        return false;
    }
}
