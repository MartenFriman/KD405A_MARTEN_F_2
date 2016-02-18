/**Class that simulates a calculator*/
public class Calculator {
	private String operator;
	private int result;
	private int operand;
	//Using this when referring to instance variables makes it extra obvious that the reference 
	//is to an instance variable. Writing this.operator is the same as  just writing operator
	
	/**Creates  a new calculator instance*/
	public Calculator() {
		this.operator = "";
		this.result = 0;
		this.operand = 0; //http://en.wikipedia.org/wiki/Operand
	}
	
	/** returns the operator */
	public String getOperator(){
		return this.operator;
	}
	
	/**returns result*/
	public int getResult(){
		return this.result;
	}
	
	public int getOperand(){
		return this.operand;	
	}
	
	/**buttonpress*/ 
	public void numberButtonPressed(int number){
	  this.operand = this.operand*10 + number;
	}
	
	/**clears all including operand and result*/
	public void clear(){
		this.operator = "";
		this.result = 0;
		this.operand = 0;
	}
	
	public void plus(){
	   this.result = this.operand;
	   this.operand = 0;
	   this.operator = "+";
	}
	
	public void minus(){
		result = operand;
		operand = 0;
		operator = "-";
	}
	
	public void mult(){
		result = operand;
		operand = 0;
		operator = "*";
	}
	
	public void equals(){
		if (operator.equals("*")){
			result= result * operand;
		}else if(operator.equals("-")){
			result= result - operand;
		}else if(operator.equals("+")){
			result= result + operand;
		}
		operand = 0;
	}
}