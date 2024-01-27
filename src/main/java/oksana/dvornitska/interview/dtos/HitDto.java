package oksana.dvornitska.interview.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HitDto {

    String objectID;

    @SerializedName("created_at")
    Integer createdAt;

    String datePosted;

    String location;

    String title;

    String url;

    OrganizationDto organization;

    String description;

    @SerializedName("_highlightResult")
    HighlightDto highlightResult;

    @SerializedName("has_description")
    Boolean hasDescription;

}
