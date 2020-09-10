package com.example.learderboard.services;

import com.example.learderboard.Leader;
import com.example.learderboard.Owner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface SubmitService {
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
  Call<Owner> creatOwner(@Field("entry.1877115667")String firstName,
                         @Field("entry.2006916086")String lastName,
                         @Field("entry.1824927963")String emailAddress,
                         @Field("entry.284483984")String projectLink
                         );
}
