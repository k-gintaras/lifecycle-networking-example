package company.app.restpackagefolder;

import android.support.annotation.NonNull;
import android.util.LruCache;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * WhyWhen
 * Thanks to (CLINTON TEEGARDEN, 2016) at https://www.captechconsulting.com/blogs/a-mvp-approach-to-lifecycle-safe-requests-with-retrofit-20-and-rxjava
 * Modified by Ubaby on 14:43 06/12/2017
 * https://www.linkedin.com/in/k-gintaras/
 */

public class NetworkService {
    private NetworkAPI networkAPI;
    /*
    * LruCache<KeyToFindResponses, Observable<?>>
    *     if all responses the same type, but you want to keep them, you might not want to use
    * LruCache<Class<?>, Observable<?>>
    *     because new addition will replace previous one, because it is not unique
    * */
    private LruCache<Integer, Observable<?>> apiObservablesByInteger = new LruCache<>(10);
//    private LruCache<Class<?>, Observable<?>> apiObservables          = new LruCache<>(10);

    public NetworkService(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        networkAPI = retrofit.create(NetworkAPI.class);
    }

    public NetworkAPI getAPI() {
        return networkAPI;
    }

    /*
     * get observable from cache or new one if not available
     */
    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable, Integer responseID) {
        Observable<?> preparedObservable = getPreviousPreparedObservable(responseID);
        if (preparedObservable == null) {
            preparedObservable = getNewPreparedObservable(unPreparedObservable);
            preparedObservable = setToCache(responseID, preparedObservable);
        }
        return preparedObservable;
    }

    @NonNull
    private Observable<?> setToCache(Integer responseID, Observable<?> preparedObservable) {
        preparedObservable = preparedObservable.cache();
        apiObservablesByInteger.put(responseID, preparedObservable);
        return preparedObservable;
    }

    /*
     * fresh request to server
     */
    public Observable<?> getNewPreparedObservable(Observable<?> unPreparedObservable) {
        return unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());/*.newThread()>>>.mainThread() solves "runOnUiThread problems"*/
    }

    /*
     * attempt to get previously received request
     * can return null
     */
    public Observable<?> getPreviousPreparedObservable(Integer responseID) {
        return apiObservablesByInteger.get(responseID);
    }

    /*
     * removes all previous responses, in case you want to make new fresh requests, for example, main activity
     */
    public void clearCache() {
        apiObservablesByInteger.evictAll();
    }
}
