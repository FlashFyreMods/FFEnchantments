package com.flashfyre.ffenchants.capability;

public class MaelstromApplied implements IMaelstromApplied {
	
	private boolean maelstromApplied = false;

	@Override
	public boolean maelstromApplied() {
		return this.maelstromApplied;
	}

	@Override
	public void setMaelstromApplied(boolean maelstromApplied) {
		this.maelstromApplied = maelstromApplied;
	}
}
