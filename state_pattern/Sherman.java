package state_pattern;

public class Sherman
{
    // Composition classes.

    public static abstract class MainGun
    {
        public abstract void fire();
    }

    public static class SixPounderGun extends MainGun
    {
        @Override
        public void fire()
        {
            System.out.println("BOOM!");
        }
    }

    public static class SeventeenPounderGun extends MainGun
    {
        @Override
        public void fire()
        {
            System.out.println("KABOOM!");
        }
    }
    
    // End of composition classes.

    // State pattern classes.

    public abstract class TankState
    {
        protected final Sherman tank;

        public TankState(Sherman tank)
        {
            this.tank = tank;
        }

        public abstract void shoot();

        public abstract void bailOut();
    }

    public class BailedOutState extends TankState
    {
        public BailedOutState(Sherman tank)
        {
            super(tank);
        }

        @Override
        public void shoot()
        {
            System.out.println("Can't shoot, tank bailed out!");
        }

        @Override
        public void bailOut()
        {
            System.out.println("Tank already bailed out!");
        }
    }

    public class CrewedWithAmmunitionState extends TankState
    {
        private final MainGun gun;

        private int ammunition;

        public CrewedWithAmmunitionState(
            final Sherman tank,
            final MainGun gun,
            final int ammunition)
        {
            super(tank);
            this.ammunition = ammunition;
            this.gun = gun;
        }

        @Override
        public void shoot()
        {
            if (ammunition > 0)
            {
                ammunition = ammunition - 1;
                gun.fire();
            }
            else
            {
                System.out.println("Click...");
                tank.state = new Sherman.CrewedWithoutAmmunitionState(tank);
            }
        }

        @Override
        public void bailOut()
        {
            System.out.println("Crew bailing out!");
            tank.state = new Sherman.BailedOutState(tank);
        }
    }

    public class CrewedWithoutAmmunitionState extends TankState
    {
        public CrewedWithoutAmmunitionState(Sherman tank)
        {
            super(tank);
        }

        @Override
        public void shoot()
        {
            System.out.println("Click...");
        }

        @Override
        public void bailOut()
        {
            System.out.println("Crew bailing out!");
            tank.state = new Sherman.BailedOutState(tank);
        }
    }

    // End of state pattern classes.

    // Tank model.

    protected TankState state;

    public Sherman(final MainGun gun, final int ammunition)
    {
        state = new Sherman.CrewedWithAmmunitionState(this, gun, ammunition);
    }

    public void shoot()
    {
        state.shoot();
    }

    public void bailOut()
    {
        state.bailOut();
    }

    // End of tank model.

    // Test code.

    public static void main(String[] args)
    {
        var tank = new Sherman(new Sherman.SeventeenPounderGun(), 2);
        tank.shoot();
        tank.shoot();
        tank.shoot();
        tank.bailOut();
        tank.bailOut();
    }
}
