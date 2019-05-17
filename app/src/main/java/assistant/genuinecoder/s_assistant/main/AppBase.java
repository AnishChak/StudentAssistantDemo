package assistant.genuinecoder.s_assistant.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import assistant.genuinecoder.s_assistant.R;
import assistant.genuinecoder.s_assistant.main.components.About;
import assistant.genuinecoder.s_assistant.main.components.GridAdapter;
import assistant.genuinecoder.s_assistant.main.components.SettingsActivity;
import assistant.genuinecoder.s_assistant.main.database.DatabaseHandler;

public class AppBase extends AppCompatActivity {

    public static ArrayList<String> divisions;
    public static DatabaseHandler handler;
    public static Activity activity;
    ArrayList<String> basicFields;
    GridAdapter adapter;
    GridView gridView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mai_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        basicFields = new ArrayList<>();
        handler = new DatabaseHandler(this);
        activity = this;

        getSupportActionBar().show();
        divisions = new ArrayList<>();
        divisions.add("DBMS");
        divisions.add("Operating System");
        divisions.add("Computer Networking");
        divisions.add("Data Analysis and Algorithms");
        divisions.add("Discreet Mathematics");
//        divisions.add("S6 COMPUTER SCIENCE");
//        divisions.add("S7 COMPUTER SCIENCE");
        gridView = (GridView) findViewById(R.id.grid);
        basicFields.add("ATTENDANCE");
        basicFields.add("SCHEDULER");
//        basicFields.add("NOTES");
        basicFields.add("PROFILE");
//        basicFields.add("CGPA CALCULATOR");
        adapter = new GridAdapter(this, basicFields);
        gridView.setAdapter(adapter);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String id = intent.getExtras().getString("id");
        TextView tn = (TextView)findViewById(R.id.display_tname);
        TextView tid = (TextView)findViewById(R.id.display_tid);
        tn.setText(name);
        tid.setText(id);

        Button btn = (Button)findViewById(R.id.btn_tlogout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void loadSettings(MenuItem item) {
        Intent launchIntent = new Intent(this, SettingsActivity.class);
        startActivity(launchIntent);
    }

    public void loadAbout(MenuItem item) {
        Intent launchIntent = new Intent(this, About.class);
        startActivity(launchIntent);
    }
}
