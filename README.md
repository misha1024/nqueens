# N-Queens Problem with additional constraint

## Problem Definition
Place N queens on an NxN chess board so that none of them attack each other 
(the classic n-queens problem). Additionally, make sure that no three queens 
are in a straight line at ANY angle, so queens on A1, C2 and E3, 
despite not attacking each other, form a straight line at some angle.

## Building project

`./gradlew build`

## Running 

`java -jar build/libs/nqueens-0.0.1-SNAPSHOT.jar --number-of-queens 8`

## Log file
If something goes wrong while running the program log file `nqueens.log` can be inspected for details.
