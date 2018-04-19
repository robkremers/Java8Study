package com.java8study.chapter08;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.java8study.chapter08.interfaces.Observer;
import com.java8study.chapter08.interfaces.Subject;

/**
 * Purpose: 
 * Example of the Observer design pattern.
 * This pattern is intended to have the right Observer reacting to an incoming signal and have it processed.
 * 
 * @author LTAdmin
 *
 */
public class ObserverMain {

	public ObserverMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		ObserverMain self = new ObserverMain();
		
		self.execute();

	}
	
	public void execute() {
//		Feed feed = new Feed();
//		feed.registerObserver( new NYTimes() );
//		feed.registerObserver( new Guardian());
//		feed.registerObserver( new LeMonde());
//		
//		feed.notifyObservers("The queen said she loves wine that costs a lot of money!");
		
		Feed feedLambda = new Feed();
		feedLambda.registerObserver( new NYTimes() );
		feedLambda.registerObserver( new Guardian());
		feedLambda.registerObserver( new LeMonde());
		feedLambda.registerObserver( (String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY! " + tweet); }
        });
		
		
		feedLambda.notifyObserversLambda("money",  (String tweet) -> {
			if(tweet != null && tweet.contains("money")){
                System.out.println("notifyObserversLambda: Breaking news in NY! " + tweet); }
			
		} );
	}
	

	/**
	 * Definition of different Observers.
	 */
	
	private class NYTimes implements Observer {

		@Override
		public void notify(String tweet) {
			if (tweet != null && tweet.contains("money") ) {
				System.out.println("Breaking news in New York: " + tweet );
			}
		}
	}

	private class Guardian implements Observer {

		@Override
		public void notify(String tweet) {
			if (tweet != null && tweet.contains("queen") ) {
				System.out.println("Breaking news in London: " + tweet );
			}
		}
	}

	private class LeMonde implements Observer {

		@Override
		public void notify(String tweet) {
			if (tweet != null && tweet.contains("wine") ) {
				System.out.println("Breaking news in Paris: " + tweet );
			}
		}
	}
	
	/**
	 * Definition of the processing of a Subject.
	 */
	
	private class Feed implements Subject {

		private final List<Observer> observers = new ArrayList<>();
		
		@Override
		public void registerObserver(Observer observer) {
			if (!observers.contains(observer) ) {
				this.observers.add(observer);				
			}
		}

		@Override
		public void notifyObservers(String tweet) {
			System.out.println("notifyObserver for tweet: " + tweet);
			observers.forEach( observer -> observer.notify(tweet) );
		}

		@Override
		public void notifyObserversLambda(String string, Consumer<String> tweet) {
			tweet.accept(string);
			
		}
		
	}

}
