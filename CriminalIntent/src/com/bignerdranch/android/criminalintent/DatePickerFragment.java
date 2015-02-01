package com.bignerdranch.android.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment {
	
	public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";
	private Date mDate;
	
	public static DatePickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);
		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDate = (Date)getArguments().getSerializable(EXTRA_DATE); //grabbing Date from Crime Fragment through arguments
		// Create a Calendar to get the year, month, and day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
			
		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_date, null); 
		//this is to display date picker layout in the dialog fragment
		//you don't have to inflate a custom layout if you want just a date picker but this allows customization
		//if you want just a default date picker, use DatePicker dp = new DatePicker(getActivity()), and setView(dp)
		
		
		DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
		datePicker.init(year, month, day, new OnDateChangedListener() {
			public void onDateChanged(DatePicker view, int year, int month, int day) {
				// Translate year, month, day into a Date object using a calendar
				mDate = new GregorianCalendar(year, month, day).getTime();
				// Update argument to preserve selected value on rotation
				getArguments().putSerializable(EXTRA_DATE, mDate);
			}
		});
		
		return new AlertDialog.Builder(getActivity())
		.setView(v)
		.setTitle(R.string.date_picker_title)
		.setPositiveButton(
				android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				})
		.create();
	}
	
	private void sendResult(int resultCode) {
		if (getTargetFragment() == null)
			return;
		Intent i = new Intent();
		i.putExtra(EXTRA_DATE, mDate);
		getTargetFragment()
		.onActivityResult(getTargetRequestCode(), resultCode, i);
	}

}