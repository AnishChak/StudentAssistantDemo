package assistant.genuinecoder.s_assistant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import assistant.genuinecoder.s_assistant.main.AppBase;
import assistant.genuinecoder.s_assistant.main.database.DatabaseHandler;

public class TeacherLogin extends AppCompatActivity {
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        TextView textView;
        textView = (TextView)(findViewById(R.id.register));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherLogin.this,TeacherReg.class);
                startActivity(intent);
            }
        });
        Button btn = (Button) findViewById(R.id.btn_tlogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractLogin(v);
            }
        });
    }
    public void extractLogin(View v)
    {
        EditText tid = (EditText) findViewById(R.id.login_tid);
        EditText pass = (EditText) findViewById(R.id.login_tpassword);
        String qu = "SELECT * FROM TEACHER WHERE TEACHER_ID = '" + tid.getText().toString() + "' AND PASSWORD = '" + pass.getText().toString() + "';";
        db = new DatabaseHandler(this);
        Cursor cursor = db.execQuery(qu);
        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(getBaseContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
        }
        else if(cursor!=null)
        {
            int index;
            String n,id;
            cursor.moveToFirst();
            //index =cursor.getColumnIndex(1);
           id = cursor.getString(1);
//            index = cursor.getColumnIndex("name");
            n = cursor.getString(0);

            Toast.makeText(getBaseContext(),"Login Success " + n,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(TeacherLogin.this, AppBase.class);
            intent.putExtra("name",n);
            intent.putExtra("id",id);
            startActivity(intent);
        }

    }
}
