package ar.com.terminal.dto;

import java.net.URL;

/**
 * Created by ivan on 10/09/15.
 */
public class ProductDescription {

    private String fullDescription;
    private URL pictureURL;

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setPictureURL(URL pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public URL getPictureURL() {
        return pictureURL;
    }
}
