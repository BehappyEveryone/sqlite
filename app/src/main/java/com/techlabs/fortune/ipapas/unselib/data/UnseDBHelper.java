package com.techlabs.fortune.ipapas.unselib.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.ipapas.unselib.Define;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class UnseDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "new_unse_db.sqlite";

    private static final int DB_VERSION = 1;

    private String DB_PATH;

    private Context mContext = null;

    private SQLiteDatabase mDataBase = null;

    boolean needUpdate = false;

    public UnseDBHelper(Context paramContext) {
        super(paramContext, stringBuilder2.toString(), null, 1);
        this.mContext = paramContext;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("/data/data/");
        stringBuilder1.append(this.mContext.getPackageName());
        stringBuilder1.append("/databases/");
        this.DB_PATH = stringBuilder1.toString();
        try {
            makeDataBase();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        this.mDataBase = getWritableDatabase();
    }

    private boolean attachDB(String paramString) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.DB_PATH);
        stringBuilder.append(paramString);
        paramString = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("Attach Database '");
        stringBuilder.append(paramString);
        stringBuilder.append("' AS attachDB");
        paramString = stringBuilder.toString();
        System.out.println(paramString);
        try {
            this.mDataBase.execSQL(paramString);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private boolean checkDataBase(String paramString) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.DB_PATH);
            stringBuilder.append(paramString);
            SQLiteDatabase sQLiteDatabase = SQLiteDatabase.openDatabase(stringBuilder.toString(), null, 1);
        } catch (SQLiteException sQLiteException) {
            System.out.println("database Not found in checkDatabase");
            sQLiteException.printStackTrace();
            sQLiteException = null;
        }
        if (sQLiteException != null)
            sQLiteException.close();
        return (sQLiteException != null);
    }

    private void copyDataBase() throws IOException {
        copyDataBase("new_unse_db.sqlite");
    }

    private void copyDataBase(String paramString) throws IOException {
        InputStream inputStream = this.mContext.getAssets().open(paramString);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.DB_PATH);
        stringBuilder.append(paramString);
        FileOutputStream fileOutputStream = new FileOutputStream(stringBuilder.toString());
        byte[] arrayOfByte = new byte[1024];
        while (true) {
            int i = inputStream.read(arrayOfByte);
            if (i > 0) {
                fileOutputStream.write(arrayOfByte, 0, i);
                continue;
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            return;
        }
    }

    private void copyFile(String paramString) throws IOException {
        File file = this.mContext.getDir("model", 0);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(file.getAbsolutePath());
        stringBuilder1.append(File.separator);
        stringBuilder1.append(paramString);
        file = new File(stringBuilder1.toString());
        PrintStream printStream = System.out;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        stringBuilder2.append(file.toString());
        printStream.println(stringBuilder2.toString());
        if (!file.createNewFile())
            return;
        printStream = System.out;
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("################## ");
        stringBuilder2.append(file.toString());
        printStream.println(stringBuilder2.toString());
        InputStream inputStream = this.mContext.getAssets().open(paramString);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] arrayOfByte = new byte[1024];
        while (true) {
            int i = inputStream.read(arrayOfByte);
            if (i > 0) {
                fileOutputStream.write(arrayOfByte, 0, i);
                continue;
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            return;
        }
    }

    private boolean detachDB() {
        try {
            this.mDataBase.execSQL("Detach Database attachDB");
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private void makeModelFile() {
        String[] arrayOfString = new String[4];
        int i = 0;
        arrayOfString[0] = "irregular.model";
        arrayOfString[1] = "observation.model";
        arrayOfString[2] = "pos.table";
        arrayOfString[3] = "transition.model";
        while (i < arrayOfString.length) {
            try {
                copyFile(arrayOfString[i]);
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            i++;
        }
    }

    public int insertUser(User paramUser) {
        MCalendar mCalendar;
        System.out.println(paramUser._name);
        if (paramUser._dateType == Define.DateType.Solar) {
            mCalendar = paramUser.getSolarDate();
        } else {
            mCalendar = paramUser.getLunarDate();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", paramUser._name);
        contentValues.put("sex", Integer.valueOf(paramUser._sex));
        contentValues.put("lunar", Integer.valueOf(paramUser._dateType.ordinal()));
        contentValues.put("byear", Integer.valueOf(mCalendar.get(MCalendar.YEAR)));
        contentValues.put("bmonth", Integer.valueOf(mCalendar.get(MCalendar.MONTH)));
        contentValues.put("bday", Integer.valueOf(mCalendar.get(MCalendar.DAY_OF_MONTH)));
        contentValues.put("btime", Integer.valueOf(paramUser.birthTime));
        contentValues.put("facebookid", paramUser._facebookid);
        return (int)this.mDataBase.insert("tb_user", null, contentValues);
    }

    public void makeDataBase() throws IOException {
        try {
            if (!checkDataBase("new_unse_db.sqlite")) {
                getReadableDatabase();
                copyDataBase("new_unse_db.sqlite");
                this.needUpdate = true;
                makeModelFile();
            }
            return;
        } catch (IOException iOException) {
            iOException.printStackTrace();
            throw new Error("Error copying database");
        }
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase) {}

    public void onOpen(SQLiteDatabase paramSQLiteDatabase) {
        super.onOpen(paramSQLiteDatabase);
        paramSQLiteDatabase.disableWriteAheadLogging();
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}

    public void updateDB() {
        if (this.needUpdate) {
            if (checkDataBase("new_unse_db.sqlite")) {
                System.out.println("on Updating user table");
                this.mDataBase = getWritableDatabase();
                attachDB("new_unse_db.sqlite");
                try {
                    Cursor cursor = this.mDataBase.rawQuery("Select _id, name, sex, lunar, byear, bmonth, bday, btime, facebookid From attachDB.tb_user", null);
                    cursor.moveToFirst();
                    ArrayList<User> arrayList = new ArrayList();
                    int i;
                    for (i = 0; i < cursor.getCount(); i++) {
                        arrayList.add(new User(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), Define.DateType.values()[cursor.getInt(3)], cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8)));
                        cursor.moveToNext();
                    }
                    cursor.close();
                    detachDB();
                    for (i = 0; i < arrayList.size(); i++)
                        insertUser(arrayList.get(i));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            this.needUpdate = false;
        }
    }
}
