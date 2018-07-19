package payment.service.c_customAssertion_1.assertobject;

import org.assertj.core.api.SoftAssertions;
import payment.service.Response;

import static org.assertj.core.api.BDDAssertions.then;

public class ResponseAssert {


    private Response response;
    private boolean rejectedBecauseOfX;

    public ResponseAssert(Response response) {
        this.response = response;
    }

    public void isRejectedBecauseOf(RejectReason missingInvoiceId) {
//        then(response.getStatus()).isEmpty();
//        then(response.getCode()).isEqualTo("10003");

        SoftAssertions softly = new SoftAssertions();
             softly.assertThat(response.getStatus()).as("Status").isNotEmpty();
            softly.assertThat(response.getCode()).as("Code").isEqualTo("10003345");
        softly.assertAll();
    }

    public boolean isRejectedBecauseOfX() {
        return rejectedBecauseOfX;
    }
}
