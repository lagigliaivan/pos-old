package ar.com.terminal.dto;

/**
 * Created by ivan on 12/09/15.
 */
public class ProfitPolicyDto {
    private Float percentage = 0F;

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

        if (!percentage.equals(that.percentage)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return percentage.hashCode();
    }
}
