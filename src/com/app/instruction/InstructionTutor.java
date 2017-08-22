package com.app.instruction;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class InstructionTutor {
	
	
	//class for instruction structure
	
	private String ins_text;
	private String ins_shape;
	private Image image;
	private  int posX;
	private int posY;
	private int width;
	private boolean switchable;
	/**
	 * @return the switchable
	 */
	public boolean isSwitchable() {
		return switchable;
	}


	/**
	 * @param switchable the switchable to set
	 */
	public void setSwitchable(boolean switchable) {
		this.switchable = switchable;
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	private int height;
	private boolean isthinking;
	
	
	

	
	
	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}


	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}


	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}


	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}


	/**
	 * @return the isthinking
	 */
	public boolean isIsthinking() {
		return isthinking;
	}


	/**
	 * @param isthinking the isthinking to set
	 */
	public void setIsthinking(boolean isthinking) {
		this.isthinking = isthinking;
	}


	
	
	public InstructionTutor()
	{
		//default constructor
		
	}
	
	
	public void draw_instruction(Graphics g)
	
	{	
		if(isSwitchable())
		{
			String padded = String.format("%-20s", getIns_text());
			g.drawImage(getImage(),getPosX()-10, getPosY()-20, getWidth()+ins_text.length()+10,getHeight(), null);
			for(int i=0; i<=padded.length()-20;i=i+20)
			{

				g.setColor(Color.BLACK);
				g.drawString(padded.substring(i, i+20), getPosX()+15, getPosY()+i+25);
				
			}
			
		}
		else
		{

			//g.setColor(Color.TRANSLUCENT);

			g.setColor(Color.WHITE);
			
			 g.drawRect(getPosX(), getPosY(), getWidth(), getHeight());
		}
	
		
		
	}
	
	public Image getImage() {
		return this.image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	public String getIns_shape() {
		return this.ins_shape;
	}
	
	public String getIns_text() {
		return this.ins_text;
	}
	
	public void setIns_shape(String ins_shape) {
		this.ins_shape = ins_shape;
	}
	
	public void setIns_text(String ins_text) {
		this.ins_text = ins_text;
	}
	
	

}
