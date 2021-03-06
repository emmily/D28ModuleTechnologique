package wenli.univ_tln.fr.myvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import wenli.univ_tln.fr.myvolley.adapter.StudentAdapter;
import wenli.univ_tln.fr.myvolley.entities.Student;

/**
 * Created by wenlixing on 03/04/2017.
 */
public class FindByIdActivity extends AppCompatActivity {
    ListView listView;
    List<Student> students = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_id);
        listView = (ListView) findViewById(R.id.lv_findbyid);
        students = (List<Student>) getIntent().getSerializableExtra("students");
        Log.d("St1",students.toString());
        StudentAdapter studentAdapter = new StudentAdapter(FindByIdActivity.this,R.layout.listview_item,students);
        listView.setAdapter(studentAdapter);
    }
}
