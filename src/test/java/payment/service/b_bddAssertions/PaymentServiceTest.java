package payment.service.b_bddAssertions;

import org.junit.Test;
import org.mockito.BDDMockito;
import payment.service.PaymentService;
import payment.service.Request;
import payment.service.Response;


import java.util.List;

//import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.BDDAssertions.*;

public class PaymentServiceTest {


    private PaymentService paymentService = new PaymentService();

    @Test
    public void should_reject_request_with_missing_invoice_id() {
        //given
        Request paymentRequest = new Request();

        //when
        Response response = paymentService.makePayment(paymentRequest);

        //then
        then(response.getStatus()).isEmpty();
        then(response.getCode()).isEqualTo("10003");

    }

}

/*

 1. Wszystko wydaje się fajne - jakie tutaj są problemy?
  Kolizja z then() z BDDMockito.

 Wyobraźmy sobie, że chcemy teraz utworzyć mocka:
 Niech to bedzie mock Listy

 List list = mock(List.class);

 Zamiast metody z BDDMockito dostajemy metodę z BDDAssertions.

 a) Czemu tak się dzieje?

 - zobaczmy sobie jakie przeciązone wersje metody then() dostarcza BDDAssertions
   Jest tego bardzo dużo

 - zobaczmy jakie wersje metody then( ) dostarcza BDDMockito
   Jest tylko jedna wersja!

   Po prostu poprzez statyczny import mamy dostep do wszystkim metod then - tych z BDDMockito oraz BDDAssertions.
   To która metoda zostanie wybrana jest rozwiązywane na etapie kompilacji.
   Po prostu metody z BDDAssertions są bardziej specyficzne - dlatego są wybierane


b) Co się stanie, jeżeli do metody then() dostarczymy obiekt dla którego nie istnieje przeciążona wersja w BDDAssertions?

    Request request = mock(Request.class);

    - z róznych miejsc mamy dostep do tej samej metody która pasuje - dwóznaczność.
     Typ zwracany nie jest brany pod uwagę przez kompilator podczas rozwiązywania wywołania metody.

     Rozwiązanie jest takie, że po prostu musimy jawnie powiedziec, że chcemy korzystać z BDDMockito lub BDDAssertions.

     - Czy to jest problem?
     - Kiedy tam naprawdę na raz będziemy używać zarówno metody then() z BDDMockito oraz BDDAssertions?
       W toeri testowania te dwie metody nie powinny nigdy stać obok siebie - mówie o then z BDDMockito oraz BDDAssertions.


c)  W testach możemy testować tylko dwie rzeczy - TYLKO DWIE -
     - Albo zwracany rezultat, czyli testujemy jakiś stan.
        Testowany obiekt, traktujemy jak black box.
        Nie zaglądamy w bebechy, nie wnikamy co jest w środku.
        Mówimy wtedy o testach czarno-skrzynkowych, z angielskiego Black-box testing
        Link: https://www.google.pl/search?q=black+box+testing&oq=blax+box+te&aqs=chrome.1.69i57j0l5.3446j0j7&sourceid=chrome&ie=UTF-8


     - Albo testujemy interakcje.
       Czyli patrzymy jakie sygnały są przesyłane wewnątrz testowanego obiektu.
       Mówimy wtedy o testach biało-skrzynkowych, z ang. White-box testing
       Link: https://en.wikipedia.org/wiki/White-box_testing
       W celu sprawdzania interakcji


       'Szalenie ważne' (J.Engel) aby nie mieszać sprawdzania stanu ze sprawdzaniem inerakcji.
       Nie robić hybryd - to jest najgorsze.

       - przykład. Wyobraźmy sobie, że nasza metoda ma wysyłac maila podczas nieudanej płatności.
       Obecna nazwa testu nic nam nie mówi o wysyłaniu e-maila.
       Powinien powstać raczej osobny test który powinien to sprawdzać.
       Sprawdzanie czy wysłal się e-mail będzie właśnie sprawdzaniem czy zaszła interakcja między metoda płatniczą a serwisem odpowiedzialnym za wysyłanie e-maili.
        Do tego powinna istnieć inan metoda: should_send_email_when_payment_is_rejected

        - wiele osób nie robi osobnego testu i wpycha wszystko do jednego.
        - testy też obowiazuje SRP - powinien istnieć jeden powód biznesowy dla zmiany jakiegoś testu.
          W końcu dobrze napisane testy opisują zachowania.

          - Przy okazji SRP chciałem jeszcze powiedzieć, że test ma opisywać jedno zachowanie, jeden case biznesowy.
          To nie oznacza, że w takim teście ma istnieć jedna asercja.
          Czasami biznesowo jedna asercja oznacza sprawdzenie 5 rzeczy - w naszym przypadku jest to sprawdzenie dwóch rzeczy (Code oraz status)



d)
       Tak jak mówiłem, sprawdzanie stanu nie wchodzi w bebechy a sprawdzanie interakcji tak.
       Zastanówmy się czy to co teraz powiedziałem ma sens?

       A co w przypadku stubów? W jakiej kategori testów je wykorzystujemy?
       Stuby są wykorzystywane po to aby ustawić testowany system w jakimś określonym stanie.

       Finalnie ich użycie może objawić się weryfikacją stanu końcowego, albo jakiejś inerakcji.
       Czyli wykorzystywane są tu i tu.
       Czyli jednak testowanie stanu może czasami wejść z butami w implementacje - Hahaha

       Tylko ważne, żeby stubbów nigdy nie weryfikować.
       Stub ma ustawić testowany system w jakimś stanie X i tyle.
       Weryfikacja to albo sprawdzenie stanu albo interakcji na jakimś innym mocku.



e)  Wróćmy do naszej asercji - czy wszystko z nią jest OK?

   Jaki ja tutaj widze problem.
   Tak naprawdę skupiamy się na jakiś technicznych detalach - sprawdzanie jakiś statusów.
   Zamiast jasno okreslić weryfikowaną hipotezę - request powinien być odrzucony z powodu X, Y, Z - w naszym przypadku brak Invoice ID

   Jeżeli zachowanie się nie zmieniło to nasz test też nie powinien się zmieniać!



#Mockito BDD
BDDMockito
    public static <T> BDDMockito.Then<T> then(T mock) {
        return new BDDMockito.ThenImpl(mock);
    }


#AssertBDD
BDDAssertions
    @CheckReturnValue
    public static AbstractCharSequenceAssert<?, String> then(String actual) {
        return assertThat(actual);
    }


















 */