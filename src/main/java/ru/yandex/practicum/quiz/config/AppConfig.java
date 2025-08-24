package ru.yandex.practicum.quiz.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@RequiredArgsConstructor
@ConfigurationProperties("sprint-quiz")
@Getter
@ToString
public class AppConfig {
    private final String title;
}
