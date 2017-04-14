package wenli.univ_tln.fr.myvolley.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wenli.univ_tln.fr.myvolley.R;
import wenli.univ_tln.fr.myvolley.entities.Student;

/**
 * Created by wenlixing on 03/04/2017.
 */

public class StudentAdapter extends ArrayAdapter<Student> {
    private int resourceId;
    public StudentAdapter(Context context, int textViewResourceId, List<Student> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView id = (TextView) view.findViewById(R.id.tv_stu_id);
        TextView firstname = (TextView) view.findViewById(R.id.tv_firstname);
        TextView lastname = (TextView) view.findViewById(R.id.tv_lastname);
        TextView univ = (TextView) view.findViewById(R.id.tv_univ);

        id.setText(String.valueOf(student.getId()));
        firstname.setText(student.getFirstname());
        lastname.setText(student.getLastname());
        univ.setText(student.getUniversity().getName());
        return view;
    }
}
