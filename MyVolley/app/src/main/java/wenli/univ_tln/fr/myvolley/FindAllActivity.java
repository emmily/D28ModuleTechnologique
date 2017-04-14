package wenli.univ_tln.fr.myvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import java.util.List;

import wenli.univ_tln.fr.myvolley.adapter.StudentAdapter;
import wenli.univ_tln.fr.myvolley.entities.Student;


public class FindAllActivity extends AppCompatActivity {
    ListView listView;
    List<Student> students = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_all);

        listView = (ListView) findViewById(R.id.lv_findall);
        students = (List<Student>) getIntent().getSerializableExtra("students");
        Log.d("St1",students.toString());
        StudentAdapter studentAdapter = new StudentAdapter(FindAllActivity.this,R.layout.listview_item,students);
        listView.setAdapter(studentAdapter);
    }
}
