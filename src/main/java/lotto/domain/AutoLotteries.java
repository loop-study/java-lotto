package lotto.domain;

import util.Random;

public class AutoLotteries {
    private Lotteries autoLotteries;
    private int quantity;

    public AutoLotteries(int totalQuantity, int manualQuantity) {
        this.autoLotteries = new Lotteries();
        this.quantity = totalQuantity - manualQuantity;
    }

    public Lotteries generateAutoLotteries(Random random) {

        for (int i = 0; i < this.quantity; i++) {
            this.autoLotteries.add(random.generateRandom());
        }

        return this.autoLotteries;
    }

    public int getAutoLotteriesSize() {
        return this.autoLotteries.size();
    }
}