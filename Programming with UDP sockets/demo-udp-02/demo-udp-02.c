/*
	demo-udp-01: create a UDP/IP socket

	usage:	demo-udp-01 

	create a socket. Bind it to any available port.
	Then use getsockname to print the port.
	That's it.
	Paul Krzyzanowski
*/

#include <stdlib.h>	/* defines system calls */
#include <stdio.h>	/* needed for printf */
#include <string.h>	/* needed for memset */
#include <sys/socket.h>
#include <netinet/in.h>	/* needed for sockaddr_in */

int main(int argc, char **argv)
{
	struct sockaddr_in myaddr;	/* our address */
	int fd;	/* our socket */
	unsigned int alen;	/* length of address (for getsockname) */

	/* create a udp/ip socket */
	/* request the Internet address protocol */
	/* and a reliable 2-way byte stream (TCP/IP) */

	if ((fd = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
		perror("cannot create socket");
		return 0;
	}

	printf("created socket: descriptor = %d\n", fd);

	/* bind to an arbitrary return address */
	/* in this case, assume it's the client side, so we won't */
	/* care about the port number since no application will initiate service here */
	/* INADDR_ANY is the IP address and 0 is the socket */
	/* htonl converts a long integer (e.g. address) to a network */
	/* representation (IP-standard byte ordering) */

	/* if you want to use a specific port number (such as myport), use    */
	/*	 	myaddr.sin_port = htons(myport)    */
	/* instead of	myaddr.sin_port = htons(0);    */

	memset((void *)&myaddr, 0, sizeof(myaddr));
	myaddr.sin_family = AF_INET;
	myaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	myaddr.sin_port = htons(0);

	if (bind(fd, (struct sockaddr *)&myaddr, sizeof(myaddr)) < 0) {
		perror("bind failed");
		return 0;
	}

	alen = sizeof(myaddr);
	if (getsockname(fd, (struct sockaddr *)&myaddr, &alen) < 0) {
		perror("getsockname failed");
		return 0;
	}

	printf("bind complete. Port number = %d\n", ntohs(myaddr.sin_port));
	exit(0);
}
