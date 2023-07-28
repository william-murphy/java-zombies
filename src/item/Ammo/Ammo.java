package item.ammo;

public abstract class Ammo extends item.StackableItem {
    
    @Override
    public void use() {}

    @Override
    public void stopUse() {}

    public void shoot(item.weapon.Weapon weapon) {

    }

}
