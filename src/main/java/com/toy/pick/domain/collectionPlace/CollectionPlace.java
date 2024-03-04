package com.toy.pick.domain.collectionPlace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toy.pick.domain.BaseEntity;
import com.toy.pick.domain.collection.Collection;
import com.toy.pick.domain.place.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CollectionPlace extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @JsonIgnore
    private Place place;

    private String memo;

    private String url;


    public CollectionPlace(Collection collection, Place place, String memo, String url) {
        this.collection = collection;
        this.place = place;
        this.memo = memo;
        this.url = url;
    }

    public static CollectionPlace create(Collection collection, Place place, String memo, String url){
        return new CollectionPlace(collection, place, memo, url);
    }
}
