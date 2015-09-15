package ar.com.terminal.model;

/**
 * Created by ivan on 12/09/15.
 */
public class ProfitPolicy {

    private String id;
    private Float percentage = 5F;

    public ProfitPolicy(String id, Float percentage){
        this.id = id;
        this.percentage = percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Float getPercentage() {
        return percentage;
    }

    public String getId() {
        return id;
    }
}
