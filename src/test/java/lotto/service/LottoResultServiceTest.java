package lotto.service;

import lotto.domain.Amount;
import lotto.domain.ProfitRate;
import lotto.domain.lotto.LottoList;
import lotto.domain.rank.Rank;
import lotto.domain.lotto.Lotto;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberSet;
import lotto.domain.lotto.LottoWinner;
import lotto.domain.rank.RankMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class LottoResultServiceTest {

    private LottoResultService lottoResultService;

    @BeforeEach
    void setUp() {
        this.lottoResultService = new LottoResultService();
    }

    @DisplayName("당첨번호와 일치하는 갯수 배열을 반환한다.")
    @Test
    void check_lotto() {
        LottoList lottoList = new LottoList(Arrays.asList(
                new Lotto(1, 2, 3, 4, 5, 6),
                new Lotto(2, 3, 4, 5, 6, 7),
                new Lotto(2, 3, 4, 5, 6, 9),
                new Lotto(3, 4, 5, 6, 7, 8),
                new Lotto(4, 5, 6, 7, 8, 9),
                new Lotto(5, 6, 7, 8, 9, 10)
        ));
        LottoWinner winner = new LottoWinner(new LottoNumberSet(1, 2, 3, 4, 5, 6), LottoNumber.get(7));
        RankMap rankMap = lottoResultService.checkLotto(lottoList, winner);

        assertThat(rankMap).isEqualTo(new RankMap(Rank.values()));
    }

    @DisplayName("로또 수익률을 반환한다.")
    @Test
    void yield() {
        LottoList lottoList = new LottoList(Arrays.asList(
                new Lotto(4, 5, 6, 7, 8, 9),
                new Lotto(5, 6, 7, 8, 9, 10),
                new Lotto(6, 7, 8, 9, 10, 11),
                new Lotto(7, 8, 9, 10, 11, 12),
                new Lotto(7, 8, 9, 10, 11, 12),
                new Lotto(7, 8, 9, 10, 11, 12),
                new Lotto(7, 8, 9, 10, 11, 12),
                new Lotto(7, 8, 9, 10, 11, 12),
                new Lotto(7, 8, 9, 10, 11, 12),
                new Lotto(7, 8, 9, 10, 11, 12)
        ));
        LottoWinner winner = new LottoWinner(new LottoNumberSet(1, 2, 3, 4, 5, 6), LottoNumber.get(7));


        assertThat(lottoResultService.caculateProfitRate(lottoList, winner)).isEqualTo(new ProfitRate(0.50));
    }
}
