package com.apress.prospring5.ch3.sec4;

import com.apress.prospring5.ch3.Encyclopedia;


//3-11.
interface Oracle{
	String defineMeaningOfLife();
}

public class BookwormOracle implements Oracle{
	
	private Encyclopedia encyclopedia;
	
	public void setEncyclopedia(Encyclopedia encyclopedia) {
		this.encyclopedia = encyclopedia;
	}
	
	@Override
	public String defineMeaningOfLife() {
		return "Encyclopedias are waste of money - go see the world instead : 3-11";
	}


}
