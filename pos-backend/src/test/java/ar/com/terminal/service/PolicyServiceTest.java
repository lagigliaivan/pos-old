package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.db.DBMemory;
import ar.com.terminal.dto.ProductDto;
import ar.com.terminal.dto.ProfitPolicyDto;
import ar.com.terminal.model.Catalog;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

/**
 * Created by ivan on 13/09/15.
 */
public class PolicyServiceTest {
    @Test
    public void return_all_the_available_profit_policies() {

        PolicyService service = getPolicyService();

        ProfitPolicyDto profitPolicyDto = getProfitPolicyDto("21.5% policy", 25.5F);
        service.addProfitPolicy(profitPolicyDto);

        ProfitPolicyDto profitPolicyDto2 = getProfitPolicyDto("5% policy", 5F);
        service.addProfitPolicy(profitPolicyDto2);

        List<ProfitPolicyDto> profitPolicies = service.getAvailableProfitPolicies();

        assertThat(profitPolicies, is(notNullValue()));
        assertThat(profitPolicies.isEmpty(), is(false));

        assertThat(profitPolicies.size(), is(2));
        assertThat(profitPolicies.contains(profitPolicyDto), is(true));
        assertThat(profitPolicies.contains(profitPolicyDto2), is(true));
    }


    @Test
    public void return_an_empty_list_when_there_is_not_available_profit_policies(){

        PolicyService service = getPolicyService();
        List<ProfitPolicyDto> profitPolicies = service.getAvailableProfitPolicies();
        assertThat(profitPolicies, is(notNullValue()));
        assertThat(profitPolicies, empty());
    }

    private ProfitPolicyDto getProfitPolicyDto(String id, Float percentage) {
        ProfitPolicyDto profitPolicyDto;
        profitPolicyDto = new ProfitPolicyDto();
        profitPolicyDto.setId(id);
        profitPolicyDto.setPercentage(21.5F);
        return profitPolicyDto;
    }
    private PolicyService getPolicyService() {
        Catalog catalog = new Catalog(new DBMemory());
        Controller controller = new Controller(catalog);
        return new PolicyService(controller);
    }
}
