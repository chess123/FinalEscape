import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.AlphaComposite;

public class ItemPanel extends JPanel {

	private final int BLOCK_SIZE = 10;
	private Inventory inventory;
	private int index;

	public ItemPanel(Inventory inventory, int index) {
		this.inventory = inventory;
		this.index = index;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawImage(g);
		drawBorder(g);
	}

	private void drawImage(Graphics g) {
		Item item = inventory.get(index);
		if (item != null)
			if (item.getOpacity() != 1f) {
				((Graphics2D)g).setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, item.getOpacity()));
				g.drawImage(item.getImage(), 0, 0, getWidth(), getHeight(), this);
				((Graphics2D)g).setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
			else g.drawImage(item.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	private void drawBorder(Graphics g) {
		if (index == inventory.getSelectedItemIndex())
			g.setColor(Color.YELLOW);
		else g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), BLOCK_SIZE);
		g.fillRect(0, getHeight() - BLOCK_SIZE, getWidth(), BLOCK_SIZE);
		g.fillRect(0, 0, BLOCK_SIZE, getHeight());
		g.fillRect(getWidth() - BLOCK_SIZE, 0, BLOCK_SIZE, getHeight());
	}
}