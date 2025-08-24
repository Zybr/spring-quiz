package ru.yandex.practicum.quiz.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@RequiredArgsConstructor
@ConfigurationProperties("spring-quiz")
@Setter
@Getter
@ToString
public class AppConfig {
    private final String title;
    private final ReportSettings report;

    @RequiredArgsConstructor
    @Getter
    @ToString
    public static class ReportSettings {
        private final boolean enabled;
        private final ReportMode mode;
        private final ReportOutputSettings output;
    }

    @RequiredArgsConstructor
    @Getter
    @ToString
    public static class ReportOutputSettings {
        private final ReportOutputMode mode;
        private final String path;
    }

    public enum ReportMode {
        VERBOSE,
        CONCISE;
    }

    public enum ReportOutputMode {
        CONSOLE,
        FILE;
    }
}
