package de.ssherlock.control.backing;

import de.ssherlock.business.service.SystemService;
import de.ssherlock.control.session.AppSession;
import de.ssherlock.global.logging.SerializableLogger;
import de.ssherlock.global.transport.SystemSettings;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing Bean for the adminSettings.xhtml facelet.
 *
 * @author Leon Höfling
 */
@Named
@ViewScoped
public class AdminSettingsBean implements Serializable {

    /** Serial Version UID. */
    @Serial private static final long serialVersionUID = 1L;

    /** Logger for logging within this class. */
    private final SerializableLogger logger;

    /** The active session. */
    private final AppSession appSession;

    /** Service to handle SystemSetting-specific actions. */
    private final SystemService systemService;

    /** Currently active SystemSettings. */
    private SystemSettings systemSettings;

    /** The uploaded logo. */
    private transient Part uploadedLogo;


    /** List of available colors. */
    private List<String> availableColors;

    /**
     * Constructs an AdminSettingsBean.
     *
     * @param logger The logger used for logging within this class (Injected).
     * @param appSession The active session (Injected).
     * @param systemService The SystemService (Injected).
     */
    @Inject
    public AdminSettingsBean(
            SerializableLogger logger, AppSession appSession, SystemService systemService) {
        this.logger = logger;
        this.appSession = appSession;
        this.systemService = systemService;
    }

    /** Initializes the bean after construction. */
    @PostConstruct
    public void initialize() {
        availableColors = new ArrayList<>();
        availableColors.add("#ff0000"); // Red
        availableColors.add("#00ff00"); // Green
        availableColors.add("#0000ff"); // Blue
        availableColors.add("#ffff00"); // Yellow
        availableColors.add("#800080"); // Purple
        availableColors.add("#ffA500"); // Orange
        availableColors.add("#ffc0cb"); // Pink
        availableColors.add("#a52a2a"); // Brown
        systemSettings = new SystemSettings();
        systemSettings = systemService.getSystemSettings();
    }

    /** Action for submitting all current changes. */
    public void submitAllChanges() {
        systemService.updateSystemSettings(systemSettings);
    }

    /** Uploads the logo. */
    public void uploadLogo() {
        try {
            this.systemSettings.setLogo(uploadedLogo.getInputStream().readAllBytes());
        } catch(IOException e) {
            logger.severe("Upload Logo failed");
        }
    }

    /**
     * Gets uploaded logo.
     *
     * @return the uploaded logo
     */
    public Part getUploadedLogo() {
        return uploadedLogo;
    }

    /**
     * Setter for the uploaded logo.
     *
     * @param uploadedLogo The uploaded logo.
     */
    public void setUploadedLogo(Part uploadedLogo) {
        this.uploadedLogo = uploadedLogo;
    }

    /**
     * Gets system settings.
     *
     * @return the system settings
     */
    public SystemSettings getSystemSettings() {
        return systemSettings;
    }

    /**
     * Sets system settings.
     *
     * @param systemSettings the system settings
     */
    public void setSystemSettings(SystemSettings systemSettings) {
        this.systemSettings = systemSettings;
    }

    /**
     * Gets the list of available colors.
     *
     * @return List of available colors
     */
    public List<String> getAvailableColors() {
        return availableColors;
    }
}
