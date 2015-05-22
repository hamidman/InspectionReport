package com.twinpeaks.inspectionreport.core;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mary on 21/05/2015.
 */
public class JobsResponse {

    public String ID;
    public String Title;
    public String Filename;
    public String Filepath;
    public String Projects;

    public JobsResponse(JSONObject obj) {
        try {
            this.ID = obj.getString("id");
            this.Title = obj.getString("title");
            this.Filename = obj.getString("filename");
            this.Filepath = obj.getString("filepath");
            this.Projects = obj.getString("projects");
        } catch (JSONException ex) {

        }
    }
}
