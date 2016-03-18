package server.commands.moves;

import server.commands.ICommand;
import server.exceptions.BuildSettlementException;
import server.facade.IFacade;
import shared.dto.BuildSettlementDTO;
import shared.dto.IDTO;

/**
 * A command object that builds a settlement
 *
 * @author Joel Bradley
 */
public class BuildSettlementCommand implements ICommand {

    private IFacade facade;
    private BuildSettlementDTO dto;

    public BuildSettlementCommand(IFacade facade, BuildSettlementDTO dto) {
        this.facade = facade;
        this.dto = dto;
    }

    /**
     * Communicates with the ServerFacade to carry out the Build Settlement command
     * @return IDTO
     */
    @Override
    public IDTO execute() {
        try {
            facade.buildSettlement(1, dto.getPlayerIndex(), dto.getLocation());
        } catch (BuildSettlementException e) {
            e.printStackTrace();
        }
        //TODO: change this later fool
        return null;
    }

}
