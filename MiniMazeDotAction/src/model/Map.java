package model;

import controll.Const_Game;
import util.CommonUtil;

public class Map {
	public int[][] map;
	public int[][] floorMap;

	private int mapCol;
	private int mapRow;

	public Const_Game stageState;

	public void init(int row, int col) {
		map = new int[mapRow = row][mapCol = col];
		floorMap = new int[row][mapCol];
	}

	public void setMap(int[][] map) {
		this.map = (int[][])CommonUtil.deep2CopyInt(map);
		mapCol = map[0].length;
		mapRow = map.length;
		createFloorMap();
	}

	public void createRandomMap() {
		map = new int[mapRow][mapCol];
		for(int i = 0; i < mapRow; i++) {
			for(int j = 0; j < mapCol; j++) {
				if(Math.random() < 0.2) {
					map[i][j] = CommonUtil.randInt(1, 3);
				}
			}
		}

		createFloorMap();

		while(true) {
			int rndRow = CommonUtil.randInt(0, mapRow - 1);
			int rndCol = CommonUtil.randInt(0, mapCol - 1);
			if(map[rndRow][rndCol] == 0) {
				map[rndRow][rndCol] = Const_Game.PLAYER.getCnt();
				break;
			}
		}
	}

	private void createFloorMap() {
		for(int i = 0; i < mapRow; i++) {
			for(int j = 0; j < mapCol; j++) {
				floorMap[i][j] = CommonUtil.randInt(0, 1);
			}
		}
	}

	public int getMapCol() {return mapCol;}
	public int getMapRow() {return mapRow;}
}
