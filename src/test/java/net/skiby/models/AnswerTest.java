package net.skiby.models;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AnswerTest {
    @Test
    void default_constructor_sets_field_correctly() {
        final var answer = new Answer("hello", "world", true);
        assertEquals("hello", answer.id);
        assertEquals("world", answer.text);
        assertTrue(answer.isValidAnswer);
    }

    @Test
    void json_object_factory_returns_single_correct_answer_object() {
        final var obj = new JSONObject();
        obj.put("id", "such");
        obj.put("answer", "wow");
        obj.put("isValid", true);

        final var expected = new Answer("such", "wow", true);
        assertEquals(expected, Answer.fromJson(obj));
    }

    @Test
    void json_array_factory_returns_correct_list_of_answers() {
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

        final List<Answer> expected = new ArrayList<>();
        expected.add(new Answer("such", "wow", true));
        expected.add(new Answer("foo", "bar", false));

        assertIterableEquals(expected, Answer.fromJson(arr));
    }
}