package com.example.search.controller;

import com.example.search.domain.GeneralResponse;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SearchController {
    @Autowired
    EurekaClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() {
        //System.out.println(discoveryClient);

        InstanceInfo detailsInstanceInfo = discoveryClient.getNextServerFromEureka("details", false);
        String detailsURL = detailsInstanceInfo.getHomePageUrl();

        GeneralResponse resDetails = restTemplate.getForObject(detailsURL + "/details/port", GeneralResponse.class);

        InstanceInfo schoolInstanceInfo = discoveryClient.getNextServerFromEureka("school", false);
        String schoolURL = schoolInstanceInfo.getHomePageUrl();

        String resSchool = restTemplate.getForObject(schoolURL + "/school", String.class);
        resDetails.setData(resSchool);

        return new ResponseEntity<>(resDetails, HttpStatus.OK);
    }
}
