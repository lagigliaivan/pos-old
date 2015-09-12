package ar.com.terminal.model;

/**
 * Created by ivan on 12/09/15.
 */
public class ProfitPolicy {

    private Float percentage = 5F;
    private String applicability = "";

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Float getPercentage() {
        return percentage;
    }
}
