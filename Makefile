all:
ifeq ($(OS), Windows_NT)
	pwsh -Command "echo 'y' | mvn compile exec:java '-Dexec.mainClass=Game' '-Dexec.args=10 0 highmem members 32'"
else
	mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32"
endif

cmd:
	mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32" < nul

clean:
	mvn clean

