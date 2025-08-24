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
    private static class ReportSettings {
        private final boolean enabled;
        private final ReportMode mode;
        private final ReportOutputSettings output;
    }

    @RequiredArgsConstructor
    @Getter
    @ToString
    private static class ReportOutputSettings {
        private final ReportOutputMode mode;
        private final String path;
    }

    public enum ReportMode {
        VERBOSE,
        CONSOLE;
    }

    public enum ReportOutputMode {
        CONSOLE,
        FILE;
    }
}
