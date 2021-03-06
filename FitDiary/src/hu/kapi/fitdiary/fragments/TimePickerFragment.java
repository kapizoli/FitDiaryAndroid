package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.communicators.TimePickerCommunicator;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    Calendar c;
    int year;
    int month;
    int day;
    TimePickerCommunicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (TimePickerCommunicator) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        
        tpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Beállít", tpd);
        tpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Mégsem", tpd);
        
        return tpd;
    }

    @Override
    public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
        //String result = String.valueOf(arg1) + ":" + String.valueOf(arg2);
        String result = String.format("%02d:%02d", arg1, arg2);
        communicator.onTimePicked(result);
    }

}