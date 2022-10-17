package lotto.domain;

import java.util.Objects;

public class Amount {

    private int amount;
    private static final int AMOUNT_MINIMUM = 0;
    private static final String AMOUNT_BOUND_EXCEPTION = "Amount에는 " + AMOUNT_MINIMUM + "이상의 값만 들어올 수 있습니다.";

    public Amount(final int amount) {
        if (amount < AMOUNT_MINIMUM) {
            throw new IllegalArgumentException(AMOUNT_BOUND_EXCEPTION);
        }
        this.amount = amount;
    }

    public int amount() {
        return amount;
    }

    public void minus(final Amount amount) {
        this.amount -= amount.amount;
    }
    public void add(final Amount amount) {
        this.amount += amount.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount1 = (Amount) o;
        return amount == amount1.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return Integer.toString(amount);
    }
}
