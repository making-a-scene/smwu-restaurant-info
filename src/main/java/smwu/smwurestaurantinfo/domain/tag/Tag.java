package smwu.smwurestaurantinfo.domain.tag;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tag")
public class Tag {
    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private TagClassification classification;
    @OneToMany(mappedBy = "tag")
    private List<RestaurantTag> restaurantTags = new ArrayList<>(); // 해당 태그가 N번 이상 달린 식당들 목록

    @Builder
    public Tag(String name, TagClassification classification) {
        this.name = name;
        this.classification = classification;
    }

    public void updateTagName(String name) {
        this.name = name;
    }

    public void updateTagClassification(TagClassification classification) {
        this.classification = classification;
    }

}
