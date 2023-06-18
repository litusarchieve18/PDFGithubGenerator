package com.maybank.pdf.util.demo.get.user.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "github")
@NoArgsConstructor
public class GithubConfig {
    private String url;
    private String mediaType;
    private String auth;
    private String apiVersion;
}
