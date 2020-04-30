#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>
#include "history.h"
#define MAX 2048
#define CHANGE "cd"
#define LEAVE "exit"
#define PRINT "pwd"
#define HIST "history"


/*parse arguments*/
 void parse_args(char* line, char** argv, int* argc){

 	*argc = 0;
	char* token = strtok(line, " ");

 	while(token != NULL){
		
		 argv[*argc] = (char*)malloc(sizeof(char)*(strlen(token)+1));
		 strcpy(argv[*argc], token);
		 token = strtok(NULL, " ");
		  (*argc)++;

 	}
	(argv[*argc]) = NULL;

}

/*cd function*/
void smash_cd(char *path){

	int i = chdir(path);
	char x[MAX];
	
	if (i == 0){

		if(getcwd(x, MAX)!= NULL){
			printf("%s\n", x);
			}
	}
	else{
		fprintf(stderr, "Directory %s Doesn't exist\n", path);	
	}
}

/*pwd function*/
void smash_pwd(){

	char x[MAX];
	getcwd(x, MAX);
	printf("%s\n", x);
}

/*input function*/
int smash_input(char *line, int i, char** argv, int argc){

	while (fgets(line, MAX, stdin) != NULL){
		
		line[strlen(line) - 1] = '\0';
		parse_args(line, argv, &argc);
		char *token = argv[0];
		char *path = argv[1];
		srand(time(NULL));
		int modVal = 4;

		add_history(token);


		//empty command
		if(token == NULL){
			fprintf(stderr,"$ ");
			continue;
		}

		//exit
		else if(strcmp(token, LEAVE) == 0){
			i = 0;
			while(argv[i] != NULL){
				free(argv[i]);
				i++;
			}
			clear_history();
			return EXIT_SUCCESS;
		}

		//cd
		else if(strcmp(token, CHANGE) == 0){
			smash_cd(path);
		}

		//pwd
		else if(strcmp(token, PRINT) == 0){
			smash_pwd();
		}

		//history
		else if(strcmp(token, HIST) == 0){
			print_history();
		}

		//fork/exc/wait
		else{

				pid_t pid;
				int status;
				char* exe = argv[0];
				char** args = argv;

				if((pid = fork()) < 0){
					perror("fork");
					
					clear_history();
					exit(errno);
				}
				else if (pid == 0){

					//if no args to exe
					if (exe == NULL){ 

						free(argv);
						clear_history();
						printf("erorr");
					}
					//else execvp
					else{
						int randVal = rand() % modVal;
					
						if(randVal == 1){
					 		kill(getpid(), SIGKILL);
					 	}					 

						execvp(exe, args);

					}

					/*SHOULDN'T GET THIS FAR*/
					//free(args);
					perror("exec failed");
					exit(errno);
				}		
				

				else {
					while (wait(&status) != pid); //wait for child to die
					int randVal = rand() % modVal;
					if(randVal == 1){
					 		printf("Child %d: terminated\n", pid);
					 	}
					 	else{
					 		printf("Child %d: exited normally\n", pid);
						}
					}
			}
		fprintf(stderr,"$ ");
					
		i = 0;
		while(argv[i] != NULL){
				free(argv[i]);
				i++;
		}
	}

	return EXIT_SUCCESS;
}

int main(void){
	
	init_history(10);
	char line[MAX];
	char* argv[MAX];
	int argc = 0;

	int i = 0;

	fprintf(stderr,"$ ");

	smash_input(line, i, argv, argc);

	/*SHOULDN'T GET THIS FAR*/
	return EXIT_SUCCESS;
}
