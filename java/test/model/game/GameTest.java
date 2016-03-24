package model.game;

import client.facade.Facade;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import shared.definitions.CatanColor;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.exceptions.*;
import shared.locations.*;
import shared.model.bank.InvalidTypeException;
import shared.model.cards.devcards.*;
import shared.model.cards.resources.ResourceCard;
import shared.model.cards.resources.Wood;
import shared.model.game.Game;
import shared.model.game.IGame;
import shared.model.game.TurnTracker;
import shared.model.game.trade.TradePackage;
import shared.model.player.Player;
import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Corbin Byers
 */
public class GameTest {

    private final String json = "{\"deck\":\n" +
            "{\"yearOfPlenty\":\n" +
            "2,\"monopoly\":\n" +
            "2,\"soldier\":\n" +
            "10,\"roadBuilding\":\n" +
            "1,\"monument\":\n" +
            "4},\"map\":\n" +
            "{\"hexGrid\":\n" +
            "{\"hexes\":\n" +
            "[[{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "0,\"y\":\n" +
            "-3},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "1,\"y\":\n" +
            "-3},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "2,\"y\":\n" +
            "-3},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "3,\"y\":\n" +
            "-3},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]}],[{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "-1,\"y\":\n" +
            "-2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "0,\"y\":\n" +
            "-2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Brick\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "1,\"y\":\n" +
            "-2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "3}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Wood\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "2,\"y\":\n" +
            "-2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "3}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "3}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "3,\"y\":\n" +
            "-2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "2,\"ownerID\":\n" +
            "0}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}}]}],[{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "-2,\"y\":\n" +
            "-1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Brick\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "-1,\"y\":\n" +
            "-1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Wood\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "0,\"y\":\n" +
            "-1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Ore\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "1,\"y\":\n" +
            "-1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "3}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "2}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "3}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "2}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Sheep\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "2,\"y\":\n" +
            "-1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "2,\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "3,\"y\":\n" +
            "-1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "2,\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]}],[{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "-3,\"y\":\n" +
            "0},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Ore\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "-2,\"y\":\n" +
            "0},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Sheep\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "-1,\"y\":\n" +
            "0},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Wheat\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "0,\"y\":\n" +
            "0},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "2}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "2}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "2}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Brick\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "1,\"y\":\n" +
            "0},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "2}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "2}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Wheat\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "2,\"y\":\n" +
            "0},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "2,\"ownerID\":\n" +
            "0}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "3,\"y\":\n" +
            "0},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]}],[{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "-3,\"y\":\n" +
            "1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Wheat\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "-2,\"y\":\n" +
            "1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "1}}]},{\"landtype\":\n" +
            "\"Sheep\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "-1,\"y\":\n" +
            "1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "2}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "3}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "3}}]},{\"landtype\":\n" +
            "\"Wood\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "0,\"y\":\n" +
            "1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "2}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "2}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Sheep\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "1,\"y\":\n" +
            "1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "2,\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "2,\"y\":\n" +
            "1},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "2,\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]}],[{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "-3,\"y\":\n" +
            "2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Wood\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "-2,\"y\":\n" +
            "2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "3}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "3}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Ore\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "-1,\"y\":\n" +
            "2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "3}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"landtype\":\n" +
            "\"Wheat\",\"isLand\":\n" +
            "true,\"location\":\n" +
            "{\"x\":\n" +
            "0,\"y\":\n" +
            "2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "1,\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "0}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "1,\"y\":\n" +
            "2},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]}],[{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "-3,\"y\":\n" +
            "3},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "-2,\"y\":\n" +
            "3},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "-1,\"y\":\n" +
            "3},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]},{\"isLand\":\n" +
            "false,\"location\":\n" +
            "{\"x\":\n" +
            "0,\"y\":\n" +
            "3},\"vertexes\":\n" +
            "[{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"worth\":\n" +
            "0,\"ownerID\":\n" +
            "-1}}],\"edges\":\n" +
            "[{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}},{\"value\":\n" +
            "{\"ownerID\":\n" +
            "-1}}]}]],\"offsets\":\n" +
            "[3,2,1,0,0,0,0],\"radius\":\n" +
            "4,\"x0\":\n" +
            "3,\"y0\":\n" +
            "3},\"radius\":\n" +
            "4,\"numbers\":\n" +
            "{\"2\":\n" +
            "[{\"x\":\n" +
            "-2,\"y\":\n" +
            "1}],\"3\":\n" +
            "[{\"x\":\n" +
            "-1,\"y\":\n" +
            "2},{\"x\":\n" +
            "0,\"y\":\n" +
            "-1}],\"4\":\n" +
            "[{\"x\":\n" +
            "1,\"y\":\n" +
            "-2},{\"x\":\n" +
            "0,\"y\":\n" +
            "1}],\"5\":\n" +
            "[{\"x\":\n" +
            "1,\"y\":\n" +
            "0},{\"x\":\n" +
            "-2,\"y\":\n" +
            "0}],\"6\":\n" +
            "[{\"x\":\n" +
            "2,\"y\":\n" +
            "0},{\"x\":\n" +
            "-2,\"y\":\n" +
            "2}],\"8\":\n" +
            "[{\"x\":\n" +
            "0,\"y\":\n" +
            "2},{\"x\":\n" +
            "-1,\"y\":\n" +
            "-1}],\"9\":\n" +
            "[{\"x\":\n" +
            "1,\"y\":\n" +
            "-1},{\"x\":\n" +
            "-1,\"y\":\n" +
            "1}],\"10\":\n" +
            "[{\"x\":\n" +
            "1,\"y\":\n" +
            "1},{\"x\":\n" +
            "-1,\"y\":\n" +
            "0}],\"11\":\n" +
            "[{\"x\":\n" +
            "2,\"y\":\n" +
            "-2},{\"x\":\n" +
            "0,\"y\":\n" +
            "0}],\"12\":\n" +
            "[{\"x\":\n" +
            "2,\"y\":\n" +
            "-1}]},\"ports\":\n" +
            "[{\"ratio\":\n" +
            "3,\"validVertex1\":\n" +
            "{\"direction\":\n" +
            "\"SW\",\"x\":\n" +
            "3,\"y\":\n" +
            "-3},\"validVertex2\":\n" +
            "{\"direction\":\n" +
            "\"W\",\"x\":\n" +
            "3,\"y\":\n" +
            "-3},\"orientation\":\n" +
            "\"SW\",\"location\":\n" +
            "{\"x\":\n" +
            "3,\"y\":\n" +
            "-3}},{\"ratio\":\n" +
            "2,\"inputResource\":\n" +
            "\"Wheat\",\"validVertex1\":\n" +
            "{\"direction\":\n" +
            "\"SE\",\"x\":\n" +
            "-1,\"y\":\n" +
            "-2},\"validVertex2\":\n" +
            "{\"direction\":\n" +
            "\"SW\",\"x\":\n" +
            "-1,\"y\":\n" +
            "-2},\"orientation\":\n" +
            "\"S\",\"location\":\n" +
            "{\"x\":\n" +
            "-1,\"y\":\n" +
            "-2}},{\"ratio\":\n" +
            "2,\"inputResource\":\n" +
            "\"Wood\",\"validVertex1\":\n" +
            "{\"direction\":\n" +
            "\"NE\",\"x\":\n" +
            "-3,\"y\":\n" +
            "2},\"validVertex2\":\n" +
            "{\"direction\":\n" +
            "\"E\",\"x\":\n" +
            "-3,\"y\":\n" +
            "2},\"orientation\":\n" +
            "\"NE\",\"location\":\n" +
            "{\"x\":\n" +
            "-3,\"y\":\n" +
            "2}},{\"ratio\":\n" +
            "3,\"validVertex1\":\n" +
            "{\"direction\":\n" +
            "\"NW\",\"x\":\n" +
            "0,\"y\":\n" +
            "3},\"validVertex2\":\n" +
            "{\"direction\":\n" +
            "\"NE\",\"x\":\n" +
            "0,\"y\":\n" +
            "3},\"orientation\":\n" +
            "\"N\",\"location\":\n" +
            "{\"x\":\n" +
            "0,\"y\":\n" +
            "3}},{\"ratio\":\n" +
            "2,\"inputResource\":\n" +
            "\"Brick\",\"validVertex1\":\n" +
            "{\"direction\":\n" +
            "\"NE\",\"x\":\n" +
            "-2,\"y\":\n" +
            "3},\"validVertex2\":\n" +
            "{\"direction\":\n" +
            "\"E\",\"x\":\n" +
            "-2,\"y\":\n" +
            "3},\"orientation\":\n" +
            "\"NE\",\"location\":\n" +
            "{\"x\":\n" +
            "-2,\"y\":\n" +
            "3}},{\"ratio\":\n" +
            "3,\"validVertex1\":\n" +
            "{\"direction\":\n" +
            "\"E\",\"x\":\n" +
            "-3,\"y\":\n" +
            "0},\"validVertex2\":\n" +
            "{\"direction\":\n" +
            "\"SE\",\"x\":\n" +
            "-3,\"y\":\n" +
            "0},\"orientation\":\n" +
            "\"SE\",\"location\":\n" +
            "{\"x\":\n" +
            "-3,\"y\":\n" +
            "0}},{\"ratio\":\n" +
            "2,\"inputResource\":\n" +
            "\"Ore\",\"validVertex1\":\n" +
            "{\"direction\":\n" +
            "\"SE\",\"x\":\n" +
            "1,\"y\":\n" +
            "-3},\"validVertex2\":\n" +
            "{\"direction\":\n" +
            "\"SW\",\"x\":\n" +
            "1,\"y\":\n" +
            "-3},\"orientation\":\n" +
            "\"S\",\"location\":\n" +
            "{\"x\":\n" +
            "1,\"y\":\n" +
            "-3}},{\"ratio\":\n" +
            "2,\"inputResource\":\n" +
            "\"Sheep\",\"validVertex1\":\n" +
            "{\"direction\":\n" +
            "\"W\",\"x\":\n" +
            "3,\"y\":\n" +
            "-1},\"validVertex2\":\n" +
            "{\"direction\":\n" +
            "\"NW\",\"x\":\n" +
            "3,\"y\":\n" +
            "-1},\"orientation\":\n" +
            "\"NW\",\"location\":\n" +
            "{\"x\":\n" +
            "3,\"y\":\n" +
            "-1}},{\"ratio\":\n" +
            "3,\"validVertex1\":\n" +
            "{\"direction\":\n" +
            "\"W\",\"x\":\n" +
            "2,\"y\":\n" +
            "1},\"validVertex2\":\n" +
            "{\"direction\":\n" +
            "\"NW\",\"x\":\n" +
            "2,\"y\":\n" +
            "1},\"orientation\":\n" +
            "\"NW\",\"location\":\n" +
            "{\"x\":\n" +
            "2,\"y\":\n" +
            "1}}],\"robber\":\n" +
            "{\"x\":\n" +
            "1,\"y\":\n" +
            "-1}},\"players\":\n" +
            "[{\"MAX_GAME_POINTS\":\n" +
            "10,\"resources\":\n" +
            "{\"brick\":\n" +
            "14,\"wood\":\n" +
            "13,\"sheep\":\n" +
            "15,\"wheat\":\n" +
            "10,\"ore\":\n" +
            "8},\"oldDevCards\":\n" +
            "{\"yearOfPlenty\":\n" +
            "0,\"monopoly\":\n" +
            "0,\"soldier\":\n" +
            "2,\"roadBuilding\":\n" +
            "0,\"monument\":\n" +
            "1},\"newDevCards\":\n" +
            "{\"yearOfPlenty\":\n" +
            "0,\"monopoly\":\n" +
            "0,\"soldier\":\n" +
            "1,\"roadBuilding\":\n" +
            "1,\"monument\":\n" +
            "0},\"roads\":\n" +
            "8,\"cities\":\n" +
            "2,\"settlements\":\n" +
            "4,\"soldiers\":\n" +
            "1,\"victoryPoints\":\n" +
            "7,\"monuments\":\n" +
            "0,\"longestRoad\":\n" +
            "true,\"largestArmy\":\n" +
            "false,\"playedDevCard\":\n" +
            "true,\"discarded\":\n" +
            "false,\"playerID\":\n" +
            "0,\"orderNumber\":\n" +
            "0,\"name\":\n" +
            "\"Sam\",\"color\":\n" +
            "\"orange\"},{\"MAX_GAME_POINTS\":\n" +
            "10,\"resources\":\n" +
            "{\"brick\":\n" +
            "1,\"wood\":\n" +
            "0,\"sheep\":\n" +
            "1,\"wheat\":\n" +
            "0,\"ore\":\n" +
            "6},\"oldDevCards\":\n" +
            "{\"yearOfPlenty\":\n" +
            "0,\"monopoly\":\n" +
            "0,\"soldier\":\n" +
            "0,\"roadBuilding\":\n" +
            "0,\"monument\":\n" +
            "0},\"newDevCards\":\n" +
            "{\"yearOfPlenty\":\n" +
            "0,\"monopoly\":\n" +
            "0,\"soldier\":\n" +
            "0,\"roadBuilding\":\n" +
            "0,\"monument\":\n" +
            "0},\"roads\":\n" +
            "13,\"cities\":\n" +
            "4,\"settlements\":\n" +
            "3,\"soldiers\":\n" +
            "0,\"victoryPoints\":\n" +
            "2,\"monuments\":\n" +
            "0,\"longestRoad\":\n" +
            "false,\"largestArmy\":\n" +
            "false,\"playedDevCard\":\n" +
            "false,\"discarded\":\n" +
            "false,\"playerID\":\n" +
            "1,\"orderNumber\":\n" +
            "1,\"name\":\n" +
            "\"Brooke\",\"color\":\n" +
            "\"blue\"},{\"MAX_GAME_POINTS\":\n" +
            "10,\"resources\":\n" +
            "{\"brick\":\n" +
            "5,\"wood\":\n" +
            "1,\"sheep\":\n" +
            "0,\"wheat\":\n" +
            "1,\"ore\":\n" +
            "0},\"oldDevCards\":\n" +
            "{\"yearOfPlenty\":\n" +
            "0,\"monopoly\":\n" +
            "0,\"soldier\":\n" +
            "0,\"roadBuilding\":\n" +
            "0,\"monument\":\n" +
            "0},\"newDevCards\":\n" +
            "{\"yearOfPlenty\":\n" +
            "0,\"monopoly\":\n" +
            "0,\"soldier\":\n" +
            "0,\"roadBuilding\":\n" +
            "0,\"monument\":\n" +
            "0},\"roads\":\n" +
            "13,\"cities\":\n" +
            "4,\"settlements\":\n" +
            "3,\"soldiers\":\n" +
            "0,\"victoryPoints\":\n" +
            "2,\"monuments\":\n" +
            "0,\"longestRoad\":\n" +
            "false,\"largestArmy\":\n" +
            "false,\"playedDevCard\":\n" +
            "false,\"discarded\":\n" +
            "false,\"playerID\":\n" +
            "10,\"orderNumber\":\n" +
            "2,\"name\":\n" +
            "\"Pete\",\"color\":\n" +
            "\"red\"},{\"MAX_GAME_POINTS\":\n" +
            "10,\"resources\":\n" +
            "{\"brick\":\n" +
            "0,\"wood\":\n" +
            "1,\"sheep\":\n" +
            "1,\"wheat\":\n" +
            "0,\"ore\":\n" +
            "2},\"oldDevCards\":\n" +
            "{\"yearOfPlenty\":\n" +
            "0,\"monopoly\":\n" +
            "0,\"soldier\":\n" +
            "0,\"roadBuilding\":\n" +
            "0,\"monument\":\n" +
            "0},\"newDevCards\":\n" +
            "{\"yearOfPlenty\":\n" +
            "0,\"monopoly\":\n" +
            "0,\"soldier\":\n" +
            "0,\"roadBuilding\":\n" +
            "0,\"monument\":\n" +
            "0},\"roads\":\n" +
            "13,\"cities\":\n" +
            "4,\"settlements\":\n" +
            "3,\"soldiers\":\n" +
            "0,\"victoryPoints\":\n" +
            "2,\"monuments\":\n" +
            "0,\"longestRoad\":\n" +
            "false,\"largestArmy\":\n" +
            "false,\"playedDevCard\":\n" +
            "false,\"discarded\":\n" +
            "false,\"playerID\":\n" +
            "11,\"orderNumber\":\n" +
            "3,\"name\":\n" +
            "\"Mark\",\"color\":\n" +
            "\"green\"}],\"log\":\n" +
            "{\"lines\":\n" +
            "[{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a road\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a settlement\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Brooke\",\"message\":\n" +
            "\"Brooke built a road\"},{\"source\":\n" +
            "\"Brooke\",\"message\":\n" +
            "\"Brooke built a settlement\"},{\"source\":\n" +
            "\"Brooke\",\"message\":\n" +
            "\"Brooke\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Pete\",\"message\":\n" +
            "\"Pete built a road\"},{\"source\":\n" +
            "\"Pete\",\"message\":\n" +
            "\"Pete built a settlement\"},{\"source\":\n" +
            "\"Pete\",\"message\":\n" +
            "\"Pete\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark built a road\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark built a settlement\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark built a road\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark built a settlement\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Pete\",\"message\":\n" +
            "\"Pete built a road\"},{\"source\":\n" +
            "\"Pete\",\"message\":\n" +
            "\"Pete built a settlement\"},{\"source\":\n" +
            "\"Pete\",\"message\":\n" +
            "\"Pete\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Brooke\",\"message\":\n" +
            "\"Brooke built a road\"},{\"source\":\n" +
            "\"Brooke\",\"message\":\n" +
            "\"Brooke built a settlement\"},{\"source\":\n" +
            "\"Brooke\",\"message\":\n" +
            "\"Brooke\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a road\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a settlement\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam rolled a 3.\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a road\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a road\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a road\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a road\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a road\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam upgraded to a city\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam built a settlement\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam upgraded to a city\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam bought a Development Card.\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam bought a Development Card.\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam bought a Development Card.\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam bought a Development Card.\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Brooke\",\"message\":\n" +
            "\"Brooke rolled a 5.\"},{\"source\":\n" +
            "\"Brooke\",\"message\":\n" +
            "\"Brooke\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Pete\",\"message\":\n" +
            "\"Pete rolled a 5.\"},{\"source\":\n" +
            "\"Pete\",\"message\":\n" +
            "\"Pete\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark rolled a 5.\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark rolled a 5.\"},{\"source\":\n" +
            "\"Mark\",\"message\":\n" +
            "\"Mark\\u0027s turn just ended\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam rolled a 5.\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam bought a Development Card.\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam bought a Development Card.\"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam used a soldier \"},{\"source\":\n" +
            "\"Sam\",\"message\":\n" +
            "\"Sam moved the robber and robbed Pete.\"}]},\"chat\":\n" +
            "{\"lines\":\n" +
            "[]},\"bank\":\n" +
            "{\"brick\":\n" +
            "4,\"wood\":\n" +
            "9,\"sheep\":\n" +
            "1,\"wheat\":\n" +
            "7,\"ore\":\n" +
            "2},\"turnTracker\":\n" +
            "{\"status\":\n" +
            "\"Playing\",\"currentTurn\":\n" +
            "0},\"biggestArmy\":\n" +
            "2,\"longestRoad\":\n" +
            "0,\"winner\":\n" +
            "-1}\n";

    private IGame game;

    /**
     * Creates a game and make sure I can only add 4 players
     * Tests initializing settlements and roads
     * Tests finish turn
     * Tests initializing settlement when it's not your turn
     * Initializes the game for the other tests
     * Tests that SetUpTwo initiateSettlement gives resources
     * @throws InvalidNameException
     * @throws InvalidPlayerException
     */
    @Before
    public void testInitializeGame() throws Exception {
        game = new Game();

        final Player one = new Player(0, CatanColor.BLUE, 0, 0, "Hope");
        final Player two = new Player(0, CatanColor.BROWN, 1, 1, "Corbin");
        final Player three = new Player(0, CatanColor.GREEN, 2, 2, "Hanna");
        final Player four = new Player(0, CatanColor.ORANGE, 3, 3, "Becca");

        //Checks if you can add player
        assertTrue(game.canAddPlayer());
        game.getPlayerManager().addPlayer(one);
        game.getPlayerManager().addPlayer(two);
        game.getPlayerManager().addPlayer(three);
        game.getPlayerManager().addPlayer(four);

        //Checks if can add player when you have too many
        assertFalse(game.canAddPlayer());

        int current_turn = game.getCurrentTurn();
        HexLocation hloc = new HexLocation(0,0);
        VertexLocation vloc = new VertexLocation(hloc, VertexDirection.West);
        EdgeLocation eloc = new EdgeLocation(hloc, EdgeDirection.NorthWest);

        //Checks if you can initiate settlement
        assertTrue(game.canInitiateSettlement(current_turn,vloc));
        game.initiateSettlement(current_turn,vloc);
        assertTrue(game.canInitiateRoad(current_turn,eloc));
        game.initiateRoad(current_turn,eloc);

        //Checks if you can finish turn when you should
        assertTrue(game.canFinishTurn(current_turn));
        current_turn = game.finishTurn(current_turn);

        hloc = new HexLocation(-1,-1);
        vloc = new VertexLocation(hloc,VertexDirection.NorthEast);
        eloc = new EdgeLocation(hloc,EdgeDirection.NorthEast);

        //Checks that you can't initiate Settlement when it's not your turn
        assertFalse(game.canInitiateSettlement(current_turn-1,vloc));

        //initializes the rest of the game so we can use the game for other tests
        game.initiateSettlement(current_turn,vloc);
        game.initiateRoad(current_turn,eloc);
        current_turn = game.finishTurn(current_turn);

        hloc = new HexLocation(2,2);
        vloc = new VertexLocation(hloc,VertexDirection.NorthEast);
        eloc = new EdgeLocation(hloc,EdgeDirection.NorthEast);

        game.initiateSettlement(current_turn,vloc);
        game.initiateRoad(current_turn,eloc);
        current_turn = game.finishTurn(current_turn);

        hloc = new HexLocation(-2,-2);
        vloc = new VertexLocation(hloc,VertexDirection.NorthEast);
        eloc = new EdgeLocation(hloc,EdgeDirection.NorthEast);

        game.initiateSettlement(current_turn,vloc);
        game.initiateRoad(current_turn,eloc);
        int phaseTransitionTurn = current_turn;
        current_turn = game.finishTurn(current_turn);

        //Verify that the turntracker changed the phase correctly
        assertTrue(game.getCurrentPhase()== TurnTracker.Phase.SETUPTWO);
        assertTrue(phaseTransitionTurn == current_turn);

        for(int i=2; i> -2; i--){
            hloc = new HexLocation(i,1);
            vloc = new VertexLocation(hloc,VertexDirection.NorthEast);
            eloc = new EdgeLocation(hloc,EdgeDirection.North);

            game.initiateSettlement(current_turn,vloc);
            game.initiateRoad(current_turn,eloc);

            //Checks that Setuptwo gives resources
            assertTrue(game.getNumberResourceCards(current_turn)>0);

            current_turn = game.finishTurn(current_turn);
        }

    }

    @Test
    public void testCanRollNumber() {
        game.setPhase(TurnTracker.Phase.ROLLING);
        final int turn = game.getCurrentTurn();
        assertTrue(game.canRollNumber(turn));
        try {
            game.getTurnTracker().nextPhase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertFalse(game.canRollNumber(turn));
    }

    @Test
    public void testRollNumber() throws InvalidDiceRollException{
        final int turn = game.getCurrentTurn();
        try {
            game.getTurnTracker().nextPhase();
            game.getTurnTracker().nextPhase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final int roll = game.rollDice(turn);
        assertTrue(roll > 1);
        assertTrue(roll <= 12);
    }

    @Test
    public void testCanOfferTrade() {
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);
        assertTrue(game.canOfferTrade(guy));
    }

    @Test
    public void testOfferTrade() throws InsufficientResourcesException, InvalidTypeException, PlayerExistsException {
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);

        int friend = 3;
        if(guy == 3) {
            friend = 1;
        }
        final ResourceCard one = game.getResourceCard(ResourceType.BRICK);
        final ResourceCard two = game.getResourceCard(ResourceType.ORE);
        final ResourceCard three = game.getResourceCard(ResourceType.SHEEP);

        game.giveResource(one, guy);
        game.giveResource(two, guy);
        game.giveResource(three, friend);

        //Checks if cards were added to players
        assertTrue(game.amountOwnedResource(guy, ResourceType.BRICK) > 0);
        assertTrue(game.amountOwnedResource(guy, ResourceType.ORE) > 0);
        assertTrue(game.amountOwnedResource(friend, ResourceType.SHEEP) > 0);

        int guyBrick = game.amountOwnedResource(guy, ResourceType.BRICK);
        int guyOre = game.amountOwnedResource(guy, ResourceType.ORE);
        int guySheep = game.amountOwnedResource(guy, ResourceType.SHEEP);
        int friendSheep = game.amountOwnedResource(friend, ResourceType.SHEEP);
        int friendBrick = game.amountOwnedResource(friend,ResourceType.BRICK);
        int friendOre = game.amountOwnedResource(friend,ResourceType.ORE);

        final List<ResourceType> ones = new ArrayList<>();
        final List<ResourceType> twos = new ArrayList<>();
        ones.add(ResourceType.BRICK);
        ones.add(ResourceType.ORE);
        twos.add(ResourceType.SHEEP);


        TradePackage package1 = new TradePackage(guy,ones);
        TradePackage package2 = new TradePackage(friend, twos);

        game.offerTrade(package1, package2);
        game.acceptTrade(friend, true);

        assertTrue(game.amountOwnedResource(friend, ResourceType.BRICK) == friendBrick +1);
        assertTrue(game.amountOwnedResource(friend, ResourceType.ORE) == friendOre + 1);
        assertTrue(game.amountOwnedResource(guy, ResourceType.SHEEP) == guySheep +1);
        assertTrue(game.amountOwnedResource(guy, ResourceType.BRICK) == guyBrick - 1);
    }

    @Test
    public void testCanFinishTurn() {
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);
        assertTrue(game.canFinishTurn(guy));
    }

    @Test
    public void testFinishTurn() {
        final int guy = game.getCurrentTurn();
        game.nextPhase();
        final TurnTracker.Phase p = game.getCurrentPhase();

        if(p == TurnTracker.Phase.PLAYING || p == TurnTracker.Phase.SETUPONE || p == TurnTracker.Phase.SETUPTWO){
            assertTrue(game.canFinishTurn(guy));
        } else{
            assertFalse(game.canFinishTurn(guy));
        }

    }

    @Test
    public void testCanBuyDevCard() throws InsufficientResourcesException, InvalidTypeException, PlayerExistsException{
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);
        assertFalse(game.canBuyDevelopmentCard(guy));

        final ResourceCard one = game.getResourceCard(ResourceType.WHEAT);
        final ResourceCard two = game.getResourceCard(ResourceType.ORE);
        final ResourceCard three = game.getResourceCard(ResourceType.SHEEP);

        game.giveResource(one, guy);
        game.giveResource(two, guy);
        game.giveResource(three, guy);

        assertTrue(game.canBuyDevelopmentCard(guy));
    }

    @Test
    public void testCanUseYearOfPlenty() throws PlayerExistsException, BadCallerException {
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);

        if(game.numberOfDevCard(guy) == 0){
            assertFalse(game.canUseYearOfPlenty(guy));
        }
        final YearOfPlentyCard card = new YearOfPlentyCard();
        game.addDevCard(card, guy);
        game.getPlayerManager().moveNewToOld(guy);
        assertTrue(game.canUseYearOfPlenty(guy));

    }

    @Test
    public void testCanUseRoadBuilder() throws PlayerExistsException, BadCallerException{
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);

        if(game.numberOfDevCard(guy) == 0){
            assertFalse(game.canUseRoadBuilding(guy));
        }
        final RoadBuildCard card = new RoadBuildCard();
        game.addDevCard(card, guy);
        game.getPlayerManager().moveNewToOld(guy);
        assertTrue(game.canUseRoadBuilding(guy));
    }

    @Test
    public void testCanUseSoldier() throws PlayerExistsException, BadCallerException{
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);

        if(game.numberOfDevCard(guy) == 0){
            //assertFalse(game.canUseSoldier(guy));
        }
        final SoldierCard card = new SoldierCard();
        game.addDevCard(card, guy);
        game.getPlayerManager().moveNewToOld(guy);
        //assertTrue(game.canUseSoldier(guy));
    }

    @Test
    public void testCanUseMonopoly() throws PlayerExistsException, BadCallerException{
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);

        if(game.numberOfDevCard(guy) == 0){
            assertFalse(game.canUseMonopoly(guy));
        }
        final MonopolyCard card = new MonopolyCard();
        game.addDevCard(card, guy);
        game.getPlayerManager().moveNewToOld(guy);
        assertTrue(game.canUseMonopoly(guy));
    }

    @Test
    public void testUseMonopoly() throws InsufficientResourcesException, InvalidTypeException, PlayerExistsException, DevCardException, BadCallerException{
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);
        final ResourceCard one = game.getResourceCard(ResourceType.ORE);
        final ResourceCard two = game.getResourceCard(ResourceType.ORE);
        final ResourceCard three = game.getResourceCard(ResourceType.ORE);
        final ResourceCard four = game.getResourceCard(ResourceType.ORE);

        game.giveResource(one, 1);
        game.giveResource(two, 2);
        game.giveResource(three, 3);
        game.giveResource(four, 3);

        // give the user the monopoly card
        final MonopolyCard card = new MonopolyCard();
        game.addDevCard(card, guy);
        game.getPlayerManager().moveNewToOld(guy);

        game.useMonopoly(guy, ResourceType.ORE);
    }

    @Test
    public void testCanUseMonument() throws PlayerExistsException, BadCallerException{
        final int playerIndex = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);
        final int guy = game.getCurrentTurn();

        if(game.numberOfDevCard(playerIndex) == 0){
            assertFalse(game.canUseMonument(playerIndex));
        }
        final MonumentCard card = new MonumentCard();
        game.addDevCard(card, guy);
        game.getPlayerManager().moveNewToOld(guy);
        game.getPlayerManager().getPlayerByIndex(guy).incrementPoints(10);
        assertTrue(game.canUseMonument(guy));
    }

    @Test
    @Ignore
    public void testBuyDevCard() throws InvalidTypeException, Exception{
        final int guy = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.DISCARDING);

        final ResourceCard one = game.getResourceCard(ResourceType.WHEAT);
        final ResourceCard two = game.getResourceCard(ResourceType.ORE);
        final ResourceCard three = game.getResourceCard(ResourceType.SHEEP);

        game.giveResource(one, guy);
        game.giveResource(two, guy);
        game.giveResource(three, guy);

        Player p = game.getPlayerManager().getPlayerByID(guy);
        final int sizeold = p.quantityOfDevCards();

        game.buyDevelopmentCard(guy);
        p = game.getPlayerManager().getPlayerByID(guy);
        final int sizenew = p.quantityOfDevCards();

        assertTrue(sizenew == sizeold + 1);
    }

    /**
     * Checks canMaritimeTrade
     * @throws PlayerExistsException
     * @throws InvalidPlayerException
     */
    @Test
    public void testCanMaritimeTrade() throws PlayerExistsException, InvalidPlayerException {
        int current_turn = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.DISCARDING);
        assertFalse(game.canMaritimeTrade(current_turn, PortType.WHEAT));
        game.setPhase(TurnTracker.Phase.PLAYING);
        assertTrue(game.canTrade(current_turn));
    }

    /**
     * checks canBuildRoad
     * @throws InvalidTypeException
     * @throws InsufficientResourcesException
     * @throws PlayerExistsException
     * @throws InvalidLocationException
     * @throws InvalidPlayerException
     */
    @Test
    public void testCanBuildRoad() throws InvalidTypeException, InsufficientResourcesException, PlayerExistsException, InvalidLocationException, InvalidPlayerException {
        int current_turn = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.PLAYING);
        game.giveResource(game.getResourceCard(ResourceType.BRICK),current_turn);
        game.giveResource(game.getResourceCard(ResourceType.WOOD),current_turn);
        EdgeLocation eloc1 = new EdgeLocation(new HexLocation(-1, 0), EdgeDirection.NorthEast);
        EdgeLocation eloc2 = new EdgeLocation(new HexLocation(2,1),EdgeDirection.NorthWest);
        if(current_turn < 2) {
            assertTrue(game.canBuildRoad(current_turn,eloc1));
            assertFalse(game.canBuildRoad(current_turn,eloc2));
        }else{
            assertTrue(game.canBuildRoad(current_turn,eloc2));
            assertFalse(game.canBuildRoad(current_turn,eloc1));
        }

    }

    /**
     * Checks canBuildSettlement
     * checks canBuildCity
     * @throws InvalidPlayerException
     * @throws PlayerExistsException
     * @throws InvalidLocationException
     * @throws InvalidTypeException
     * @throws InsufficientResourcesException
     * @throws StructureException
     */
    @Test
    public void testCanBuildRoadandCity() throws InvalidPlayerException, PlayerExistsException, InvalidLocationException, InvalidTypeException, InsufficientResourcesException, StructureException {
        int current_turn = game.getCurrentTurn();
        game.setPhase(TurnTracker.Phase.ROLLING);
        assertFalse(game.canBuildSettlement(current_turn,new VertexLocation(new HexLocation(0,0),VertexDirection.NorthEast)));
        game.setPhase(TurnTracker.Phase.PLAYING);

        game.giveResource(game.getResourceCard(ResourceType.BRICK),current_turn);
        game.giveResource(game.getResourceCard(ResourceType.WOOD),current_turn);
        EdgeLocation eloc;
        VertexLocation vloc;
        if(current_turn < 2){
            eloc = new EdgeLocation(new HexLocation(0,0),EdgeDirection.North);
            vloc = new VertexLocation(new HexLocation(0,0),VertexDirection.NorthEast);
        }else if(current_turn == 2){
            eloc = new EdgeLocation(new HexLocation(0,0),EdgeDirection.NorthEast);
            vloc = new VertexLocation(new HexLocation(0,0),VertexDirection.NorthEast);
        }else{
            eloc = new EdgeLocation(new HexLocation(1,0),EdgeDirection.NorthEast);
            vloc = new VertexLocation(new HexLocation(1,0),VertexDirection.NorthEast);
        }
        game.buildRoad(current_turn, eloc);

        //Checks if you can build Settlement
        if(game.amountOwnedResource(current_turn,ResourceType.BRICK) > 0 && game.amountOwnedResource(current_turn, ResourceType.WOOD) > 0 &&
                game.amountOwnedResource(current_turn, ResourceType.WHEAT) > 0 && game.amountOwnedResource(current_turn, ResourceType.SHEEP) > 0){
            assertTrue(game.canBuildSettlement(current_turn,vloc));
        }else{
            assertFalse(game.canBuildSettlement(current_turn,vloc));
            game.giveResource(game.getResourceCard(ResourceType.BRICK),current_turn);
            game.giveResource(game.getResourceCard(ResourceType.WOOD),current_turn);
            game.giveResource(game.getResourceCard(ResourceType.SHEEP),current_turn);
            game.giveResource(game.getResourceCard(ResourceType.WHEAT),current_turn);
            assertTrue(game.canBuildSettlement(current_turn,vloc));
        }
        game.buildSettlement(current_turn,vloc);

        //Checks if you can build city
        if(game.amountOwnedResource(current_turn,ResourceType.ORE) >= 3 && game.amountOwnedResource(current_turn, ResourceType.WHEAT) >= 2){
            assertTrue(game.canBuildCity(current_turn,vloc));
        }else{
            assertFalse(game.canBuildCity(current_turn,vloc));
            game.giveResource(game.getResourceCard(ResourceType.ORE),current_turn);
            game.giveResource(game.getResourceCard(ResourceType.WHEAT),current_turn);
            game.giveResource(game.getResourceCard(ResourceType.ORE),current_turn);
            game.giveResource(game.getResourceCard(ResourceType.WHEAT),current_turn);
            game.giveResource(game.getResourceCard(ResourceType.ORE),current_turn);
            assertTrue(game.canBuildCity(current_turn,vloc));
        }

    }
}
