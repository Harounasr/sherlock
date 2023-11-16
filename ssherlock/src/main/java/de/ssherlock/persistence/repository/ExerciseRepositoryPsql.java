package de.ssherlock.persistence.repository;

import de.ssherlock.global.logging.LoggerCreator;
import de.ssherlock.global.transport.Exercise;

import java.sql.Connection;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
/**
 * Implementation of ExerciseRepository for PostgreSQL database.
 */
public class ExerciseRepositoryPsql extends RepositoryPsql implements ExerciseRepository {
    /**
     * Logger instance for logging messages related to ExerciseRepositoryPsql.
     */
    private final Logger logger = LoggerCreator.get(ExerciseRepositoryPsql.class);
    /**
     * Constructor to initialize the repository with a database connection.
     *
     * @param connection The database connection.
     */
    public ExerciseRepositoryPsql(Connection connection) {
        super(connection);
    }

    @Override
    public void insertExercise(Exercise exercise) {

    }

    @Override
    public void updateExercise(Exercise exercise) {

    }

    @Override
    public void deleteExercise(String exerciseName) {

    }

    @Override
    public Exercise fetchExercise(String exerciseName) {
        return null;
    }

    @Override
    public List<Exercise> fetchExercises(Predicate<Exercise> predicate) {
        return null;
    }
}
