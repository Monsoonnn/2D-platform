package utilz;

import RPGgame.Game;

public class Constants {
    public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}
	}
    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUN = 1;
        public static final int JUMP = 2;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_2 = 7;
        public static final int FALL = 8;


        public static int GetSpriteAmount(int player_action){
            switch(player_action){
                case IDLE:
                    return 6;
                case RUN:
                    return 7;
                case ATTACK_1:
                    return 4;
                case ATTACK_2:
                    return 7;
                case JUMP:
                    return 11;
                case FALL:
                    return 1;
                default:
                    return 1;
            }
        }
    }
    
}
