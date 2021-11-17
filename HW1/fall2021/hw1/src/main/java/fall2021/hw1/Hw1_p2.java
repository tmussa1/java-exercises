package fall2021.hw1.src.main.java.fall2021.hw1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class Hw1_p2 {

	public static void findByMake(Car[] cars, String make) {

		int matchCount = 0;

		for(int i = 0; i < cars.length; i++){
			if(cars[i] != null && cars[i].getMake().equals(make)){
				System.out.println("Make = "+ cars[i].getMake() +
						"  Year = " + cars[i].getYear() +
						"  Price = " + cars[i].getPrice());
				matchCount++;
			}
		}

		if(matchCount == 0){
			System.out.println("No cars made by " + make + " were found");
		}
	}

	public static void newerThan(Car[] cars, int year) {

		int matchCount = 0;

		for(int i = 0; i < cars.length; i++){
			if(cars[i] != null && cars[i].getYear() > year){
				System.out.println("Make = "+ cars[i].getMake() +
						"  Year = " + cars[i].getYear() +
						"  Price = " + cars[i].getPrice());
				matchCount++;
			}
		}

		if(matchCount == 0){
			System.out.println("No cars made after " + year + " were found");
		}
	}

	public static void main(String[] args) throws IOException {

		// complete this part
		// create an array of Car objects, cars, of size 10
		// read input file and store 10 car Objects in the array

		Car cars [] = new Car[10];
		int count = 0;

		try {
 			URL url = Hw1_p2.class.getResource("car_input.txt");
			File file = new File(url.getPath());

//			System.out.println("Tofik " + );
			Scanner scanner = new Scanner(file);

			while(scanner.hasNext()){
				String line = scanner.nextLine();
				String lineArray [] = line.split(",");

				Car car = new Car(lineArray[0],
						Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[1]));
				cars[count++] = car;
			}
		} catch (FileNotFoundException
				 | ArrayIndexOutOfBoundsException
				 | NumberFormatException e) {
			System.out.println("Error reading file " + e.getCause());;
		}

		System.out.println("\nAll cars:");
		for (int i=0; i<cars.length; i++) {
			System.out.println(cars[i]);
		}

		String make = "Honda";
		int year = 2017;

		System.out.println("\nAll cars made by " + make);
		findByMake(cars, make);
		System.out.println("\nAll cars made after " + year);
		newerThan(cars, year);

	}

}