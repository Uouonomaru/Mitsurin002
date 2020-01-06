package com.originalgames.mitsurin.card;

import com.originalgames.mitsurin.log.ErrorWriter;

import java.util.ArrayList;

public class Card {
    private final int cardId;               // カードID
    private String cardName;                // カード名
    // byte型は8bit(1byte)の整数型
    private byte cost;                      // カードコスト
    private byte subCost;                   // カードサブコスト
    private CardTypeCombi cardTypeCombi;    // カードの属性

    // error
    private ErrorWriter errorWriter;

    // コンストラクタ
    public Card(int cardId, ArrayList<Object> status) {
        errorWriter = new ErrorWriter(this);
        if(status.size() < 4) {
            errorWriter.appendErrorLog("amount of argument[status] is " + status.size());
            errorWriter.appendErrorLog("force quit");
            errorWriter.closeStream();
            System.exit(1);
        }

        this.cardId = cardId;
        this.cardName = String.valueOf(status.get(0));
        this.cost = Byte.parseByte(String.valueOf(status.get(1)));
        this.subCost = Byte.parseByte(String.valueOf(status.get(2)));
        try {
            this.cardTypeCombi = CardTypeCombi.getCardTypeCombi(String.valueOf(status.get(3)));
        }catch (IllegalArgumentException e) {
            errorWriter.appendErrorLog(String.valueOf(e));
            errorWriter.appendErrorLog("force quit");
            errorWriter.closeStream();
            System.exit(1);
        }
    }

    public int getCardId() {
        return cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public byte getCost() {
        return cost;
    }

    public void setCost(byte cost) {
        this.cost = cost;
    }

    public byte getSubCost() {
        return subCost;
    }

    public void setSubCost(byte subCost) {
        this.subCost = subCost;
    }

    public CardTypeCombi getCardTypeCombi() {
        return cardTypeCombi;
    }

    public void setCardTypeCombi(CardTypeCombi cardTypeCombi) {
        this.cardTypeCombi = cardTypeCombi;
    }
}

