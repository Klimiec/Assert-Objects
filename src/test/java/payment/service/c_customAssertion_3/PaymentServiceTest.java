package payment.service.c_customAssertion_3;

import org.assertj.core.api.Condition;
import org.junit.Test;
import payment.service.PaymentService;
import payment.service.Request;
import payment.service.Response;
import payment.service.c_customAssertion_1.assertobject.ResponseAssert;

import static org.assertj.core.api.BDDAssertions.then;
import static payment.service.c_customAssertion_1.assertobject.RejectReason.MISSING_INVOICE_ID;
import static payment.service.c_customAssertion_3.conditions.Conditions.rejectedBecauseMissingInvoiceId;


public class PaymentServiceTest {


    private PaymentService paymentService = new PaymentService();

    @Test
    public void should_reject_request_with_missing_invoice_id() {
        //given
        Request paymentRequest = new Request();

        //when
        Response response = paymentService.makePayment(paymentRequest);

        //then
        then(response).is(rejectedBecauseMissingInvoiceId());




    }



}


/*

        2. Alternatywa dla asertObjectów - Condition

    Link: http://joel-costigliola.github.io/assertj/assertj-core-conditions.html

    Jeżeli nasz assertObject jest mały.
    Ma tylko jedną metodę to można pójść w kierunko Conditional.
    - zwłaszcza, jeżeli ten warunek zaczyna się od is()

    Alternatywny:
    - isNot()

    Należy rozszerzyć klasę Condition<> oraz nadpisać metodę matches()


    #Podsumowanie
    -



 */