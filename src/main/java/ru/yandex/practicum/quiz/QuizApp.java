package ru.yandex.practicum.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import ru.yandex.practicum.quiz.config.AppConfig;
import ru.yandex.practicum.quiz.model.QuizLog;
import ru.yandex.practicum.quiz.service.ConsoleUI;
import ru.yandex.practicum.quiz.service.ReportGenerator;

@SpringBootApplication
@RequiredArgsConstructor
@ConfigurationPropertiesScan
public class QuizApp implements CommandLineRunner {
    private final ConsoleUI ui;
    private final ReportGenerator reportGenerator;
    private final AppConfig appConfig;

    public static void main(String[] args) {
        SpringApplication.run(QuizApp.class, args);
    }

    @Override
    public void run(String... args) {
        QuizLog log = ui.startQuiz();

        if (appConfig.getReport().isEnabled()) {
            reportGenerator.generate(log);
        }
    }
}