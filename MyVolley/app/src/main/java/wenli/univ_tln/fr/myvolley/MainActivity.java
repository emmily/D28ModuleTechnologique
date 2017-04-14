package wenli.univ_tln.fr.myvolley;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import wenli.univ_tln.fr.myvolley.entities.Student;
import wenli.univ_tln.fr.myvolley.entities.University;


public class MainActivity extends AppCompatActivity {

    Button bt_findAll;
    Button bt_findById;
    Button bt_add_new_student;
    Button btn_delete;
    Button btn_update;
    EditText et_stu_id;
    EditText et_delete;
    //final String url_base="http://192.168.0.13:8080/myapp/studentresource";
    final  static String url_base="http://10.21.141.126:8080/myapp/studentresource";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_findAll = (Button) findViewById(R.id.btn_findAll);
        bt_findById = (Button) findViewById(R.id.btn_findById);
        bt_add_new_student = (Button) findViewById(R.id.btn_add_student);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        et_stu_id = (EditText) findViewById(R.id.et_findById);
        et_delete = (EditText) findViewById(R.id.et_delete);
    }


    public void findAll(View view) {

        String url = url_base+"/all";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<Student> students = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Student student = new Student();
                                student.setFirstname(jsonObject.getString("firstname"));
                                student.setLastname(jsonObject.getString("lastname"));
                                student.setId(jsonObject.getInt("id"));
                                University university = new University();
                                university.setName(jsonObject.getJSONObject("university").getString("name"));
                                university.setUnivId(jsonObject.getJSONObject("university").getInt("univId"));
                                student.setUniversity(university);
                                students.add(student);
                                Log.d("students", students.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Intent intent = new Intent(MainActivity.this, FindAllActivity.class);
                        intent.putExtra("students", (Serializable) students);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);


    }

    public void findById(View view) {

        String url = url_base+"/student/";
        String id = et_stu_id.getText().toString();
        url = url + id;
        Log.d("Url", url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        List<Student> students = new ArrayList<>();
                        Student student = new Student();
                        University university = new University();
                        try {
                            student.setId(response.getInt("id"));
                            student.setFirstname(response.getString("firstname"));
                            student.setLastname(response.getString("lastname"));
                            university.setUnivId(response.getJSONObject("university").getInt("univId"));
                            university.setName(response.getJSONObject("university").getString("name"));
                            student.setUniversity(university);
                            students.add(student);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(MainActivity.this, FindByIdActivity.class);
                        intent.putExtra("students", (Serializable) students);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

    public void add_new_student(View view) {
        Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
        startActivity(intent);
    }

    public void deleteById(View view) {
        String stuId = et_delete.getText().toString();

        String url = url_base+"/student/" + stuId;
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        Log.d("response",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void update(View view){
        Intent intent = new Intent(MainActivity.this, UpdateStudentActivity.class);
        startActivity(intent);
    }
}
