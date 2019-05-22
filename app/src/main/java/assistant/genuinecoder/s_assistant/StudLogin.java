package assistant.genuinecoder.s_assistant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import assistant.genuinecoder.s_assistant.main.AppBase;
import assistant.genuinecoder.s_assistant.main.database.DatabaseHandler;

public class StudLogin extends AppCompatActivity {

    DatabaseHandler db;
    private LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_login);
        ll = (LinearLayout)findViewById(R.id.llstud);
        ll.setBackgroundResource(R.drawable.stud_bg);
        TextView textView;
        textView = (TextView)(findViewById(R.id.sregister));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudLogin.this,StudReg.class);
                startActivity(intent);
            }
        });
        Button btn = (Button) findViewById(R.id.btn_slogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractLogin(v);
            }
        });
    }
    public void extractLogin(View v)
    {
        EditText sid = (EditText) findViewById(R.id.login_sid);
        EditText pass = (EditText) findViewById(R.id.login_spassword);
        String qu = "SELECT * FROM STUDENT WHERE SID = '" + sid.getText().toString() + "' AND PASSWORD = '" + pass.getText().toString() + "';";
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

            sid.setText("");
            pass.setText("");
            Intent intent = new Intent(StudLogin.this, SAppBase.class);
            intent.putExtra("name",n);
            intent.putExtra("id",id);
            startActivity(intent);
        }

    }
}
