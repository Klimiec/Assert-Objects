package payment.service.a_classic;

import org.junit.Test;
import payment.service.PaymentService;
import payment.service.Request;
import payment.service.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentServiceTest {


    private PaymentService paymentService = new PaymentService();

    @Test
    public void should_reject_request_with_missing_invoice_id() {
        //given
        Request paymentRequest = new Request();

        //when
        Response response = paymentService.makePayment(paymentRequest);

        //then
        assertThat(response.getStatus()).isEmpty();
        assertThat(response.getCode()).isEqualTo("10003");
    }

}


/*
1.

Scenariusz testowy:
- Chcemy przetestować odrzucenie payment requestu jezeli nie zawiera ono invoice_id;
- Technicznie odrzucenie objawia sie tym, ze:
    * Status odpowiedzi nie jest ustawiony (ktos moze sie zapytac jaki to ma sens, sorry taka mamy implementacje)
    * Ustawiony jest odpowiedni code ktory określ błąd - w naszym wypadku będzie to 1003 (tak sobie wymyśliłem :P)

a) Nazwa metody
- Chcemy skupic się na zachowaniu
- Chcemy opisać jaki będzie rezultat wykonania akcji - sukces, porażka, może zostanie rzucony wyjątek
- Słowo SHOULD pozwala skupić się na zachowaniu!
- bedziemy używać opcji z podkresleniami - to bardzo podnosi czytelność!
  Przykłady takiej notacji:
  * https://github.com/joel-costigliola/assertj-examples/blob/master/assertions-examples/src/test/java/org/assertj/examples/custom/CustomAssertExamples.java
  *
  *
  Alternatywne nazwy, czyli jak by to mogło wyglądać.
  # testMakePaymentInvalid() :
    - standardowe rozpoczęcie od słowa test
    - słowo Invalid sugeruje testowanie negatywnego scenariusza ale jaki jest spodziewany rezultat? Co jest invalid, OCB?

  # testShouldMakePaymentInvalid() :
    - ktoś usłyszał, że dawanie should jest OK, ale nadal poprzedza test słowem test — litości! :(
    - słowo 'should' w żaden sposób nie nakierowuje na zachowanie.
    - ja po przeczytaniu tej nazwy + nazwy klasy nie mam pojęcia o co chodzi.
    - może być miliony powodów, dla których płatność ma się nie udać-fajnie to napisać explicite.
     W naszym przypadku jest to brak InvoiceID w requescie. Często sprawdzany jest tylko jeden scenariusz

  # testMakePaymentWithInvalid()

Co w nich jest złe? Co łączy wszystkie te trzy nazwy?
- abstrahuję od tego, ze nie skupiają się na opisaniu testowanego zachowania.
- każda nazwa testu zawiera nazwę testowanej metody-po co ja się pytam? Co się stanie jak zmienimy nazwę metody?

Podaj przykład:

  Jaki to ma wpływ na określenie testowanego zachowania-żaden
- Czytam nazwę metody i nie wiem, jaki ma byc rezultat - sukces czy porażka, a jak porażka to z jakiego powodu?

Najważniejsza sprawa:
Wszystkie alternatywne nazwy pomijają kluczowe słowo dla domeny: REJECT!!!! Mega istotne!
Płatność może być odrzucona-tak powiedział nasz ekspert domenowy.
My w naszym teście - który powinien stanowić DOKUMENTACJE naszej pracy pomijamy ten aspekt wiedzy domenowej!
W DDD jest coś takiego jak Ubiquitous Language  (rozwiń ten temat)

Przykład: Item i Organisation

- Używanie różnych pojęć (języków)  (w kodzie i w rozmowie) powoduje, zę zachodzi proces translacji.
  Jeżeli coś tłumaczymy, to jednocześnie coś tracimy.
  Im więcej tracimy tym mniej rozumiemy problem który modelujemy.
  W osatecznosci prowadzi to do słabego designu (jeden z wielu czynników, ale ważny)
  Link: https://images.fineartamerica.com/images-medium-large-5/-rickety-old-shack-karin-pinkham.jpg

b) Struktura testu

Schabowy clasic czyli esencja BDD:
//given
//when
//then   ---> GWT --> Go... Warty Toool

Fajna sprawa bo narzuca nam to szablon - mam nadzieje, ze każdy ma szablon (na ostatniej prezentacji to konfigurowałem)
'Szalenie ważne' jest to aby mieć to ustawione w szablonie a nie wpisywać ręcznie. Ręcznie robi się inne rzeczy (Bóbr)

c) Sekcja //then (czyli asercji) : co jest w niej nie tak?

Niby jesteśmy w //then ale wszystko zaczyna się od assertThat - no ale od czego ma się zacyznać? O co chodzi?

  Czy w spocku asercje zaczynają się od assertThat?
  Tam czegoś takiego nie ma
  Przykład: https://thejavatar.com/testing-with-spock/

  W Javie nie możemy napisac czeogś takiego ale możemy wymusić bardziej konwecje z BDD
  - W assertJ mamy coś takiego jak BDDAssertions
    zmienia to standardowy sposób robienia asercji z assertThat()  na then()
  Link: http://joel-costigliola.github.io/assertj/core/api/org/assertj/core/api/BDDAssertions.html


d) zmienmy nasz szablon aby używać właśnie BBAssertions zamiast zwykłych asercji
  - podmień import w szablonie
   > z: import static org.assertj.core.api.Assertions.*;
   >na: import static org.assertj.core.api.BDDAssertions.*;

   Napisz raz jeszcze test od zera

 */