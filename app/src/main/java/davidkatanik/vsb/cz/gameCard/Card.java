package davidkatanik.vsb.cz.gameCard;

import java.util.ArrayList;
import java.util.List;

import davidkatanik.vsb.cz.gameUtils.CardTypes;

/**
 * Created by David on 07.11.2015.
 *
 */
public class Card implements Cloneable {
    private List<Ability> abilities = new ArrayList<>();
    private boolean affectHome;
    private boolean transactional;
    private Ability neededResources;
    private CardTypes type;

    public List<Ability> getAbilities() {
        return abilities;
    }

    public boolean isAffectHome() {
        return affectHome;
    }

    public void setAffectHome(boolean affectHome) {
        this.affectHome = affectHome;
    }

    public boolean isTransactional() {
        return transactional;
    }

    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }

    public Ability getNeededResources() {
        return neededResources;
    }

    public void setNeededResources(Ability neededResources) {
        this.neededResources = neededResources;
    }

    public CardTypes getType() {
        return type;
    }

    public void setType(CardTypes type) {
        this.type = type;
    }
}
