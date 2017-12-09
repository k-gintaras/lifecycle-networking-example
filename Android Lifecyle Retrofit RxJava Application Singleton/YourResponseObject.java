package company.app.restpackagefolder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *	if your server response is JSON
 *  you could use http://www.jsonschema2pojo.org/
 *  to create this class
 */
public class YourResponseObject {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("userKey")
    @Expose
    private String  userKey;
    @SerializedName("gameKey")
    @Expose
    private String  gameKey;
    @SerializedName("joined")
    @Expose
    private Integer joined;
    @SerializedName("completed")
    @Expose
    private Integer completed;
    @SerializedName("story")
    @Expose
    private String  story;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

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

    public Integer getJoined() {
        return joined;
    }

    public void setJoined(Integer joined) {
        this.joined = joined;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
