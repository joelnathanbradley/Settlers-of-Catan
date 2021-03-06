package shared.model.game;

import com.google.gson.JsonObject;
import shared.model.JsonSerializable;

import java.io.Serializable;

/**
 * @author Corbin Byers
 */
public final class MessageLine implements Serializable, JsonSerializable {

    private String source;
    private String content;

    public MessageLine(final String person, final String c) {
        assert person != null;
        assert person.length() > 0;
        assert c != null;
        assert c.length() > 0;

        source = person;
        content = c;
    }

    public MessageLine(final JsonObject jo) {
        assert jo != null;
        assert jo.has("message");
        assert jo.has("source");

        content = jo.get("message").getAsString();
        source = jo.get("source").getAsString();
    }

    public String getPlayer(){
        return source;
    }

    public String getMessage(){
        return content;
    }

    @Override
    public JsonObject toJSON() {
        return null;
    }
}
