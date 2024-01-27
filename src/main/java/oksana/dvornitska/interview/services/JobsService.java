package oksana.dvornitska.interview.services;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import oksana.dvornitska.interview.dtos.HitDto;
import oksana.dvornitska.interview.dtos.JobFunction;
import oksana.dvornitska.interview.dtos.ResponseDto;
import oksana.dvornitska.interview.dtos.ResultDto;
import oksana.dvornitska.interview.entities.Job;
import oksana.dvornitska.interview.repositories.JobRepository;
import oksana.dvornitska.interview.services.interfaces.JobServiceI;
import oksana.dvornitska.interview.utils.ApiUtil;
import oksana.dvornitska.interview.utils.FunctionsUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobsService implements JobServiceI {

    @Autowired
    ApiUtil apiUtil;
    @Autowired
    JobRepository jobRepository;

    final static String FUNCTION_DELIMITER = "%22+OR+job_functions%3A%22";
    final static String JOB_LOCATION = "jobLocation";
    final static String DESCRIPTION = "description";
    final static String DATE_POSTED = "datePosted";
    final static String ADDRESS = "address";
    final static String ADDRESS_LOCALITY = "addressLocality";
    static String REPLACEMENT_1 = "+";
    static String REPLACEMENT_2 = "%26";
    static String TARGET_1 = " ";
    static String TARGET_2 = "&";

    @Value("${jobs.url}")
    String url;
    @Value("${css.query}")
    String cssQuery;
    @Value("${hits.per.page}")
    String hitsPerPage;
    @Value("${job.functions}")
    String jobFunctionsReplace;

    @Override
    @SneakyThrows
    public ResponseDto getJobs(List<String> functions, String hits) {
        String jobFunctions = String.join(FUNCTION_DELIMITER, functions);
        jobFunctions = jobFunctions.replace(TARGET_1, REPLACEMENT_1).replace(TARGET_2, REPLACEMENT_2);
        url = url.replace(jobFunctionsReplace, jobFunctions).replace(hitsPerPage, hits);
        String response = apiUtil.getRequest(url);
        Gson gson = new Gson();
        ResponseDto responseDto = gson.fromJson(response, ResponseDto.class);
        responseDto.getResults().parallelStream()
                .flatMap(result -> result.getHits().stream())
                .forEach(hit -> {
                    log.info(String.format("save hit %s", hit.getObjectID()));
                    parseHit(hit);
                    saveHit(hit);
                });
        return responseDto;
    }

    @Override
    @SneakyThrows
    public void parseHit(HitDto hit) {
        try {
            String response = apiUtil.getRequest(hit.getUrl());
            Document document = Jsoup.parse(response);
            Elements scriptElements = document.select(cssQuery);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
            for (Element script : scriptElements) {
                String jsonContent = script.html();
                JsonNode jsonNode = objectMapper.readTree(jsonContent);
                String location = jsonNode.path(JOB_LOCATION).path(ADDRESS).path(ADDRESS_LOCALITY).asText();
                String description = jsonNode.path(DESCRIPTION).asText();
                String datePosted = jsonNode.path(DATE_POSTED).asText();
                if (!"".equals(location)) {
                    hit.setLocation(location);
                }
                if (!"".equals(description)) {
                    hit.setDescription(description);
                }
                if (!"".equals(datePosted)) {
                    hit.setDatePosted(datePosted);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveHit(HitDto hit) {
        Job job = new Job();
        job.setObjectID(Integer.parseInt(hit.getObjectID()));
        if (hit.getTitle() != null) {
            job.setTitle(hit.getTitle());
        }
        if (hit.getHasDescription()) {
            job.setOrganisationUrl(hit.getUrl());
        } else {
            job.setUrl(hit.getUrl());
        }
        if (hit.getOrganization().getLogoUrl() != null) {
            job.setLogo(hit.getOrganization().getLogoUrl());
        }
        if (hit.getOrganization().getName() != null) {
            job.setOrganisationTitle(hit.getOrganization().getName());
        }
        job.setLaborFunction(hit.getHighlightResult().getJobFunctions().stream()
                .map(JobFunction::getValue)
                .collect(Collectors.joining(", ")));
        if (hit.getLocation() != null) {
            job.setLocation(hit.getLocation());
        }
        if (hit.getDatePosted() != null) {
            job.setPostedDate(hit.getDatePosted());
        }
        if (hit.getDescription() != null) {
            job.setDescription(hit.getDescription());
        }
        job.setTags(String.join("", hit.getOrganization().getIndustryTags()));
        try {
            jobRepository.save(job);
        } catch (DataIntegrityViolationException e) {
            log.error(String.format("Job with object_id: %s is already saved", hit.getObjectID()));
        }
    }

    @Override
    public List<String> getFunctions() {
        return FunctionsUtil.getFunctions();
    }

}
