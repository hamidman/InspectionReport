package com.twinpeaks.inspectionreport.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twinpeaks.inspectionreport.R;
import com.twinpeaks.inspectionreport.core.ProjectsResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mary on 18/05/2015.
 */
public class ProjectsAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final JSONArray jaProjects;

    public ProjectsAdapter(Activity context, String[] itemname, JSONArray ja) {
        super(context, R.layout.row_project, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.jaProjects = ja;
     }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row_project, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.text_project_name);
        TextView extratxt = (TextView) rowView.findViewById(R.id.text_project_description);

        ProjectsResponse pr;
        try {
            pr = new ProjectsResponse((JSONObject) jaProjects.get(position));
            txtTitle.setText( pr.Name );//itemname[position]);
        }
        catch (Exception ex) {

        }

        extratxt.setText("Description "+itemname[position]);
        return rowView;

    };
}
