package assistant.genuinecoder.s_assistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import assistant.genuinecoder.s_assistant.main.database.DatabaseHandler;

public class StudReg extends AppCompatActivity {


    Activity activity = this;
    Spinner spinner;
    DatabaseHandler db;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_reg);
        ll = (LinearLayout)findViewById(R.id.llsreg);
        ll.setBackgroundResource(R.drawable.stud_bg);
        db = new DatabaseHandler(this);
//        spinner = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);// AppBase.divisions);
//        spinner.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.buttonSReg);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(v);
            }
        });
    }


    public void saveToDatabase(View view) {
        EditText name = (EditText) findViewById(R.id.edit_s_name);
        EditText sid = (EditText) findViewById(R.id.student_id);
//        EditText dept = (EditText) findViewById(R.id.t_dept);
        EditText email = (EditText) findViewById(R.id.s_email);
        EditText pass = (EditText) findViewById(R.id.sreg_password);
        //String classSelected = spinner.getSelectedItem().toString();

        if (name.getText().length() < 2 || sid.getText().length() < 2 ||
                email.getText().length() < 2) {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        String qu = "INSERT INTO STUDENT(name,sid,email,password) VALUES('" + name.getText().toString() + "'," +
                "'" + sid.getText().toString() + "'," +
                "'" + email.getText().toString() + "'," +
                "'" + pass.getText().toString() + "');";
        Log.d("Student Reg", qu);
        db.execAction(qu);
        qu = "SELECT * FROM STUDENT WHERE sid = '" + sid.getText().toString() + "';";
        Log.d("Student Reg", qu);
        if (db.execQuery(qu) != null) {
            Toast.makeText(getBaseContext(), "Student Added", Toast.LENGTH_LONG).show();
            this.finish();
//        Intent intent = new Intent(TeacherReg.this,TeacherLogin.class);
//        startActivity(intent);
        }
    }
}