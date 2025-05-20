# JSON parsing library: https://github.com/DaveGamble/cJSON
# cJOSN.h is also part of this library
# Covered by MIT License
JSON = ../c/cJSON.c ../c/load.c

all: show start move solve check

start: $(JSON) ../c/model.c share.c start.c 
	gcc $^ -g -I ../c -Wall -Wpedantic -o $@

show: show.c 
	gcc $^ -g -I ../c -Wall -Wpedantic -o $@

check: check.c ../c/model.c share.c
	gcc $^ -g -I ../c -Wall -Wpedantic -o $@

move: $(FILES) ../c/model.c share.c move.c 
	gcc $^ -g -I ../c -Wall -Wpedantic -o $@

solve: $(FILES) ../c/model.c ../c/solve.c share.c ../c/queue.c solve.c 
	gcc $^ -g -I ../c -Wall -Wpedantic -o $@

clean:
	rm -f show start move solve check *~ *.o PUZZLE MOVES


