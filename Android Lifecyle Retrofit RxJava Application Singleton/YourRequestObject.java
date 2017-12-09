package company.app.restpackagefolder;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YourRequestObject {
    public YourRequestObject() {
    }

    @SerializedName("userKey")
    @Expose
    private String userKey;
    @SerializedName("gameKey")
    @Expose
    private String gameKey;
    @SerializedName("story")
    @Expose
    private String story;

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String toJSON() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}