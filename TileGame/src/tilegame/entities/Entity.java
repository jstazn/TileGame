package tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import tilegame.Game;
import tilegame.Handler;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y;//private variable, but classes that extend this can use it
						//use float so it is more smooth
	protected int width, height;//used for dimensions of entity
	
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0, 0, width, height);//for hitbox, aka bounds of entity
	}
	

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {//tests each entity to see if it collides with entity
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {//get list of entitties and loop through it
			if(e.equals(this))//makes sure it does not check against itself
				continue;
			if(e.getCollisionBounds(0f,  0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;//if two rectancles overlap, return true
		}
		return false;//else
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset),  (int) (y + bounds.y + yOffset), 
				bounds.width, bounds.height);
	}
	
	
	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	
}			
