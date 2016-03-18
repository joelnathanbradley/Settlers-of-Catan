package server.commands.moves;

import server.commands.ICommand;
import server.exceptions.BuildCityException;
import server.facade.IFacade;
import shared.dto.BuildCityDTO;
import shared.dto.IDTO;

/**
 * A command object that builds a city
 *
 * @author Joel Bradley
 */
public class BuildCityCommand implements ICommand {

    private IFacade facade;
    private BuildCityDTO dto;

    /**
     * Constructor
     */
    public BuildCityCommand(IFacade facade, BuildCityDTO dto) {
        this.facade = facade;
        this.dto = dto;
    }

    /**
     * Communicates with the ServerFacade to carry out the Build City command
     * @return IDTO
     */
    @Override
    public IDTO execute() {
        try {
            facade.buildCity(1, dto.getPlayerIndex(), dto.getLocation());
        } catch (BuildCityException e) {
            e.printStackTrace();
        }
        //TODO: change this later fool
        return null;
    }

}
