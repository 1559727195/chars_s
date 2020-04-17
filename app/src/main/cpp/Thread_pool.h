//
// Created by zhu on 2020/4/17.
//

#ifndef CHARS_S_THREAD_POOL_H

#include "Queue.h"
#include <stdlib.h>
#include <pthread.h>

#define CHARS_S_THREAD_POOL_H


class Thread_pool {
public:
    struct thread_pool {
        unsigned int thread_count;
        //pthread_t *threads;
        pthread_t *threads;
        Queue::queue_t tasks;
        // pthread_mutex_t lock;
        // pthread_cond_t task_ready;
        pthread_mutex_t lock;
        pthread_cond_t task_ready;
    };

    struct task {
//        void *(*routine)(void *arg);
//        void *arg;
        void *(*routine)(void *arg);

        void *arg;
    };

    //typedef struct thread_pool *thread_pool_t;
    typedef struct thread_pool *thread_pool_t;

    thread_pool_t thread_pool_create(unsigned int thread_count);

    void thread_pool_add_task(thread_pool_t pool,

            // void *(*routine)(void *arg), void *arg);
                              void *(*routine)(void *arg), void *arg);

    void thread_pool_destroy(thread_pool_t pool);

};


#endif //CHARS_S_THREAD_POOL_H
