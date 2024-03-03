package com.toy.pick;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DistanceTest {

    @Test
    @DisplayName("")
    void distanceTest() {
        double lat1 = 37.5665; // A 지점 위도
        double lon1 = 126.9780; // A 지점 경도
        double lat2 = 35.1796; // B 지점 위도
        double lon2 = 129.0756; // B 지점 경도


        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        System.out.println("A 지점과 B 지점 사이의 거리: " + distance + " km");

    }
    public static final double RADIUS_OF_EARTH = 6371; // 지구 반지름 (단위: km)

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 위도 및 경도를 라디안 값으로 변환
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        // Haversine 공식 계산
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 거리 계산
        double distance = RADIUS_OF_EARTH * c;

        return distance;
    }




}
