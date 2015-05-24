package com.twinpeaks.inspectionreport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.twinpeaks.inspectionreport.Adapters.JobsAdapter;
import com.twinpeaks.inspectionreport.Adapters.ProjectsAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class FrgJobs extends Fragment {//} implements AdapterView.OnItemClickListener {

    private TextView txtSelectedProject;
    private JSONArray ja_projects, ja_jobs;
    private int projectIndex;
    private String selectedProjectName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jobs, null);

        ListView listJobs;

        try {
            ja_projects = ((MainActivity) getActivity()).getProjectsJsonArray();
            projectIndex = ((MainActivity) getActivity()).getSelectedProjectIndex();
            txtSelectedProject = (TextView) v.findViewById(R.id.text_selected_project);
            selectedProjectName = ((JSONObject) ja_projects.get(projectIndex)).getString("name");
            txtSelectedProject.setText(selectedProjectName);

            ja_jobs = ((MainActivity) getActivity()).getJobsJsonArray();
            removeIrrelevantJobs();

            final String[] itemname = new String[ja_jobs.length()];
            JobsAdapter adapter=new JobsAdapter(getActivity(), itemname, ja_jobs);
            listJobs=(ListView) v.findViewById(R.id.list_jobs);
            listJobs.setAdapter(adapter);

            //listJobs.setOnItemClickListener(this);
            /*new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    try {
                        int i = 0;
                        int j = i + 1;
                        int c = 0;
                        Toast.makeText(getActivity(), "here", Toast.LENGTH_SHORT).show();
                        //
                        //String Slecteditem = ((JSONObject) ja.get(position)).getString("name"); //itemname[+position];
                        //Toast.makeText(v.getContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        //((MainActivity) getActivity()).setSelectedProjectIndex(position);
                        //((MainActivity) getActivity()).changeToJobs();
                        //
                    } catch (Exception ex) {
                        Log.e("Error", "Error in showing selected item.");
                    }
                }
            });  */


        } catch (Exception ex) {

        }
        return v;
    }

    public void removeIrrelevantJobs() {
        try {
            int j = 0;
            for (int i = 0; i < ja_jobs.length(); i++) {
                if (((JSONObject) ja_jobs.get(i)).get("projects").toString().indexOf(selectedProjectName) < 0) {
                    ja_jobs = ((MainActivity) getActivity()).RemoveJSONArray(ja_jobs, j);
                } else {
                    j++;
                }
            }
        } catch (Exception ex) {

        }
    }

/*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "id " + view.getId(), Toast.LENGTH_SHORT).show();
    }  */
}
