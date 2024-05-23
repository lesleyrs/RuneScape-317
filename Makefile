all: build
	java -cp bin Game 10 0 highmem members 32

build:
	mkdir -p bin
	javac -d bin src/*.java

jar: build
	jar cmvf META-INF/MANIFEST.MF localhost.jar -C bin/ .
	java -jar localhost.jar 10 0 highmem members 32

.PHONY: test
test:
	java -cp bin test/PreviewSinCos2D.java

# recurse:
# 	mkdir -p bin
# 	javac -d bin $(shell find src -name *.java)

# old:
# ifeq ($(OS), Windows_NT)
#     ifeq ($(shell pwsh -v > nul 2>&1 && echo 1), 1)
# 		pwsh -Command "echo 'y' | mvn compile exec:java '-Dexec.mainClass=Game' '-Dexec.args=10 0 highmem members 32'"
#     else
# 		mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32" < nul
#     endif
# else
# 	mvn compile exec:java -Dexec.mainClass=Game -Dexec.args="10 0 highmem members 32"
# endif
