package com.maybank.pdf.util.demo.get.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maybank.pdf.util.demo.get.user.config.GithubConfig;
import com.maybank.pdf.util.demo.get.user.response.GithubUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class GithubService {
    private final GithubConfig githubConfig;
    private final RestTemplate restTemplate;
    public static final String githubApiVersion = "X-GitHub-Api-Version";

    public GithubUserResponse getListUser (int perPage, String sort ) throws HttpClientErrorException, HttpServerErrorException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(githubConfig.getAuth());
        headers.setAccept(Arrays.asList(MediaType.valueOf(githubConfig.getMediaType())));
        headers.add(githubApiVersion, githubConfig.getApiVersion());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriBuilder uriBuilder= UriComponentsBuilder.fromUri(URI.create(githubConfig.getUrl()));
        uriBuilder.queryParam("q","litusarchieve18");
        uriBuilder.queryParam("sort", sort);
        uriBuilder.queryParam("per_page",perPage);

        ResponseEntity<GithubUserResponse> response = restTemplate.exchange(String.valueOf(uriBuilder.build()), HttpMethod.GET, entity, GithubUserResponse.class);

        return  response.getBody();
    }
}
