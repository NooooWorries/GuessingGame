package com.czxbnb.guessinggame.network.converter

import android.widget.Toast
import com.czxbnb.guessinggame.base.BaseData
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonToken
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class BaseDataResponseBodyConverter<T>(private val converter: Converter<ResponseBody, BaseData<T>>) :
    Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(responseBody: ResponseBody): T? {
        val response = converter.convert(responseBody)
        // We can add a interceptor here
        // Do something when data arrives
        return response.data
    }
}