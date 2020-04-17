//
// Created by zhu on 2020/4/17.
//

#include "Queue.h"
#include <string>


Queue::Queue() {

}

Queue::~Queue() {

}

Queue::queue_t Queue::queue_create() {
    queue_t q;

    //q = (queue_t) malloc(sizeof(struct queue));
    q = (queue_t) (malloc(sizeof(struct queue)));

    q->front.element = NULL;

    q->front.next = NULL;

    //  q->tail = &q->front;
    q->tail = &q->front;

    return q;
}

int Queue::queue_isempty(Queue::queue_t q) {
    //return &q->front == q->tail;
    return &q->front == q->tail;
}

void *Queue::queue_enqueue(Queue::queue_t q, unsigned int bytes) {
    //p<-q
    //(content)
    //NULL<-p
    //p=>q
    q->tail->next = (struct node *) malloc(sizeof(node));
    q->tail->next->element = malloc(bytes);
    q->tail->next->next = NULL;
    q->tail = q->tail->next;

    //return q->tail->element;
    return q->tail->element;
}

void *Queue::queue_dequeue(Queue::queue_t q) {

    //   t <-f
    //   content
    //   t->f
    //   free(t)


    struct node *tmp = q->front.next;
    void *element;
    if (tmp == NULL)
        return NULL;
    element = tmp->element;
    q->front.next = tmp->next;
    free(tmp);
    if (q->front.next == NULL) {
        q->tail = &q->front;
    }
    return element;
}

void Queue::queue_destroy(Queue::queue_t q) {
//   struct node *tmp, *p = q->front.next;
//
//    while (p != NULL) {
//
//        tmp = p;
//
//        p = p->next;
//
//        free(tmp);
//
//    }

    struct node *tmp, *p = q->front.next;

    while (p != NULL) {
        tmp = p;
        p = p->next;
        free(tmp);
    }

    //p<-f
    //p->f
    //free(q); // 感谢@Toudsour指正
    free(q);
}
