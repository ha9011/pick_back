package com.toy.pick.api.service.place.response;

import com.toy.pick.domain.place.Place;
import lombok.Getter;

@Getter
public class SavePlaceWithImageRes {
    private final Long placeId;

    public SavePlaceWithImageRes(Long placeId) {
        this.placeId = placeId;
    }

    public static SavePlaceWithImageRes of(Place place) {

        return new SavePlaceWithImageRes(place.getId());
    }

}
