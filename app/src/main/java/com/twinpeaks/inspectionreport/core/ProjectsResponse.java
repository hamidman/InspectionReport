package com.twinpeaks.inspectionreport.core;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mary on 18/05/2015.
 */
public class ProjectsResponse {
    public String ID;
    public String Name;
    public String StartDate;
    public String EndDate;
    public String Description;
    public String UsersOfThisProject;


    public ProjectsResponse(JSONObject obj) {
        try {
            this.ID = obj.getString("id");
            this.Name = obj.getString("name");
            this.StartDate = obj.getString("start_date");
            this.EndDate = obj.getString("end_date");
            this.Description = obj.getString("description");
            this.UsersOfThisProject = obj.getString("users");
        } catch (JSONException ex) {

        }
    }
}
