package wenli.univ_tln.fr.myvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import wenli.univ_tln.fr.myvolley.entities.University;

public class AddStudentActivity extends AppCompatActivity {
    Button bt_add;
    EditText et_firstname;
    EditText et_lastname;
    EditText et_id;
    EditText et_univ_id;
    EditText et_univ_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        bt_add = (Button) findViewById(R.id.add);
        et_firstname = (EditText) findViewById(R.id.et_firstname_add);
        et_lastname = (EditText) findViewById(R.id.et_lastname_add);
        et_id = (EditText) findViewById(R.id.et_stu_id_add);
        et_univ_id = (EditText) findViewById(R.id.et_univId_add);
        et_univ_name = (EditText) findViewById(R.id.et_univ_name_add);
    }

    public void add(View view) {
        int id = Integer.parseInt(et_id.getText().toString());
        String firstname = et_firstname.getText().toString();
        String lastname = et_lastname.getText().toString();
        int univId = Integer.parseInt(et_univ_id.getText().toString());
        String univName = et_univ_name.getText().toString();
        JSONObject studentJson = new JSONObject();
        try {
            studentJson.put("id", id);
            studentJson.put("firstname", firstname);
            studentJson.put("lastname", lastname);
            JSONObject univJson = new JSONObject();
            univJson.put("univId", univId);
            univJson.put("name", univName);
            studentJson.put("university", univJson);
            Log.d("Tag", studentJson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = studentJson.toString();
        String url = MainActivity.url_base+"/create";
/*        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, studentJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });*/
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            public String getBodyContentType() {
                return String.format("application/json; charset=utf-8");
            }

            @Override
            public byte[] getBody() throws AuthFailureError {

                try {

                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
