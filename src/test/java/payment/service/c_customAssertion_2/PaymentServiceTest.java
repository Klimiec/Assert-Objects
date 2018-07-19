package payment.service.c_customAssertion_2;

import org.junit.Test;
import payment.service.PaymentService;
import payment.service.Request;
import payment.service.Response;
import payment.service.c_customAssertion_1.assertobject.ResponseAssert;

import static payment.service.c_customAssertion_1.assertobject.RejectReason.MISSING_INVOICE_ID;


public class PaymentServiceTest {


    private PaymentService paymentService = new PaymentService();

    @Test
    public void should_reject_request_with_missing_invoice_id() {
        //given
        Request paymentRequest = new Request();

        //when
        Response response = paymentService.makePayment(paymentRequest);

        //then
        thenPaymentResponse(response).isRejectedBecauseOf(MISSING_INVOICE_ID);

    }


    private ResponseAssert thenPaymentResponse(Response response) {

        return new ResponseAssert(response);
    }


}


/*

    1. Stworzenie AssertObjectu - skorzystanie z implementacji w AssertJ
    Link: http://joel-costigliola.github.io/assertj/assertj-core-custom-assertions.html
    Link: http://www.baeldung.com/assertj-custom-assertion

    - musi rozszerzać klasę: extends AbstractAssert<X, Y>
   Gdzie:
        X: assertObject
        Y: obiekt na którym będziemy robić asercje


    2. Alternatywa dla asertObjectów - Condition

    Link: http://joel-costigliola.github.io/assertj/assertj-core-conditions.html

    Jeżeli nasz assertObject jest mały.
    Ma tylko jedną metodę to można pójść w kierunko Conditional.
    - zwłaszcza, jeżeli ten warunek zaczyna się od is()

    Alternatywny:
    - isNot()

    Należy rozszerzyć klasę Condition<> oraz nadpisać metodę matches()

    P







 */