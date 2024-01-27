package oksana.dvornitska.interview.dtos;

import com.google.gson.annotations.SerializedName;
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
public class OrganizationDto {

    String name;

    @SerializedName("logo_url")
    String logoUrl;

    @SerializedName("industry_tags")
    List<String> industryTags;

}
