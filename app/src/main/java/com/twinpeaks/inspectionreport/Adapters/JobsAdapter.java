package com.twinpeaks.inspectionreport.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    public JobsAdapter(Activity context, String[] itemname, JSONArray ja) {
        super(context, R.layout.row_job, itemname);

        this.context=context;
        this.itemname=itemname;
        this.jaJobs = ja;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row_job, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.text_job_title);
        TextView txtFilename = (TextView) rowView.findViewById(R.id.text_job_filename);


        JobsResponse jr;
        try {
            jr = new JobsResponse((JSONObject) jaJobs.get(position));
            txtTitle.setText( jr.Title );
            txtFilename.setText( jr.Filename);
        }
        catch (Exception ex) {

        }

        return rowView;

    }
}
