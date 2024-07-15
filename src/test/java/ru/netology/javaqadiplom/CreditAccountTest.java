package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void giveMeNegativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(
                20_000,
                20_000,
                -15
        ));
    }

    @Test
    public void giveMeNegativeInitialBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(
                -20_000,
                20_000,
                15
        ));
    }

    @Test
    public void giveMeNegativeCreditLimit() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(
                20_000,
                -20_000,
                15
        ));
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void shouldPayToOverCreditLimit() {
        CreditAccount account = new CreditAccount(
                5_000,
                20_000,
                15
        );

        account.pay(30_000);

        Assertions.assertEquals(-25_000, account.getBalance());
        Assertions.assertFalse(account.pay(30_000));
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void interestAccrualWhenBalanceIsLessThanZero() {
        CreditAccount account = new CreditAccount(
                -1_000,
                20_000,
                15
        );

        int actual = account.yearChange();
        int expected = 150;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void interestAccrualWhenBalanceIsGreaterThanZero() {
        CreditAccount account = new CreditAccount(
                1_000,
                20_000,
                15
        );

        int actual = account.yearChange();
        int expected = 0;

        Assertions.assertEquals(expected, actual);
    }

}
