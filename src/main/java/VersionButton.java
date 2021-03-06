import de.gerrygames.the5zig.clientviaversion.main.ClientViaVersion;
import eu.the5zig.mod.gui.Gui;

import java.util.Collections;
import java.util.stream.Collectors;

public class VersionButton extends ClientViaVersionDropDownButton {

	public VersionButton(int id, int x, int y, int width) {
		// :^)
		super(id,
				x,
				y,
				width,
				20,
				15,
				ClientViaVersion.supportedVersion
						.stream()
				        .map(protocolVersion -> new DropDownElement(protocolVersion.getName(),
							() -> ClientViaVersion.setProtocol(protocolVersion)))
						.collect(Collectors.toList()),
				ClientViaVersion.supportedVersion.indexOf(ClientViaVersion.selected)
		);
		Collections.reverse(elements);
	}

	private float lastX = Float.NaN;
	private float lastY = Float.NaN;
	private static final float drag = 0.5f;
	private static final String toolTip = "§cUse at own risk!";
	@Override
	protected void onPostDraw(int mouseX, int mouseY) {
		super.onPostDraw(mouseX, mouseY);
		if (Float.isNaN(lastX)) {
			lastX = mouseX;
			lastY = mouseY;
		} else {
			lastX = lastX + (((float)mouseX) - lastX) * drag;
			lastY = lastY + (((float)mouseY) - lastY) * drag;
		}

		if (isHovered(mouseX, mouseY)) {
			Gui.drawScaledString(toolTip, lastX+5, lastY, 1.1f);
		}
	}
}