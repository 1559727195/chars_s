//
// Created by zhu on 2020/4/17.
//

#ifndef CHARS_S_QUEUE_H
#define CHARS_S_QUEUE_H


class Queue {

public:
    Queue();

    ~Queue();


    struct node {
//        void *element;
//        struct node *next;
        void *element;
        struct node *next;
    };


    struct queue {
        struct node front;
        // struct node *tail;
        struct node *tail;
    };

    //typedef struct queue *queue_t;
    typedef struct queue *queue_t;

    static queue_t queue_create();


    static int queue_isempty(queue_t q);


    static void *queue_enqueue(queue_t q, unsigned int bytes);


    static void *queue_dequeue(queue_t q);


    static void queue_destroy(queue_t q);
};


#endif //CHARS_S_QUEUE_H
