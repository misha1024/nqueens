package com.cleancodetips.nqueens;

import com.cleancodetips.nqueens.domain.NQueensArguments;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.cleancodetips.nqueens.NQueensOptionsConfig.NUMBER_OF_QUEENS_OPTION;
import static java.lang.String.format;

@Component
public class NQueensCommandLineArgParser {
    private final CommandLineParser commandLineParser;
    private final Options options;

    @Autowired
    public NQueensCommandLineArgParser(
            @NotNull CommandLineParser commandLineParser,
            @NotNull Options options) {
        this.commandLineParser = commandLineParser;
        this.options = options;
    }

    public NQueensArguments parse(@NotNull String[] args) {
        try {
            return convertToArguments(
                    commandLineParser.parse(options, args));
        } catch (Exception ex) {
            throw new CommandLineArgsParsingException(
                    buildErrorMessage(ex.getMessage(), options), ex);
        }
    }

    private static NQueensArguments convertToArguments(@NotNull CommandLine commandLine) {
        int numberOfQueens = Integer.parseInt(
                commandLine.getOptionValue(NUMBER_OF_QUEENS_OPTION));

        return NQueensArguments.builder()
                .numberOfQueens(numberOfQueens)
                .build();
    }

    private static String buildErrorMessage(@NotNull String exceptionMessage, @NotNull Options options) {
        final String header = exceptionMessage != null
                        ? format("%s\n", exceptionMessage)
                        : "Invalid argument format";

        final int width = 120;
        final int descPadding = 5;
        StringWriter sw = new StringWriter();
        final PrintWriter out = new PrintWriter(sw, true);

        HelpFormatter formatter = new HelpFormatter();

        // Header
        formatter.printWrapped(out, width, header);
        formatter.printWrapped(out, width, "");
        formatter.printOptions(out, width, options, formatter.getLeftPadding(), formatter.getDescPadding());
        formatter.printWrapped(out, width, "");

        // Usage
        formatter.setWidth(width);
        formatter.setDescPadding(descPadding);
        formatter.printUsage(out, width, "java -jar nqueens.jar --number-of-queens <NUMBER_OF_QUEENS_TO_PLACE> ", options);

        return sw.getBuffer().toString();
    }
}
