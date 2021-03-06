package server.filters;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Verifies that a user is part of a game when wrapped around an HTTP handler
 *
 * @author Derek Argueta
 * {@link} http://sparkjava.com/documentation.html#filters
 */
public final class GameFilter implements Filter {
    @Override
    public void handle(final Request request, final Response response) throws Exception {
        // -- TODO check that the game cookie is valid and user actually belongs to game
    }
}
