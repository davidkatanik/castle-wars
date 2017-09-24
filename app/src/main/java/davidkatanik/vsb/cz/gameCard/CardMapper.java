package davidkatanik.vsb.cz.gameCard;

import java.util.LinkedList;
import java.util.List;

import davidkatanik.vsb.cz.gameUtils.CardTargetTypes;
import davidkatanik.vsb.cz.gameUtils.CardTypes;

/**
 * Created by David on 07.11.2015.
 *
 */
public class CardMapper implements ICardMapper {

    @Override
    public Card mapCard(CardTypes type) {
        Card card = null;
        switch (type) {
            case PLATOON: {
                card = mapPlatoon();
                card.setType(CardTypes.PLATOON);
                break;
            }
            case RIDER: {
                card = mapRider();
                card.setType(CardTypes.RIDER);
                break;
            }
            case BANSHEE: {
                card = mapBanshee();
                card.setType(CardTypes.BANSHEE);
                break;
            }
            case ARCHER: {
                card = mapArcher();
                card.setType(CardTypes.ARCHER);
                break;
            }
            case SWAT: {
                card = mapSwat();
                card.setType(CardTypes.SWAT);
                break;
            }
            case CATAPULT: {
                card = mapCatapult();
                card.setType(CardTypes.CATAPULT);
                break;
            }
            case DRAGON: {
                card = mapDragon();
                card.setType(CardTypes.DRAGON);
                break;
            }
            case SABOTEUR: {
                card = mapSaboteur();
                card.setType(CardTypes.SABOTEUR);
                break;
            }
            case CRUSH_BRICKS: {
                card = mapCrushBricks();
                card.setType(CardTypes.CRUSH_BRICKS);
                break;
            }
            case CRUSH_CRYSTALS: {
                card = mapCrushCrystals();
                card.setType(CardTypes.CRUSH_CRYSTALS);
                break;
            }
            case CRUSH_WEAPONS: {
                card = mapCrushWeapons();
                card.setType(CardTypes.CRUSH_WEAPONS);
                break;
            }
            case THIEF: {
                card = mapThief();
                card.setType(CardTypes.THIEF);
                break;
            }
            case CURSE: {
                card = maCurse();
                card.setType(CardTypes.CURSE);
                break;
            }
            case WAIN: {
                card = mapWain();
                card.setType(CardTypes.WAIN);
                break;
            }
            case CONJURE_BRICKS: {
                card = mapConjureBrick();
                card.setType(CardTypes.CONJURE_BRICKS);
                break;
            }
            case CONJURE_CRYSTALS: {
                card = mapConjureCrystals();
                card.setType(CardTypes.CONJURE_CRYSTALS);
                break;
            }
            case CONJURE_WEAPONS: {
                card = mapConjureWeapons();
                card.setType(CardTypes.CONJURE_WEAPONS);
                break;
            }
            case RECRUIT: {
                card = mapRecruit();
                card.setType(CardTypes.RECRUIT);
                break;
            }
            case SCHOOL: {
                card = mapSchool();
                card.setType(CardTypes.SCHOOL);
                break;
            }
            case SORCERER: {
                card = mapSorcerer();
                card.setType(CardTypes.SORCERER);
                break;
            }
            case PIXIES: {
                card = mapPixies();
                card.setType(CardTypes.PIXIES);
                break;
            }
            case BABYLON: {
                card = mapBabylon();
                card.setType(CardTypes.BABYLON);
                break;
            }
            case FENCE: {
                card = mapFence();
                card.setType(CardTypes.FENCE);
                break;
            }
            case DEFENCE: {
                card = mapDefence();
                card.setType(CardTypes.DEFENCE);
                break;
            }
            case FORT: {
                card = mapFort();
                card.setType(CardTypes.FORT);
                break;
            }
            case TOWER: {
                card = mapTower();
                card.setType(CardTypes.TOWER);
                break;
            }
            case BASE: {
                card = mapBase();
                card.setType(CardTypes.BASE);
                break;
            }
            case WALL: {
                card = mapWall();
                card.setType(CardTypes.WALL);
                break;
            }
            case RESERVE: {
                card = mapReserve();
                card.setType(CardTypes.RESERVE);
                break;
            }
        }
        return card;
    }

    private Card mapAttack(CardTargetTypes target, int power, CardTargetTypes resource, int stock) {
        Card card = new Card();
        Ability ability = new Ability(target, power);
        card.getAbilities().add(ability);
        card.setAffectHome(false);
        card.setTransactional(false);
        Ability resources = new Ability(resource, stock);
        card.setNeededResources(resources);
        return card;
    }

    private Card mapResources(CardTargetTypes target, int power, CardTargetTypes resource, int stock) {
        Card card = new Card();
        Ability ability = new Ability(target, power);
        card.getAbilities().add(ability);
        card.setAffectHome(true);
        card.setTransactional(false);
        Ability resources = new Ability(resource, stock);
        card.setNeededResources(resources);
        return card;
    }

    private Card mapPlatoon() {
        return mapAttack(CardTargetTypes.CASTLE, 6, CardTargetTypes.WEAPONS, 4);
    }

    private Card mapRider() {
        return mapAttack(CardTargetTypes.CASTLE, 4, CardTargetTypes.WEAPONS, 2);
    }

    private Card mapBanshee() {
        return mapAttack(CardTargetTypes.CASTLE, 32, CardTargetTypes.WEAPONS, 28);

    }

    private Card mapArcher() {
        return mapAttack(CardTargetTypes.CASTLE, 2, CardTargetTypes.WEAPONS, 1);
    }

    private Card mapSwat() {
        return mapAttack(CardTargetTypes.CASTLE, 10, CardTargetTypes.WEAPONS, 18);
    }

    private Card mapCatapult() {
        return mapAttack(CardTargetTypes.CASTLE, 12, CardTargetTypes.WEAPONS, 10);
    }

    private Card mapDragon() {
        return mapAttack(CardTargetTypes.CASTLE, 25, CardTargetTypes.WEAPONS, 21);
    }

    private Card mapCrushBricks() {
        return mapAttack(CardTargetTypes.BRICKS, 8, CardTargetTypes.CRYSTALS, 4);
    }

    private Card mapCrushCrystals() {
        return mapAttack(CardTargetTypes.CRYSTALS, 8, CardTargetTypes.CRYSTALS, 4);
    }

    private Card mapCrushWeapons() {
        return mapAttack(CardTargetTypes.WEAPONS, 8, CardTargetTypes.CRYSTALS, 4);
    }

    private Card mapSaboteur() {
        Card card = new Card();
        Ability ability = new Ability(CardTargetTypes.CRYSTALS, 4);
        Ability ability2 = new Ability(CardTargetTypes.WEAPONS, 4);
        Ability ability3 = new Ability(CardTargetTypes.BRICKS, 4);
        card.getAbilities().add(ability);
        card.getAbilities().add(ability2);
        card.getAbilities().add(ability3);
        card.setAffectHome(false);
        card.setTransactional(false);
        Ability resources = new Ability(CardTargetTypes.WEAPONS, 12);
        card.setNeededResources(resources);
        return card;
    }


    private Card mapThief() {
        Card card = new Card();
        Ability ability = new Ability(CardTargetTypes.CRYSTALS, 5);
        Ability ability2 = new Ability(CardTargetTypes.WEAPONS, 5);
        Ability ability3 = new Ability(CardTargetTypes.BRICKS, 5);
        card.getAbilities().add(ability);
        card.getAbilities().add(ability2);
        card.getAbilities().add(ability3);
        card.setAffectHome(false);
        card.setTransactional(true);
        Ability resources = new Ability(CardTargetTypes.WEAPONS, 15);
        card.setNeededResources(resources);
        return card;
    }

    private Card maCurse() {
        Card card = new Card();
        List<Ability> abilities = new LinkedList<>();
        abilities.add(new Ability(CardTargetTypes.BUILDERS, 1));
        abilities.add(new Ability(CardTargetTypes.BRICKS, 1));
        abilities.add(new Ability(CardTargetTypes.SOLDIERS, 1));
        abilities.add(new Ability(CardTargetTypes.WEAPONS, 1));
        abilities.add(new Ability(CardTargetTypes.MAGI, 1));
        abilities.add(new Ability(CardTargetTypes.CRYSTALS, 1));
        abilities.add(new Ability(CardTargetTypes.CASTLE, 100));
        abilities.add(new Ability(CardTargetTypes.FENCE, 1));
        card.getAbilities().addAll(abilities);
        card.setAffectHome(false);
        card.setTransactional(true);
        Ability resources = new Ability(CardTargetTypes.CRYSTALS, 25);
        card.setNeededResources(resources);
        return card;
    }

    private Card mapWain() {
        Card card = new Card();
        Ability ability = new Ability(CardTargetTypes.CASTLE, 40);
        card.getAbilities().add(ability);
        card.setAffectHome(false);
        card.setTransactional(true);
        Ability resources = new Ability(CardTargetTypes.BRICKS, 10);
        card.setNeededResources(resources);
        return card;
    }

    private Card mapConjureBrick() {
        return mapResources(CardTargetTypes.BRICKS, 8, CardTargetTypes.CRYSTALS, 4);
    }

    private Card mapConjureCrystals() {
        return mapResources(CardTargetTypes.CRYSTALS, 8, CardTargetTypes.CRYSTALS, 4);
    }

    private Card mapConjureWeapons() {
        return mapResources(CardTargetTypes.WEAPONS, 8, CardTargetTypes.CRYSTALS, 4);
    }

    private Card mapRecruit() {
        return mapResources(CardTargetTypes.SOLDIERS, 1, CardTargetTypes.WEAPONS, 8);
    }

    private Card mapSchool() {
        return mapResources(CardTargetTypes.BUILDERS, 1, CardTargetTypes.BRICKS, 8);
    }

    private Card mapSorcerer() {
        return mapResources(CardTargetTypes.MAGI, 1, CardTargetTypes.CRYSTALS, 8);
    }

    private Card mapPixies() {
        return mapResources(CardTargetTypes.CASTLE, 22, CardTargetTypes.CRYSTALS, 22);
    }

    private Card mapBabylon() {
        return mapResources(CardTargetTypes.CASTLE, 32, CardTargetTypes.BRICKS, 39);
    }

    private Card mapFence() {
        return mapResources(CardTargetTypes.FENCE, 22, CardTargetTypes.BRICKS, 12);
    }

    private Card mapDefence() {
        return mapResources(CardTargetTypes.FENCE, 6, CardTargetTypes.BRICKS, 3);
    }

    private Card mapFort() {
        return mapResources(CardTargetTypes.CASTLE, 20, CardTargetTypes.BRICKS, 18);
    }

    private Card mapTower() {
        return mapResources(CardTargetTypes.CASTLE, 5, CardTargetTypes.BRICKS, 5);
    }

    private Card mapBase() {
        return mapResources(CardTargetTypes.CASTLE, 2, CardTargetTypes.BRICKS, 1);
    }

    private Card mapWall() {
        return mapResources(CardTargetTypes.FENCE, 3, CardTargetTypes.BRICKS, 1);

    }

    private Card mapReserve() {
        Card card = new Card();
        Ability ability = new Ability(CardTargetTypes.FENCE, 4);
        Ability ability2 = new Ability(CardTargetTypes.CASTLE, 8);
        card.getAbilities().add(ability);
        card.getAbilities().add(ability2);
        card.setAffectHome(true);
        card.setTransactional(false);
        Ability resources = new Ability(CardTargetTypes.BRICKS, 3);
        card.setNeededResources(resources);
        return card;
    }


}
