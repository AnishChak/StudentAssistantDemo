package assistant.genuinecoder.s_assistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import assistant.genuinecoder.s_assistant.main.AppBase;
import assistant.genuinecoder.s_assistant.main.database.DatabaseHandler;

//import assistant.genuinecoder.s_assistant.R;


public class TeacherReg extends AppCompatActivity {


    Activity activity = this;
    Spinner spinner;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_reg);

        db = new DatabaseHandler(this);
//        spinner = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);// AppBase.divisions);
//        spinner.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.buttonTReg);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(v);
            }
        });
    }


    public void saveToDatabase(View view) {
        EditText name = (EditText) findViewById(R.id.edit_t_name);
        EditText t_id = (EditText) findViewById(R.id.t_id);
        EditText dept = (EditText) findViewById(R.id.t_dept);
        EditText contact = (EditText) findViewById(R.id.t_contact);
        EditText pass = (EditText) findViewById(R.id.treg_password);
        //String classSelected = spinner.getSelectedItem().toString();

        if (name.getText().length() < 2 || dept.getText().length() == 0 || t_id.getText().length() < 2 ||
                contact.getText().length() < 2) {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Invalid");
            alert.setMessage("Insufficient Data");
            alert.setPositiveButton("OK", null);
            alert.show();
            return;
        }

        String qu = "INSERT INTO TEACHER(name,teacher_id,dept,contact,password) VALUES('" + name.getText().toString() + "'," +
                "'" + t_id.getText().toString() + "'," +
                "'" + "CSE" + "'," +
                "'" + contact.getText().toString() + "'," +
                "'" + pass.getText().toString() + "');";
        Log.d("Teacher Reg", qu);
        db.execAction(qu);
        qu = "SELECT * FROM TEACHER WHERE teacher_id = '" + t_id.getText().toString() + "';";
        Log.d("Student Reg", qu);
        if (db.execQuery(qu) != null) {
            Toast.makeText(getBaseContext(), "Teacher Added", Toast.LENGTH_LONG).show();
           this.finish();
//        Intent intent = new Intent(TeacherReg.this,TeacherLogin.class);
//        startActivity(intent);
        }
    }
}
