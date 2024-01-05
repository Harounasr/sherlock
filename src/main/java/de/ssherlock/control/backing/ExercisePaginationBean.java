package de.ssherlock.control.backing;

import de.ssherlock.business.service.ExerciseService;
import de.ssherlock.control.session.AppSession;
import de.ssherlock.global.logging.SerializableLogger;
import de.ssherlock.global.transport.Exercise;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

/**
 * Backing bean for the exercisePagination.xhtml facelet.
 *
 * @author Haroun Alswedany
 */
@Named
@ViewScoped
public class ExercisePaginationBean extends AbstractPaginationBean implements Serializable {

    /**
     * Serial Version UID.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The page size for the pagination.
     */
    private static final int PAGE_SIZE = 10;

    /**
     * Logger for logging within this class.
     */
    private final SerializableLogger logger;

    /**
     * The active session.
     */
    private final AppSession appSession;

    /**
     * Service for handling Exercise-related actions.
     */
    private final ExerciseService exerciseService;

    /**
     * The parent bean.
     */
    private final CourseBean courseBean;

    /**
     * The current course.
     */
    private List<Exercise> exercises;

    /**
     * The new Exercise.
     */
    private Exercise exercise;

    /**
     * Constructs an ExercisePaginationBean.
     *
     * @param logger          The logger used for logging within this class (Injected).
     * @param appSession      The active session (Injected).
     * @param exerciseService The ExerciseService (Injected).
     * @param courseBean The parent bean.
     */
    @Inject
    public ExercisePaginationBean(
            SerializableLogger logger, AppSession appSession, ExerciseService exerciseService, CourseBean courseBean) {
        this.logger = logger;
        this.appSession = appSession;
        this.exerciseService = exerciseService;
        this.courseBean = courseBean;
    }

    /**
     * Initializes the ExercisePaginationBean after construction. Retrieves the exercises from the service.
     */
    @PostConstruct
    public void initialize() {
        exercise = new Exercise();
        getPagination().setPageSize(PAGE_SIZE);
        getPagination().setSortBy("obligatoryDeadline");
        exercises = exerciseService.getExercises(getPagination(), courseBean.getCourse());
        getPagination().setLastIndex(exercises.size() - 1);
    }

    /**
     * Navigates to the selected exercise.
     *
     * @param exercise The selected exercise.
     * @return The navigation outcome.
     */
    public String select(Exercise exercise) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("courseID", courseBean.getCourse().getId());
        logger.log(Level.INFO, "Selected Course: " + courseBean.getCourse().getId());
        logger.info("Selected Exercise: " + exercise.getId());
        return "/view/registered/exercise.xhtml?faces-redirect=true&Id=" + exercise.getId();
    }

    /**
     * Gets exercises.
     *
     * @return the exercises
     */
    public List<Exercise> getExercises() {
        return exercises;
    }

    /**
     * Sets exercises.
     *
     * @param exercises the exercises
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    /**
     * Adds an exercise to the database.
     */
    public String addExercise() {
        logger.log(Level.INFO, "add new exercise.");
        exercise.setCourseId(courseBean.getCourse().getId());
        exerciseService.addExercise(exercise);
        return "/view/registered/course.xhtml?faces-redirect=true&Id=" + courseBean.getCourse().getId();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void loadData() {
        exercises = exerciseService.getExercises(getPagination(), courseBean.getCourse());
    }

    /**
     * Gets exercise.
     *
     * @return the exercise
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * Sets exercise.
     *
     * @param exercise the exercise
     */
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    /**
     * Checks whether the publish date of the exercise has passed.
     *
     * @param exercise The Exercise.
     * @return Whether the date has passed.
     */
    public boolean isPublishDatePast(Exercise exercise) {
        return exercise.getPublishDate().toInstant().isBefore(Calendar.getInstance().toInstant());
    }

    /**
     * Checks whether the recommended deadline of the exercise has passed.
     *
     * @param exercise The Exercise.
     * @return Whether the date has passed.
     */
    public boolean isRecommendedDeadlinePast(Exercise exercise) {
        return exercise.getRecommendedDeadline().toInstant().isBefore(Calendar.getInstance().toInstant());
    }

    /**
     * Checks whether the obligatory deadline of the exercise has passed.
     *
     * @param exercise The Exercise.
     * @return Whether the date has passed.
     */
    public boolean isObligatoryDeadlinePast(Exercise exercise) {
        return exercise.getObligatoryDeadline().toInstant().isBefore(Calendar.getInstance().toInstant());
    }
}
