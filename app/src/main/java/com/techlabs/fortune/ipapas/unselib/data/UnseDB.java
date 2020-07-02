package com.techlabs.fortune.ipapas.unselib.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class UnseDB {
    private static final String DATABASE_TABLE = "tb_jeolgi";

    private static final String DAY = "day";

    private static final String MONTH = "month";

    private static final String NEW_DB_NAME = "new_unse_db.sqlite";

    private static final String YEAR = "year";

    private String DB_PATH = "";

    private UnseDBHelper mOpenHelper = null;

    private Context m_context = null;

    private Context m_ctx;

    private SQLiteDatabase m_dataBase = null;

    public UnseDB(Context paramContext) {
        this.m_context = paramContext;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("/data/data/");
        stringBuilder2.append(this.m_context.getPackageName());
        stringBuilder2.append("/databases/");
        this.DB_PATH = stringBuilder2.toString();
        this.mOpenHelper = new UnseDBHelper(paramContext);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(this.DB_PATH);
        stringBuilder1.append("new_unse_db.sqlite");
        this.m_dataBase = SQLiteDatabase.openDatabase(stringBuilder1.toString(), null, 0);
    }

    public String getToday(int paramInt) {
        Cursor cursor = this.m_dataBase.rawQuery("select contents from tb_today where _id=?", new String[]{Integer.toString(paramInt)});
        cursor.moveToFirst();
        String str = null;
        for (int i = 0; i < cursor.getCount(); i++) {
            str = cursor.getString(0);
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(paramInt);
            stringBuilder.append(" : ");
            stringBuilder.append(str);
            printStream.println(stringBuilder.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return str;
    }

//    public void deleteUser(int userId) {
//        String id = Integer.toString(userId);
//        this.m_dataBase.delete("tb_user", "_id=?", new String[]{id});
//    }
//
//    public int[] fetchDayFromMonth(int paramYear, int paramMonth) throws SQLException {
//        Cursor cursor = this.m_dataBase.rawQuery("select day, hour from tb_jeolgi where year=? and month=?", new String[]{Integer.toString(paramYear), Integer.toString(paramMonth)});
//        if (cursor != null) {
//            cursor.moveToFirst();
//            paramYear = cursor.getInt(0);
//            paramMonth = cursor.getInt(1);
//        } else {
//            paramYear = 5;
//            paramMonth = 12;
//        }
//        cursor.close();
//        return new int[]{paramYear, paramMonth};
//    }
//
//    public int[] fetchJeolgi(int paramYear, Jeolgi paramJeolgi) throws SQLException {
//        int b1;
//        int b2;
//        Cursor cursor = this.m_dataBase.rawQuery("select month, day, hour from tb_jeolgi where year=? and jeolgi=?", new String[]{Integer.toString(paramYear), Integer.toString(paramJeolgi.ordinal() + 1)});
//        cursor.moveToFirst();
//        if (cursor != null) {
//            paramYear = cursor.getInt(0);
//            b1 = cursor.getInt(1);
//            b2 = cursor.getInt(2);
//        } else {
//            b1 = 5;
//            b2 = 12;
//            paramYear = 2;
//        }
//        cursor.close();
//        return new int[]{paramYear, b1, b2};
//    }
//
////    public LinkedHashMap<String, DreamData> getDream(ArrayList<String> paramArrayList) {
////        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
////        String str2 = "";
////        int i;
////        for (i = 0; i < paramArrayList.size(); i++) {
////            String str = str2;
////            if (i > 0) {
////                StringBuilder stringBuilder1 = new StringBuilder();
////                stringBuilder1.append(str2);
////                stringBuilder1.append(" NEAR ");
////                str = stringBuilder1.toString();
////            }
////            StringBuilder stringBuilder = new StringBuilder();
////            stringBuilder.append(str);
////            stringBuilder.append(paramArrayList.get(i));
////            stringBuilder.append("* ");
////            str2 = stringBuilder.toString();
////        }
////        Cursor cursor2 = this.m_dataBase.rawQuery("select docid, dream from tb_dream_search where tb_dream_search MATCH ?", new String[]{str2});
////        System.out.println("a1111111111111111111111111111");
////        cursor2.moveToFirst();
////        for (i = 0; i < cursor2.getCount(); i++) {
////            int j = cursor2.getInt(0);
////            DreamData dreamData = new DreamData(j, cursor2.getString(1));
////            if (!linkedHashMap.containsKey(Integer.toString(j)))
////                linkedHashMap.put(Integer.toString(j), dreamData);
////            PrintStream printStream = System.out;
////            StringBuilder stringBuilder = new StringBuilder();
////            stringBuilder.append(">>>>>>>>>>>>11 ");
////            stringBuilder.append(dreamData.dream);
////            printStream.println(stringBuilder.toString());
////            cursor2.moveToNext();
////        }
////        cursor2.close();
////        if (paramArrayList.size() > 2) {
////            String str = "";
////            for (i = 0; i < paramArrayList.size(); i = j) {
////                int j = i + 1;
////                int k;
////                for (k = j; k < paramArrayList.size(); k++) {
////                    String str3 = str;
////                    if (k > 1) {
////                        StringBuilder stringBuilder1 = new StringBuilder();
////                        stringBuilder1.append(str);
////                        stringBuilder1.append(" OR ");
////                        str3 = stringBuilder1.toString();
////                    }
////                    StringBuilder stringBuilder = new StringBuilder();
////                    stringBuilder.append(str3);
////                    stringBuilder.append(paramArrayList.get(i));
////                    stringBuilder.append("* NEAR ");
////                    stringBuilder.append(paramArrayList.get(k));
////                    stringBuilder.append("* ");
////                    str = stringBuilder.toString();
////                }
////            }
////            Cursor cursor = this.m_dataBase.rawQuery("select docid, dream from tb_dream_search where tb_dream_search MATCH ?", new String[]{str});
////            System.out.println("a111111111111111111111111111122222222222222");
////            cursor.moveToFirst();
////            for (i = 0; i < cursor.getCount(); i++) {
////                int j = cursor.getInt(0);
////                DreamData dreamData = new DreamData(j, cursor.getString(1));
////                if (!linkedHashMap.containsKey(Integer.toString(j)))
////                    linkedHashMap.put(Integer.toString(j), dreamData);
////                PrintStream printStream = System.out;
////                StringBuilder stringBuilder = new StringBuilder();
////                stringBuilder.append(">>>>>>>>>>>>22 ");
////                stringBuilder.append(dreamData.dream);
////                printStream.println(stringBuilder.toString());
////                cursor.moveToNext();
////            }
////            cursor.close();
////        }
////        String str1 = "";
////        for (i = 0; i < paramArrayList.size(); i++) {
////            String str = str1;
////            if (i > 0) {
////                StringBuilder stringBuilder1 = new StringBuilder();
////                stringBuilder1.append(str1);
////                stringBuilder1.append(" OR ");
////                str = stringBuilder1.toString();
////            }
////            StringBuilder stringBuilder = new StringBuilder();
////            stringBuilder.append(str);
////            stringBuilder.append(paramArrayList.get(i));
////            stringBuilder.append("* ");
////            str1 = stringBuilder.toString();
////        }
////        Cursor cursor1 = this.m_dataBase.rawQuery("select docid, dream from tb_dream_search where tb_dream_search MATCH ?", new String[]{str1});
////        cursor1.moveToFirst();
////        for (i = 0; i < cursor1.getCount(); i++) {
////            int j = cursor1.getInt(0);
////            DreamData dreamData = new DreamData(j, cursor1.getString(1));
////            if (!linkedHashMap.containsKey(Integer.toString(j)))
////                linkedHashMap.put(Integer.toString(j), dreamData);
////            cursor1.moveToNext();
////        }
////        cursor1.close();
////        return (LinkedHashMap) linkedHashMap;
////    }
//
//    public String getDreamInterpret(int paramId) {
//        Cursor cursor = this.m_dataBase.rawQuery("select interpret from tb_dream where _id=?", new String[]{Integer.toString(paramId)});
//        cursor.moveToFirst();
//        String str = null;
//        for (int i = 0; i < cursor.getCount(); i++) {
//            str = cursor.getString(0);
//            PrintStream printStream = System.out;
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("");
//            stringBuilder.append(paramId);
//            stringBuilder.append(" : ");
//            stringBuilder.append(str);
//            printStream.println(stringBuilder.toString());
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
//
//    public String getFunnyHarmony(int paramMan, int paramWoman, int paramCtg) {
//        Cursor cursor = this.m_dataBase.rawQuery("select content from tb_fharmony where ctg=? and man=? and woman=?", new String[]{Integer.toString(paramCtg), Integer.toString(paramMan), Integer.toString(paramWoman)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramMan = 0; paramMan < cursor.getCount(); paramMan++) {
//            if (cursor.getString(0) == null) {
//                str = "";
//            } else {
//                str = cursor.getString(0);
//            }
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
//
//    public String getFunnySaju(int paramInt1, int paramInt2, int paramInt3) {
//        Cursor cursor = this.m_dataBase.rawQuery("select content from tb_fsaju where ctg=? and num1=? and num2=? ", new String[]{Integer.toString(paramInt3), Integer.toString(paramInt1), Integer.toString(paramInt2)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramInt1 = 0; paramInt1 < cursor.getCount(); paramInt1++) {
//            if (cursor.getString(0) == null) {
//                str = "";
//            } else {
//                str = cursor.getString(0);
//            }
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
//
//    public String getHarmony(int paramInt1, int paramInt2, int paramInt3) {
//        Cursor cursor = this.m_dataBase.rawQuery("select content1, content2, content3 from tb_harmony where ctg=? and num1=? and num2=?", new String[]{Integer.toString(paramInt3), Integer.toString(paramInt1), Integer.toString(paramInt2)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramInt1 = 0; paramInt1 < cursor.getCount(); paramInt1++) {
//            String str1;
//            String str2;
//            if (cursor.getString(0) == null) {
//                str = "";
//            } else {
//                str = cursor.getString(0);
//            }
//            if (cursor.getString(1) == null) {
//                str1 = "";
//            } else {
//                str1 = cursor.getString(1);
//            }
//            if (cursor.getString(2) == null) {
//                str2 = "";
//            } else {
//                str2 = cursor.getString(2);
//            }
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("");
//            stringBuilder.append(str);
//            stringBuilder.append(str1);
//            stringBuilder.append(str2);
//            str = stringBuilder.toString();
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
//
//    public String getJuyuk(int paramInt1, int paramInt2) {
//        Cursor cursor = this.m_dataBase.rawQuery("select content2 from tb_juyuk where ctg=? and num1=?", new String[]{Integer.toString(paramInt2), Integer.toString(paramInt1)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramInt1 = 0; paramInt1 < cursor.getCount(); paramInt1++) {
//            str = cursor.getString(0);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
//
//    public ArrayList<String> getMonth(int paramInt1, int paramInt2) {
//        ArrayList<String> arrayList = new ArrayList();
//        Cursor cursor = this.m_dataBase.rawQuery("select content from tb_month where num1=? and num2=? ", new String[]{Integer.toString(paramInt1), Integer.toString(paramInt2)});
//        cursor.moveToFirst();
//        for (paramInt1 = 0; paramInt1 < cursor.getCount(); paramInt1++) {
//            String str;
//            if (cursor.getString(0) == null) {
//                str = "";
//            } else {
//                str = cursor.getString(0);
//            }
//            arrayList.add(str);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return arrayList;
//    }
//
//    public String getPremiumHarmony(int paramInt1, int paramInt2, int paramInt3) {
//        Cursor cursor = this.m_dataBase.rawQuery("select content1, content2 from tb_pharmony where ctg=? and num1=? and num2=?", new String[]{Integer.toString(paramInt3), Integer.toString(paramInt1), Integer.toString(paramInt2)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramInt1 = 0; paramInt1 < cursor.getCount(); paramInt1++) {
//            String str1;
//            if (cursor.getString(0) == null) {
//                str = "";
//            } else {
//                str = cursor.getString(0);
//            }
//            if (cursor.getString(1) == null) {
//                str1 = "";
//            } else {
//                str1 = cursor.getString(1);
//            }
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("  ");
//            stringBuilder.append(str);
//            stringBuilder.append("  ");
//            stringBuilder.append(str1);
//            str = stringBuilder.toString();
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
//
//    public String getPremiumNewYear(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
//        Cursor cursor = this.m_dataBase.rawQuery("select content1, content2, content3 from tb_newyear where ctg=? and num1=? and num2=? and num3=?", new String[]{Integer.toString(paramInt4), Integer.toString(paramInt1), Integer.toString(paramInt2), Integer.toString(paramInt3)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramInt1 = 0; paramInt1 < cursor.getCount(); paramInt1++) {
//            String str1;
//            String str2;
//            if (cursor.getString(0) == null) {
//                str = "";
//            } else {
//                str = cursor.getString(0);
//            }
//            if (cursor.getString(1) == null) {
//                str1 = "";
//            } else {
//                str1 = cursor.getString(1);
//            }
//            if (cursor.getString(2) == null) {
//                str2 = "";
//            } else {
//                str2 = cursor.getString(2);
//            }
//            StringBuilder stringBuilder2 = new StringBuilder();
//            stringBuilder2.append("  ");
//            stringBuilder2.append(str);
//            stringBuilder2.append("  ");
//            stringBuilder2.append(str1);
//            stringBuilder2.append("  ");
//            stringBuilder2.append(str2);
//            str = stringBuilder2.toString();
//            PrintStream printStream = System.out;
//            StringBuilder stringBuilder1 = new StringBuilder();
//            stringBuilder1.append("");
//            stringBuilder1.append(paramInt4);
//            stringBuilder1.append(" : ");
//            stringBuilder1.append(str);
//            printStream.println(stringBuilder1.toString());
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
//
//    public String getPremiumSaju(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
//        Cursor cursor = this.m_dataBase.rawQuery("select content1, content2 from tb_psaju where ctg=? and num1=? and num2=? and num3=?", new String[]{Integer.toString(paramInt4), Integer.toString(paramInt1), Integer.toString(paramInt2), Integer.toString(paramInt3)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramInt1 = 0; paramInt1 < cursor.getCount(); paramInt1++) {
//            String str1;
//            if (cursor.getString(0) == null) {
//                str = "";
//            } else {
//                str = cursor.getString(0);
//            }
//            if (cursor.getString(1) == null) {
//                str1 = "";
//            } else {
//                str1 = cursor.getString(1);
//            }
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("  ");
//            stringBuilder.append(str);
//            stringBuilder.append("  ");
//            stringBuilder.append(str1);
//            str = stringBuilder.toString();
//            PrintStream printStream = System.out;
//            stringBuilder = new StringBuilder();
//            stringBuilder.append("");
//            stringBuilder.append(paramInt4);
//            stringBuilder.append(" : ");
//            stringBuilder.append(str);
//            printStream.println(stringBuilder.toString());
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
//
//    public String getSaju(int paramInt1, int paramInt2) {
//        Cursor cursor = this.m_dataBase.rawQuery("select content from tb_saju where ctg=? and rst_key=?", new String[]{Integer.toString(paramInt2), Integer.toString(paramInt1)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramInt1 = 0; paramInt1 < cursor.getCount(); paramInt1++) {
//            str = cursor.getString(0);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }

//
//    public String getTodayHealth(int paramInt) {
//        Cursor cursor = this.m_dataBase.rawQuery("select contents from today_health where _id=?", new String[]{Integer.toString(paramInt)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramInt = 0; paramInt < cursor.getCount(); paramInt++) {
//            str = cursor.getString(0);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
//
//    public String getTodayMoney(int paramInt) {
//        Cursor cursor = this.m_dataBase.rawQuery("select contents from today_money where _id=?", new String[]{Integer.toString(paramInt)});
//        cursor.moveToFirst();
//        String str = null;
//        for (paramInt = 0; paramInt < cursor.getCount(); paramInt++) {
//            str = cursor.getString(0);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return str;
//    }
////
////    public ArrayList<String> getTojungForNewYear(int paramInt) {
////        PrintStream printStream2 = System.out;
////        StringBuilder stringBuilder = new StringBuilder();
////        stringBuilder.append("!!!!!!!!!!!!!!!!!!!!!!!!start :");
////        stringBuilder.append(System.currentTimeMillis());
////        printStream2.println(stringBuilder.toString());
////        ArrayList<String> arrayList = new ArrayList();
////        SQLiteDatabase sQLiteDatabase = this.m_dataBase;
////        boolean bool = true;
////        Cursor cursor = sQLiteDatabase.rawQuery("select heading, ex1_1, ex2_1, ex3_1, ex4_1, ex5_1, ex6_1, ex7_1, ex8_1, ex1_2,  ex2_2, ex3_2, ex4_2, ex5_2, ex6_2, ex7_2, ex8_2, explain, modern,  col_21,col_22,col_23,col_24,col_25,col_26,col_27,col_28,col_29,col_30,col_31,col_32  from tb_tojung where idx = ?", new String[]{Integer.toString(paramInt)});
////        cursor.moveToFirst();
////        String str = "";
////        paramInt = 0;
////        while (paramInt < cursor.getColumnCount()) {
////            if (cursor.getString(paramInt) == null) {
////                str2 = "";
////            } else {
////                str2 = cursor.getString(paramInt);
////            }
////            String str1 = str2;
////            if (cursor.getColumnName(paramInt).equals("heading")) {
////                str1 = str2.trim();
////                StringBuilder stringBuilder1 = new StringBuilder();
////                stringBuilder1.append("[ ");
////                stringBuilder1.append(str1);
////                stringBuilder1.append(" ]\n");
////                str1 = stringBuilder1.toString();
////            }
////            String str2 = str1;
////            if (cursor.getColumnName(paramInt).equals("explain")) {
////                StringBuilder stringBuilder1 = new StringBuilder();
////                stringBuilder1.append(str1);
////                stringBuilder1.append("\n\n");
////                str2 = stringBuilder1.toString();
////            }
////            if (bool) {
////                StringBuilder stringBuilder1 = new StringBuilder();
////                stringBuilder1.append(str);
////                stringBuilder1.append(str2);
////                str2 = stringBuilder1.toString();
////                if (cursor.getColumnName(paramInt).equals("ex2_1") || cursor.getColumnName(paramInt).equals("ex4_1") || cursor.getColumnName(paramInt).equals("ex6_1")) {
////                    stringBuilder1 = new StringBuilder();
////                    stringBuilder1.append(str2);
////                    stringBuilder1.append("\n");
////                    String str3 = stringBuilder1.toString();
////                } else if (cursor.getColumnName(paramInt).equals("ex8_1")) {
////                    stringBuilder1 = new StringBuilder();
////                    stringBuilder1.append(str2);
////                    stringBuilder1.append("\n\n");
////                    String str3 = stringBuilder1.toString();
////                } else {
////                    String str3 = str2;
////                    if (cursor.getColumnName(paramInt).equals("ex8_2")) {
////                        arrayList.add(str2);
////                        bool = false;
////                        str3 = str2;
////                    }
////                }
////            } else {
////                arrayList.add(str2);
////                str1 = str;
////            }
////            paramInt++;
////            str = str1;
////        }
////        cursor.close();
////        PrintStream printStream1 = System.out;
////        stringBuilder = new StringBuilder();
////        stringBuilder.append("!!!!!!!!!!!!!!!!!!!!End : ");
////        stringBuilder.append(System.currentTimeMillis());
////        printStream1.println(stringBuilder.toString());
////        return arrayList;
////    }
//
//    public User getUser(int paramInt) {
//        new ArrayList();
//        Cursor cursor = this.m_dataBase.rawQuery("select _id, name,sex,lunar,byear,bmonth,bday,btime,facebookid from tb_user where _id=?", new String[]{Integer.toString(paramInt)});
//        cursor.moveToFirst();
//        ArrayList<User> arrayList = new ArrayList();
//        for (paramInt = 0; paramInt < cursor.getCount(); paramInt++) {
//            arrayList.add(new User(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), Define.DateType.values()[cursor.getInt(3)], cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8)));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return arrayList.get(0);
//    }
//
//    public ArrayList<User> getUsers() {
//        new ArrayList();
//        Cursor cursor = this.m_dataBase.rawQuery("select _id, name,sex,lunar,byear,bmonth,bday,btime,facebookid from tb_user", null);
//        cursor.moveToFirst();
//        ArrayList<User> arrayList = new ArrayList();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            int j = cursor.getInt(0);
//            String str = cursor.getString(1);
//            int k = cursor.getInt(2);
//            Define.DateType dateType = Define.DateType.values()[cursor.getInt(3)];
//            int m = cursor.getInt(4);
//            int n = cursor.getInt(5);
//            User user = new User(j, str, k, dateType, m, n, cursor.getInt(6), cursor.getInt(7), cursor.getString(8));
//            arrayList.add(user);
//            PrintStream printStream = System.out;
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("");
//            stringBuilder.append(j);
//            stringBuilder.append(" : ");
//            stringBuilder.append(str);
//            stringBuilder.append(" : ");
//            stringBuilder.append(n);
//            stringBuilder.append(" : ");
//            stringBuilder.append(user.getSolarDate().get(2));
//            printStream.println(stringBuilder.toString());
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return arrayList;
//    }
//
//    public int insertUser(User paramUser) {
//        MCalendar mCalendar;
//        System.out.println(paramUser._name);
//        if (paramUser._dateType == Define.DateType.Solar) {
//            mCalendar = paramUser.getSolarDate();
//        } else {
//            mCalendar = paramUser.getLunarDate();
//        }
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", paramUser._name);
//        contentValues.put("sex", Integer.valueOf(paramUser._sex));
//        contentValues.put("lunar", Integer.valueOf(paramUser._dateType.ordinal()));
//        contentValues.put("byear", Integer.valueOf(mCalendar.get(MCalendar.YEAR)));
//        contentValues.put("bmonth", Integer.valueOf(mCalendar.get(MCalendar.MONTH)));
//        contentValues.put("bday", Integer.valueOf(mCalendar.get(MCalendar.DAY_OF_MONTH)));
//        contentValues.put("btime", Integer.valueOf(paramUser.birthTime));
//        contentValues.put("facebookid", paramUser._facebookid);
//        return (int) this.m_dataBase.insert("tb_user", null, contentValues);
//    }
//
//    public void updateDB() {
//        this.mOpenHelper.updateDB();
//        this.mOpenHelper.close();
//    }
//
//    public void updateUser(User paramUser) {
//        MCalendar mCalendar;
//        if (paramUser._dateType == Define.DateType.Solar) {
//            mCalendar = paramUser.getSolarDate();
//        } else {
//            mCalendar = paramUser.getLunarDate();
//        }
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", paramUser._name);
//        contentValues.put("sex", Integer.valueOf(paramUser._sex));
//        contentValues.put("lunar", Integer.valueOf(paramUser._dateType.ordinal()));
//        contentValues.put("byear", Integer.valueOf(mCalendar.get(MCalendar.YEAR)));
//        contentValues.put("bmonth", Integer.valueOf(mCalendar.get(MCalendar.MONTH)));
//        contentValues.put("bday", Integer.valueOf(mCalendar.get(MCalendar.DAY_OF_MONTH)));
//        contentValues.put("btime", Integer.valueOf(paramUser.birthTime));
//        contentValues.put("facebookid", paramUser._facebookid);
//        this.m_dataBase.update("tb_user", contentValues, "_id=?", new String[]{Integer.toString(paramUser.index)});
//    }
//
//    public enum Jeolgi {
//        우수("우수",3),경칩("경칩", 4),춘분("춘분",5),청명("청명",6),곡우("곡우",7),입하("입하",8),소만("소만",9),망종("망종",10),하지("하지",11),소서("소서",12),대서("대서",13),입추("입추",14),
//        처서("처서",15),백로("백로",16),추분("추분",17),한로("한로",18),상강("상강",19),입동("입동",20),소설("소설",21),대설("대설",22),동지("동지",23);
//
//        Jeolgi(String name, int a) {
//
//        }
//    }
}