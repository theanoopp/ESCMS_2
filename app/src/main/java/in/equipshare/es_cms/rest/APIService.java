package in.equipshare.es_cms.rest;

import java.util.Map;

import in.equipshare.es_cms.model.RFQ;
import in.equipshare.es_cms.model.Result;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {


    @FormUrlEncoded
    @POST("/signup")
    Call<Result> signup(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/login")
    Call<Result>login(@Field(value="username",encoded = true) String username, @Field(value="password",encoded = true) String password);


    @POST("/rfq")
    Call<Result> rfqSet(@Body RFQ order);


}
