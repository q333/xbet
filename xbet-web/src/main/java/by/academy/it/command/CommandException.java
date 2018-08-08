package by.academy.it.command;

/**
 * The exception occurs when the main controller receives an incorrect command
 *
 */
public class CommandException extends Exception {

    /**
     * Constructs an instance of <code>CommandException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CommandException(String msg) {
        super(msg);
    }
}
