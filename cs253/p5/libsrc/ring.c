#include <string.h>
#include <stdio.h>
#include <time.h>
#include <ring.h>
#include <signal.h>
#include <unistd.h>
#include <stdbool.h>

static void onalarm(int signo);
static void dump_buffer();
static bool alarmFlag = false;

static struct {
    int curr;
    char log[MAX_LOG_ENTRY][MAX_STRING_LENGTH];
} buff;

/*
 * Initializes buffer, alarm and signal
 */
void init_buffer()
{
    printf("Initialize the ring buffer\n");
    int i;
    for(i = 0; i < MAX_LOG_ENTRY; i++) {
        buff.log[i][0]='\0';
    }
    buff.curr = 0;

    alarm(alarm_interval);
    signal(SIGALRM, onalarm);
}

/*
 * Return the current timestamp (localtime) from the system.
 */
static char *getTimeString()
{
    time_t myTime = time(NULL); //this is a system call
    char *timeString = ctime(&myTime);
    timeString[strlen(timeString)-1] = '\0'; //erase the newline at the end
    return timeString;
}

/*
 *   creates an entry in log checks if alarm is done
 */
void log_msg(char *entry)
{
    if (entry == NULL) {
        printf("Skipping null log entry!\n");
        return;
    }

    printf("Adding log entry into buffer\n");

    char *timeString = getTimeString();
    int idx = buff.curr % MAX_LOG_ENTRY;
    printf("%s\n", entry);

    snprintf(buff.log[idx], MAX_STRING_LENGTH, "%s -- %s", timeString, entry);
        
    if (alarmFlag == true){
        dump_buffer();
        alarmFlag = false;
    }

    buff.curr++;
}

/*
 * This writes to a file (log_name) and uses a signal to trigger the logging
 * event.
 */
static void dump_buffer()
{
    FILE* outFile = NULL;
    outFile = fopen(log_name, "w");

    if (outFile == NULL){
        printf("Couldn't open file log_name.\n");
    }

    int i;
    for(i = 0; i < MAX_LOG_ENTRY; i++){
        fprintf(outFile, "log %d: %s\n",i, buff.log[i]);
    }

    fclose(outFile);
}

/*
 * When alarm goes off sets flag to true and resets signal and alarm
 */
static void onalarm(int signo)
{
    fflush(stdout);
    alarmFlag = true;
    signal(SIGALRM, onalarm);      // reset signal handler
    alarm(alarm_interval);         // reset alarm
}
