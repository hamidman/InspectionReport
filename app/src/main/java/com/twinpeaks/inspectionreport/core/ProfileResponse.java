package com.twinpeaks.inspectionreport.core;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mary on 15/03/2015.
 */
public class ProfileResponse {
    public String Name;
    public String Email;
    public PhoneList Phone;

    public class PhoneList {
        public String Home;
        public String Mobile;

        PhoneList(JSONObject obj) {
            try {
                this.Home = obj.getString("home");
                this.Mobile = obj.getString("mobile");
            } catch (JSONException ex) {

            }
        }
    }

    ProfileResponse(JSONObject obj) {
        try {
            this.Name = obj.getString("name");
            this.Email = obj.getString("email");
            this.Phone = new PhoneList(obj.getJSONObject("phone"));
        } catch (JSONException ex) {

        }
    }
}
