package net.skiby.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answer {
    public final String id;
    public final String text;
    public final boolean isValidAnswer;

    public Answer(String id, String text, boolean isValidAnswer) {
        this.id = id;
        this.text = text;
        this.isValidAnswer = isValidAnswer;
    }

    public static Answer fromJson(JSONObject jsonObject) {
        final var id = jsonObject.getString("id");
        final var answer = jsonObject.getString("answer");
        final var isValid = jsonObject.getBoolean("isValid");

        return new Answer(id, answer, isValid);
    }

    public static List<Answer> fromJson(JSONArray jsonArray) {
        final var answers = new ArrayList<Answer>();

        for (int i = 0; i < jsonArray.length(); i++) {
            final var answer = fromJson(jsonArray.getJSONObject(i));
            answers.add(answer);
        }

        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return isValidAnswer == answer.isValidAnswer && Objects.equals(id, answer.id) && Objects.equals(text, answer.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, isValidAnswer);
    }
}
