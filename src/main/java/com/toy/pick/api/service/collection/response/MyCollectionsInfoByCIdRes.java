package com.toy.pick.api.service.collection.response;

import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.place.Place;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.pick.domain.place.QPlace.place;

@Getter
@ToString
public class MyCollectionsInfoByCIdRes {
    private final Long id;
    private final String title;
    private final String memo;
    private final List<test> places;

    @Builder
    public MyCollectionsInfoByCIdRes(Collection collection) {
        this.id = collection.getId();
        this.title = collection.getTitle();
        this.memo = collection.getMemo();

        List<test> collect = collection.getCollectionPlaces().stream().map(cp -> {
            String memo1 = cp.getMemo();
            System.out.println("memo1 : " + memo1);
            String url = cp.getUrl();
            System.out.println("url : " + url);
            return new test(cp.getPlace(), memo1, url);
        }).collect(Collectors.toList());
        this.places = collect;


    }

    public static MyCollectionsInfoByCIdRes of(Collection collection){
        return new MyCollectionsInfoByCIdRes(collection);
    }

    @Getter
    class test {
        private Long id;
        private String name;
        private String category;
        private String address;
        private String detailAddress;
        private String x;
        private String y;
        private String memo;
        private String url;

        public test(Place place, String memo, String url) {
            this.id = place.getId();
            this.name = place.getName();
            this.category = place.getCategory().getText();
            this.address = place.getAddress();
            this.detailAddress = place.getDetailAddress();
            this.x = place.getX();
            this.y = place.getY();
            this.memo = memo;
            this.url = url;
        }
    }


}
