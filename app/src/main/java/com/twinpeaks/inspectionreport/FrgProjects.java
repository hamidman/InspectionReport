package com.twinpeaks.inspectionreport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.twinpeaks.inspectionreport.Adapters.ProjectsAdapter;

import org.json.JSONArray;

public class FrgProjects extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_projects, null);

        ListView listProjects;

        JSONArray ja = ((MainActivity) getActivity()).getProjectsJsonArray();

        final String[] itemname ={
                "Safari",
                "Camera"

        };

        ProjectsAdapter adapter=new ProjectsAdapter(getActivity(), itemname, ja);
        listProjects=(ListView) v.findViewById(R.id.list_projects);
        listProjects.setAdapter(adapter);

        listProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(v.getContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
    }

}
