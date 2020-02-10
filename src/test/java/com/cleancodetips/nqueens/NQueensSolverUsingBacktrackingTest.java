package com.cleancodetips.nqueens;

import com.cleancodetips.nqueens.domain.Board;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
public class NQueensSolverUsingBacktrackingTest {

    private static final Map<Integer, Integer> numberOfQueensWithAnswerCount = new LinkedHashMap<>();

    @Autowired
    private NQueensSolverUsingBacktracking solver;

    @BeforeAll
    static void setup() {
        numberOfQueensWithAnswerCount.put(1, 1);
        numberOfQueensWithAnswerCount.put(2, 0);
        numberOfQueensWithAnswerCount.put(3, 0);
        numberOfQueensWithAnswerCount.put(4, 2);
        numberOfQueensWithAnswerCount.put(5, 0);
        numberOfQueensWithAnswerCount.put(6, 0);
        numberOfQueensWithAnswerCount.put(7, 0);
        numberOfQueensWithAnswerCount.put(8, 8);
    }

    @Test
    public void givenNQueensWithAnswerCount_whenSolve_thenCorrectCountReturned() {
        SoftAssertions softly = new SoftAssertions();

        for(Map.Entry<Integer, Integer> testCase : numberOfQueensWithAnswerCount.entrySet()) {
            Collection<Board> boardsCollection = solver.solve(testCase.getKey());

            softly.assertThat(boardsCollection.size())
                    .isEqualTo(testCase.getValue());
        }
        softly.assertAll();
    }
}
