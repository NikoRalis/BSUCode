#ifndef _HISTORY_H
#define _HISTORY_H

#define MAX_HIST 10

void init_history(int size);
void add_history(char* line);
void clear_history();
void print_history();

typedef struct Command_Struct{
        void *pid;
        char *cmd;
}Command;

#endif