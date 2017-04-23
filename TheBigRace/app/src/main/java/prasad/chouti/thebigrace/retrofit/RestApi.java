

package prasad.chouti.thebigrace.retrofit;

import prasad.chouti.thebigrace.models.Contestants;

import retrofit2.http.GET;
import rx.Observable;

public interface RestApi {


    @GET("/mobile/runners.json")
    Observable<Contestants> getRaceContestants();



}
