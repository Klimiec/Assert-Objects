package payment.service.c_customAssertion_1;

import org.junit.Test;
import payment.service.PaymentService;
import payment.service.Request;
import payment.service.Response;
import payment.service.c_customAssertion_1.assertobject.ResponseAssert;

import static org.assertj.core.api.BDDAssertions.*;
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
    1.

      then(response).isRejectedBecauseOf(MISSING_INVOICE_ID);

    2. Tworzymy AssertObject - czyli wraper na testowany obiekt, obiekt na których chcemy wykonywać asercje
      - chowa  on techniczny aspekt wykonywanych asercji, w naszym przypadku sprawdzenie dwóch rzeczy i uwykla domene.
        Głównie poprzez:
        - wprowadzenie DSL : https://martinfowler.com/books/dsl.html  (Jezyk specyficzny dla naszej domeny)
        - urzycie fluent interface :

    3. Kod testów to również kod produkcyjny:
      - nie wystarczy wszystkiego wrzucić do jednej klasy z napisem test
      - testy również mają architekture, je też należy modelować.

      - Cały nasz wysiłek ma zmierzać do 'ochrony' testu przed jego modyfikacją.
        Jeżeli zachowanie nie uległo zmianie, to test ma się nie zmieniac.

        Jeżeli w naszym przypadku zmianie ulegną statusy, albo dodane zostaną kolejne, to zmianę na sibie przyjmie assertObject.
        Ten pozostanie niekniety.

        To ma trochę działać tak jak strefa zgniotu:
        - Pokaż slaajd ze starej prezentacji

    4. Pomocnicza metoda  - thenPaymentResponse()

        - Jej głównym zadaniem jest schowanie technicznego aspektu tworzenia nowych obiektów - uzycie słówka NEW.
          ZObaczcie jak daleko się posunołem. Dzięki temu, mój test jest jeszcze bardziej czytelny.


          Pokaż przykłady:





 */