package com.twinpeaks.inspectionreport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.twinpeaks.inspectionreport.Adapters.JobsAdapter;
import com.twinpeaks.inspectionreport.Adapters.ProjectsAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class FrgJobs extends Fragment implements OnClickListener {

    private TextView txtSelectedProject;
    private JSONArray ja;
    private int projectIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jobs, null);

        ListView listJobs;

        try {
            ja = ((MainActivity) getActivity()).getProjectsJsonArray();
            projectIndex = ((MainActivity) getActivity()).getSelectedProjectIndex();

            txtSelectedProject = (TextView) v.findViewById(R.id.text_selected_project);
            txtSelectedProject.setText(((JSONObject) ja.get(projectIndex)).getString("name"));

            final String[] itemname = new String[ja.length()];
            JobsAdapter adapter=new JobsAdapter(getActivity(), itemname, ja);
            listJobs=(ListView) v.findViewById(R.id.list_jobs);
            listJobs.setAdapter(adapter);


        } catch (Exception ex) {

        }
        return v;
    }

    @Override
    public void onClick(View v) {
    }

}
