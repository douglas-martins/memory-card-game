package br.univali.kob.model;


public class Card {
    private final CardType cardType;

    private Boolean isShowing;

    public Card (CardType cardType, Boolean isShowing) {
        this.isShowing = isShowing;
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Boolean getShowing() {
        return isShowing;
    }

    public void setShowing(Boolean showing) {
        isShowing = showing;
    }

    public String getImagePathToDraw() {
        return this.isShowing ? this.cardType.getVisible() : this.cardType.getHide();
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardType=" + cardType +
                ", isShowing=" + isShowing +
                '}';
    }
}
