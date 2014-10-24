package hu.kapi.fitdiary.activites;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.fragments.DatePickerCommunicator;
import hu.kapi.fitdiary.fragments.DatePickerFragment;
import hu.kapi.fitdiary.model.User;
import hu.kapi.fitdiary.util.ErrorToast;
import hu.kapi.fitdiary.util.NetThread;
import hu.kapi.fitdiary.util.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationActivity extends android.support.v4.app.FragmentActivity implements DatePickerCommunicator {
	Button doneBtn, undoBtn;
	EditText name, password, confirmPassword, email, birthday;
	Spinner sex;
	User actualUser = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		name = (EditText) findViewById(R.id.regNameField);
		password = (EditText) findViewById(R.id.regPasswordField);
		confirmPassword = (EditText) findViewById(R.id.regConfirmPasswordField);
		email = (EditText) findViewById(R.id.regEmailField);
		birthday = (EditText) findViewById(R.id.regBirthdayField);
		sex = (Spinner) findViewById(R.id.regSexSpinner);

		doneBtn = (Button) findViewById(R.id.regDoneBtn);
		doneBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("".equals(name.getText().toString())
						|| "".equals(password.getText().toString())
						|| "".equals(confirmPassword.getText().toString())
						|| "".equals(email.getText().toString())
						|| "".equals(birthday.getText().toString())
					) {

					new ErrorToast(
							RegistrationActivity.this,
							"Minden mező kitöltése kötelező!")
							.show();
				} else if ((password.getText().toString())
						.equals(confirmPassword.getText().toString())) {
					if (isEmailValid(email.getText().toString())) {
						Runnable r = new Runnable() {

							@Override
							public void run() {
								int sexInt;
								String male = getString(R.string.male);
								if ( (sex.getSelectedItem().toString()).equalsIgnoreCase(male) ){
									sexInt = 0;
								}
								else {
									sexInt = 1;
								}
								
								Activity a = RegistrationActivity.this;
								try {
									actualUser = Session
											.getInstance()
											.getActualCommunication()
											.registerANewUser(
													name.getText().toString(),
													password.getText().toString(),
													email.getText().toString(),
													sexInt,
													birthday.getText().toString());
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
								
								if (actualUser != null) {
									Session.getInstance().setActualUser(
											actualUser);
								}

								a.runOnUiThread(new Runnable() {

									@Override
									public void run() {
										if (actualUser == null) {
											Session.getInstance()
													.dismissProgressDialog();
											new ErrorToast(
													RegistrationActivity.this,
													"Sikertelen bejelentkezés! Hibás felhasználónév vagy jelszó! Próbáld újra!")
													.show();
										} else if (actualUser.getId() == -1) {
											new ErrorToast(
													RegistrationActivity.this,
													"A megadott email cím már használatban van!")
													.show();
										}
										else {
											Intent newIntent = new Intent(
													RegistrationActivity.this,
													SecondActivity.class);

											startActivity(newIntent);
											overridePendingTransition(
													R.anim.slide_left_in,
													R.anim.slide_left_out);
											finish();
										}
										Session.getInstance()
												.dismissProgressDialog();
									}
								});

							}
						};

						Session.getInstance().progressDialog = ProgressDialog
								.show(RegistrationActivity.this, "Kérlek várj",
										"Regisztráció folyamatban...", true,
										false);
						new NetThread(RegistrationActivity.this, r).start();
					} else {
						new ErrorToast(
								RegistrationActivity.this,
								"Nem megfelelő email cím!")
								.show();
					}
				} else {

					new ErrorToast(
							RegistrationActivity.this,
							"A megadott jelszavak nem egyeznek!")
							.show();
				}
			}
		});

		undoBtn = (Button) findViewById(R.id.regUndoBtn);
		undoBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(RegistrationActivity.this,
						MainActivity.class);
				startActivity(i);
			}
		});
	}
	
	public void showPicker(View v) {
		DialogFragment datepicker = new DatePickerFragment();
        datepicker.show(getSupportFragmentManager(), "timePicker");
	}
	
	
	
	public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

	@Override
	public void onDatePicked(String date) {
		birthday.setText(date);
		
		SimpleDateFormat dateformat2 = new SimpleDateFormat(
	             "yyyy-MM-dd");
	}
}
