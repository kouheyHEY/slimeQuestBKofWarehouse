package view;

import java.awt.Color;

public enum Const_Color {
	CREATE_COMPONENT_STROKE_DARK(new Color(0, 192, 0)),
	CREATE_COMPONENT_STROKE_LIGHT(Color.GREEN),
	CREATE_COMPONENT_BG(new Color(160, 255, 192));

	private Color c;
	private Const_Color(Color c) {
		this.c = c;
	}

	public Color getColor() {
		return c;
	}
}
