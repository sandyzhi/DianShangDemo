package samsung.zss.com.dianshangdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.common.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanshanzhi on 2017/11/27.
 */

public class DbManager {
    private static String TAG = "DbManager";
    private SQLiteDatabase mDb;
    private DbHelper mHelper;
    private static DbManager mInstance ;
    private Context mContext;
    private DbManager(Context context){
        mHelper = new DbHelper(context);
    }
    public static DbManager getInstance(Context context){
        if (mInstance == null){
            synchronized (DbManager.class){
                if (mInstance ==null){
                    mInstance = new DbManager(context);
                }
            }
        }
        return mInstance;
    }

    public boolean hasRecord (String keyword){
        boolean hasRecord = false;
        mDb = mHelper.getWritableDatabase();
        if (mDb!=null){
            Cursor cr = mDb.query(DbHelper.TABLE_NAME,null,null,null,null,null,null);
            while (cr.moveToNext()){
                if (keyword.equals(cr.getString(cr.getColumnIndex(DbHelper.KEYWORD))));
                {
                    hasRecord = true;
                }

            }
            mDb.close();
        }
        return hasRecord;
    }

    public void insertRecord(String keyword,long time ){
        if (hasRecord(keyword)){
            L.i(TAG," the record has insert ");
            return;
        }
        mDb = mHelper.getWritableDatabase();
        if (mDb!=null){
            ContentValues cv = new ContentValues();
            cv.put(DbHelper.KEYWORD,keyword);
            cv.put(DbHelper.TIME,System.currentTimeMillis());
            long index = mDb.insert(DbHelper.TABLE_NAME,null,cv);
            L.i(TAG," insert index "+index);
            mDb.close();
        }
    }

    public List<String> queryRecord(){
        List<String> allRecords = new ArrayList<>();
        mDb = mHelper.getReadableDatabase();
        if (mDb!=null){
            Cursor cr = mDb.query(DbHelper.TABLE_NAME,null,null,null,null,null,null);
            while (cr.moveToNext()){
                String keyword = cr.getString(cr.getColumnIndex(DbHelper.KEYWORD));
                L.i(TAG," query keyword "+keyword);
                if (keyword!=null){
                    allRecords.add(keyword);
                }
            }
            mDb.close();
            cr.close();
        }
        return allRecords;
    }

    public void deleteAllRecords(){
        mDb = mHelper.getWritableDatabase();
        if (mDb!=null){
            mDb.execSQL("delete from "+DbHelper.TABLE_NAME);
            mDb.close();
        }
    }
}
