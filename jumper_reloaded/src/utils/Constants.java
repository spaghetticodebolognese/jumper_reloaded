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


}
