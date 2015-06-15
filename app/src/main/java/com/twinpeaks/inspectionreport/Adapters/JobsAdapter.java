package com.twinpeaks.inspectionreport.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.twinpeaks.inspectionreport.MainActivity;
import com.twinpeaks.inspectionreport.R;
import com.twinpeaks.inspectionreport.core.JobsResponse;
import com.twinpeaks.inspectionreport.core.ProjectsResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Mary on 21/05/2015.
 */
public class JobsAdapter  extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    private final JSONArray jaJobs;
    private TextView txtTitle, txtFilename;
    private Button btnGetFile, btnOpenFile;

    public JobsAdapter(Activity context, String[] itemname, JSONArray ja) {
        super(context, R.layout.row_job, itemname);

        this.context=context;
        this.itemname=itemname;
        this.jaJobs = ja;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row_job, null, true);

        txtTitle = (TextView) rowView.findViewById(R.id.text_job_title);
        txtFilename = (TextView) rowView.findViewById(R.id.text_job_filename);
        btnGetFile = (Button) rowView.findViewById(R.id.button_get_file);
        btnOpenFile = (Button) rowView.findViewById(R.id.button_open_file);

        btnGetFile.setTag(position);
        btnOpenFile.setTag(position);

        JobsResponse jr;
        try {
            jr = new JobsResponse((JSONObject) jaJobs.get(position));
            txtTitle.setText( jr.Title );
            txtFilename.setText( jr.Filename);
        }
        catch (Exception ex) {

        }


        btnGetFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Get " + v.getTag(), Toast.LENGTH_SHORT).show();
                //Download("http://192.168.0.162:8080/GetFile:a.xls", "a.xls");
                try {
                    JobsResponse jr = new JobsResponse((JSONObject) jaJobs.get(Integer.parseInt(v.getTag().toString())));
                    ((MainActivity) context).Download("http://192.168.0.162:8080/GetFile:" + jr.Filename, jr.Filename);
                } catch (Exception ex) {

                }
            }
        });


        btnOpenFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Open " + v.getTag(), Toast.LENGTH_SHORT).show();
            }
        });


        return rowView;

    }
}
