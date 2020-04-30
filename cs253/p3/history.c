#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "history.h"

static struct History_Stuct{
    
    Command commands[MAX_HIST];
    int currIndex;
}History;

void init_history(int size){

    History.currIndex = 0;

    int i;
    for( i=0; i<MAX_HIST; i++ ){
        History.commands[i].cmd = NULL;
        History.commands[i].pid = NULL;
    }
    return;
}

void add_history(char* line){

    if (line == NULL){
        return;
    }
    if(History.currIndex == MAX_HIST ){
        History.currIndex = 0;
       
    }

    History.commands[History.currIndex].pid = NULL;

    History.commands[History.currIndex].cmd = (char*)malloc(sizeof(char) * strlen(line) + 1);
    strncpy(History.commands[History.currIndex].cmd, line, strlen(line));
	History.commands[History.currIndex].cmd[strlen(line)] = '\0';

    History.currIndex++;

}

void clear_history(){

    int i;
    for(i = 0; i < MAX_HIST; i++){
        if(History.commands[i].cmd != NULL){
            free(History.commands[i].cmd);
        }
    }
}

void print_history(){

        int i;
        for (i = 0; i < MAX_HIST; i++){
            if(History.commands[i].cmd != NULL){
                printf("%d   %s\n", i, History.commands[i].cmd);
            }
        }
}
