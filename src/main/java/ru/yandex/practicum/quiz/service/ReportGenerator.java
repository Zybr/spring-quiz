package ru.yandex.practicum.quiz.service;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.quiz.config.AppConfig;
import ru.yandex.practicum.quiz.model.QuizLog;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ReportGenerator {
    private final AppConfig appConfig;

    public ReportGenerator(
            AppConfig appConfig
    ) {
        this.appConfig = appConfig;
    }

    public void generate(QuizLog quizLog){
        AppConfig.ReportOutputSettings settings = appConfig.getReport().getOutput();

        try (
                PrintWriter writer = settings.getMode() == AppConfig.ReportOutputMode.FILE
                        ? new PrintWriter(settings.getPath())
                        : new PrintWriter(System.out)
        ) {
            // записываем отчет
            write(quizLog, writer);
        } catch (Exception exception) {
            System.out.println("При генерации отчёта произошла ошибка: " + exception.getMessage());
        }
    }

    private void write(QuizLog quizLog, PrintWriter writer) {
        writer.println("Отчет о прохождении теста \"" + appConfig.getTitle() + "\".\n");
        if (
                appConfig.getReport().getMode() == AppConfig.ReportMode.VERBOSE
        ) {
            for (QuizLog.Entry entry : quizLog) {
                // Записываем номер вопроса и текст вопроса
                writer.println("Вопрос " + entry.getNumber() + ": " + entry.getQuestion().getText());

                // Записываем варианты ответов
                List<String> options = entry.getQuestion().getOptions();
                for (int i = 0; i < options.size(); i++) {
                    writer.println((i + 1) + ") " + options.get(i));
                }

                // Записываем ответы пользователя
                writer.print("Ответы пользователя: ");
                List<Integer> answers = entry.getAnswers();
                for (Integer answer : answers) {
                    writer.print(answer + " ");
                }
                writer.println();

                //Записываем флаг успешности ответа
                String successFlag = entry.isSuccessful() ? "да" : "нет";
                writer.println("Содержит правильный ответ: " + successFlag);

                // Добавляем пустую строку между записями
                writer.println();
            }
        }

        writer.printf("Всего вопросов: %d\nОтвечено правильно: %d\n", quizLog.total(), quizLog.successful());
    }
}

