//
// Created by zhu on 2020/4/16.
//

#include "stack.h"
#include <string>
#include "logs.h"

void stack::init(stack::Stack *pstack) {
    pstack->top = static_cast<StackNode>(malloc(sizeof(node)));

    if (pstack->top == NULL) {
        LOGE("分配内存失败！\n");
        return;
    }

    pstack->bottom = pstack->top;
    pstack->top->next = NULL;

}

void stack::push(stack::Stack *pstack, stack::StackNode pnode) {
    if (pstack == NULL)
        printf("错误：栈不存在！\n");
    //pnode->next = pstack->top;
    //pstack->top = pnode;
     // p->t
     //p<-t
     pnode->next = pstack->top;
     pstack->top = pnode;
}

stack::StackNode stack::pop(stack::Stack *pstack) {

    StackNode result = NULL;
    if (isEmpty(pstack)) {
        printf("栈为空，弹栈失败！\n");
    } else {
        result = pstack->top;
        // pstack->top = pstack->top->next;

        pstack->top = pstack->top->next;
        //r<-p
        //p->p

    }
    return result;

}

int stack::isEmpty(stack::Stack *pstack) {
    if (pstack == NULL) {
        LOGE("栈不存在！ \n");
        return 0;
    } else {
        if (pstack->top == pstack->bottom)
            return 1;
        else
            return 0;
    }
}

void stack::ClearAll(stack::Stack *pstack) {
    if (isEmpty(pstack)) return;
    StackNode p = pstack->top;
    StackNode q = NULL;

    //    q<-p,
    //    q->p
    while (p != pstack->bottom) {
//        q = p->next;
//        free(p);
//        p = q;
        q = p->next;
        free(p);
        p  = q;
    }
    pstack->top = pstack->bottom;
}

void stack::print(stack::Stack *pstack) {
    if (pstack == NULL)
        LOGE("错误：栈不存在！");
    else if (pstack->top == pstack->bottom) {
        LOGE("栈为空!\n");
    }
    else {
        StackNode p = pstack->top;
        while (p != pstack->bottom) {
           LOGE("姓名：%s\t年级：%d\t年龄：%d\n",p->name, p->grade, p->age);
            p = p->next;
        }
    }
}



