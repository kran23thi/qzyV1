package com.example.qzy;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;

public class QzyDataProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.example.Qzy.QzyDataProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/quizes";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "quizId";
    static final String NAME = "Title";
    static final String Category="Category";

    private static HashMap<String, String> QUIZES_PROJECTION_MAP;

    static final int QUIZES = 1;
    static final int QUIZ_ID = 2;
    static final int QUIZ_NAME=3;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "quizes", QUIZES);
        uriMatcher.addURI(PROVIDER_NAME, "/quizid/*", QUIZ_ID);
        uriMatcher.addURI(PROVIDER_NAME, "quizes/quizTitle/", QUIZ_NAME);
    }

    static final String DATABASE_NAME = "Qzy";
    static final String QUIZES_TABLE_NAME = "Quiz";
    static final int DATABASE_VERSION = 1;

    QzyDb qzyDb;
    private SQLiteDatabase db;

    public QzyDataProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowID = db.insert(	QUIZES_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        Context context = getContext().getApplicationContext();
        qzyDb=new QzyDb(context,"Qzy",null,1);
        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */
        db = qzyDb.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(QUIZES_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case QUIZES:
                qb.setProjectionMap(QUIZES_PROJECTION_MAP);
                break;

            case QUIZ_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(2));
                break;

            case QUIZ_NAME:
                Log.e("QzyDataProvider","Reached Name section");
                Log.e("QzyDataProvider","URI"+uri.toString());
                Log.e("QzyDataProvider","URI segment"+uri.getPathSegments().get(1));
                qb.appendWhere("Title"+"="+'"'+uri.getPathSegments().get(1)+'"');

                //"SELECT * FROM "+TABLE_quiz+" WHERE Title"+"="+'"'+quizId+'"';

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = NAME;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, null);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;


        // TODO: Implement this to handle query requests from clients.
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
