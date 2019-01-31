package org.vaadin.jonni;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

	public MainView() {
		Button showCookieInfo = new Button("Get cookie info", click -> {
			// inside the jsStatement:
			// $0 is a reference to the client-side element because of the second argument
			// of executeJavaScript call
			// $0.$server is a reference to the @ClientCallable API of this
			String jsStatement = "$0.$server.showCookieInfo(document.cookie)";
			UI.getCurrent().getPage().executeJavaScript(jsStatement, this);
		});
		Button setCookieButton = new Button("Set cookie 'foobar'", click -> {
			String jsStatement = "document.cookie = 'foobar=42';";
			UI.getCurrent().getPage().executeJavaScript(jsStatement);
		});
		Button unsetCookieButton = new Button("Unset cookie 'foobar'", click -> {
			String jsStatement = "document.cookie = 'foobar=; expires=Thu, 01 Jan 1970 00:00:00 UTC;'";
			UI.getCurrent().getPage().executeJavaScript(jsStatement);
		});
		add(showCookieInfo, setCookieButton, unsetCookieButton);
	}

	@ClientCallable
	public void showCookieInfo(String info) {
		String formated = Arrays.asList(info.split(";")).stream().map(s -> s.trim()).collect(Collectors.joining(";\n"));
		new Dialog(new Pre(formated)).open();
	}
}
