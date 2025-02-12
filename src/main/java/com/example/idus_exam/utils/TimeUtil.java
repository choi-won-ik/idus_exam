package com.example.idus_exam.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public static ZonedDateTime timeChange(String time) {

        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

        // LocalDateTime을 ZonedDateTime으로 변환 (예: Asia/Seoul 기준)
        return localDateTime.atZone(ZoneId.of("Asia/Seoul"));
    }
}
