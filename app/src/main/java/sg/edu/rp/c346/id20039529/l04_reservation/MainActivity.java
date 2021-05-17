package sg.edu.rp.c346.id20039529.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView firstName;
        EditText editName;
        TextView phone;
        EditText editPhone;
        TextView pax;
        EditText editPax;
        CheckBox smoking;
        DatePicker dp;
        TimePicker tp;
        Button btnReserve;
        Button btnReset;

        firstName = findViewById(R.id.Name);
        editName = findViewById(R.id.editName);
        phone = findViewById(R.id.Phone);
        editPhone = findViewById(R.id.editPhone);
        pax = findViewById(R.id.Pax);
        editPax = findViewById(R.id.editPax);
        smoking = findViewById(R.id.checkBoxSmoking);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);

        dp.updateDate(2021, 5, 1);
        dp.setMinDate(System.currentTimeMillis() - 3000);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(tp.getCurrentHour() <= 20 && tp.getCurrentHour() >= 8){
                    if(tp.getCurrentMinute() <= 59 && tp.getCurrentMinute() >= 00){
                        tp.setCurrentHour(tp.getCurrentHour());
                        tp.setCurrentMinute(tp.getCurrentMinute());
                    }
                }else{
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(59);
                }
            }
        });



        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String telephone = editPhone.getText().toString();
                String size = editPax.getText().toString();
                if (editName.getText().toString().trim().length() != 0 || editPhone.getText().toString().trim().length() != 0 || editPax.getText().toString().trim().length() != 0) {
                    String smoke = "";
                    if (smoking.isChecked()) {
                        smoke = "Smoking";
                    } else {
                        smoke = "Non - Smoking";
                    }

                    String date = dp.getDayOfMonth() + "/" + (dp.getMonth() + 1) + "/" + dp.getYear();
                    String time = tp.getCurrentHour() + ":" + String.format("%02d", tp.getCurrentMinute());

                    String msg = "Hi, " + name + ", you have booked a "
                            + size + " person(s) "
                            + smoke + " table on "
                            + date + " at "
                            + time + ". Your phone number is "
                            + telephone + ".";

                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error: You have not filled in a blank", Toast.LENGTH_LONG).show();
                }
            }


    });
        btnReset.setOnClickListener((v) -> {
            editName.setText("");
            editPhone.setText("");
            editPax.setText("");
            smoking.setChecked(false);
            dp.updateDate(2021, 5, 1);
            tp.setCurrentHour(19);
            tp.setCurrentMinute(30);
        });
}}
