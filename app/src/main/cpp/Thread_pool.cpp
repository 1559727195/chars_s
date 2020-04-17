//
// Created by zhu on 2020/4/17.
//

#include "Thread_pool.h"
#include <string>


static void cleanup(pthread_mutex_t *lock) {
    // pthread_mutex_unlock(lock);
    pthread_mutex_unlock(lock);
}

static void *worker(Thread_pool::thread_pool_t pool) {
    //struct Thread_pool::task *t;
    struct Thread_pool::task *t;
    while (1) {
        //pthread_mutex_lock(&pool->lock);
        pthread_mutex_lock(&pool->lock);
        //pthread_cleanup_push((void (*)(void *)) cleanup, &pool->lock);
        pthread_cleanup_push((void (*)(void *)) cleanup, &pool->lock);
            while (Queue::queue_isempty(pool->tasks)) {
                // pthread_cond_wait(&pool->task_ready, &pool->lock);
                pthread_cond_wait(&pool->task_ready, &pool->lock);
                /*A  condition  wait  (whether  timed  or  not)  is  a  cancellation point ... a side-effect of acting upon a cancellation request  while in a condition wait is that the mutex is (in  effect)  re-acquired  before  calling  the  first  cancellation  cleanup  handler.*/
            }
            // t = (struct Thread_pool::task *) Queue::queue_dequeue(pool->tasks);
            t = (struct Thread_pool::task *) (Queue::queue_dequeue(pool->tasks));
            // pthread_cleanup_pop(0);
                pthread_cleanup_pop(0);
        //pthread_mutex_unlock(&pool->lock);
        pthread_mutex_unlock(&pool->lock);
        // t->routine(t->arg);/*todo: report returned value*/
        t->routine(t->arg);
        //free(t);
        free(t);
    }
}


Thread_pool::thread_pool_t Thread_pool::thread_pool_create(unsigned int thread_count) {
    unsigned int i;
    thread_pool_t pool = NULL;
    //pool=(thread_pool_t)malloc(sizeof(struct thread_pool));
    pool = (thread_pool_t) malloc(sizeof(struct thread_pool));
    pool->thread_count = thread_count;
    //pool->threads=(pthread_t*)malloc(sizeof(pthread_t)*thread_count);
    pool->threads = (pthread_t *) malloc(sizeof(pthread_t) * thread_count);

    pool->tasks = Queue::queue_create();

//    pthread_mutex_init(&pool->lock, NULL);
//    pthread_cond_init(&pool->task_ready, NULL);
    pthread_mutex_init(&pool->lock, NULL);
    pthread_cond_init(&pool->task_ready, NULL);

    for (i = 0; i < thread_count; i++) {
        //pthread_create(pool->threads + i, NULL, (void *(*)(void *)) worker, pool);
        pthread_create(pool->threads + i, NULL, (void *(*)(void *)) worker, pool);
    }
    return pool;
}

void Thread_pool::thread_pool_add_task(Thread_pool::thread_pool_t pool, void *(*routine)(void *),
                                       void *arg) {
    //struct task *t;
    struct task *t;
    //pthread_mutex_lock(&pool->lock);
    pthread_mutex_lock(&pool->lock);
    //t = (struct task *) queue_enqueue(pool->tasks, sizeof(struct task));
    t = (struct task *) Queue::queue_enqueue(pool->tasks, sizeof(struct task));
//    t->routine = routine;
//    t->arg = arg;
    t->routine = routine;
    t->arg = arg;

    //pthread_cond_signal(&pool->task_ready);
    pthread_cond_signal(&pool->task_ready);
    //pthread_mutex_unlock(&pool->lock);
    pthread_mutex_unlock(&pool->lock);
}

void Thread_pool::thread_pool_destroy(Thread_pool::thread_pool_t pool) {
    unsigned int i;
//    for(i=0; i<pool->thread_count; i++) {
//        pthread_cancel(pool->threads[i]);
//    }
    for (i = 0; i < pool->thread_count; i++) {
        //pthread_join(pool->threads[i], NULL);
        pthread_join(pool->threads[i],NULL);
    }
    //pthread_mutex_destroy(&pool->lock);
    //pthread_cond_destroy(&pool->task_ready);
    //Queue::queue_destroy(pool->tasks);
    //free(pool->threads);
    //free(pool);
    pthread_mutex_destroy(&pool->lock);
    pthread_cond_destroy(&pool->task_ready);
    Queue::queue_destroy(pool->tasks);
    free(pool->threads);
    free(pool);

}
