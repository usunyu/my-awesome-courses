/*
	demo-udp-01: create a UDP socket

	usage:	demo-udp-01 

	create a socket. Doesn't do much
	Paul Krzyzanowski
*/

#include <stdio.h>	/* defines printf */
#include <stdlib.h>	/* defines exit and other sys calls */
#include <sys/socket.h>

main(int argc, char **argv)
{
	int fd;

	/* create a udp/ip socket */
	/* request the Internet address protocol */
	/* and a datagram interface (UDP/IP) */

	if ((fd = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
		perror("cannot create socket");
		return 0;
	}

	printf("created socket: descriptor=%d\n", fd);
	exit(0);
}
