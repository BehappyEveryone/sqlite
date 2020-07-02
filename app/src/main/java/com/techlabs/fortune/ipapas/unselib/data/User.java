package com.techlabs.fortune.ipapas.unselib.data;

import android.content.Context;
import java.util.Calendar;

public class User {
    public Define.DateType _dateType;

    int _day;

    public String _facebookid = "";

    int _month;

    public String _name;

    public int _sex;

    int _year;

    public int birthTime;

    public int index;

    Context m_context;

    public User() {
        Calendar calendar = Calendar.getInstance();
        this.index = -1;
        this._sex = 1;
        this._name = "";
        this._facebookid = "";
        this._year = calendar.get(1);
        this._month = calendar.get(2) + 1;
        this._day = calendar.get(5);
        this.birthTime = 0;
        this._dateType = Define.DateType.Solar;
    }

    public User(int paramInt1, String paramString1, int paramInt2, Define.DateType paramDateType, int paramInt3, int paramInt4, int paramInt5, int paramInt6, String paramString2) {
        this.index = paramInt1;
        this._sex = paramInt2;
        this._name = paramString1;
        this._facebookid = paramString2;
        this._year = paramInt3;
        this._month = paramInt4;
        this._day = paramInt5;
        paramInt1 = 12;
        if (paramInt6 > 12)
            paramInt6 = paramInt1;
        this.birthTime = paramInt6;
        this._dateType = paramDateType;
    }

    public MCalendar getLunarDate() {
        if (this._dateType == Define.DateType.Lunar || this._dateType == Define.DateType.Lunar_leap)
            return new MCalendar(this._year, this._month, this._day);
        int[] arrayOfInt2 = CalendarConverter.solarToLunarByLeapMonth(this._year, this._month, this._day);
        int[] arrayOfInt1 = arrayOfInt2;
        if (arrayOfInt2 == null) {
            arrayOfInt1 = new int[3];
            arrayOfInt1[0] = this._year;
            arrayOfInt1[1] = this._month;
            arrayOfInt1[2] = this._day;
        }
        return new MCalendar(arrayOfInt1[0], arrayOfInt1[1], arrayOfInt1[2]);
    }

    public MCalendar getSolarDate() {
        int[] arrayOfInt1;
        if (this._dateType == Define.DateType.Solar)
            return new MCalendar(this._year, this._month, this._day);
        if (this._dateType == Define.DateType.Lunar) {
            arrayOfInt1 = CalendarConverter.lunarToSolar(this._year, this._month, this._day);
        } else {
            arrayOfInt1 = CalendarConverter.lunarToSolarByLeapMonth(this._year, this._month, this._day, true);
        }
        int[] arrayOfInt2 = arrayOfInt1;
        if (arrayOfInt1 == null) {
            arrayOfInt2 = new int[3];
            arrayOfInt2[0] = this._year;
            arrayOfInt2[1] = this._month;
            arrayOfInt2[2] = this._day;
        }
        return new MCalendar(arrayOfInt2[0], arrayOfInt2[1], arrayOfInt2[2]);
    }

    public boolean isNewUser() {
        return (this.index < 0);
    }

    public void setBirthDay(int paramInt1, int paramInt2, int paramInt3) {
        this._year = paramInt1;
        this._month = paramInt2;
        this._day = paramInt3;
    }

    public void setBirthtime(int paramInt) {
        int i = paramInt;
        if (paramInt > 12)
            i = 12;
        this.birthTime = i;
    }

    public void setContext(Context paramContext) {
        this.m_context = paramContext;
    }
}
