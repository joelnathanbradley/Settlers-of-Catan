package server.controllers;

import server.commands.CommandExecutionResult;
import server.factories.GameCommandFactory;
import shared.dto.AddAIDTO;
import shared.dto.CookieWrapperDTO;
import shared.dto.ListAIDTO;

/**
 * @author Derek Argueta
 */
public class GameController {

    public static CommandExecutionResult getModel(final CookieWrapperDTO dto) {
        try {
            return GameCommandFactory.getInstance().executeCommand("model", dto);
        } catch (Exception e) {
            CommandExecutionResult result = new CommandExecutionResult("Something went wrong getting the model :(");
            result.triggerError(500);
            return result;
        }
    }

    public static CommandExecutionResult addAI(final CookieWrapperDTO dto) {
        try {
            CommandExecutionResult result = GameCommandFactory.getInstance().executeCommand("addAI", dto);
            return result;
        } catch (Exception e) {
            CommandExecutionResult result = new CommandExecutionResult("Something went wrong adding an AI :(");
            result.triggerError(500);
            return result;
        }
    }

    public static CommandExecutionResult listAI() {
        try {
            return GameCommandFactory.getInstance().executeCommand("listAI");
        } catch (Exception e) {
            CommandExecutionResult result = new CommandExecutionResult("Something went wrong listing the AI :(");
            result.triggerError(500);
            return result;
        }
    }
}
