package co.ahuskano.something.api;

import co.ahuskano.something.models.Space;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ahuskano on 10.10.2015..
 */
public interface OSCApi {

    public static String SPACES="/spaces";

    public static String API_PREFIX="api";
    public static String API_LOCATION="http://osijek.party/"+API_PREFIX;

    @GET(SPACES)
    void getSpaces(Callback<SpacesResponse> callback);


    @GET(SPACES+"/{id}")
    void getSpace(@Path("id") String id, Callback<SpaceResponse> callback);

}
