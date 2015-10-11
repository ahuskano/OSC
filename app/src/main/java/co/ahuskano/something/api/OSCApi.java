package co.ahuskano.something.api;

import co.ahuskano.something.models.Space;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ahuskano on 10.10.2015..
 */
public interface OSCApi {

    public static String SPACES="/spaces";
    public static String LOGIN="/login";
    public static String CHECKIN="/checkin";

    public static String API_PREFIX="api";
    public static String API_LOCATION="http://osijek.party/"+API_PREFIX;

    @GET(SPACES)
    void getSpaces(Callback<SpacesResponse> callback);


    @GET(SPACES+"/{id}")
    void getSpace(@Path("id") String id, Callback<SpaceResponse> callback);

    @GET(SPACES)
    void getTopSpaces(@Query("long") String lng,@Query("lat")String lat, Callback<SpacesResponse> callback);

    @POST(LOGIN)
    void logIn(@Body LogInRequest body, Callback<LogInResponse> callback);

    @POST(CHECKIN)
    void checkIn(@Query("space_id") String id, @Header("x-api-key") String token,Callback<CheckInReponse> callback);

}
