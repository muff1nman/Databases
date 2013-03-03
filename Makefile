all : create run

create:
	@javac Main.java

run: 
	@java -classpath sqlite-jdbc-3.7.2.jar:. Main

clean:
	@rm -f *.class
