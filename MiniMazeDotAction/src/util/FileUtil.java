package util;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controll.Const_Game;
import controll.CreateController;
import model.Map;

public class FileUtil {
	public static File chooseFile(String currentD, String fileDescription, String fileExtension, Component parent) {
		JFileChooser chooser = new JFileChooser(currentD);

		FileNameExtensionFilter filter = new FileNameExtensionFilter(fileDescription, fileExtension);
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(parent);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getName());
			return chooser.getSelectedFile();
		}else if(returnVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("cancel to choose File.");
		}

		return null;
	}

	public static String saveFile(String currentD, String fileDescription, String fileExtension, Component parent, CreateController createCtrl) {

		JFileChooser chooser = new JFileChooser(currentD);

		FileNameExtensionFilter filter = new FileNameExtensionFilter(fileDescription, fileExtension);
		chooser.setFileFilter(filter);

		int returnVal = chooser.showSaveDialog(parent);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getCurrentDirectory() + "\\" +
					chooser.getSelectedFile().getName() +
					"." + fileExtension;

		}else if(returnVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("cancel to save file.");
		}

		return null;
	}

	public static Map loadUnitMapFromFile(String filePath){
		Map map = new Map();
		int[][] newMap;
		try(
				FileReader fr = new FileReader(filePath);
				BufferedReader br = new BufferedReader(fr)
				){
			String str;

			str = br.readLine();
			int row = Integer.parseInt(str);

			str = br.readLine();
			int col = Integer.parseInt(str);

			str = br.readLine();
			map.stageState = CommonUtil.changeToUnitState(Integer.parseInt(str));

			newMap = new int[row][col];

			int r = 0;
			while((str = br.readLine()) != null) {
				String[] strs = str.split(",");
				for(int c = 0; c < col; c++) {
					newMap[r][c] = Integer.parseInt(strs[c]);
				}
				r++;
			}

			map.init(row, col);
			map.setMap(newMap);

		}catch(FileNotFoundException e) {
			System.out.println("ファイルが見つかりませんでした。");
			e.printStackTrace();
			map = null;
		}catch(Exception e) {
			System.out.println("不明なエラーが発生しました。");
			e.printStackTrace();
			map = null;
		}

		return map;
	}

	public static boolean saveMapToFile(File saveFile, int[][] field, Const_Game stageState) {
		if(saveFile == null) {
			return false;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))){

			writer.write(Integer.toString(field.length));
			writer.newLine();
			writer.write(Integer.toString(field[0].length));
			writer.newLine();
			writer.write(Integer.toString(stageState.getCnt()));
			writer.newLine();

			for(int y = 0; y < field.length; y++) {
				String fieldString = "";
				for(int x = 0; x < field[0].length; x++) {
					fieldString += Integer.toString(field[y][x]) + ",";
				}

				fieldString = fieldString.substring(0, fieldString.length() - 1);

				writer.write(fieldString);
				writer.newLine();
			}

			writer.flush();

			return true;
		}catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
