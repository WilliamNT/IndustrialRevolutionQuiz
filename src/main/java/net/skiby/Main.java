package net.skiby;

import net.skiby.repository.QuestionRepository;
import net.skiby.repository.impl.FileSystemQuestionRepository;

public class Main {
    public static void main(String[] args) {
        final QuestionRepository questionRepository = new FileSystemQuestionRepository();
        final var questions = questionRepository.getAll();

        Quiz.start(questions);
    }
}