package payment.service.c_customAssertion_3.conditions;

import org.assertj.core.api.Condition;
import payment.service.Response;

public class Conditions {


    public static Condition<Response> rejectedBecauseMissingInvoiceId() {

        return new Condition<Response>("rejected when invoice ID is missing") {
            @Override
            public boolean matches(Response response) {
                return !response.getStatus().isEmpty();
            }
        };
    }
}
