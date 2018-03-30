import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

import entities.Apple;

/**
 * 
 */

/**
 * @author LTAdmin
 *
 */
public class FilteringApples {
	
	private final String color;
	private final int boundary;

	/**
	 * 
	 * Examples of the use of predicates and Lambda functions.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<Apple> inventory = Arrays.asList( new Apple(80, "green")
				                             , new Apple(155, "green")
				                             , new Apple(120, "red")
				
				);
		
		FilteringApples self = new FilteringApples("green", 150);
		
		List<Apple> greenApples = FilteringApples.filterApples(inventory, self::isColorApple );
		System.out.println("Green Apples: " + greenApples);
		
		List<Apple> heavyApples = FilteringApples.filterApples(inventory, self::isHeavyApple);
		System.out.println("Heavy Apples: " + heavyApples);
		
		List<Apple> greenApples2 = FilteringApples.filterApples( inventory
				                                    , (Apple apple) -> self.getColor().equals( apple.getColor() ) 
				                                    );
		System.out.println("Green Apples: " + greenApples2);
		
		List<Apple> heavyApples2 = FilteringApples.filterApples(inventory
				                                    , (Apple apple) -> ( apple.getWeight() > self.getBoundary() )
				                                    );
		System.out.println("Heavy Apples: " + heavyApples2);
		
		List<Apple> weirdApples = FilteringApples.filterApples( inventory
				                                   , (Apple apple) -> (apple.getWeight() < 80 || apple.getColor().equals("brown") )
				                                   );
		System.out.println("Weird Apples: " + weirdApples);
		
		
		List<Apple> greenApples3 = filterApplesStream( inventory
				                                     , (Apple apple) -> self.getColor().equals( apple.getColor() ) );
		System.out.println("Green Apples via Stream: " + greenApples3);
		
		List<Apple> heavyApples3 = filterApplesParallelStream( inventory
				                                             , (Apple apple) -> ( apple.getWeight() > self.getBoundary() ) 
				                                             );
		System.out.println("Heavy Apples: " + heavyApples3);
		
		
		// Find all files that are hidden in the base directory, Java 7 Style:
		File[] hiddenFiles = new File(".").listFiles( new FileFilter() {

			@Override
			public boolean accept(File file) {
				
				return file.isHidden();
			}
			
		});
		
		// Java 8 style, using component reference:
		hiddenFiles = new File(".").listFiles( File::isHidden );
		
		System.out.println("Overview of hidden files:");
		for(File file: hiddenFiles) {
			System.out.println("File " + file.getAbsolutePath());
		}
		
		// Find all files that are visible in the base directory, also in Java 8 style):
		// Note that I can not use: !File::isHidden.
		File[] visibleFiles = new File(".").listFiles(  file -> !file.isHidden() );
		
		System.out.println("Overview of visible files:");
		for(File file: visibleFiles) {
			System.out.println("File " + file.getAbsolutePath());
		}		
		
	}
	
	
	public FilteringApples(String color, int boundary) {
		super();
		this.color = color;
		this.boundary = boundary;
	}



	public String getColor() {
		return color;
	}

	public int getBoundary() {
		return boundary;
	}


	// Java 7 Style of retrieving all Apple instances that meet the criteria:
	public List<Apple> filterGreenApples(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		
		for (Apple apple: inventory) {
			if (color.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		
		return result;
	}
	
	public List<Apple> filterHeavyApples(List<Apple> inventory, int boundary) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple: inventory) {
			if (apple.getWeight() > boundary) {
				result.add(apple);
			}
		}
		
		return result;
	}
	
	// Java 8 style conditions that can be passed as arguments to a Predicate (accepting booleans).
	public boolean isGreenApple(Apple apple) {
		
		return "green".equals(apple.getColor());
	}

	public boolean isColorApple(Apple apple) {
		
		return this.getColor().equals(apple.getColor());
	}
	
	public boolean isHeavyApple(Apple apple) {
		return (apple.getWeight() > this.getBoundary() );
	}
	
	public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate ) {
		List<Apple> result = new ArrayList<>();
		
		for (Apple apple: inventory) {
			if (predicate.test(apple)) {
				result.add(apple);
			}
		}
		
		return result;
	}

	// Now using Stream functionality:
	public static List<Apple> filterApplesStream(List<Apple> inventory, Predicate<Apple> predicate ) {
		List<Apple> result = new ArrayList<>();
		
		result = inventory.stream().filter(predicate).collect( toList() );	
		return result;
	}

	public static List<Apple> filterApplesParallelStream(List<Apple> inventory, Predicate<Apple> predicate ) {
		List<Apple> result = new ArrayList<>();
		
		result = inventory.parallelStream().filter(predicate).collect( toList() );	
		return result;
	}

}
