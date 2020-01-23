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

public class QzyQProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.example.Qzy.QzyQProvider";
    static final String URL = "content://" + PROVIDER_NAME;
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "Qid";
    static final String NAME = "Title";
    static final String CATEGORY="Category";

    private static HashMap<String, String> QUIZES_PROJECTION_MAP;

    static final int QS = 1;
    static final int QID = 2;
    static final int QCATEGORIY=0;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "/getQ/Qid/*", QID);
        uriMatcher.addURI(PROVIDER_NAME, "getRQ/QCategorie", QCATEGORIY);
    }

    static final String DATABASE_NAME = "Qzy";
    static final String QUIZQS_TABLE_NAME= "Quizqs";
    static final int DATABASE_VERSION = 1;
    QzyDb qzyDb;
    private SQLiteDatabase db;

    public QzyQProvider() {
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

Log.e("qzyQ Provider",values.toString());

        long rowID = db.insert(	QUIZQS_TABLE_NAME, "", values);

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
        Log.e("QzyQProv","On create");
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
        qb.setTables(QUIZQS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case QID:
                Log.e("QzyQDataProvider","Reached Q getter from QId");
                Log.e("QzyQDataProvider","URI"+uri.toString());
                Log.e("QzyQDataProvider","URI segment--"+uri.getPathSegments().get(2));
                Log.e("QzyQDataProvider","URI ID"+_ID);
                qb.appendWhere( _ID + "="+'"'+ uri.getPathSegments().get(2)+'"');
                Log.e("QzyQDataProvider","Append done"+_ID);
                break;

            case QCATEGORIY:
                Log.e("QzyQDataProvider","Reached Random Q section");
                Log.e("QzyQDataProvider","URI"+uri.toString());
                Log.e("QzyQDataProvider","URI segment"+uri.getPathSegments().get(1));
                qb.appendWhere("Category"+"="+'"'+uri.getPathSegments().get(1)+'"');

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
