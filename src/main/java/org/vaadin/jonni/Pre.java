package org.vaadin.jonni;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;

@Tag("pre")
public class Pre extends HtmlContainer {

	public Pre() {
		super();
	}

	public Pre(Component... components) {
		super(components);
	}

	public Pre(String text) {
		super();
		setText(text);
	}

}