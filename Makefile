all:
	javac Card.java
	javac CardTest.java
	javac Deck.java
	javac DeckTest.java
	javac Player.java
	javac PlayerTest.java
	javac Dealer.java
	javac DealerTest.java
	javac UnoTestDriver.java

clean:
	rm -f *~
	rm -f *.class