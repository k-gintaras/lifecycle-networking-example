package company.app.restpackagefolder;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface NetworkAPI {
    @FormUrlEncoded
    @POST("api_url.php")
    Observable<YourResponseObject> getDataFromPOSTRequest(@Field("phpPostKey") String phpPostValue);
}
