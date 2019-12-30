package com.originalgames.mitsurin.card;

public class Card {
    private String cardName;
    private byte cardCost;
    private byte subCardCost1;
    private byte subCardCost2;
    // TODO カードの属性を管理するフィールドを追加する

    // コンストラクタ
    // TODO カードの属性を追加したらコンストラクタから代入するようにする
    public Card(String cardName, byte cardCost, byte subCardCost1, byte subCardCost2) {
        this.cardName = cardName;
        this.cardCost = cardCost;
        this.subCardCost1 = subCardCost1;
        this.subCardCost2 = subCardCost2;
    }

    public String getCardName() {
        return cardName;
    }

    public byte getCardCost() {
        return cardCost;
    }

    public byte getSubCardCost1() {
        return subCardCost1;
    }

    public byte getSubCardCost2() {
        return subCardCost2;
    }
}
