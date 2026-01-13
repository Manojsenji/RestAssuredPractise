package day1;

public class Car {
	String color;

	public void start() {
		System.out.println("......starting......");

	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car maruthi = new Car();
		System.out.println("Maruthi car color..."+(maruthi.color = "red"));
		maruthi.start();
		
		Car hyundai = new Car();
		System.out.println("Hyundai car color..."+(hyundai.color="white"));
		hyundai.start();
	}
}