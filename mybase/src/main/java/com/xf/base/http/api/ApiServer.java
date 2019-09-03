package com.xf.base.http.api;




import com.xf.base.BaseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {


    @GET("/")
    Observable<BaseModel> getBaiDu();

}
