package com.xf.base.http.api;

import com.xf.base.BaseModel;

public interface DataSource {

    void getBaiDu(BaseObserver<BaseModel> observer);

}
