package com.example.search.service;

import com.example.search.domain.GeneralResponse;
import com.example.search.domain.StudentDTO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.micrometer.core.instrument.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Completable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchServiceImpl implements SearchService{
    private final EurekaClient discoveryClient;
    private final RestTemplate restTemplate;

    @Autowired
    public SearchServiceImpl(EurekaClient discoveryClient,RestTemplate restTemplate){
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> getDetails() {

//        InstanceInfo detailsInstanceInfo = discoveryClient.getNextServerFromEureka("details", false);
//        String detailsURL = detailsInstanceInfo.getHomePageUrl();
//
//        GeneralResponse resDetails = restTemplate.getForObject(detailsURL + "/details/port", GeneralResponse.class);
//
//        InstanceInfo schoolInstanceInfo = discoveryClient.getNextServerFromEureka("school", false);
//        String schoolURL = schoolInstanceInfo.getHomePageUrl();
//
//        StudentDTO resSchool = restTemplate.getForObject(schoolURL + "/student/16", StudentDTO.class);
//        resDetails.setData(resSchool);
//
//        return new ResponseEntity<>(resDetails, HttpStatus.OK);

        List<CompletableFuture<?>> futures = new ArrayList<>();
        futures.add(CompletableFuture.supplyAsync( ()->{
            InstanceInfo detailsInstanceInfo = discoveryClient.getNextServerFromEureka("details", false);
            String detailsURL = detailsInstanceInfo.getHomePageUrl();
            GeneralResponse resDetails = restTemplate.getForObject(detailsURL + "/details/port", GeneralResponse.class);
            return resDetails;
        }));
        futures.add(CompletableFuture.supplyAsync(()->{
            InstanceInfo schoolInstanceInfo = discoveryClient.getNextServerFromEureka("school", false);
            String schoolURL = schoolInstanceInfo.getHomePageUrl();
            StudentDTO resSchool = restTemplate.getForObject(schoolURL + "/student/16", StudentDTO.class);
            return resSchool;
        }));

        CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]))
                .thenRun(() -> futures.stream().forEach(CompletableFuture::join));
        GeneralResponse mergedRes = (GeneralResponse) futures.get(0).join();
        mergedRes.setData((StudentDTO) futures.get(1).join());
        return new ResponseEntity<>(mergedRes, HttpStatus.OK);

    }
}
