package assistant.genuinecoder.s_assistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

import assistant.genuinecoder.s_assistant.main.database.DatabaseHandler;

public class LaunchActivity extends AppCompatActivity {

    private Spinner spinner;
    public static DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        handler = new DatabaseHandler(this);
        spinner = findViewById(R.id.spinner);

        List<String> categories = new ArrayList<>();
        categories.add(0, "Choose Category");
        categories.add("Student");
        categories.add("Teacher");


        //Style and populate the spinner
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categories);

        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Choose Category"))
                {
                    //do nothing
                }
                else
                {
                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();

                    //show selected spinner item
                    // Toast.makeText(parent.getContext(), "Selected: " +item, Toast.LENGTH_SHORT).show();
                    if(parent.getItemAtPosition(position).equals("Teacher"))
                    {

                        Intent intent = new Intent(LaunchActivity.this,TeacherLogin.class);
                        startActivity(intent);
                    }
                    else if(parent.getItemAtPosition(position).equals("Student"))
                    {

                        Intent intent = new Intent(LaunchActivity.this,StudLogin.class);
                        startActivity(intent);
                    }
                    //anything else you want to do on item selection do here
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // TODO Auto-generated method stub
            }
        });
    }
}
