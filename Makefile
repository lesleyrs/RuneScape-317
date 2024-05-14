all:
ifeq ($(OS), Windows_NT)
    ifeq ($(shell pwsh -v > nul 2>&1 && echo 1), 1)
		pwsh -Command "echo 'y' | mvn compile exec:java '-Dexec.mainClass=Game' '-Dexec.args=10 0 highmem members 32'"
    else
		mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32" < nul
    endif
else
	mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32"
endif

pkg:
	mvn package

clean:
	mvn clean

