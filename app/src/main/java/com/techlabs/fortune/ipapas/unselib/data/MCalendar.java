package com.techlabs.fortune.ipapas.unselib.data;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class MCalendar {
    public static int DAY_OF_MONTH = 5;

    public static int MONTH = 2;

    public static int YEAR = 1;

    private GregorianCalendar m_cal = null;

    public MCalendar(int year, int month, int day) {
        this.m_cal = new GregorianCalendar(year, month - 1,day);
    }

    public MCalendar(GregorianCalendar paramGregorianCalendar) {
        this.m_cal = paramGregorianCalendar;
    }

    public static MCalendar getInstance() {
        Calendar calendar = Calendar.getInstance();
        return new MCalendar(new GregorianCalendar(calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13)));
    }

    public int get(int paramInt) {
        return (paramInt == 2) ? (this.m_cal.get(paramInt) + 1) : this.m_cal.get(paramInt);
    }
}
