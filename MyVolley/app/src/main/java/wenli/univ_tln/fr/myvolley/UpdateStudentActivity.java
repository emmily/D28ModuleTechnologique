package wenli.univ_tln.fr.myvolley;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenlixing on 04/04/2017.
 */

public class UpdateStudentActivity extends AppCompatActivity {
    EditText stuId_update;
    EditText firstname_update;
    Button updateStu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        stuId_update = (EditText) findViewById(R.id.et_stuId_update);
        firstname_update = (EditText) findViewById(R.id.et_stu_firstname_update);
        updateStu = (Button)findViewById(R.id.btn_update_student);
    }

    public void updateStudent(View view){
        String url = MainActivity.url_base+"/update";
        final String firstname = firstname_update.getText().toString();
        final int stuId = Integer.parseInt(stuId_update.getText().toString());
        url = url + "?id="+stuId+"&&"+"firstname="+firstname;
        Log.d("url",url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
