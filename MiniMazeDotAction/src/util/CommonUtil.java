package util;

import java.awt.Component;
import java.awt.Image;

import javax.swing.JOptionPane;

import controll.Const_Game;

public class CommonUtil {
	public static Image[] deepCopyImage(Image[] originArray) {
		Image[] newArray = new Image[originArray.length];

		for(int i = 0; i < originArray.length; i++) {
			newArray[i] = originArray[i];
		}

		return newArray;
	}

	public static int[][] deep2CopyInt(int[][] origin2Array) {
		int[][] newArray = new int[origin2Array.length][origin2Array[0].length];

		for(int i = 0; i < newArray.length; i++) {
			for(int j = 0; j < newArray[0].length; j++) {
				newArray[i][j] = origin2Array[i][j];
			}
		}

		return newArray;
	}

	public static int randInt(int min, int max) {
		return (int)(Math.random() * (max - min + 1)) + min;
	}

	public static int[][] changeToIntArray(Const_Game[][] miniField){
		int[][] newArray = new int[miniField.length][miniField[0].length];

		for(int y = 0; y < miniField.length; y++) {
			for(int x = 0; x < miniField[0].length; x++) {
				newArray[y][x] = miniField[y][x].getCnt();
			}
		}

		return newArray;
	}

	public static Const_Game[][] changeToUnitArray(int[][] intArray){
		Const_Game[][] newArray = new Const_Game[intArray.length][intArray[0].length];

		for(int y = 0; y < intArray.length; y++) {
			for(int x = 0; x < intArray[0].length; x++) {
				newArray[y][x] = changeToUnitState(intArray[y][x]);
			}
		}

		return newArray;
	}

	public static Const_Game changeToUnitState(int unitId) {
		Const_Game unitState = Const_Game.FLOOR;
		if(unitId == Const_Game.BLOCK.getCnt()) {
			unitState = Const_Game.BLOCK;
		}else if (unitId == Const_Game.HOLE.getCnt()) {
			unitState = Const_Game.HOLE;
		}else if(unitId == Const_Game.BOX.getCnt()) {
			unitState = Const_Game.BOX;
		}else if(unitId == Const_Game.GOAL.getCnt()) {
			unitState = Const_Game.GOAL;
		}else if(unitId == Const_Game.PLAYER.getCnt()) {
			unitState = Const_Game.PLAYER;
		}else{
			unitState = Const_Game.FLOOR;
		}

		return unitState;
	}

	public static void AlertWithMessage(String alertMessage, Component parent) {
		JOptionPane.showMessageDialog(parent, alertMessage);
	}

	public static int confirmDialog(String message, Component parent) {
		int option = JOptionPane.showConfirmDialog(parent, message);
		switch(option) {
		case JOptionPane.YES_OPTION:
			return 1;
		case JOptionPane.NO_OPTION:
			return -1;
		default:
			return 0;
		}
	}
}
