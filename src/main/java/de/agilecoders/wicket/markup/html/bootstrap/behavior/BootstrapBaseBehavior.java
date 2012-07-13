package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.protocol.http.BootstrapApplication;
import de.agilecoders.wicket.protocol.http.IBootstrapApplication;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapBaseBehavior extends Behavior {

    private BootstrapApplication getApplication(Component component) {
        if (component.getApplication() instanceof IBootstrapApplication) {
            return (BootstrapApplication) component.getApplication();
        } else {
            throw new WicketRuntimeException("you have to extend BootstrapApplication or implement IBootstrapApplication to use this behavior.");
        }
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        IBootstrapSettings settings = getApplication(component).getBootstrapSettings();

        headerResponse.render(CssHeaderItem.forReference(settings.getCssResourceReference()));

        if (settings.useResponsiveCss()) {
            headerResponse.render(CssHeaderItem.forReference(settings.getResponsiveCssResourceReference()));
        }

        headerResponse.render(JavaScriptHeaderItem.forUrl(settings.getJqueryUrl(), "jquery"));
        headerResponse.render(JavaScriptHeaderItem.forReference(settings.getJsResourceReference(), new PageParameters(), "bootstrap-js", true));
    }
}