package ro.dand.attractionsmate.Models;

/**
 * Class to model marker details as description-image pair.
 */

public class DescriptionImagePair {
    private String description;
    private int imageAddress;

    public DescriptionImagePair(String description, int imageAddress) {
        this.description = description;
        this.imageAddress = imageAddress;
    }

    public String getDescription() {
        return description;
    }

    public int getImageAddress() {
        return imageAddress;
    }
}
