//
// Created by zhu on 2020/4/16.
//

#ifndef CHARS_S_STACK_H
#define CHARS_S_STACK_H


class stack {
public:
    typedef struct node {
        //char *name;
        char *name;
        int grade;
        int age;
       // struct node *next;
       struct node *next;
    }
    //*StackNode, node;
    *StackNode,node;

    // typedef struct Stack {
    typedef  struct  Stack {
        StackNode top;
        StackNode bottom;
    } Stack;


public:
    //初始化栈
    void init(Stack *pstack);
//压栈
    void push(Stack *pstack, StackNode pnode );
//弹栈
    StackNode pop(Stack *pstack);
//是否为空
    int isEmpty(Stack *pstack);
//清空
    void ClearAll(Stack *pstack);


    void print(stack::Stack  *pstack);



};


#endif //CHARS_S_STACK_H
