package de.ssherlock.control.validation;

import de.ssherlock.global.logging.SerializableLogger;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;

/**
 * Handles validation of uploaded archive files.
 */
@Named
@Dependent
@FacesValidator(value = "archiveValidator", managed = true)
public class ArchiveValidator implements Validator<Part> {

    /**
     * The logger instance for this class.
     */
    private final SerializableLogger logger;

    /**
     * Default constructor.
     *
     * @param logger The logger instance.
     */
    @Inject
    public ArchiveValidator(SerializableLogger logger) {
        this.logger = logger;
    }

    /**
     * Validates the uploaded archive file.
     *
     * @param facesContext The FacesContext for the current request.
     * @param uiComponent  The UIComponent associated with the component being validated.
     * @param part         The Part representing the uploaded archive file to be validated.
     * @throws ValidatorException if the validation fails.
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Part part) throws ValidatorException {

    }
}