<pre><code>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    /*
    TODO: definirajte argumenti vo nizata args koi oznachuvaat izvrshuvanje
    na komandata ls koja se naogja na patekata /bin/ls so argument -l
    */

    char *args[] = {"/bin/ls", "-l", NULL}//niza so argumenti

    printf("This program is about to run ls -l\n");

    // TODO: zamenete go momentalniot proces so ls -l

    int result = execv(args[0], args);// povik kon soodvetnata funkcija koja ke go zameni momentalniot proces so ls -l

    if (result == -1)
    {
        printf("Error in replacing process", stderr);
        exit(1);
    }

    /*
    programata my_program ja sodrzhi samo naredbata
    printf("Hello from my_program\n");
    */

    char *args_new[] = {"my_program", NULL};
    result = execv(args_new[0], args_new);

    printf("Hello from main\n");

    return 0;
}

</code></pre>