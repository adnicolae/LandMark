package ro.dand.attractionsmate.Models;

/**
 * Class to model a title-description pair for the list fragment.
 */

public class TitleDescriptionPair {
    private String locationTitle;
    private String locationShortDescription;

    public TitleDescriptionPair(String locationTitle, String locationShortDescription) {
        this.locationTitle = locationTitle;
        this.locationShortDescription = locationShortDescription;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    public String getLocationShortDescription() {
        return locationShortDescription;
    }

    public void setLocationShortDescription(String locationShortDescription) {
        this.locationShortDescription = locationShortDescription;
    }
}
