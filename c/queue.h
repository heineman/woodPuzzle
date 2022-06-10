#ifndef _QUEUEE_H
#define _QUEUE_H_

#include "solve.h"

/* A Queue of NODE_PTR */

int is_empty(QUEUE_PTR queue);
void enqueue(QUEUE_PTR queue, NODE_PTR n);
NODE_PTR dequeue(QUEUE_PTR queue);

#endif /* _QUEUE_H_ */
