package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test // выкидываем исключение при отрицательном Rate
    public void giveMeNegativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(
                20_000,
                20_000,
                -15
        ));
    }

    @Test // выкидываем исключение при отрицательном InitialBalance
    public void giveMeNegativeInitialBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(
                -21_000,
                20_000,
                15
        ));
    }

    @Test // выкидываем исключение при отрицательном CreditLimit
    public void giveMeNegativeCreditLimit() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(
                20_000,
                -20_000,
                15
        ));
    }

    @Test // Отказ при оплате со значением 0
    public void shouldPayToEqualToZeroBalance() {
        CreditAccount account = new CreditAccount(
                5_000,
                20_000,
                15
        );

        account.pay(0);

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertFalse(account.pay(0));
    }

    @Test // Отказ оплаты при отрицательном значении
    public void shouldPayToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                5_000,
                20_000,
                15
        );

        account.pay(-1);

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertFalse(account.pay(-1));
    }

    @Test // Оплата со значением больше нуля, но В пределах лимита
    public void shouldPayToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                5_000,
                20_000,
                15
        );

        account.pay(10_000);

        Assertions.assertEquals(-5_000, account.getBalance());
        Assertions.assertTrue(account.pay(10_000));
    }

    @Test // Оплата со значением больше нуля, но ЗА пределами лимита
    public void shouldPayToOverCreditLimit() {
        CreditAccount account = new CreditAccount(
                5_000,
                20_000,
                15
        );

        account.pay(30_000);

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertFalse(account.pay(30_000));
    }

    @Test // Пополнение на значение "ноль". Баланс не меняется
    public void shouldAddToEqualToZeroBalance() {
        CreditAccount account = new CreditAccount(
                5_000,
                20_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertFalse(account.add(0));
    }

    @Test // Пополнение на значение "минус 1". Баланс не меняется
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                5_000,
                20_000,
                15
        );

        account.add(-1);

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertFalse(account.add(-1));
    }

    @Test // Пополнение при балансе "ноль". Значение больше нуля
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
        Assertions.assertTrue(account.add(3_000));
    }

    @Test // Пополнение при отрицательном балансе. Значение больше нуля
    public void shouldAddToPositiveBalanceSecond() {
        CreditAccount account = new CreditAccount(
                -5_000,
                20_000,
                15
        );

        account.add(10_000);

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertTrue(account.add(10_000));
    }

    @Test // Пополнение при положительном балансе. Значение больше нуля
    public void shouldAddToPositiveBalanceEqualToTheLimit() {
        CreditAccount account = new CreditAccount(
                5_000,
                20_000,
                15
        );

        account.add(5_000);

        Assertions.assertEquals(10_000, account.getBalance());
        Assertions.assertTrue(account.add(5_000));
    }

    @Test // Начисление процентов при балансе "ноль". Проценты не начисляются
    public void interestAccrualOnBalanceEqualToZero() {
        CreditAccount account = new CreditAccount(
                0,
                20_000,
                15
        );

        int actual = account.yearChange();
        int expected = 0;

        Assertions.assertEquals(expected, actual);
    }

    @Test // Начисление процентов при балансе меньше нуля. Проценты начисляются
    public void interestAccrualWhenBalanceIsLessThanZero() {
        CreditAccount account = new CreditAccount(
                -200,
                20_000,
                15
        );

        int actual = account.yearChange();
        int expected = -30;

        Assertions.assertEquals(expected, actual);
    }

    @Test // Начисление процентов при балансе больше "нуля", проценты не начисляются
    public void interestAccrualWhenBalanceIsGreaterThanZero() {
        CreditAccount account = new CreditAccount(
                200,
                20_000,
                15
        );

        int actual = account.yearChange();
        int expected = 0;

        Assertions.assertEquals(expected, actual);
    }

}
