package com.cleancodetips.nqueens.domain;

import lombok.Builder;
import lombok.Value;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

@Value
@Builder
public class NQueensArguments {

    int numberOfQueens;
}
