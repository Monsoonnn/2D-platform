 package utilz;

import RPGgame.Game;


public class Constants {
    
    public static class EnemyConstants {
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;
                //GOBLIN
                public static final int GOBLIN = 52;

		public static final int GOBLIN_WIDTH_DEFAULT = 63;
		public static final int GOBLIN_HEIGHT_DEFAULT = 32;

		public static final int GOBLIN_WIDTH = (int) (GOBLIN_WIDTH_DEFAULT * Game.SCALE);
		public static final int GOBLIN_HEIGHT = (int) (GOBLIN_HEIGHT_DEFAULT * Game.SCALE);

		public static final int GOBLIN_DRAWOFFSET_X = (int) (15 * Game.SCALE);
		public static final int GOBLIN_DRAWOFFSET_Y = (int) (9 * Game.SCALE);
                
                //SKELETON
                public static final int SKELETON = 53;

		public static final int SKELETON_WIDTH_DEFAULT = 96;
		public static final int SKELETON_HEIGHT_DEFAULT = 64;

		public static final int SKELETON_WIDTH = (int) (SKELETON_WIDTH_DEFAULT * Game.SCALE);
		public static final int SKELETON_HEIGHT = (int) (SKELETON_HEIGHT_DEFAULT * Game.SCALE);

		public static final int SKELETON_DRAWOFFSET_X = (int) (5 * Game.SCALE);
		public static final int SKELETON_DRAWOFFSET_Y = (int) (5 * Game.SCALE);


		public static int GetSpriteAmount(int enemy_type, int enemy_state) {

			switch (enemy_type) {
			case GOBLIN:
				switch (enemy_state) {
				case IDLE:
					return 4;
				case RUNNING:
					return 8;
				case ATTACK:
					return 8;
				case HIT:
					return 4;
				case DEAD:
					return 4;
				}
                        case SKELETON:
                        switch (enemy_state) {
				case IDLE:
					return 4;
				case RUNNING:
					return 4;
				case ATTACK:
					return 8;
				case HIT:
					return 4;
				case DEAD:
					return 4;
				}
			}
                        

			return 0;

		}
                	public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case GOBLIN:
                            return 20;
                        case SKELETON:
                            return 50;
			default:
                            return 1;
                        
			}
		}

		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case GOBLIN:
                            return 15;
                        case SKELETON:
                            return 20;
			default:
                            return 0;
			}

		}
                public static int GetEnemyScore(int enemy_type){
                switch(enemy_type){
                    case GOBLIN: return 1;
                    case SKELETON: return 5;
                    default: return 0;
                }
        }
           
    }
    public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 48;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

		}

		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
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