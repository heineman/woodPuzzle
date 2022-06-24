FILES = queue.c \
	model.c \
	solve.c \
	puzzle.c \
	boundary.c \
	load.c

# JSON parsing library: https://github.com/DaveGamble/cJSON
# cJOSN.h is also part of this library
# Covered by MIT License
JSON = cJSON.c

puzzle: $(FILES) $(JSON) main.c
	gcc $^ -g -Wall -pedantic -lncurses -o $@

clean:
	rm -f puzzle *~ *.o

