package client.domestic;

import client.base.Controller;
import client.facade.Facade;
import client.misc.IWaitView;
import client.services.UserCookie;
import shared.definitions.ResourceType;
import shared.exceptions.PlayerExistsException;
import shared.model.ai.AIPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, Observer {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private Facade facade;

	private int woodcount = 0;
	private int brickcount = 0;
	private int sheepcount = 0;
	private int wheatcount = 0;
	private int orecount = 0;

	private int sendwood = 0;
	private int sendbrick = 0;
	private int sendsheep = 0;
	private int sendwheat = 0;
	private int sendore = 0;

	private int receivewood = 0;
	private int receivebrick = 0;
	private int receivesheep = 0;
	private int receivewheat = 0;
	private int receiveore = 0;

	//False means sending, true means receiving
	private boolean woodStatus = false;
	private boolean brickStatus = false;
	private boolean sheepStatus = false;
	private boolean wheatStatus = false;
	private boolean oreStatus = false;

	private int tradePartner = -1;


	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		facade = Facade.getInstance();
		facade.addObserver(this);
	}

	public void update(Observable obs, Object obj){
		getTradeOverlay().setPlayers(facade.getOtherPlayers(UserCookie.getInstance().getPlayerIndex()));
		getTradeView().enableDomesticTrade(facade.canTrade(UserCookie.getInstance().getPlayerIndex()));
		if (facade.getWinnerId() != -1) {
			getTradeView().enableDomesticTrade(false);
		}

		// get the amount of resources available to the current user
		woodcount = facade.getAmountOfResource(UserCookie.getInstance().getPlayerIndex(), ResourceType.WOOD);
		brickcount = facade.getAmountOfResource(UserCookie.getInstance().getPlayerIndex(), ResourceType.BRICK);
		sheepcount = facade.getAmountOfResource(UserCookie.getInstance().getPlayerIndex(), ResourceType.SHEEP);
		wheatcount = facade.getAmountOfResource(UserCookie.getInstance().getPlayerIndex(), ResourceType.WHEAT);
		orecount = facade.getAmountOfResource(UserCookie.getInstance().getPlayerIndex(), ResourceType.ORE);

		sendwood = 0;
		sendbrick = 0;
		sendsheep = 0;
		sendwheat = 0;
		sendore = 0;

		receivewood = 0;
		receivebrick = 0;
		receivesheep = 0;
		receivewheat = 0;
		receiveore = 0;

		woodStatus = false;
		brickStatus = false;
		sheepStatus = false;
		wheatStatus = false;
		oreStatus = false;

		tradePartner = -1;

		getTradeOverlay().setTradeEnabled(false);

		if (facade.isTradeActive()) {
			if (facade.getTradeSender() == UserCookie.getInstance().getPlayerIndex()){
				getWaitOverlay().showModal();
			} else if (facade.getTradeReceiver() == UserCookie.getInstance().getPlayerIndex()) {
				createAccept();
			}
		} else {
			if (getWaitOverlay().isModalShowing()) {
				getWaitOverlay().closeModal();
			}
			if (getTradeOverlay().isModalShowing()) {
				getTradeOverlay().closeModal();
				cancelTrade();
			}
			if (getAcceptOverlay().isModalShowing()) {
				getAcceptOverlay().closeModal();
			}

		}
	}
	
	private IDomesticTradeView getTradeView() {
		return (IDomesticTradeView)super.getView();
	}

	private IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	private void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	private IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	private void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	private IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	private void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	private void createAccept(){
		int brick = facade.getTradeBrick();
		int wood = facade.getTradeWood();
		int sheep = facade.getTradeSheep();
		int wheat = facade.getTradeWheat();
		int ore = facade.getTradeOre();
		int me = UserCookie.getInstance().getPlayerIndex();

		if (brick > 0) {
			getAcceptOverlay().addGetResource(ResourceType.BRICK, brick);
		} else if (brick < 0) {
			brick *= -1;
			getAcceptOverlay().addGiveResource(ResourceType.BRICK, brick);
			if(facade.getAmountOfResource(me,ResourceType.BRICK) < brick) {
				getAcceptOverlay().setAcceptEnabled(false);
			}
		}

		if (wood > 0) {
			getAcceptOverlay().addGetResource(ResourceType.WOOD, wood);
		} else if (wood < 0 || wood > woodcount) {
			wood *= -1;
			getAcceptOverlay().addGiveResource(ResourceType.WOOD, wood);
			if(facade.getAmountOfResource(me, ResourceType.WOOD) < wood) {
				getAcceptOverlay().setAcceptEnabled(false);
			}
		}

		if (sheep > 0) {
			getAcceptOverlay().addGetResource(ResourceType.SHEEP, sheep);
		} else if (sheep < 0) {
			sheep *= -1;
			getAcceptOverlay().addGiveResource(ResourceType.SHEEP, sheep);
			if(facade.getAmountOfResource(me, ResourceType.SHEEP) < sheep) {
				getAcceptOverlay().setAcceptEnabled(false);
			}
		}

		if (wheat > 0) {
			getAcceptOverlay().addGetResource(ResourceType.WHEAT, wheat);
		} else if (wheat < 0) {
			wheat *= -1;
			getAcceptOverlay().addGiveResource(ResourceType.WHEAT, wheat);
			if(facade.getAmountOfResource(me, ResourceType.WHEAT) < wheat) {
				getAcceptOverlay().setAcceptEnabled(false);
			}
		}

		if (ore > 0) {
			getAcceptOverlay().addGetResource(ResourceType.ORE, ore);
		} else if (ore < 0) {
			ore *= -1;
			getAcceptOverlay().addGiveResource(ResourceType.ORE, ore);
			if(facade.getAmountOfResource(me, ResourceType.ORE) < ore) {
				getAcceptOverlay().setAcceptEnabled(false);
			}
		}
		getAcceptOverlay().showModal();
	}

	@Override
	public void startTrade() {
		getTradeOverlay().reset();
		update(null, null);
		getTradeOverlay().setPlayerSelectionEnabled(true);
		getTradeOverlay().setResourceSelectionEnabled(true);
		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		switch (resource) {
			case BRICK:
				if(brickStatus) {
					receivebrick--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, true, receivebrick > 0);
				} else {
					sendbrick--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, sendbrick < brickcount, sendbrick > 0);
				}
				break;
			case WOOD:
				if(woodStatus) {
					receivewood--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, true, receivewood > 0);
				} else {
					sendwood--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, sendwood < woodcount, sendwood > 0);
				}
				break;
			case WHEAT:
				if(wheatStatus) {
					receivewheat--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, receivewheat > 0);
				} else {
					sendwheat--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, sendwheat < wheatcount, sendwheat > 0);
				}
				break;
			case SHEEP:
				if(sheepStatus) {
					receivesheep--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, receivesheep > 0);
				} else {
					sendsheep--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, sendsheep < sheepcount, sendsheep > 0);
				}
				break;
			case ORE:
				if(oreStatus) {
					receiveore--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, true, receiveore > 0);
				} else {
					sendore--;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, sendore < orecount, sendore > 0);
				}
		}
		if (sendbrick+sendwood+sendsheep+sendwheat+sendore > 0 && receivebrick+receivewood+receiveore+receivesheep+receivewheat > 0 && tradePartner != -1) {
			getTradeOverlay().setTradeEnabled(true);
			getTradeOverlay().setStateMessage("Trade!");
		} else if (tradePartner == -1) {
			getTradeOverlay().setStateMessage("Select player to trade with");
			getTradeOverlay().setTradeEnabled(false);
		} else if (!(sendbrick+sendwood+sendsheep+sendwheat+sendore > 0)) {
			getTradeOverlay().setStateMessage("Select Resource to send to other player");
			getTradeOverlay().setTradeEnabled(false);
		} else if (!(receivebrick+receivewood+receiveore+receivesheep+receivewheat > 0)) {
			getTradeOverlay().setStateMessage("Select Resource to receive from other player");
			getTradeOverlay().setTradeEnabled(false);
		}
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		switch (resource) {
			case BRICK:
				if(brickStatus) {
					receivebrick++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, receivebrick<20, receivebrick > 0);
				} else{
					sendbrick++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, sendbrick < brickcount, true);
				}
				break;
			case WOOD:
				if(woodStatus){
					receivewood++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, receivewood<20, receivewood > 0);
				} else {
					sendwood++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, sendwood < woodcount, true);
				}
				break;
			case WHEAT:
				if(wheatStatus){
					receivewheat++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, receivewheat<20, receivewheat > 0);
				} else {
					sendwheat++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, sendwheat < wheatcount, true);
				}
				break;
			case SHEEP:
				if(sheepStatus){
					receivesheep++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, receivesheep<20, receivesheep > 0);
				} else {
					sendsheep++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, sendsheep < sheepcount, true);
				}
				break;
			case ORE:
				if(oreStatus){
					receiveore++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, receiveore <20, receiveore > 0);
				} else {
					sendore++;
					getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, sendore < orecount, true);
				}

		}
		if(sendbrick+sendwood+sendsheep+sendwheat+sendore > 0 && receivebrick+receivewood+receiveore+receivesheep+receivewheat > 0 && tradePartner != -1) {
			getTradeOverlay().setTradeEnabled(true);
			getTradeOverlay().setStateMessage("Trade!");
		} else if(tradePartner == -1) {
			getTradeOverlay().setStateMessage("Select player to trade with");
			getTradeOverlay().setTradeEnabled(false);
		} else if(!(sendbrick+sendwood+sendsheep+sendwheat+sendore > 0)) {
			getTradeOverlay().setStateMessage("Select Resource to send to other player");
			getTradeOverlay().setTradeEnabled(false);
		} else if(!(receivebrick+receivewood+receiveore+receivesheep+receivewheat > 0)) {
			getTradeOverlay().setStateMessage("Select Resource to receive from other player");
			getTradeOverlay().setTradeEnabled(false);
		}
	}

	@Override
	public void sendTradeOffer() {
		List<ResourceType> sending = new ArrayList<>();
		for (int i = 0; i < sendbrick; i++) {
			sending.add(ResourceType.BRICK);
		}
		for (int i = 0; i < sendwood; i++) {
			sending.add(ResourceType.WOOD);
		}
		for (int i = 0; i < sendwheat; i++) {
			sending.add(ResourceType.WHEAT);
		}
		for (int i = 0; i < sendsheep; i++) {
			sending.add(ResourceType.SHEEP);
		}
		for (int i = 0; i < sendore; i++) {
			sending.add(ResourceType.ORE);
		}

		List<ResourceType> receiving = new ArrayList<>();
		for (int i = 0; i < receivebrick; i++) {
			receiving.add(ResourceType.BRICK);
		}
		for (int i = 0; i < receivewood; i++) {
			receiving.add(ResourceType.WOOD);
		}
		for (int i = 0; i < receivesheep; i++) {
			receiving.add(ResourceType.SHEEP);
		}
		for (int i = 0; i < receivewheat; i++) {
			receiving.add(ResourceType.WHEAT);
		}
		for (int i = 0; i < receiveore; i++) {
			receiving.add(ResourceType.ORE);
		}

		getTradeOverlay().closeModal();
		try {
			if(!(facade.getGame().getPlayerManager().getPlayerByIndex(tradePartner) instanceof AIPlayer)) {
				getWaitOverlay().showModal();
			}
		} catch (PlayerExistsException e) {
			e.printStackTrace();
		}
		facade.tradeWithPlayer(UserCookie.getInstance().getPlayerIndex(), tradePartner, sending, receiving);

		sendwood = 0;
		sendbrick = 0;
		sendsheep = 0;
		sendwheat = 0;
		sendore = 0;

		receivewood = 0;
		receivebrick = 0;
		receivesheep = 0;
		receivewheat = 0;
		receiveore = 0;

		woodStatus = false;
		brickStatus = false;
		sheepStatus = false;
		wheatStatus = false;
		oreStatus = false;

		tradePartner = -1;
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		tradePartner = playerIndex;
		if (sendbrick+sendwood+sendsheep+sendwheat+sendore > 0 && receivebrick+receivewood+receiveore+receivesheep+receivewheat > 0) {
			getTradeOverlay().setTradeEnabled(true);
			getTradeOverlay().setStateMessage("Trade!");
		} else if (tradePartner == -1) {
			getTradeOverlay().setStateMessage("Select player to trade with");
			getTradeOverlay().setTradeEnabled(false);
		} else if (!(sendbrick+sendwood+sendsheep+sendwheat+sendore > 0)) {
			getTradeOverlay().setStateMessage("Select Resource to send to other player");
			getTradeOverlay().setTradeEnabled(false);
		} else if (!(receivebrick+receivewood+receiveore+receivesheep+receivewheat > 0)) {
			getTradeOverlay().setStateMessage("Select Resource to receive from other player");
			getTradeOverlay().setTradeEnabled(false);
		}
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		switch (resource) {
			case BRICK:
				brickStatus = true;
				receivebrick = 0;
				break;
			case WOOD:
				woodStatus = true;
				receivewood = 0;
				break;
			case WHEAT:
				wheatStatus = true;
				receivewheat = 0;
				break;
			case SHEEP:
				sheepStatus = true;
				receivesheep = 0;
				break;
			case ORE:
				oreStatus = true;
				receiveore = 0;
				break;

		}
		getTradeOverlay().setResourceAmount(resource, "0");
		getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		switch (resource) {
			case BRICK:
				brickStatus = false;
				sendbrick = 0;
				getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, brickcount > 0, false);
				break;
			case WOOD:
				woodStatus = false;
				sendwood = 0;
				getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, woodcount > 0 , false);
				break;
			case WHEAT:
				wheatStatus = false;
				sendwheat = 0;
				getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, wheatcount > 0 , false);
				break;
			case SHEEP:
				sheepStatus = false;
				sendsheep = 0;
				getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, sheepcount > 0 , false);
				break;
			case ORE:
				oreStatus = false;
				sendore = 0;
				getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, orecount > 0 , false);
				break;
		}
		getTradeOverlay().setResourceAmount(resource, "0");
	}

	@Override
	public void unsetResource(ResourceType resource) {
		switch (resource) {
			case BRICK:
				sendbrick = 0;
				receivebrick = 0;
				break;
			case WOOD:
				sendwood = 0;
				receivewood = 0;
				break;
			case SHEEP:
				sendsheep = 0;
				receivesheep = 0;
				break;
			case WHEAT:
				sendwheat = 0;
				receivewheat = 0;
				break;
			case ORE:
				sendore = 0;
				receiveore = 0;
				break;
		}
		if(sendbrick+sendwood+sendsheep+sendwheat+sendore <= 0) {
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().setStateMessage("Select Resource to Send to Other Player");
		}
		if(receivebrick+receivewood+receivesheep+receivewheat+receiveore <= 0) {
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().setStateMessage("Select Resource to Receive from Other Player");
		}
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().closeModal();
		getTradeOverlay().setPlayerSelectionEnabled(false);

		sendwood = 0;
		sendbrick = 0;
		sendsheep = 0;
		sendwheat = 0;
		sendore = 0;

		receivewood = 0;
		receivebrick = 0;
		receivesheep = 0;
		receivewheat = 0;
		receiveore = 0;

		woodStatus = false;
		brickStatus = false;
		sheepStatus = false;
		wheatStatus = false;
		oreStatus = false;

		tradePartner = -1;

		getTradeOverlay().setTradeEnabled(false);

		getTradeOverlay().setResourceAmount(ResourceType.BRICK, "0");
		getTradeOverlay().setResourceAmount(ResourceType.WOOD, "0");
		getTradeOverlay().setResourceAmount(ResourceType.WHEAT, "0");
		getTradeOverlay().setResourceAmount(ResourceType.ORE, "0");
		getTradeOverlay().setResourceAmount(ResourceType.SHEEP, "0");

		getTradeOverlay().unSetResource(ResourceType.BRICK);
		getTradeOverlay().unSetResource(ResourceType.WOOD);
		getTradeOverlay().unSetResource(ResourceType.WHEAT);
		getTradeOverlay().unSetResource(ResourceType.SHEEP);

		getTradeOverlay().unSetResource(ResourceType.ORE);
		getTradeOverlay().setResourceSelectionEnabled(false);
		getTradeOverlay().setStateMessage("Select player to trade with");
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		getAcceptOverlay().closeModal();
		getAcceptOverlay().reset();
		facade.answerTrade(UserCookie.getInstance().getPlayerIndex(), willAccept);
	}
}
