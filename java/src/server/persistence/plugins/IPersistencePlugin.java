package server.persistence.plugins;


/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public interface IPersistencePlugin {
    //region Plugin Methods
    /**
     * Returns a connection to the database
     *
     * @return
     */
    Object getConnection(); // TODO: 4/2/2016 Maybe use a wrapper instead of Object...

    /**
     * Clears the database
     */
    void clear();

    /**
     * Starts a transaction on the database
     *
     */
    void startTransaction();

    /**
     * Ends a transaction on the database
     *
     * @param commitTransaction
     * @throws
     */
    void endTransaction(boolean commitTransaction);
    //endregion
}