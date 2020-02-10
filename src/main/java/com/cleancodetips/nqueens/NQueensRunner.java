package com.cleancodetips.nqueens;

import com.cleancodetips.nqueens.domain.Board;
import com.cleancodetips.nqueens.domain.NQueensArguments;
import com.cleancodetips.nqueens.view.BoardViewer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Profile("!test")
public class NQueensRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(NQueensRunner.class);

    private final NQueensSolver nQueensSolver;
    private final NQueensCommandLineArgParser commandLineArgParser;
    private final BoardViewer boardViewer;

    @Autowired
    public NQueensRunner(@NotNull NQueensSolver nQueensSolver,
                         @NotNull BoardViewer boardViewer,
                         @NotNull NQueensCommandLineArgParser commandLineArgParser) {
        this.nQueensSolver = nQueensSolver;
        this.boardViewer = boardViewer;
        this.commandLineArgParser = commandLineArgParser;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            NQueensArguments parserArgs = commandLineArgParser.parse(args);
            Collection<Board> boards = nQueensSolver.solve(parserArgs.getNumberOfQueens());
            boardViewer.viewCollection(boards);
        } catch (CommandLineArgsParsingException ex) {
            logger.error("Failed to parse command line arguments", ex);
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Processing error", ex);
        }
    }

}
