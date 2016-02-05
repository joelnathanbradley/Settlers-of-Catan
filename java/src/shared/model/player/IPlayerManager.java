package shared.model.player;

import shared.exceptions.DevCardException;
import shared.exceptions.FailedToRandomizeException;
import shared.exceptions.MoveRobberException;
import shared.exceptions.PlayerExistsException;
import shared.model.cards.Card;
import shared.model.game.trade.TradeType;
import shared.model.cards.resources.ResourceCard;

import javax.security.sasl.AuthenticationException;
import java.util.List;

/**
 * Created by corne on 1/30/2016.
 */
public interface IPlayerManager {

    //Manager Methods
    //==========================================================
    /**
     * Randomize player order (turn order)
     * @throws FailedToRandomizeException
     */
    void randomizePlayers() throws FailedToRandomizeException;

    /**
     * Authenticates a player
     * @param id ID of the player
     * @return True if player authentication is successful
     * @throws AuthenticationException
     */
    boolean authenticatePlayer(int id) throws AuthenticationException;

    /**
     * Gets a player by id
     * @param id ID of the Player
     * @return The Player with the specified ID
     * @throws PlayerExistsException
     */
    Player getPlayerByID(int id) throws PlayerExistsException;

    /**
     * Gets a player by index
     * @param index Index of the player
     * @return Player at index
     * @throws PlayerExistsException
     */
    Player getPlayerByIndex(int index) throws PlayerExistsException;


    //Can Do & Do
    //==========================================================
    /**
     * Determine if Player can discard cards
     * Checks resource cards, robber position,
     *        and hexes from dice roll
     * @param id ID of the player
     * @return True if Player can discard cards
     */
    boolean canDiscardCards(int id) throws PlayerExistsException;

    /**
     * Action - Player discards cards
     * @param id ID of the player
     * @param cards Cards to be discarded
     */
    void discardCards(int id, List<Card> cards) throws PlayerExistsException;

    /**
     * Determine if Player can offer a trade
     * Checks Player turn, phase, and resources
     * @param id ID of the player
     * @return True if Player can offer a trade
     */
    boolean canOfferTrade(int id) throws PlayerExistsException;

    /**
     * Determine if Player can perform maritime trade
     * Checks Player turn, phase, resources, and ports
     * @param id ID of the player
     * @param type Type of trade
     * @return True if Player can perform a maritime trade
     */
    boolean canMaritimeTrade(int id, TradeType type) throws PlayerExistsException;

    /**
     * Determine if Player can buy a dev card
     * Checks Player turn, phase, and resources
     * @param id ID of the player
     * @return True if Player can buy a dev card
     */
    boolean canBuyDevCard(int id) throws PlayerExistsException;

    /**
     * Action - Player buys a dev card
     * @param id ID of the player
     */
    void buyDevCard(int id) throws PlayerExistsException;

    /**
     * Determine if Player can play Year of Plenty
     * Checks Player turn, and dev cards
     * @param id ID of the player
     * @return True if Player can play Year of Plenty
     */
    boolean canUseYearOfPlenty(int id) throws PlayerExistsException;

    /**
     * Action - Player plays Year of Plenty
     * @param id ID of the player
     */
    void useYearOfPlenty(int id) throws DevCardException,PlayerExistsException;

    /**
     * Determine if Player can play Road Builder
     * Checks Player turn, and dev cards
     * @param id ID of the player
     * @return True if Player can play Road Builder
     */
    boolean canUseRoadBuilder(int id) throws PlayerExistsException;

    /**
     * Action - Player plays Road Builder
     * @param id ID of the player
     */
    void useRoadBuilder(int id) throws DevCardException,PlayerExistsException;

    /**
     * Determine if Player can play Soldier
     * Checks Player turn, and dev cards
     * @param id ID of the player
     * @return True if Player can play Soldier
     */
    boolean canUseSoldier(int id) throws PlayerExistsException;

    /**
     * Action - Player plays Soldier
     * @param id ID of the player
     */
    void useSoldier(int id) throws DevCardException,PlayerExistsException;

    /**
     * Determine if Player can play Monopoly
     * Checks Player turn, and dev cards
     * @param id ID of the player
     * @return True if Player can play Monopoly
     */
    boolean canUseMonopoly(int id) throws PlayerExistsException;

    /**
     * Action - Player plays Monopoly
     * @param id ID of the player
     */
    void useMonopoly(int id) throws DevCardException,PlayerExistsException;

    /**
     * Determine if Player can play Monument
     * Checks Player turn, and dev cards
     * @param id ID of the player
     * @return True if Player can play Monument
     */
    boolean canUseMonument(int id) throws PlayerExistsException;

    /**
     * Action - Player plays Monument
     * @param id ID of the player
     */
    void useMonument(int id) throws DevCardException,PlayerExistsException;

    /**
     * Determine if Player can place the Robber
     * Checks Player turn, event(ie roll 7 or play Soldier)
     * @param id ID of the player
     * @return True if Player can place the Robber
     */
    boolean canPlaceRobber(int id) throws PlayerExistsException;

    /**
     * Action - Player places the Robber
     * @param id ID of the player
     */
    void placeRobber(int id) throws MoveRobberException,PlayerExistsException;

    /**
     * Determine if Player can build a road
     * Checks resource cards
     * @param id ID of the player
     * @return True if Player can build a road
     */
    boolean canBuildRoad(int id) throws PlayerExistsException;

    /**
     * Action - Player builds a road
     * @param id ID of the player
     */
    void buildRoad(int id) throws PlayerExistsException;

    /**
     * Determine if Player can build a settlement
     * Checks resource cards
     * @param id ID of the player
     * @return True if Player can build a settlement
     */
    boolean canBuildSettlement(int id) throws PlayerExistsException;

    /**
     * Action - Player builds a settlement
     * @param id ID of the player
     */
    void buildSettlement(int id) throws PlayerExistsException;

    /**
     * Determine if Player can build a city
     * Checks resource cards
     * @param id ID of the player
     * @return True if Player can build a city
     */
    boolean canBuildCity(int id) throws PlayerExistsException;

    /**
     * Action - Player builds a city
     * @param id ID of the player
     */
    void buildCity(int id) throws PlayerExistsException;
}
