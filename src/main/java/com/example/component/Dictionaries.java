package com.example.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dzh
 * @since 2020-05-15
 */
@Data
@Component
@ConfigurationProperties(prefix = "download")
public class Dictionaries {
    private String path;
}
