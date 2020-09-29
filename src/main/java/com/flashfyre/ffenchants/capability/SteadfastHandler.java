package com.flashfyre.ffenchants.capability;

public class SteadfastHandler implements ISteadfastHandler {
	
	private boolean handled;
	
	public SteadfastHandler() {
		this.handled = false;
	}

	@Override
	public void setSteadfastHandled(boolean handled) {
		this.handled = handled;
		
	}

	@Override
	public boolean getSteadfastHandled() {
		return this.handled;
	}
	

}
