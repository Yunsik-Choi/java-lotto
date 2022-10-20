package lotto.controller.lotto;

import lotto.domain.Amount;
import lotto.domain.Money.ImmutableMoney;
import lotto.domain.lotto.Lotto;
import lotto.domain.lottonumber.LottoNumberSet;
import lotto.service.LottoPurchaseService;
import lotto.view.lotto.LottoInput;
import lotto.view.lotto.LottoOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoPurchaseController {

    private final LottoPurchaseService lottoPurchaseService;

    public LottoPurchaseController(LottoPurchaseService lottoPurchaseService) {
        this.lottoPurchaseService = lottoPurchaseService;
    }

    public List<Lotto> purchaseLotto(final ImmutableMoney immutableMoney) {
        Amount totalAmount = lottoPurchaseService.purchaseNumber(immutableMoney);
        List<Lotto> lottoList = purchaseAutoLotto(totalAmount, purchaseManualLotto());
        LottoOutput.lotto(lottoList);
        return Collections.unmodifiableList(lottoList);
    }

    private List<Lotto> purchaseAutoLotto(final Amount amount, final List<Lotto> lottoList) {
        Amount manualLottoAmount = new Amount(lottoList.size());
        amount.minus(manualLottoAmount);
        LottoOutput.purchaseCount(manualLottoAmount, amount);
        lottoList.addAll(lottoPurchaseService.purchaseLotto(amount));
        return lottoList;
    }

    private List<Lotto> purchaseManualLotto() {
        LottoOutput.purchaseManualAmount();
        Amount manualAmount = new Amount(LottoInput.purchaseManualAmount());
        LottoOutput.manualLottoNumbers();
        return lottoPurchaseService.purchaseLotto(inputLottoNumberSet(manualAmount));
    }

    private static List<LottoNumberSet> inputLottoNumberSet(final Amount manualAmount) {
        List<LottoNumberSet> lottoNumberSets = new ArrayList<>();
        for (int i = 0; i < manualAmount.amount(); i++) {
            lottoNumberSets.add(LottoNumberSet.createLottoNumberSet(LottoInput.lottoNumbers(LottoNumberSet.LOTTONUMBERSET_DELIMITER)));
        }
        return lottoNumberSets;
    }
}
