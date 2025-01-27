package de.ssherlock.control.validation;

import de.ssherlock.control.notification.Notification;
import de.ssherlock.control.notification.NotificationType;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Handles testate comment validation.
 *
 * @author Leon Höfling
 */
@Named
@Dependent
@FacesValidator(value = "testateCommentValidator", managed = true)
public class TestateCommentValidator implements Validator<String> {

    /** The maximum length for a comment.*/
    private static final int MAXCOMMENTLENGTH = 500;

    /**
     * Constructs an TestateCommentValidator.
     */
    @Inject
    public TestateCommentValidator() {}

    /**
     * Validates the username for uniqueness and format.
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, String comment)
            throws ValidatorException {

        if (comment.length() > MAXCOMMENTLENGTH) {
            Notification notification = new Notification("Comment may not be longer than 500 characters.", NotificationType.ERROR);
            notification.generateUIMessage();
        }
    }

}
