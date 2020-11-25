package model.sprite.movable;

import model.sprite.Sprite;

public interface Movable {

	public void moveRight();
	public void moveLeft();
	public void moveFront();
	public void moveBack();

	public void stop();

	public boolean collideSprite(Sprite sprite);
}
