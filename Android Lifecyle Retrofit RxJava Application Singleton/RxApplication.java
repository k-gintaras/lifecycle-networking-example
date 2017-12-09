package company.app.restpackagefolder;

import android.app.Application;

public class RxApplication extends Application {
    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();
        networkService = new NetworkService("your_server.com/apis/this_api/");
    }

    public NetworkService getNetworkService(){
        return networkService;
    }
}
