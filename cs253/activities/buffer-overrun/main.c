#include <stdio.h>
#include <string.h>

int main(int argc, char*argv[]){

	char stringOne[6] = "Hello";
	int value = 999;
	char stringTwo[15] = "Goodbye";
	
	
	printf("stringOne: [%s]\n", stringOne);
	printf("value: [%d]\n", value);
	printf("stringTwo: [%s]\n", stringTwo);

//	for(int i= 0; i< strlen(stringTwo)- 1; i++){
//		stringTwo[i] = 'a';
//	}

	strncpy(stringTwo, "this is too big to fit", 14);
	printf("stringOne: [%s]\n", stringOne);
	printf("value: [%d]\n", value);
        printf("stringTwo: [%s]\n", stringTwo);

	
return 0;
}
