package controll;

public enum Const_Game {

	THREAD_SLEEP_TIME(25),

	TITLE(0),
	CREATE(1),
	PLAY(2),
	GAME_STATE_NUM(2),

	BACK(0),
	RIGHT(1),
	FRONT(2),
	LEFT(3),

	PLAYER_SPEED(6),
	BOX_SPEED(6),

	STAGE_NUM(1),
	UNIT_NUM(5),
	PLAYER_ANIMATION_NUM(4),
	HOLE_ANIMATION_NUM(3),
	CREATE_UNIT_ANIMATION_NUM(4),
	CREATE_BUTTON_ANIMATION_NUM(2),

	PLAYER_ANIMATION_FRAME_NUM(3),
	HOLE_ANIMATION_FRAME_NUM(20),
	CREATE_UNIT_ANIMATION_FRAME_NUM(3),


	CREATE_BUTTON_OPEN(1),
	CREATE_BUTTON_SAVE(2),
	CREATE_BUTTON_TITLE(3),

	GRASS(0),

	PLAYER(-1),
	FLOOR(0),
	BLOCK(1),
	HOLE(2),
	BOX(3),
	GOAL(4);


	private int cnt;

	private Const_Game(int cnt) {
		this.cnt = cnt;
	}

	public int getCnt() {
		return this.cnt;
	}

}
