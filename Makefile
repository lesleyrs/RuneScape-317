all:
ifeq ($(OS), Windows_NT)
	pwsh -Command "echo 'y' | mvn compile exec:java '-Dexec.mainClass=Game' '-Dexec.args=10 0 highmem members 32' '-Djava.net.preferIPv6Addresses=true'"
else
	mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32"
endif

cmd:
	mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32" -Djava.net.preferIPv6Addresses=true < nul

clean:
	mvn clean

