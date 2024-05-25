package utils;

public class Constants {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
    }


    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int JUMP = 2;
        public static final int ATTACK = 3;

        public static int getSpriteAmount(int player_action){
            switch (player_action){
                case IDLE:
                case WALK:
                    return 4;
                case JUMP:
                    return 6;
                case ATTACK:
                    return 5;
                default:
                    return 1;
            }
        }

    }

    public static class ZombieConstants{
    public static final int IDLE = 0;
    public static final int WALK = 1;
    public static final int ATTACK = 2;
    public static final int DEATH = 3;
    public static final int DISAPPEAR = 4;
    public static final int DODGE = 5;
    public static final int HURT = 6;
    public static final int UPRISE = 7;

        public static int getSpriteAmount(int zombieAction){
            switch (zombieAction){
                case IDLE:
                case WALK:
                    return 3;
                case DISAPPEAR:
                case DODGE:
                case HURT:
                    return 4;
                case ATTACK:
                    return 7;
                case DEATH:
                    return 9;
                case UPRISE:
                    return 12;
                default:
                    return 1;
            }
        }
    }
}

//
//zombie:
//1 idle
//2 walk
//3 attack
//4 death
//5 disappear
//6 dodge
//7 hurt
//8 uprise
//
//




