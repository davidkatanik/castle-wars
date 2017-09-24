package davidkatanik.vsb.cz.game;

import java.util.HashMap;
import java.util.Map;

import davidkatanik.vsb.cz.gameUtils.CardTargetTypes;
import davidkatanik.vsb.cz.gameUtils.CardTypes;
import davidkatanik.vsb.cz.gameCard.Ability;
import davidkatanik.vsb.cz.gameCard.Card;
import davidkatanik.vsb.cz.gameCard.CardMapper;
import davidkatanik.vsb.cz.gameCard.ICardMapper;

/**
 * Created by David on 07.11.2015.
 * This is class for business logic of application
 * TODO: Make interface for better access.
 * TODO: Make javadoc maybe.
 */
public class Game {
    private static final int NUM_OF_CARDS = 6;
    private Player red;
    private Player blue;
    public Map<CardTypes, Card> cards = new HashMap<>();
    public ICardMapper cardMapper = new CardMapper();
    private int round = 1;
    /**
     * 0 as red and 1 as blue player
     */
    private int actualPlayer;

    public Game(String redName, String blueName) {

        red = new Player();
        red.setName(redName);
        blue = new Player();
        blue.setName(blueName);
        actualPlayer = 0;
        initCards();
        initProperties(blue);
        initProperties(red);
        initPlayerCards(blue);
        initPlayerCards(red);
    }

    /**
     * Initialization of player cards.
     * @param player for whom has to be initialized cards.
     */
    private void initPlayerCards(Player player) {
        for (int i = 0; i < NUM_OF_CARDS; i++) {
            Card card = generateRandomCard();
            player.getCards().add(card);
        }
    }

    /**
     * Initialization of Player properties.
     * @param player for whom has to be initialized his properties.
     */
    private void initProperties(Player player) {
        int fixed = 2;
        int stock = 5;
        int castle = 30;
        int fence = 10;

        player.setBuilders(fixed);
        player.setBricks(stock);
        player.setSoldiers(fixed);
        player.setWeapons(stock);
        player.setMagi(fixed);
        player.setCrystals(stock);
        player.setCastle(castle);
        player.setFence(fence);
    }

    private void initCards() {
        for (CardTypes type : CardTypes.values()) {
            Card card = generateCard(type);
            cards.put(type, card);
        }
    }

    public Card generateCard(CardTypes type) {
        return cardMapper.mapCard(type);
    }

    public Card generateRandomCard() {
        int randomNum = (int) (Math.random() * CardTypes.values().length);

        return generateCard(CardTypes.values()[randomNum]);
    }

    public void nextMove(Card card) {
        if (checkIsAvailable(card)) {
            applyCard(card);
            checkNotNegativeProperties(getActualPlayer());
            checkNotNegativeProperties(getNonActualPlayer());
        }

        if (checkWinner()!=null)
            return;
        changeUsedCard(card, getActualPlayer());
        switchPlayer();
        collectStock();
    }

    private boolean checkResources(Card card, Player player) {
        CardTargetTypes targetType = card.getNeededResources().getTargetType();
        int power = card.getNeededResources().getPower();
        switch (targetType) {
            case WEAPONS: {
                return player.getWeapons() >= power;
            }
            case BRICKS: {
                return player.getBricks() >= power;
            }
            case CRYSTALS: {
                return player.getCrystals() >= power;
            }
        }
        return false;
    }

    public boolean checkIsAvailable(Card card) {
        return checkResources(card, getActualPlayer());
    }

    public void collectStock() {
        Player p = getActualPlayer();
        p.setBricks(p.getBricks() + p.getBuilders());
        p.setWeapons(p.getWeapons() + p.getSoldiers());
        p.setCrystals(p.getCrystals() + p.getMagi());
    }

    private void checkNotNegativeProperties(Player p) {
        if (p.getBuilders() < 0) p.setBuilders(0);
        if (p.getBricks() < 0) p.setBricks(0);
        if (p.getSoldiers() < 0) p.setSoldiers(0);
        if (p.getWeapons() < 0) p.setWeapons(0);
        if (p.getMagi() < 0) p.setMagi(0);
        if (p.getCrystals() < 0) p.setCrystals(0);
        if (p.getFence() < 0) p.setFence(0);
    }

    public Player checkWinner() {
        if (blue.getCastle() >= 100 || red.getCastle() <= 0) {
            return blue;
        } else if (blue.getCastle() <= 0 || red.getCastle() >= 100) {
            return red;
        } else {
            return null;
        }
    }

    private void applyCard(Card card) {
        if (!card.isTransactional()) {
            for (Ability ability : card.getAbilities()) {
                if (card.isAffectHome()) {
                    applyAbility(ability, true, false);
                } else {
                    applyAbility(ability, false, false);
                }
            }
        } else {
            for (Ability ability : card.getAbilities()) {
                applyAbility(ability, false, true);
            }
        }
        card.getNeededResources().setPower(-card.getNeededResources().getPower());
        applyAbility(card.getNeededResources(), true, false);
    }

    private void changeUsedCard(Card card, Player player) {
        int index = player.getCards().indexOf(card);
        player.getCards().remove(index);
        player.getCards().add(index, generateRandomCard());
    }

    private void applyAbility(Ability ability, boolean increase, boolean transactional) {
        CardTargetTypes targetProperty = ability.getTargetType();
        int power = ability.getPower();
        switch (targetProperty) {
            case BUILDERS: {
                if (increase) {
                    getActualPlayer().setBuilders(getActualPlayer().getBuilders() + power);
                } else {
                    getNonActualPlayer().setBuilders(getNonActualPlayer().getBuilders() - power);
                    if (transactional) {
                        getActualPlayer().setBuilders(getActualPlayer().getBuilders() + power);
                    }
                }
                break;
            }
            case BRICKS: {
                if (increase) {
                    getActualPlayer().setBricks(getActualPlayer().getBricks() + power);
                } else {
                    getNonActualPlayer().setBricks(getNonActualPlayer().getBricks() - power);
                    if (transactional) {
                        getActualPlayer().setBricks(getActualPlayer().getBricks() + power);
                    }
                }
                break;
            }
            case CASTLE: {
                if (increase) {
                    getActualPlayer().setCastle(getActualPlayer().getCastle() + power);
                } else {
                    Player nonActual = getNonActualPlayer();
                    if (power == 40) {
                        nonActual.setCastle(nonActual.getCastle() - power / 10);
                        getActualPlayer().setCastle(getActualPlayer().getCastle() + (power / 10) * 2);
                        return;
                    } else if (power == 10) {
                        nonActual.setCastle(nonActual.getCastle() - power);
                        return;
                    } else if (power == 100) {
                        nonActual.setCastle(nonActual.getCastle() - power / 100);
                        getActualPlayer().setCastle(getActualPlayer().getCastle() + power / 100);
                        return;
                    } else if (nonActual.getFence() > 0) {
                        while (power > 0) {
                            nonActual.setFence(nonActual.getFence() - 1);
                            power--;
                            if (nonActual.getFence() < 1)
                                break;
                        }
                    }
                    nonActual.setCastle(nonActual.getCastle() - power);
                    if (transactional) {
                        getActualPlayer().setCastle(getActualPlayer().getCastle() + power);
                    }
                }
                break;
            }
            case CRYSTALS: {
                if (increase) {
                    getActualPlayer().setCrystals(getActualPlayer().getCrystals() + power);
                } else {
                    getNonActualPlayer().setCrystals(getNonActualPlayer().getCrystals() - power);
                    if (transactional) {
                        getActualPlayer().setCrystals(getActualPlayer().getCrystals() + power);
                    }
                }
                break;
            }
            case FENCE: {
                if (increase) {
                    if (power == 4) {
                        getActualPlayer().setFence(getActualPlayer().getFence() - power);
                        return;
                    }
                    getActualPlayer().setFence(getActualPlayer().getFence() + power);
                } else {
                    getNonActualPlayer().setFence(getNonActualPlayer().getFence() - power);
                    if (transactional) {
                        getActualPlayer().setFence(getActualPlayer().getFence() + power);
                    }
                }
                break;
            }
            case MAGI: {
                if (increase) {
                    getActualPlayer().setMagi(getActualPlayer().getMagi() + power);
                } else {
                    getNonActualPlayer().setMagi(getNonActualPlayer().getMagi() - power);
                    if (transactional) {
                        getActualPlayer().setMagi(getActualPlayer().getMagi() + power);
                    }
                }
                break;
            }
            case SOLDIERS: {
                if (increase) {
                    getActualPlayer().setSoldiers(getActualPlayer().getSoldiers() + power);
                } else {
                    getNonActualPlayer().setSoldiers(getNonActualPlayer().getSoldiers() - power);
                    if (transactional) {
                        getActualPlayer().setSoldiers(getActualPlayer().getSoldiers() + power);
                    }
                }
                break;
            }
            case WEAPONS: {
                if (increase) {
                    getActualPlayer().setWeapons(getActualPlayer().getWeapons() + power);
                } else {
                    getNonActualPlayer().setWeapons(getNonActualPlayer().getWeapons() - power);
                    if (transactional) {
                        getActualPlayer().setWeapons(getActualPlayer().getWeapons() + power);
                    }
                }
                break;
            }
        }
    }

    private void switchPlayer() {
        if (actualPlayer > 0) {
            actualPlayer = 0;
            round++;
        } else {
            actualPlayer = 1;
        }
    }

    public Player getActualPlayer() {
        return (actualPlayer > 0) ? blue : red;
    }

    public Player getNonActualPlayer() {
        return (actualPlayer > 0) ? red : blue;
    }

    public boolean isPlayerHome(Player player) {
        return player.equals(red);
    }

    public Player getRed() {
        return red;
    }

    public Player getBlue() {
        return blue;
    }

    public int getRound() {
        return round;
    }
}
