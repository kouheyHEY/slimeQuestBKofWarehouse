package resouceControll.load;

import java.awt.Image;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.swing.JFrame;

import util.CommonUtil;

public class ImageLoader extends JFrame{

	//load all images
	public void loadAllImages() {return;}

	public Image loadImageFromFilePath(String filePath) {
		try {
			URL url = this.getClass().getClassLoader().getResource(filePath);
			Image image = createImage((ImageProducer)url.getContent());
			return image;
		}catch(FileNotFoundException e) {
			CommonUtil.AlertWithMessage(e.toString(), null);
		}catch(NullPointerException e){
			CommonUtil.AlertWithMessage(e.toString() + "\n" + filePath + "の読み込みに失敗しました。", null);
		}catch (Exception e) {
			CommonUtil.AlertWithMessage(e.toString(), null);
		}

		return null;
	}
}
