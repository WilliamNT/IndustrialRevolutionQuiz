package net.skiby.repository;

import net.skiby.models.Question;

import java.io.File;
import java.util.List;

public interface QuestionRepository {
    List<Question> getAll();
}
