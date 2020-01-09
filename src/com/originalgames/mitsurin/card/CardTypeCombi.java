package com.originalgames.mitsurin.card;

public enum CardTypeCombi {
    ATTACK(CardType.AP, CardType.NONE),
    GUARD(CardType.HP, CardType.NONE),
    MAGIC(CardType.MP, CardType.NONE),
    ATTACK_GUARD(CardType.AP, CardType.HP),
    ATTACK_MAGIC(CardType.AP, CardType.MP),
    GUARD_MAGIC(CardType.HP, CardType.MP);

    private CardType type1;
    private CardType type2;

    CardTypeCombi(CardType type1, CardType type2) {
        this.type1 = type1;
        this.type2 = type2;
    }

    public String getCardTypeCombiString(CardTypeCombi cardTypeCombi) {
        return String.valueOf(cardTypeCombi);
    }

    public static CardTypeCombi getCardTypeCombi(String cardTypeCombiString) throws IllegalArgumentException {
        return CardTypeCombi.valueOf(cardTypeCombiString);
    }

    public CardType getType1() {
        return type1;
    }

    public CardType getType2() {
        return type2;
    }
}
