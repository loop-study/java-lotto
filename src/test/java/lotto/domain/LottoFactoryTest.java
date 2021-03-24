package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("로또 기계")
class LottoFactoryTest {

    LottoQuantity createQuantity(int amount, int quantity) {
        return new LottoQuantity(amount, quantity);
    }

    List<Integer> manualLotto() {
        return Arrays.asList(1,2,3,4,5,6);
    }

    List<List<Integer>> createManualLottos(int quantity) {
        return Stream.generate(this::manualLotto)
                .limit(quantity)
                .collect(Collectors.toList());
    }

    @ParameterizedTest
    @CsvSource(value = {"5000,0", "1000,0"})
    @DisplayName("로또 자동 개수 확인")
    void createAutoLottoSizeTest(int amount, int quantity) {
        //given
        LottoQuantity lottoQuantity = createQuantity(amount, quantity);
        LottoTickets lottoTickets = LottoFactory.createLottoTickets(lottoQuantity);

        //when
        int result = lottoTickets.readOnlyLottoTicket().size();

        //then
        assertThat(lottoQuantity.autoQuantity()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"5000,5", "10000,10"})
    @DisplayName("로또 수동 개수 확인")
    void createManualLottoSizeTest(int amount, int quantity) {
        //given
        LottoQuantity lottoQuantity = createQuantity(amount, quantity);
        LottoTickets lottoTickets = LottoFactory.createLottoTickets(lottoQuantity, createManualLottos(quantity));

        //when
        int result = lottoTickets.readOnlyLottoTicket().size();

        //then
        assertThat(lottoQuantity.manualQuantity()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"5000,0,5", "5000,2,5", "10000,2,10"})
    @DisplayName("로또 자동,수동 개수 확인")
    void createAutoAndManualLottoSizeTest(int amount, int quantity, int ticketSize) {
        //given
        LottoQuantity lottoQuantity = createQuantity(amount, quantity);
        LottoTickets lottoTickets = LottoFactory.createLottoTickets(lottoQuantity, createManualLottos(quantity));

        //when

        //then
        assertThat(lottoTickets.readOnlyLottoTicket().size()).isEqualTo(ticketSize);
    }

}