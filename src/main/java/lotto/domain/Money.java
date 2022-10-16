package lotto.domain;

import java.util.Objects;

public class Money {

    private final long money;
    private static final int MONEY_MINIMUM = 0;

    private final static String MONEY_NEGATIVE_EXCEPTION = "Money에는 " + MONEY_MINIMUM + "이상의 값만 들어올 수 있습니다.";

    public Money(final long money) {
        if (money < MONEY_MINIMUM) {
            throw new IllegalArgumentException(MONEY_NEGATIVE_EXCEPTION);
        }
        this.money = money;
    }

    public Money add(final Money money) {
        return new Money(this.money + money.money);
    }

    public Money multiply(final int value) {
        return new Money(this.money * value);
    }

    public Double divide(final Money money) {
        return (double) this.money / money.money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return String.valueOf(money);
    }
}
