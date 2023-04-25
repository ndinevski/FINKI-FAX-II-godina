#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[])
{

    pid_t pid;
    
    if ((pid = fork()) == 0){ /* child */
        printf("Proces dete\n");
        sleep(5);
        while(1){
            int broj;
            printf("Vnesete broj: ");
            scanf("%d", &broj);
            printf("Vnesovte %d. ", broj);
            if(broj == 0){
                exit(0);
            }
        }
    } else {
    if (pid > 0){ /* parent */
            printf("Proces tatko\n");
            wait(NULL);
            printf("\nSe budam\nPovtorno proces tatko");
            exit(0);
        }
    }

}
