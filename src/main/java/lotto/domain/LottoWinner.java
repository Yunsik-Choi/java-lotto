package lotto.domain;

public class LottoWinner extends Lotto {

    private final LottoNumber lottoNumber;
    private final String LOTTOWINNER_BONUSNUMBER_EXCEPTION = "보너스 번호는 다른 번호와 겹칠 수 없습니다.";

    public LottoWinner(LottoNumberSet lottoNumberSet, LottoNumber bonusNumber) {
        super(lottoNumberSet);
        if (lottoNumberSet.contain(bonusNumber)) {
            throw new IllegalArgumentException(LOTTOWINNER_BONUSNUMBER_EXCEPTION);
        }
        this.lottoNumber = bonusNumber;
    }
}
