package com.originalgames.mitsurin.card;

public class Card {
    private String cardName;
    private byte cardCost;
    private short subCardCost;
    // TODO カードの属性を管理するフィールドを追加する

    // コンストラクタ
    // TODO カードの属性を追加したらコンストラクタから代入するようにする
    public Card(String cardName, byte cardCost, byte subCardCost) {
        this.cardName = cardName;
        this.cardCost = cardCost;
        this.subCardCost = subCardCost;
    }

    public String getCardName() {
        return cardName;
    }

    public byte getCardCost() {
        return cardCost;
    }

    public short getSubCardCost() {
        return subCardCost;
    }
}
