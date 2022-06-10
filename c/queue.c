#include <stdio.h>
#include <malloc.h>
#include "queue.h"

int is_empty(QUEUE_PTR queue) {
  return queue->head == NULL;
}

void enqueue(QUEUE_PTR queue, NODE_PTR n) {
  if (queue->head == NULL) {
    queue->head = calloc(1, sizeof (QNODE));
    queue->head->node = n;
    queue->tail = queue->head;
  } else {
    queue->tail->next = calloc(1, sizeof (QNODE));    
    queue->tail->next->node = n;
    queue->tail = queue->tail->next;
  }
}

NODE_PTR dequeue(QUEUE_PTR queue) {
  NODE_PTR np = queue->head->node;

  if (queue->head == queue->tail) {
    free(queue->head);
    queue->head = queue->tail = NULL;
    return np;
  }

  QNODE_PTR next = queue->head->next;
  free(queue->head);
  queue->head = next;
  return np;
}

