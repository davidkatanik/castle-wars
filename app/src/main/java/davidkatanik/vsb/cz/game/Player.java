package davidkatanik.vsb.cz.game;

import java.util.ArrayList;
import java.util.List;

import davidkatanik.vsb.cz.gameCard.Card;

/**
 * Created by David on 07.11.2015.
 *
 */
public class Player {
    String name;
    int builders;
    int bricks;
    int soldiers;
    int weapons;
    int magi;
    int crystals;
    int castle;
    int fence;
    List<Card> cards = new ArrayList<>();

    public int getBuilders() {
        return builders;
    }

    public void setBuilders(int builders) {
        this.builders = builders;
    }

    public int getBricks() {
        return bricks;
    }

    public void setBricks(int bricks) {
        this.bricks = bricks;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(int soldiers) {
        this.soldiers = soldiers;
    }

    public int getWeapons() {
        return weapons;
    }

    public void setWeapons(int weapons) {
        this.weapons = weapons;
    }

    public int getMagi() {
        return magi;
    }

    public void setMagi(int magi) {
        this.magi = magi;
    }

    public int getCrystals() {
        return crystals;
    }

    public void setCrystals(int crystals) {
        this.crystals = crystals;
    }

    public int getCastle() {
        return castle;
    }

    public void setCastle(int castle) {
        this.castle = castle;
    }

    public int getFence() {
        return fence;
    }

    public void setFence(int fence) {
        this.fence = fence;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (builders != player.builders) return false;
        if (bricks != player.bricks) return false;
        if (soldiers != player.soldiers) return false;
        if (weapons != player.weapons) return false;
        if (magi != player.magi) return false;
        if (crystals != player.crystals) return false;
        if (castle != player.castle) return false;
        if (fence != player.fence) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        return !(cards != null ? !cards.equals(player.cards) : player.cards != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + builders;
        result = 31 * result + bricks;
        result = 31 * result + soldiers;
        result = 31 * result + weapons;
        result = 31 * result + magi;
        result = 31 * result + crystals;
        result = 31 * result + castle;
        result = 31 * result + fence;
        result = 31 * result + (cards != null ? cards.hashCode() : 0);
        return result;
    }
}
