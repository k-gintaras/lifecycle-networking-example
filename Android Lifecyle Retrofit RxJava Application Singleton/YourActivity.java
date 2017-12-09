package company.app.restpackagefolder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import company.app.restpackagefolder.NetworkAPI;
import company.app.restpackagefolder.NetworkService;
import company.app.restpackagefolder.RxApplication;
import company.app.restpackagefolder.YourRequestObject;
import company.app.restpackagefolder.YourResponseObject;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class YourActivity extends AppCompatActivity {
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		 *	 remove previous responses and allow new requests, i.e. when you return to main activity
		 *	 service.clearCache();
		 */

        //initiate networking classes
        RxApplication app = (RxApplication) getApplication();
        NetworkService service = app.getNetworkService();
        NetworkAPI api = service.getAPI();

        //get your request that you want to send, i.e. JSON, XML, text
        String request = getRequestMessage();

        //start networking
        Observable<WhyWhenResponseObject> unpreparedObservable = api.getWhyWhenResponseObjectObservable(request);
        Observable<WhyWhenResponseObject> preparedObservable = (Observable<WhyWhenResponseObject>) service.getPreparedObservable(unpreparedObservable, 9999);

        subscription = preparedObservable.subscribe(new Observer<WhyWhenResponseObject>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                handleError(e);
            }

            @Override
            public void onNext(WhyWhenResponseObject response) {
                handleResponse(response);
            }
        });
    }

    private void handleResponse(WhyWhenResponseObject response) {
        //i.e. display, process
    }

    private void handleError(Throwable e) {
        //i.e. restart, exit
    }

    private String getRequestMessage() {
		//if you just want to send a key and use same json format
		//you could have different object for different request
        YourRequestObject requestObject = new YourRequestObject();
        requestObject.setUserKey("exampleUserKey");
        return requestObject.toJSON();
    }

    @Override
    protected void onPause() {
        super.onPause();
        rxUnSubscribe();
    }

    /*
    * prevents leaking when activity or fragment is destroyed
    * */
    public void rxUnSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}


