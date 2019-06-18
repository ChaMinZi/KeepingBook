package com.example.chacha.keepingbook;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "TimePickerFragment";
    final Calendar calendar = Calendar.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        return new TimePickerDialog(getActivity(), TimePickerFragment.this, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),false);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        String selectedTime, state = "AM";
        if (hour > 12) {
            state = "PM";
            hour -= 12;
        }
        selectedTime = state + " " + hour + ":" + minute;
        Log.e("TimePickerFragmet", selectedTime);

        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, new Intent().putExtra("selectedTime", selectedTime));
    }
}
