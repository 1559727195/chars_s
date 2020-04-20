//
// Created by zhu on 2020/4/20.
//

#ifndef CHARS_S_TREENODECLASS_H
#define CHARS_S_TREENODECLASS_H


#include <malloc.h>

class TreeNodeClass {

public:

    typedef int ElemType;                    //数据类型

    typedef struct BiTreeNode                //二叉树结构体
    {
        ElemType date;                        //结点数据
        struct BiTreeNode *lChild;            //左指针
        int lFlag;                            //左标记（==0时，左指针存储左孩子结点；==1时，左指针存储前驱结点）
        struct BiTreeNode *rChild;            //右指针
        int rFlag;                            //右标记（==0时，右指针存储右孩子结点；==1时，右指针存储后继结点）
    } *BiTree;
    BiTree pre;

    BiTree Tt;

    typedef struct QNode                    //结点结构体
    {
        BiTree date;                        //结点数据
        //struct QNode *next;                    //结点指针
        struct QNode *next;
    }
    // *LinkQuePtr;                            //结点名
            *LinkQuePtr;

    typedef struct                            //链队结构体
    {
//          LinkQuePtr front;                    //队头结点
//          LinkQuePtr rear;                    //队尾结点
        LinkQuePtr front;
        LinkQuePtr rear;
    } LinkQue;                                //队名

    //LinkQuePtr head = (LinkQuePtr)malloc(sizeof(QNode));                        //头结点
    LinkQuePtr head = (LinkQuePtr) malloc(sizeof(QNode));


public:
/*链队的入队操作*/
    int EnQueue(LinkQue *Q, BiTree e);

    /*链队的出队操作*/
    int DeQueue(LinkQue *Q);

    /*创建二叉树函数*/
    void CreatBiTree(BiTree *T);

    /*先序遍历二叉树*/
    void PreorderTraversal(BiTree T);


    /*中序遍历二叉树*/
    void InorderTraversal(BiTree T);

    /*后序遍历二叉树*/
    void PostorderTraversal(BiTree T);

    /*层序遍历二叉树*/
    void LevelTraversal(BiTree T);

    /*计算树的深度*/
    int Depth(BiTree T);

    /*中序遍历线索化*/
    void Inorder_Traversal_Cue(BiTree &T);

/*添加头结点，将二叉树线索化*/
    BiTree AddHead(BiTree &T);

    /*遍历线索二叉树*/
    void TreeCueTraversal(BiTree T);


};


#endif //CHARS_S_TREENODECLASS_H
