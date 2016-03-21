package server.commands.user;

import server.commands.CommandExecutionResult;
import server.commands.ICommand;
import server.exceptions.LoginException;
import server.main.Config;
import server.managers.UserManager;
import shared.dto.AuthDTO;
import shared.dto.IDTO;

/**
 * A command object that logs a player in
 *
 * @author Danny Harding
 */
public class LoginCommand implements ICommand {

    private String username;
    private String password;

    /**
     * Communicates with the ServerFacade to carry out the Login command
     *
     * @return CommandExecutionResult with information about the login
     */
    @Override
    public CommandExecutionResult execute() {
        if(Config.facade.login(this.username, this.password)) {
            final String userId = String.valueOf(UserManager.getInstance().getIdForUser(username));
            CommandExecutionResult result = new CommandExecutionResult("Success");
            result.addCookie("catan.user", "%7B%22name%22%3A%22" + username + "%22%2C%22password%22%3A%22" + password + "%22%2C%22playerID%22%3A" + UserManager.getInstance().getIdForUser(username) + "%7D");
            return result;
        } else {
            return new CommandExecutionResult("Failed");
        }
    }

    @Override
    public void setParams(IDTO dto) {
        AuthDTO tmpDTO = (AuthDTO)dto;
        this.username = tmpDTO.getUsername();
        this.password = tmpDTO.getPassword();
    }
}
