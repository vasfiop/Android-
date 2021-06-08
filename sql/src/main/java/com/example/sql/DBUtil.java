package com.example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUtil {
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASS = "passworld";
    public static final String KEY_HEIGHT = "height";
    public static final String DB_NAME = "people.db";
    public static final String DB_TABLE = "peopleinfo";
    public static final int DB_VERSION = 1;

    private final Context context;
    private SQLiteDatabase db;
    private DBOpenHelper dbOpenHelper;

    public DBUtil(Context context) {
        this.context = context;
    }

    public People[] selectid(long id) {
        Cursor cursor = db.query(DB_TABLE, new String[]{KEY_ID, KEY_NAME, KEY_PASS, KEY_HEIGHT}, KEY_ID + "=" + id, null, null, null, null);
        return toPeoples(cursor);
    }

    //    删除单个id
    public long deleteid(long id) {
        return db.delete(DB_TABLE, KEY_ID + "=" + id, null);
    }

    //    全部删除操作
    public long deleteall() {
        return db.delete(DB_TABLE, null, null);
    }

    //    插入操作
    public long insert(People people) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, people.name);
        contentValues.put(KEY_PASS, people.password);
        contentValues.put(KEY_HEIGHT, people.height);

        return db.insert(DB_TABLE, null, contentValues);
    }

    //    更新
    public long updata(long id, People people) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, people.name);
        contentValues.put(KEY_PASS, people.password);
        contentValues.put(KEY_HEIGHT, people.height);

        return db.update(DB_TABLE, contentValues, KEY_ID + "=" + id, null);
    }

    //    查询操作
    public People[] showall() {
        Cursor cursor = db.query(DB_TABLE, new String[]{KEY_ID, KEY_NAME, KEY_PASS, KEY_HEIGHT}, null, null, null, null, null);
        return toPeoples(cursor);
    }

    private People[] toPeoples(Cursor cursor) {
        int resultcount = cursor.getCount();
        if (resultcount == 0 || !cursor.moveToFirst())
            return null;
        People[] peoples = new People[resultcount];
        for (int i = 0; i < resultcount; i++) {
            peoples[i] = new People();
            peoples[i].ID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            peoples[i].name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            peoples[i].password = cursor.getString(cursor.getColumnIndex(KEY_PASS));
            peoples[i].height = cursor.getFloat(cursor.getColumnIndex(KEY_HEIGHT));

            cursor.moveToNext();
        }

        return peoples;
    }

    //打开数据库
    public void open() {
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        db = dbOpenHelper.getWritableDatabase();
    }

    //关闭数据库释放资源
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    private class DBOpenHelper extends SQLiteOpenHelper {

        private static final String TABLE_CREATE =
                "create table " + DB_TABLE
                        + " (" + KEY_ID + " integer primary key autoincrement, "
                        + KEY_NAME + " text not null, " + KEY_PASS + " text not null,"
                        + KEY_HEIGHT + " float);";

        public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }
}
