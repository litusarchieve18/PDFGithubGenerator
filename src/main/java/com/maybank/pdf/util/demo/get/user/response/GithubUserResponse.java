package com.maybank.pdf.util.demo.get.user.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class GithubUserResponse {
    private int totalCount;
    private boolean incompleteResults;
    private List<Item> items;

    @Data
    @Builder
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private String login;
        private String id;
        private String nodeId;
        private String avatarUrl;
        private String gravatarId;
        private String url;
        private String htmlUrl;
        private String followersUrl;
        private String followingUrl;
        private String gistsUrl;
        private String starredUrl;
        private String subscriptionsUrl;
        private String organizationsUrl;
        private String reposUrl;
        private String eventsUrl;
        private String receivedEventsUrl;
        private String type;
        private boolean siteAdmin;
        private BigDecimal score;
    }

    @Override
    public String toString() {
        return "GithubUserResponse{" +
                "items=" + items +
                '}';
    }
}
