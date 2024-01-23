package oksana.dvornitska.interview.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HitDto {

    String objectID;

    Integer created_at;

    String datePosted;

    String location;

    String title;

    String url;

    OrganizationDto organization;

    String description;

    HighlightDto _highlightResult;

    Boolean has_description;

}
