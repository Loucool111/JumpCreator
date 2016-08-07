package ch.reaamz.jumpcreator;

public class JumpTimer implements Runnable
{
	private int seconds = 0;
	private int jumpID = -1;
	private boolean finished = false;
	
	public JumpTimer(int jumpID)
	{
		this.jumpID = jumpID;
	}
	
	@Override
	public void run()
	{
		if (!finished)
			seconds++;
	}
	
	public int getSeconds()
	{
		return this.seconds;
	}
	
	public int getJumpID()
	{
		return this.jumpID;
	}
	
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}
	
	public boolean isFinished()
	{
		return this.finished;
	}
}
