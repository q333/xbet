package by.academy.it.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

/**
 * This command sets the locale of the application
 */
public class ChangeLocaleCommand extends Command {

    private static final Logger logger = LogManager.getLogger(ChangeLocaleCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter(LANG);
        Locale locale;
        if ("ru".equals(lang)) {
            locale = new Locale("ru", "RU");
        } else {
            locale = Locale.US;
        }
        Config.set(request.getSession(), Config.FMT_LOCALE, locale);
        logger.info("locale has been changed - "  + locale);

        response.sendRedirect(getReferrerURL(request));
    }
}
