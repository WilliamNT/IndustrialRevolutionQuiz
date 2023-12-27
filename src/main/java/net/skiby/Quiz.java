package net.skiby;

import net.skiby.models.Answer;
import net.skiby.models.Question;

import java.util.List;
import java.util.Scanner;

public class Quiz {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void start(List<Question> questions) {
        System.out.println("Üdvözlünk az ipari forradalom quizben!\n");

        int badAnswers = 0;

        final var scanner = new Scanner(System.in);

        for (int index = 0; index < questions.size(); index++) {
            final var question = questions.get(index);
            printQuestion(question, index + 1);

            System.out.print("Add meg a helyes válasz betűjelét: ");
            final var input = scanner.nextLine();

            if (question.validateAnswer(input.toUpperCase())) {
                System.out.println(ANSI_GREEN + "Helyes válasz!" + ANSI_RESET);
            } else {
                badAnswers++;
                System.out.println(ANSI_RED + "Téves válasz!" + ANSI_RESET);
            }

            System.out.println();
        }

        System.out.printf(
                "Eredmény: %s%s%s téves, %s%s%s jó válasz.",
                ANSI_RED,
                badAnswers,
                ANSI_RESET,
                ANSI_GREEN,
                questions.size() - badAnswers,
                ANSI_RESET
        );
    }

    private static void printQuestion(Question question, int index) {
        System.out.printf("(%s) %s\n", index, question.text);
        for (Answer answer : question.answers) {
            System.out.printf("\t%s - %s\n", answer.id, answer.text);
        }
    }
}
