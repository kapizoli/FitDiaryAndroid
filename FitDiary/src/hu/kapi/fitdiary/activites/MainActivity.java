package hu.kapi.fitdiary.activites;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.model.User;
import hu.kapi.fitdiary.util.ErrorToast;
import hu.kapi.fitdiary.util.NetThread;
import hu.kapi.fitdiary.util.Session;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
Button loginBtn, regBtn, loginWithoutRegBtn;
EditText email, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		email = (EditText) findViewById(R.id.loginName);
		password = (EditText) findViewById(R.id.loginPass);

		loginBtn = (Button) findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Runnable r = new Runnable() {

	                @Override
	                public void run() {

	                    final User actualUser = Session.getInstance().getActualCommunication()
	                            .authenticationUser(email.getText().toString(), password.getText().toString());

	                    if (actualUser != null) {
	                        // Ha a nĂ©v Ă©s jelszĂł pĂˇros helyes
	                        Session.getInstance().setActualUser(actualUser);
	                        Date begin = new Date();
	                        Calendar c = Calendar.getInstance();
	                        c.setTime(begin);
	                        c.add(Calendar.DATE, 1);
	                        Date end = c.getTime();
	                        Session.getInstance().getActualCommunication().getMealListForUser(begin, end);
	                        Session.getInstance().getActualCommunication().setFoodPlan();
	                    }

	                    Activity a = MainActivity.this;
	                    a.runOnUiThread(new Runnable() {

	                        @Override
	                        public void run() {
	                            if (actualUser == null) {
	                                Session.getInstance().dismissProgressDialog();
	                                new ErrorToast(MainActivity.this, "Sikertelen bejelentkezés! Hibás felhasználónév vagy jelszó! Próbáld újra!").show();
	                            } else {
	                                Intent newIntent = new Intent(MainActivity.this, SecondActivity.class);

	                                startActivity(newIntent);
	                                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
	                                finish();
	                            }
	                            Session.getInstance().dismissProgressDialog();
	                        }
	                    });

	                }
	            };

	            Session.getInstance().progressDialog = ProgressDialog.show(MainActivity.this, "Kérlek várj", "Bejelentkezés folyamatban...",
	            		true, false);
	            new NetThread(MainActivity.this, r).start();

	            
			}
		});
		
		regBtn = (Button) findViewById(R.id.regBtn);
		regBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, RegistrationActivity.class);
				startActivity(i);
			}
		});
		
		loginWithoutRegBtn = (Button) findViewById(R.id.logWithoutRegBtn);
		loginWithoutRegBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SecondActivity.class);
				startActivity(i);
			}
		});
	}
}
