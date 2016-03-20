package shared.dto;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import shared.model.JsonSerializable;

/**
 * @author Derek Argueta
 */
public final class DiscardCardsDTO implements IDTO, JsonSerializable {

    // -- JSON keys
    private static final String kType = "type";
    private static final String kPlayerIndex = "playerIndex";
    private static final String kDiscardedCards = "discardedCards";
    private static final String kBrick = "brick";
    private static final String kOre = "ore";
    private static final String kSheep = "sheep";
    private static final String kWheat = "wheat";
    private static final String kWood = "wood";

    private int playerIndex;
    private int brickCount;
    private int oreCount;

    public int getSheepCount() {
        return sheepCount;
    }

    public int getWoodCount() {
        return woodCount;
    }

    public int getWheatCount() {
        return wheatCount;
    }

    public int getOreCount() {
        return oreCount;
    }

    public int getBrickCount() {
        return brickCount;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    private int sheepCount;
    private int wheatCount;
    private int woodCount;

    /**
     *
     * @param playerIndex  Who's discarding
     * @param discardedBrick
     * @param discardedOre
     * @param discardedSheep
     * @param discardedWheat
     * @param discardedWood
     */
    public DiscardCardsDTO(final int playerIndex, final int discardedBrick, final int discardedOre, final int discardedSheep, final int discardedWheat, final int discardedWood) {
        assert playerIndex >= 0;
        assert playerIndex < 4;
        assert discardedBrick >= 0;
        assert discardedOre >= 0;
        assert discardedSheep >= 0;
        assert discardedWheat >= 0;
        assert discardedWood >= 0;
        assert discardedBrick + discardedOre + discardedSheep + discardedWheat + discardedWood > 0;

        this.playerIndex = playerIndex;
        this.brickCount = discardedBrick;
        this.oreCount = discardedOre;
        this.sheepCount = discardedSheep;
        this.wheatCount = discardedWheat;
        this.woodCount = discardedWood;
    }

    public DiscardCardsDTO(final String json) {
        final JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        this.playerIndex = obj.get(kPlayerIndex).getAsInt();
        JsonObject discardedCards = obj.get(kDiscardedCards).getAsJsonObject();
        this.brickCount = discardedCards.get(kBrick).getAsInt();
        this.oreCount = discardedCards.get(kOre).getAsInt();
        this.sheepCount = discardedCards.get(kSheep).getAsInt();
        this.wheatCount = discardedCards.get(kWheat).getAsInt();
        this.woodCount = discardedCards.get(kWood).getAsInt();
    }

    /**
     * Converts the object to JSON
     *
     * @return The JSON representation of the object
     */
    @Override
    public JsonObject toJSON() {

        final JsonObject inner = new JsonObject();
        inner.addProperty("brick", this.brickCount);
        inner.addProperty("ore", this.oreCount);
        inner.addProperty("sheep", this.sheepCount);
        inner.addProperty("wheat", this.wheatCount);
        inner.addProperty("wood", this.woodCount);

        final JsonObject obj = new JsonObject();
        obj.addProperty(kType, "discardCards");
        obj.addProperty(kPlayerIndex, this.playerIndex);
        obj.add(kDiscardedCards, inner);
        return obj;
    }
}
