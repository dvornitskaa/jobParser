package oksana.dvornitska.interview.services.interfaces;

import oksana.dvornitska.interview.dtos.HitDto;
import oksana.dvornitska.interview.dtos.ResponseDto;

import java.util.List;

public interface JobServiceI {

    ResponseDto getJobs(List<String> functions, String hits);

    void parseHit(HitDto hit);

    void saveHit(HitDto hit);

    List<String> getFunctions();

}
