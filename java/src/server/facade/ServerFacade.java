package server.facade;

import com.google.gson.JsonObject;
import server.exceptions.*;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.cards.resources.ResourceCard;
import shared.model.game.trade.TradePackage;

import java.util.List;

/**
 * Created by Kyle 'TMD' Cornelison on 3/10/2016.
 */
public class ServerFacade implements IFacade {
    private static IFacade _instance;

    /**
     * Default Constructor - Private
     */
    private ServerFacade(){
        //Create a new GameManager
        //Create a new PlayerManager
    }

    /**
     * Singleton - get instance method
     * @return
     */
    public static IFacade getInstance(){
        if(_instance == null) {
            _instance = new ServerFacade();
        }
        return _instance;
    }


    /**
     * Logs a player into the server
     *
     * @param username
     * @param password
     * @throws LoginException
     */
    @Override
    public void login(String username, String password) throws LoginException {

    }

    /**
     * Registers a user
     *
     * @param username
     * @param password
     * @throws RegisterException
     */
    @Override
    public void register(String username, String password) throws RegisterException {

    }

    /**
     * Adds an AI to the game
     *
     * @param aiType
     * @throws AddAIException
     */
    @Override
    public void addAI(Object aiType) throws AddAIException {

    }

    /**
     * List the available AI types
     *
     * @throws ListAIException
     */
    @Override
    public void listAI() throws ListAIException {

    }

    /**
     * List the current games
     *
     * @return info on the current games
     * @throws ListException
     */
    @Override
    public JsonObject list() throws ListException {
        return null;
    }

    /**
     * Creates a new game
     *
     * @param name
     * @param randomTiles
     * @param randomNumbers
     * @param randomPorts
     * @return
     * @throws CreateGameException
     */
    @Override
    public JsonObject create(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) throws CreateGameException {
        return null;
    }

    /**
     * Joins a player to the specified game
     *
     * @param gameID
     * @param color
     * @throws JoinGameException
     */
    @Override
    public void join(int gameID, CatanColor color) throws JoinGameException {

    }

    /**
     * Sends a chat message
     *
     * @param player  index of the player
     * @param message
     * @throws SendChatException
     */
    @Override
    public void sendChat(int player, String message) throws SendChatException {

    }

    /**
     * Rolls the specified value
     *
     * @param player index of the player
     * @param value
     * @throws RollNumberException
     */
    @Override
    public void rollNumber(int player, int value) throws RollNumberException {

    }

    /**
     * Robs the specified player
     *
     * @param player      index of the player robbing
     * @param newLocation
     * @param victim      index of the player being robbed
     * @throws RobPlayerException
     */
    @Override
    public void robPlayer(int player, HexLocation newLocation, int victim) throws RobPlayerException {

    }

    /**
     * Ends the current player's turn making it the next player's turn
     *
     * @param player index of the player
     * @throws FinishTurnException
     */
    @Override
    public void finishTurn(int player) throws FinishTurnException {

    }

    /**
     * Buys a new dev card
     *
     * @param player index of the player
     * @throws BuyDevCardException
     */
    @Override
    public void buyDevCard(int player) throws BuyDevCardException {

    }

    /**
     * Handles playing Year of Plenty
     *
     * @param player      index of the player
     * @param resourceOne first resource to receive
     * @param resourceTwo second resource to receive
     * @throws YearOfPlentyException
     */
    @Override
    public void yearOfPlenty(int player, ResourceType resourceOne, ResourceType resourceTwo) throws YearOfPlentyException {

    }

    /**
     * Handles playing Road Building
     *
     * @param player      index of the player
     * @param locationOne location for the first road
     * @param locationTwo location for the second road
     * @throws RoadBuildingException
     */
    @Override
    public void roadBuilding(int player, EdgeLocation locationOne, EdgeLocation locationTwo) throws RoadBuildingException {

    }

    /**
     * Handles playing Soldier
     *
     * @param player      index of the player
     * @param newLocation
     * @param victim      index of the player being robbed
     * @throws SoldierException
     */
    @Override
    public void soldier(int player, HexLocation newLocation, int victim) throws SoldierException {

    }

    /**
     * Hanldes playing Monopoly
     *
     * @param player   index of the player
     * @param resource resource to take
     * @throws MonopolyException
     */
    @Override
    public void monopoly(int player, ResourceType resource) throws MonopolyException {

    }

    /**
     * Handles playing Monument
     *
     * @param player index of the player
     * @throws MonumentException
     */
    @Override
    public void monument(int player) throws MonumentException {

    }

    /**
     * Builds a road
     *
     * @param player   index of the player
     * @param isFree   whether the piece is free
     * @param location
     * @throws BuildRoadException
     */
    @Override
    public void buildRoad(int player, boolean isFree, EdgeLocation location) throws BuildRoadException {

    }

    /**
     * Builds a settlement
     *
     * @param player   index of the player
     * @param isFree   whether the piece is free
     * @param location
     * @throws BuildSettlementException
     */
    @Override
    public void buildSettlement(int player, boolean isFree, VertexLocation location) throws BuildSettlementException {

    }

    /**
     * Builds a city
     *
     * @param player   index of the player
     * @param location
     * @throws BuildCityException
     */
    @Override
    public void buildCity(int player, VertexLocation location) throws BuildCityException {

    }

    /**
     * Offers a trade to the specified player
     *
     * @param player
     * @param recipient
     * @param send
     * @param receive
     * @throws OfferTradeException
     */
    @Override
    public void offerTrade(int player, int recipient, List<ResourceType> send, List<ResourceType> receive) throws OfferTradeException {

    }

    /**
     * Accepts a trade offer
     *
     * @param player     index of the player accepting the trade
     * @param willAccept whether or not the player accepts
     * @throws AcceptTradeException
     */
    @Override
    public void acceptTrade(int player, boolean willAccept) throws AcceptTradeException {

    }

    /**
     * Performs a maritime trade (trade with the bank)
     *
     * @param player index of the player
     * @param ratio  trade ratio [2, 3 or 4]
     * @param give   resource to trade away
     * @param get    resource to get
     * @throws MaritimeTradeException
     */
    @Override
    public void maritimeTrade(int player, int ratio, ResourceType give, ResourceType get) throws MaritimeTradeException {

    }

    /**
     * Discards the specified cards from the player's hand
     *
     * @param player         index of the player discarding
     * @param cardsToDiscard list of cards to be discarded
     * @throws DiscardCardsException
     */
    @Override
    public void discardCards(int player, List<ResourceCard> cardsToDiscard) throws DiscardCardsException {

    }
}