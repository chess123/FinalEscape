package finalescape.mapcomponent;

import finalescape.map.Map;
import finalescape.util.Direction;
import finalescape.item.Item;

/**
 * A {@link ProjectileComponent} corresponding to a homework packet (the
 * {@link finalescape.item.Packet} {@link Item}). This projectile can only kill
 * the main {@link Character} (the {@link Coder}).
 *
 * This also doesn't destroy solid objects when colliding with them (other than
 * other {@link ProjectileComponent}s, but can be used to delay the {@link Failure}
 * {@link Character}.
 *
 * @author Ofek Gila
 * @see ProjectileComponent
 * @see finalescape.item.Packet
 */
public class PacketProjectile extends ProjectileComponent {
	public PacketProjectile(Map map, int x, int y, Direction dir, Item item) {
		super(map, x, y, item, dir);
	}

	public PacketProjectile(Item item) {
		super(item);
	}

	/**
	 * Checks if {@code Packet} can move to a specific coordinate, stunning
	 * {@link Failure} components, killing the main {@link Character}, or destroying
	 * other {@link ProjectileComponent}s.
	 * @param  x x coordinate
	 * @param  y y coordinate
	 * @return   true if can move here, false otherwise
	 */
	@Override
	public boolean canMoveHere(int x, int y) {
		MapComponent componentThere = getMap().get(x, y);
		if (componentThere == null || !componentThere.isSolid())
			return true;
		else if (componentThere instanceof Failure)
			componentThere.preventUpdate(10);
		if (componentThere == getMap().getMainCharacter()
			|| componentThere instanceof ProjectileComponent)
			componentThere.destroy();
		return false;
	}
}
