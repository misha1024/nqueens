package com.cleancodetips.nqueens;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NQueensOptionsConfig {
    public static final String NUMBER_OF_QUEENS_OPTION = "number-of-queens";

    @Bean
    public CommandLineParser commandLineParser() {
        return new DefaultParser();
    }

    @Bean
    public Options options() {
        Options options = new Options();

        final Option numberOfQueens = Option.builder()
                .required()
                .longOpt(NUMBER_OF_QUEENS_OPTION)
                .hasArg()
                .desc("Number of queens to place on chessboard")
                .build();
        options.addOption(numberOfQueens);

        return options;
    }
}
