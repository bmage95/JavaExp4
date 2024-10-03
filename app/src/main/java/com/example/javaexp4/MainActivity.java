package com.example.javaexp4;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText date_picker;
    ListView list_view;
    TextView header;

    ArrayList<String> veggie_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_picker = findViewById(R.id.date_picker);
        list_view = findViewById(R.id.list_view);
        header = findViewById(R.id.header);

        date_picker.setFocusable(false);
        date_picker.setClickable(true);

        veggie_list = new ArrayList<>();
        veggie_list.add("Tomato");
        veggie_list.add("Potato");
        veggie_list.add("Onion");
        veggie_list.add("Carrot");
        veggie_list.add("Cabbage");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                veggie_list
        );
        list_view.setAdapter(adapter);

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(selectedYear, selectedMonth, selectedDay);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
                        String formattedDate = dateFormat.format(selectedDate.getTime());

                        date_picker.setText(formattedDate);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }
}
