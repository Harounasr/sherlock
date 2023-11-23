package de.ssherlock.control.backing;

import de.ssherlock.business.service.UserService;
import de.ssherlock.control.session.AppSession;
import de.ssherlock.global.logging.SerializableLogger;
import de.ssherlock.global.transport.CourseRole;
import de.ssherlock.global.transport.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Backing bean for courseUserPagination.xhtml facelet.
 *
 * @author Leon Höfling
 */
@Named
@ViewScoped
public class CourseUserPaginationBean extends AbstractPaginationBean implements Serializable {

    /**
     * Serial Version UID
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Logger for this class.
     */
    private final SerializableLogger logger;

    /**
     * The active session.
     */
    private final AppSession appSession;

    /**
     * Service handling user-related operations.
     */
    private final UserService userService;

    /**
     * Map holding users and their corresponding course roles.
     */
    private Map<User, CourseRole> userRoles;

    /**
     * String used for searching.
     */
    private String searchString;

    /**
     * Constructor for CourseUserPaginationBean.
     *
     * @param logger     The logger for logging purposes (Injected).
     * @param appSession The active session (Injected).
     * @param userService The service handling user-related operations (Injected).
     */
    @Inject
    public CourseUserPaginationBean(SerializableLogger logger, AppSession appSession, UserService userService) {
        this.logger = logger;
        this.appSession = appSession;
        this.userService = userService;
    }

    /**
     * Initializes the bean after construction.
     */
    @PostConstruct
    public void initialize() {

    }

    /**
     * Changes the role of a selected user in the UI.
     *
     * @param e The ActionEvent.
     */
    public void changeUserRole(ActionEvent e) {

    }

    /**
     * Handles search operations.
     */
    public void handleSearch() {

    }

    /**
     * Sets the search string.
     *
     * @param searchString The string used for searching.
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void loadData() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void filterBy() {

    }

}
