package com.example.hansol.xml_200321_hs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Page4 extends AppCompatActivity {

    // 날짜 관련 변수들
    Calendar myCalendar = Calendar.getInstance();
    EditText editDate;
    SimpleDateFormat sdf;
    String time;

    // 이용권 관련 변수
    EditText editDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page4);

        // 날짜 선택 눌렀을 때 DatePicker보여주기
        editDate = (EditText) findViewById(R.id.entDate_page4);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Page4.this, myDatePicker,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // 이용권 눌렀을 때 다이얼로그 띄우기
        editDays = (EditText) findViewById(R.id.entDays_page4);
        editDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayShow();
            }
        });
    }

    //날짜 값을 받아온다.
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    //선택된 날짜를 edittext에 적용시킨다.
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";
        sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText editDate = (EditText) findViewById(R.id.entDate_page4);
        time = sdf.format(myCalendar.getTime());

        editDate.setText(time);
    }

    // 이용권 눌렀을 때
    void dayShow(){
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("3일권");
        ListItems.add("5일권");
        ListItems.add("7일권");

        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("이용권 선택");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selectedText = items[i].toString();
                // 선택된 일 수는 토스트로 띄움
                Toast.makeText(Page4.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
