package com.jasper.game.entity.abilities;

import java.util.concurrent.TimeUnit;


public class Ability{
	private final String name;
    private long cd;
    private long CD=0;
    private int stacks;
    private int currentStacks;
    
    public Ability(String name,int stacks,int cd){
        this.name=name;
        this.stacks=stacks;
        this.currentStacks=stacks;
        this.cd=cd*1000;

        
    }
    public Ability(String name,int stacks,int cd,TimeUnit unit){
        this.name=name;
        this.stacks=stacks;
        this.currentStacks=stacks;
        this.cd=unit.convert(cd,unit);
  
        
    }
    public void update(){
        if(System.currentTimeMillis()>CD && currentStacks < stacks)
        {
            currentStacks++;
            CD+=cd;
        }
    }
    public boolean isReady(){
        return currentStacks>0;
    }
     public boolean tryUse(){
        return tryUse(1);
    }
    public boolean tryUse(int stacks){
        if(stacks<=currentStacks)
        {
            if(this.stacks==currentStacks)
                CD=System.currentTimeMillis()+cd;  //verry importat
            currentStacks-=stacks;
            return true;
        }
        return false;
    }
    public String getName() {
    	return name;
    }
	public String getCharges() {
		return "" + currentStacks;
	}
	public String getCooldown() {
		if(stacks == currentStacks) {
			return "" + 0;
		}
		return "" + (CD-System.currentTimeMillis());
	}
}



