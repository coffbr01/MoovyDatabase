package com.sdg.moovydatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Moovy implements Serializable {
    private String posterThumbnail;
    private String title;
    private String synopsis;
    private String posterProfile;
    private String posterDetailed;
    private String posterOriginal;

    public static Moovy parse(JSONObject movie) {
        Moovy moovy = new Moovy();
        try {
            JSONObject posters = movie.getJSONObject("posters");
            if (posters != null) {
                moovy.setPosterThumbnail(posters.getString("thumbnail"));
                moovy.setPosterProfile(posters.getString("profile"));
                moovy.setPosterDetailed(posters.getString("detailed"));
                moovy.setPosterOriginal(posters.getString("original"));
            }
            moovy.setTitle(movie.getString("title"));
            moovy.setSynopsis(movie.getString("synopsis"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moovy;
    }

    public String getPosterThumbnail() {
        return posterThumbnail;
    }

    public void setPosterThumbnail(String posterThumbnail) {
        this.posterThumbnail = posterThumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPosterProfile() {
        return posterProfile;
    }

    public void setPosterProfile(String posterProfile) {
        this.posterProfile = posterProfile;
    }

    public String getPosterDetailed() {
        return posterDetailed;
    }

    public void setPosterDetailed(String posterDetailed) {
        this.posterDetailed = posterDetailed;
    }

    public String getPosterOriginal() {
        return posterOriginal;
    }

    public void setPosterOriginal(String posterOriginal) {
        this.posterOriginal = posterOriginal;
    }
}
