package payment.service.c_customAssertion_2.assertobject;

import org.assertj.core.api.AbstractAssert;
import payment.service.Response;
import payment.service.c_customAssertion_1.assertobject.RejectReason;

import static org.assertj.core.api.BDDAssertions.then;

public class ResponseAssert extends AbstractAssert<ResponseAssert, Response> {


    private ResponseAssert(Response response) {
        super(response, ResponseAssert.class);
    }


    public ResponseAssert isRejectedBecauseOf(RejectReason missingInvoiceId) {

        if (actual.getStatus() != null) {
            failWithMessage("Expected status to be empty but was <%s>", actual.getStatus());
        }

        if (!actual.getCode().equals("1003")) {
            failWithMessage("Expected code to be 1003 but was <%s>", actual.getCode());
        }

        return this;
    }

    // static factory method  : dzieki temu mozemy zrobic
    public static ResponseAssert assertThat(Response response) {
        return new ResponseAssert(response);
    }

}
