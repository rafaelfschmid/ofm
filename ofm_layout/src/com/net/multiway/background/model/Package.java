package com.net.multiway.background.model;

import java.io.IOException;

public abstract class Package {

	protected int length;
	
	public Package()
	{
		
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	
	public abstract void parser() throws IOException;
	
}
