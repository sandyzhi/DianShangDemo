package samsung.zss.com.dianshangdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shanshanzhi on 2017/11/27.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String db = "temp.db";
    public static final String TABLE_NAME = "search_history";
    public static final String _ID = "id";
    public static final String TIME = "time";
    public static final String KEYWORD = "keyword";
    private static final int VERSION = 1;
    private static final String CREATE_SQLITE = "CREATE TABLE " + TABLE_NAME + " (" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT ," + TIME + "  INTEGER  ,"
            + KEYWORD + "  TEXT )";
    ;

    public DbHelper(Context context) {
        super(context, db, null, VERSION);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQLITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            // TODO: 2017/11/27
        }
    }
}
