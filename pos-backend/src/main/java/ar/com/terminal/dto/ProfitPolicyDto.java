package ar.com.terminal.dto;

/**
 * Created by ivan on 12/09/15.
 */
public class ProfitPolicyDto {
    private Float percentage = 0F;
    private String id = "";

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Float getPercentage() {
        return percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfitPolicyDto that = (ProfitPolicyDto) o;

        if (!id.equals(that.id)) return false;
        if (!percentage.equals(that.percentage)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = percentage.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
