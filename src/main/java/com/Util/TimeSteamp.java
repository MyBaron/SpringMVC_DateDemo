package com.Util;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/11/1.
 */
public class TimeSteamp implements Converter<String,Timestamp> {

    public Timestamp convert(String source) {
        /**
         * 注意Timestamp 只能是yyyy-MM-dd HH:mm:ss 这种格式
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new Timestamp(format.parse(source).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
