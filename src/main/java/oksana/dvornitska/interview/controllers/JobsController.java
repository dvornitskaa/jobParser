package oksana.dvornitska.interview.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.interview.dtos.ResponseDto;
import oksana.dvornitska.interview.services.interfaces.JobServiceI;
import oksana.dvornitska.interview.utils.FunctionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/v1/jobs")
public class JobsController {

    @Autowired
    JobServiceI jobService;
    @Value("${hits.standard}")
    String hitsStandard;
    @Value("${hits.all}")
    String hitsAll;

    @GetMapping
    public ResponseEntity<ResponseDto> getJobs(@RequestParam List<String> functions) {
        return ResponseEntity.ok(jobService.getJobs(functions,hitsStandard));
    }

    @GetMapping("all")
    public ResponseEntity<ResponseDto> getAllJobs() {
        return ResponseEntity.ok(jobService.getJobs(FunctionsUtil.getFunctions(), hitsAll));
    }

    @GetMapping("functions")
    public ResponseEntity<List<String>> functions() {
        return ResponseEntity.ok(jobService.getFunctions());
    }
}
