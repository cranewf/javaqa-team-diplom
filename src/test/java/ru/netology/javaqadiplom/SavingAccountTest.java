package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test //пополнение баланса на сумму в пределах максимально допустимых значений
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test  //пополнение баланса на сумму за пределами максимально допустимых значений
    public void shouldNotAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(13_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test  //пополнение баланса до максимально допустимой суммы
    public void shouldAddUpToMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test //остаток после оплаты в пределах минимально допустимых значений
    public void shouldPayLessThanMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertTrue(account.pay(500));
    }

    @Test  //остаток после оплаты меньше минимально допустимых значений
    public void shouldNotPayMoreThanMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.pay(5_000));
    }

    @Test //оплата с карты до минимально допустимой суммы
    public void shouldPayUpToMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertTrue(account.pay(1_000));
    }

    @Test  //расчет процентной ставки при положительном балансе
    public void shouldCalculateRatePositiveBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(100, account.yearChange());
    }

    @Test  //расчет процентной ставки при отрицательном балансе
    public void shouldCalculateRateNegativeBalance() {
        SavingAccount account = new SavingAccount(
                -2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test  //положительная процентная ставка
    public void changeCalculatePositiveRate() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                25
        );

        Assertions.assertEquals(25, account.getRate());
    }

    @Test  //выкидывание исключения при отрицательной процентной ставке
    public void changeCalculateNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -25
            );
        });
    }

    @Test //выкидывание исключения если минимальный баланс больше максимального
    public void shouldNotPayMimBalanceMoreThenMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    2_000,
                    10_000,
                    5_000,
                    5
            );
        });
    }
}
