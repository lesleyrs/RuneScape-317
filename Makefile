all: build
	java -cp bin Game 10 0 highmem members 32

build:
	mkdir -p bin
	javac -d bin src/*.java

recursive:
	mkdir -p bin
	javac -d bin $(shell find src -name *.java)

jar:
	jar cmvf META-INF/MANIFEST.MF 317.jar -C bin/ .
	java -jar 317.jar 10 0 highmem members 32

.PHONY: test
test:
	java -cp bin test/PreviewSinCos2D.java

old:
ifeq ($(OS), Windows_NT)
    ifeq ($(shell pwsh -v > nul 2>&1 && echo 1), 1)
		pwsh -Command "echo 'y' | mvn compile exec:java '-Dexec.mainClass=Game' '-Dexec.args=10 0 highmem members 32'"
    else
		mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32" < nul
    endif
else
	mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32"
endif
