package net.skiby.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    @Test
    void default_constructor_sets_field_correctly() {
        final List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("such", "wow", true));
        answers.add(new Answer("foo", "bar", false));

        final var question = new Question("hello", answers);
        assertEquals("hello", question.text);
        assertIterableEquals(answers, question.answers);
    }

    @Test
    void validateAnswer() {
        final List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("a", "wow", true));
        answers.add(new Answer("b", "bar", false));

        final var question = new Question("hello", answers);
        assertTrue(question.validateAnswer("a"));
        assertFalse(question.validateAnswer("b"));
        assertFalse(question.validateAnswer("x"));
        assertFalse(question.validateAnswer("asd123"));
    }

    @Test
    void json_object_factory_returns_single_correct_question_object() {
        final var obj = new JSONObject();
        obj.put("id", "such");
        obj.put("answer", "wow");
        obj.put("isValid", true);

        final var obj2 = new JSONObject();
        obj2.put("id", "foo");
        obj2.put("answer", "bar");
        obj2.put("isValid", false);

        final var arr = new JSONArray();
        arr.put(obj);
        arr.put(obj2);

        final List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("such", "wow", true));
        answers.add(new Answer("foo", "bar", false));

        final var question = new JSONObject();
        question.put("question", "foobar");
        question.put("answers", arr);

        final var expected = new Question("foobar", answers);
        assertEquals(expected, Question.fromJson(question));
    }

    @Test
    void json_array_factory_returns_correct_list_of_questions() {
        final var obj = new JSONObject();
        obj.put("question", "asd");
        obj.put("answers", new JSONArray());

        final var obj2 = new JSONObject();
        obj2.put("question", "hello");
        obj2.put("answers", new JSONArray());

        final List<Answer> answers = new ArrayList<>();

        final var questions = new JSONArray();
        questions.put(obj);
        questions.put(obj2);

        final List<Question> expected = new ArrayList<>();
        expected.add(new Question("asd", answers));
        expected.add(new Question("hello", answers));

        assertEquals(expected, Question.fromJson(questions));
    }
}