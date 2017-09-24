package davidkatanik.vsb.cz.gameCard;

import davidkatanik.vsb.cz.gameUtils.CardTypes;

/**
 * Created by David on 07.11.2015.
 *
 */
public interface ICardMapper {
    Card mapCard(CardTypes type);
}
