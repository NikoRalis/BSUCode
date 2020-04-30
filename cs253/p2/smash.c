#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#define MAX 5000
#define CHANGE "cd"
#define LEAVE "exit"
#define printDir "pwd"

int main()
{
	char line[MAX];
	fprintf(stderr,"$ ");

	while (fgets(line, MAX, stdin) != NULL){
		line[strlen(line) - 1] = '\0';
		char* token = strtok(line, " ");
		int i = 0;	
		char x[MAX];
		
		//exit
		if(strcmp(token, LEAVE) == 0){
			return EXIT_SUCCESS;
			}
		
		//cd
		else if(strcmp(token, CHANGE) == 0){
			token = strtok(NULL, " ");

				if(chdir(token)==0){	
						if( getcwd(x, MAX)!=NULL){
						if( strcmp(token, x)==0 ) printf("%s\n", x);
						}
						
				}
				else{
						fprintf(stderr, "Directory %s Doesn't exist\n", token);
					}	
					
		}
		//pwd
		//else if(strcmp(token, printDir) == 0){
		//			getcwd(x, MAX);
		//			printf("%s\n", x);
		//		}

		else{
		while (token != NULL){

				
				printf("[%d] %s\n",i, token);
				i++;
				token = strtok(NULL, " ");


			
		}
		}
		fprintf(stderr,"$ ");
	}		
	return EXIT_SUCCESS;
}






