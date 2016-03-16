package server.commands.moves;

import com.google.gson.JsonObject;
import server.commands.ICommand;

/**
 * A command object that robs a player
 *
 * @author Joel Bradley
 */
public class RobPlayerCommand implements ICommand {

    /**
     * Constructor
     */
    public RobPlayerCommand() {

    }

    /**
     * Communicates with the ServerFacade to carry out the Rob Player command
     * @return JsonObject
     */
    @Override
    public JsonObject execute() {
        return null;
    }

}