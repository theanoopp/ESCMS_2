package in.equipshare.es_cms.utils;

import in.equipshare.es_cms.rest.APIService;
import in.equipshare.es_cms.rest.RetrofitClient;

public class ApiUtils {


    private ApiUtils() {}

    public static final String BASE_URL = "http://3c26e8fd.ngrok.io/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
