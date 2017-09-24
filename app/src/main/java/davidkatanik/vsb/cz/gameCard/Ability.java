package davidkatanik.vsb.cz.gameCard;

import davidkatanik.vsb.cz.gameUtils.CardTargetTypes;

/**
 * Created by David Katanik on 07.11.2015.
 *
 */
public class Ability {
    private CardTargetTypes targetType;
    private int power;

    public Ability(CardTargetTypes targetType, int power) {
        this.targetType = targetType;
        this.power = power;
    }

    public CardTargetTypes getTargetType() {
        return targetType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
