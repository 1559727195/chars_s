//
// Created by zhu on 2020/4/14.
//

#include "dLink.h"
#include <stdio.h>
#include <malloc.h>
#include "logs.h"


//双向链表节点
typedef struct My_node {
    struct My_node *prev;
    struct My_node *pNext;
    void *p;
} my_node;
//b表头不存放元素值
my_node *phead = NULL;
//节点的个数
int node_count = 0;

//创建节点，成功返回节点指针，否则，返回NULL
my_node *create_node(void *pVal) {
    my_node *pnode = NULL;
    pnode = (my_node *) malloc(sizeof(My_node));
    if (!pnode) {
        printf("create pnode error\n");
        return NULL;
    }
    //默认的，pnode的前一节点和后一节点都指向他自己
    pnode->prev = pnode->pNext = pnode;
    //节点的值为pVal
    pnode->p = pVal;
    return pnode;
}

//新建双向链表 成功返回0 否则返回-1
int dLink::create_dLink() {
    phead = create_node(NULL);
    if (!phead)
        return -1;
    //设置节点的个数
    node_count = 0;
    return 0;
}

int dLink::destory_dLink() {
    if (!phead) {
        printf("%s failed! dlink is null!\n", __func__);
        return -1;
    }
    My_node *pnode = phead->pNext;
    my_node *ptmp = NULL;
    if (pnode != phead) {
        ptmp = pnode;
        pnode = pnode->pNext;
        free(pnode);
    }
    free(phead);
    phead = NULL;
    node_count = 0;
    return 0;
}

int dLink::is_empty_dLink() {
    return node_count == 0;
}

int dLink::dLink_size() {
    return node_count;
}

//获取双向链表中第Index位置的节点
my_node *get_node(int index) {
    if (index < 0 || index >= node_count) {
        printf("%s failed ! index out of bound\n", __func__);
        return NULL;
    }
    //正向查找
    if (index <= (node_count / 2)) {
        int i = 0;
        my_node *pnode = phead->pNext;
        while ((i++) < index) {
            pnode = pnode->pNext;
        }
        return pnode;
    }
    //反向查找
    int j = 0;
    int rindex = node_count - index - 1;
    my_node *rnode = phead->prev;
    while ((j++) < rindex) {
        rnode = rnode->prev;
    }
    return rnode;
}

void *dLink::dLink_get(int index) {
    my_node *pindex = get_node(index);
    if (!pindex) {
        printf("%s failed!\n", __func__);
        return NULL;
    }
    return pindex->p;
}

//获取第一个节点
void *dLink::dLink_getFirst() {
    return get_node(0);
}

//获取最后一个节点
void *dLink_getTail() {
    return get_node(node_count - 1);
}

//将值插入到index位置，成功返回0；否则 返回-1
int dLink::dLink_insert(int index, void *pVal) {
    //插入表头
    if (index == 0)
        return dLink_insert_head(pVal);
    //获取要插入位置对应的节点
    my_node *pindex = get_node(index);
    if (!pindex)
        return -1;
    //创建节点
    my_node *pnode = create_node(pVal);
    if (!pnode)
        return -1;
    pnode->prev = pindex->prev;
    pnode->pNext = pindex;
    pindex->prev->pNext = pnode;
    pindex->prev = pnode;
    node_count++;
    return 0;
}

//数值插入表头
int dLink::dLink_insert_head(void *pVal) {
    my_node *pnode = create_node(pVal);
    if (!pnode)
        return -1;
    pnode->prev = phead;
    pnode->pNext = phead->pNext;

    phead->pNext->prev = pnode;
    phead->pNext = pnode;
    node_count++;
    return 0;
}

int dLink::dLink_insert_tail(void *pVal) {
    my_node *pnode = create_node(pVal);
    if (!pnode)
        return -1;
    pnode->pNext = phead;
    pnode->prev = phead->prev;
    phead->prev->pNext = pnode;
    phead->prev = pnode;
    node_count++;
    return 0;
}

int dLink::dLink_delete(int index) {
    my_node *pindex = get_node(index);
    if (!pindex) {
        printf("%s failed! the index in out of bound\n", __func__);
        return -1;
    }
    pindex->pNext->prev = pindex->prev;
    pindex->prev->pNext = pindex->pNext;
    free(pindex);
    node_count--;
    return 0;
}

int dLink::dLink_delete_first() {
    return dLink_delete(0);
}

int dLink::dLink_delete_tail() {
    return dLink_delete(node_count - 1);
}

dLink::dLink() {

}

dLink::~dLink() {

}
