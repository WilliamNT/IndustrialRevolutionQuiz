package net.skiby.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
    public final String text;
    public final List<Answer> answers;

    public Question(String text, List<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }

    public boolean validateAnswer(String id) {
        for (Answer answer : answers) {
            if (id.equals(answer.id) && answer.isValidAnswer) {
                return true;
            }
        }

        return false;
    }

    public static Question fromJson(JSONObject jsonObject) {
        final var question = jsonObject.getString("question");
        final var answers = jsonObject.getJSONArray("answers");

        return new Question(
                question,
                Answer.fromJson(answers)
        );
    }

    public static List<Question> fromJson(JSONArray jsonArray) {
        final var questions = new ArrayList<Question>();

        for (int i = 0; i < jsonArray.length(); i++) {
            final var question = fromJson(jsonArray.getJSONObject(i));
            questions.add(question);
        }

        return questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(text, question.text) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, answers);
    }
}
