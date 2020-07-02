package com.techlabs.fortune.ipapas.unselib.data;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddUserActivity extends Dialog {
    final int DATE_DIALOG_ID = 0;

    final int TIME_DIALOG_ID = 1;

    Context mContext = null;

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker param1DatePicker, int param1Int1, int param1Int2, int param1Int3) {
            User user;
            int i = param1Int2 + 1;
            if (AddUser.this.mIsMe) {
                user = AddUser.this.mMe;
            } else {
                user = AddUser.this.mPartner;
            }
            user.setBirthDay(param1Int1, i, param1Int3);
            if (AddUser.this.mIsMe) {
                param1Int2 = 2131230834;
            } else {
                param1Int2 = 2131230889;
            }
            TextView textView = (TextView) AddUser.this.findViewById(param1Int2);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(param1Int1);
            stringBuilder.append("");
            stringBuilder.append(i);
            stringBuilder.append("");
            stringBuilder.append(param1Int3);
            stringBuilder.append(");
                    textView.setText(stringBuilder.toString());
        }
    };

    boolean mIsMe = true;

    User mMe = new User();

    User mPartner = new User();

    UpdateUI mUpdateUI = null;

    boolean m_isEditMode = false;

    int m_timeIndex = 1;

    public AddUser() {
        super(null);
    }

    private AddUser(Context paramContext, UpdateUI paramUpdateUI) {
        super(paramContext);
        this.mContext = paramContext;
        this.mUpdateUI = paramUpdateUI;
    }

    private void me(User paramUser) {
        MCalendar mCalendar;
        final EditText nameEdit = (EditText) findViewById(2131230836);
        editText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (nameEdit.getText().toString().equals(meInitName))
                    nameEdit.getText().clear();
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView param1TextView, int param1Int, KeyEvent param1KeyEvent) {
                if (param1Int == 6) {
                    Context context = AddUser.this.getContext();
                    AddUser.this.getContext();
                    ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(nameEdit.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
                if (param1Int == 66 && param1KeyEvent.getAction() == 0)
                    ((InputMethodManager) AddUser.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(nameEdit.getWindowToken(), 0);
                return false;
            }
        });
        TextView textView2 = (TextView) findViewById(2131230834);
        if (paramUser.isNewUser()) {
            mCalendar = MCalendar.getInstance();
        } else if (paramUser._dateType == Define.DateType.Solar) {
            mCalendar = paramUser.getSolarDate();
        } else {
            mCalendar = paramUser.getLunarDate();
        }
        textView2.setText(String.format("%d%d%d, new Object[] { Integer.valueOf(mCalendar.get(MCalendar.YEAR)), Integer.valueOf(mCalendar.get(MCalendar.MONTH)), Integer.valueOf(mCalendar.get(MCalendar.DAY_OF_MONTH)) }));
                textView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        AddUser.this.showDialog(0, true);
                    }
                });
        TextView textView1 = (TextView) findViewById(2131230835);
        textView1.setText(getContext().getResources().getStringArray(2130837505)[0]);
        textView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                AddUser.this.showDialog(1, true);
            }
        });
        if (!paramUser.isNewUser()) {
            MCalendar mCalendar1;
            editText.setText(paramUser._name);
            RadioGroup radioGroup = (RadioGroup) findViewById(2131230837);
            if (paramUser._sex == DefineHarmony.Sex.MAN.ordinal()) {
                radioGroup.check(2131230838);
            } else {
                radioGroup.check(2131230839);
            }
            radioGroup = (RadioGroup) findViewById(2131230830);
            if (paramUser._dateType == Define.DateType.Solar) {
                radioGroup.check(2131230833);
            } else if (paramUser._dateType == Define.DateType.Lunar) {
                radioGroup.check(2131230831);
            } else {
                radioGroup.check(2131230832);
            }
            if (paramUser._dateType == Define.DateType.Solar) {
                mCalendar1 = paramUser.getSolarDate();
            } else {
                mCalendar1 = paramUser.getLunarDate();
            }
            TextView textView = (TextView) findViewById(2131230834);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(mCalendar1.get(MCalendar.YEAR));
            stringBuilder.append("");
            stringBuilder.append(mCalendar1.get(MCalendar.MONTH));
            stringBuilder.append("");
            stringBuilder.append(mCalendar1.get(MCalendar.DAY_OF_MONTH));
            stringBuilder.append(");
                    textView.setText(stringBuilder.toString());
            ((TextView) findViewById(2131230835)).setText(getContext().getResources().getStringArray(2130837505)[paramUser.birthTime]);
        }
    }

    private void partner(User paramUser) {
        MCalendar mCalendar;
        final String initName = getContext().getString(2131492922);
        final EditText nameEdit = (EditText) findViewById(2131230891);
        editText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (nameEdit.getText().toString().equals(initName))
                    nameEdit.getText().clear();
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView param1TextView, int param1Int, KeyEvent param1KeyEvent) {
                if (param1Int == 6) {
                    Context context = AddUser.this.getContext();
                    AddUser.this.getContext();
                    ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(nameEdit.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
                if (param1Int == 66 && param1KeyEvent.getAction() == 0)
                    ((InputMethodManager) AddUser.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(nameEdit.getWindowToken(), 0);
                return false;
            }
        });
        TextView textView2 = (TextView) findViewById(2131230889);
        if (paramUser.isNewUser()) {
            mCalendar = MCalendar.getInstance();
        } else if (paramUser._dateType == Define.DateType.Solar) {
            mCalendar = paramUser.getSolarDate();
        } else {
            mCalendar = paramUser.getLunarDate();
        }
        textView2.setText(String.format("%d%d%d, new Object[] { Integer.valueOf(mCalendar.get(MCalendar.YEAR)), Integer.valueOf(mCalendar.get(MCalendar.MONTH)), Integer.valueOf(mCalendar.get(MCalendar.DAY_OF_MONTH)) }));
                textView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View param1View) {
                        AddUser.this.showDialog(0, false);
                    }
                });
        TextView textView1 = (TextView) findViewById(2131230890);
        textView1.setText(getContext().getResources().getStringArray(2130837505)[0]);
        textView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                AddUser.this.showDialog(1, false);
            }
        });
        if (!paramUser.isNewUser()) {
            MCalendar mCalendar1;
            editText.setText(paramUser._name);
            RadioGroup radioGroup = (RadioGroup) findViewById(2131230892);
            if (paramUser._sex == DefineHarmony.Sex.MAN.ordinal()) {
                radioGroup.check(2131230893);
            } else {
                radioGroup.check(2131230894);
            }
            radioGroup = (RadioGroup) findViewById(2131230885);
            if (paramUser._dateType == Define.DateType.Solar) {
                radioGroup.check(2131230888);
            } else if (paramUser._dateType == Define.DateType.Lunar) {
                radioGroup.check(2131230886);
            } else {
                radioGroup.check(2131230887);
            }
            if (paramUser._dateType == Define.DateType.Solar) {
                mCalendar1 = paramUser.getSolarDate();
            } else {
                mCalendar1 = paramUser.getLunarDate();
            }
            TextView textView = (TextView) findViewById(2131230889);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(mCalendar1.get(MCalendar.YEAR));
            stringBuilder.append("");
            stringBuilder.append(mCalendar1.get(MCalendar.MONTH));
            stringBuilder.append("");
            stringBuilder.append(mCalendar1.get(MCalendar.DAY_OF_MONTH));
            stringBuilder.append(");
                    textView.setText(stringBuilder.toString());
            ((TextView) findViewById(2131230890)).setText(getContext().getResources().getStringArray(2130837505)[paramUser.birthTime]);
        }
    }

    private void saveData() {
        int i;
        AlertDialog.Builder builder2;
        Define.DateType dateType3;
        AlertDialog.Builder builder1;
        Define.DateType dateType1;
        String str2 = ((EditText) findViewById(2131230836)).getText().toString();
        if (str2 == null || str2.length() < 1 || str2.equals(getContext().getString(2131492921))) {
            builder2 = new AlertDialog.Builder(this.mContext);
            builder2.setPositiveButton(", new DialogInterface.OnClickListener() {
            public void onClick (DialogInterface param1DialogInterface,int param1Int){
                param1DialogInterface.dismiss();
            }
        });
        builder2.setMessage(");
                builder2.show();
        return;
    }
    this.mMe._name =(String)builder2;
    if(((RadioGroup)

    findViewById(2131230837)).

    getCheckedRadioButtonId() ==2131230838)

    {
        i = DefineHarmony.Sex.MAN.ordinal();
    } else

    {
        i = DefineHarmony.Sex.WOMAN.ordinal();
    }
    this.mMe._sex =i;
    Define.DateType dateType4 = Define.DateType.Solar;
    RadioGroup radioGroup2 = (RadioGroup) findViewById(2131230830);
    if(radioGroup2.getCheckedRadioButtonId()==2131230833)

    {
        dateType3 = Define.DateType.Solar;
    } else if(dateType3.getCheckedRadioButtonId()==2131230831)

    {
        dateType3 = Define.DateType.Lunar;
    } else

    {
        dateType3 = Define.DateType.Lunar_leap;
    }
    this.mMe._dateType =dateType3;
    String str1 = ((EditText) findViewById(2131230891)).getText().toString();
    if(str1 ==null||str1.length() < 1||str1.equals(

    getContext().

    getString(2131492922)))

    {
        builder1 = new AlertDialog.Builder(this.mContext);
        builder1.setPositiveButton(", new DialogInterface.OnClickListener() {
        public void onClick (DialogInterface param1DialogInterface,int param1Int){
        param1DialogInterface.dismiss();
    }
    });
    builder1.setMessage(");
            builder1.show();
    return;
}
this.mPartner._name=(String)builder1;
        if(((RadioGroup)findViewById(2131230892)).getCheckedRadioButtonId()==2131230893){
        i=DefineHarmony.Sex.MAN.ordinal();
        }else{
        i=DefineHarmony.Sex.WOMAN.ordinal();
        }
        this.mPartner._sex=i;
        Define.DateType dateType2=Define.DateType.Solar;
        RadioGroup radioGroup1=(RadioGroup)findViewById(2131230885);
        if(radioGroup1.getCheckedRadioButtonId()==2131230888){
        dateType1=Define.DateType.Solar;
        }else if(dateType1.getCheckedRadioButtonId()==2131230886){
        dateType1=Define.DateType.Lunar;
        }else{
        dateType1=Define.DateType.Lunar_leap;
        }
        this.mPartner._dateType=dateType1;
        UnseDB unseDB=new UnseDB(this.mContext);
        if(!this.mMe.isNewUser()){
        System.out.println("update !!!!");
        unseDB.updateUser(this.mMe);
        }else{
        System.out.println("insert !!!!");
        this.mMe.index=unseDB.insertUser(this.mMe);
        }
        if(!this.mPartner.isNewUser()){
        System.out.println("update partner!!!!");
        unseDB.updateUser(this.mPartner);
        }else{
        System.out.println("insert partner!!!!");
        this.mPartner.index=unseDB.insertUser(this.mPartner);
        }
        unseDB.close();
        }

public static Dialog setUser(Context paramContext,UpdateUI paramUpdateUI){
        UserManager.getInstance(paramContext);
        AddUser addUser=new AddUser(paramContext,paramUpdateUI);
        addUser.setTitle(");
        TextView textView=(TextView)addUser.getWindow().findViewById(16908310);
        textView.setTextColor(-1);
        textView.setGravity(17);
        textView.setTextSize(25.0F);
        ((View)textView.getParent()).setBackgroundColor(Color.parseColor("#dac3c5"));
        addUser.show();
        return addUser;
        }

@TargetApi(11)
private void setupActionBar(){
        if(Build.VERSION.SDK_INT>=11)
        getActionBar().setDisplayHomeAsUpEnabled(true);
        }

protected void onCreate(Bundle paramBundle){
        super.onCreate(paramBundle);
        setContentView(2131361819);
        Window window=getWindow();
        window.setLayout(-1,-1);
        window.setSoftInputMode(32);
        findViewById(16908290);
        UserManager userManager=UserManager.getInstance(this.mContext);
        this.mMe=userManager.getMe();
        me(this.mMe);
        this.mPartner=userManager.getPartner();
        partner(this.mPartner);
        ((Button)findViewById(2131230958)).setOnClickListener(new View.OnClickListener(){
public void onClick(View param1View){
        System.out.println("input complete");
        AddUser.this.saveData();
        UserManager.getInstance(AddUser.this.mContext).reloadUser();
        if(AddUser.this.mUpdateUI!=null)
        AddUser.this.mUpdateUI.update();
        AddUser.this.dismiss();
        }
        });
        }

protected void showDialog(int paramInt,boolean paramBoolean){
final int oldTimeIndex;
final User user;
        MCalendar mCalendar;
        AlertDialog.Builder builder;
        this.mIsMe=paramBoolean;
        if(this.mIsMe){
        user=this.mMe;
        }else{
        user=this.mPartner;
        }
        switch(paramInt){
default:
        return;
        case 1:
        builder=new AlertDialog.Builder(getContext(),2131558402);
        builder.setTitle((CharSequence)Html.fromHtml("<strong><font color=\"#b0797d\" >));
        paramInt=0;
        if(!user.isNewUser())
        paramInt=user.birthTime;
        i=user.birthTime;
        builder.setSingleChoiceItems(2130837505,paramInt,new DialogInterface.OnClickListener(){
public void onClick(DialogInterface param1DialogInterface,int param1Int){
        user.setBirthtime(param1Int);
        }
        });
        builder.setPositiveButton(", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param1DialogInterface,int param1Int){
        user.setBirthtime(oldTimeIndex);
        }
        });
        builder.setNegativeButton(", new DialogInterface.OnClickListener() {
public void onClick(DialogInterface param1DialogInterface,int param1Int){
        String[]arrayOfString=AddUser.this.getContext().getResources().getStringArray(2130837505);
        if(AddUser.this.mIsMe){
        param1Int=2131230835;
        }else{
        param1Int=2131230890;
        }
        ((TextView)AddUser.this.findViewById(param1Int)).setText(arrayOfString[user.birthTime]);
        }
        });
        builder.show();
        return;
        case 0:
        break;
        }
        if(user.isNewUser()){
        mCalendar=MCalendar.getInstance();
        }else if(((User)mCalendar)._dateType==Define.DateType.Solar){
        mCalendar=mCalendar.getSolarDate();
        }else{
        mCalendar=mCalendar.getLunarDate();
        }
final DatePickerSpinnerDialog dialogDatePicker=new DatePickerSpinnerDialog(getContext(),mCalendar.get(MCalendar.YEAR),mCalendar.get(MCalendar.MONTH)-1,mCalendar.get(MCalendar.DAY_OF_MONTH));
        datePickerSpinnerDialog.setOnDismissListener(new DialogInterface.OnDismissListener(){
public void onDismiss(DialogInterface param1DialogInterface){
        User user;
        int i;
        int j=dialogDatePicker.getYear();
        int k=dialogDatePicker.getMonth()+1;
        int m=dialogDatePicker.getDay();
        if(AddUser.this.mIsMe){
        user=AddUser.this.mMe;
        }else{
        user=AddUser.this.mPartner;
        }
        user.setBirthDay(j,k,m);
        if(AddUser.this.mIsMe){
        i=2131230834;
        }else{
        i=2131230889;
        }
        TextView textView=(TextView)AddUser.this.findViewById(i);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(j);
        stringBuilder.append("");
        stringBuilder.append(k);
        stringBuilder.append("");
        stringBuilder.append(m);
        stringBuilder.append(");
        textView.setText(stringBuilder.toString());
        }
        });
        datePickerSpinnerDialog.show();
        }
        }
