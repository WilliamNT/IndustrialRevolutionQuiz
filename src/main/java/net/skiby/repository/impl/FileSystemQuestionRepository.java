package net.skiby.repository.impl;

import net.skiby.models.Question;
import net.skiby.repository.QuestionRepository;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileSystemQuestionRepository implements QuestionRepository {
    public List<Question> getAll() {
        final var source = new File("src/main/resources/questions.json");

        try (final var stream = new FileInputStream(source);) {
            final var stringContent = new String(stream.readAllBytes(), StandardCharsets.UTF_8);

            final var jsonArray = new JSONArray(stringContent);
            return Question.fromJson(jsonArray);
        } catch (IOException e) {
            System.out.println("Failed to load questions from source file!");
        }

        return new ArrayList<Question>();
    }
}
