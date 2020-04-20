//
// Created by zhu on 2020/4/20.
//

#include <assert.h>
#include "TreeNodeClass.h"
#include "string"
#include "logs.h"


int TreeNodeClass::EnQueue(TreeNodeClass::LinkQue *Q, TreeNodeClass::BiTree e) {
    // LinkQuePtr s = (LinkQuePtr) malloc(sizeof(QNode));            //申请新结点空间
    LinkQuePtr s = (LinkQuePtr) malloc(sizeof(QNode));
    if (!s)
        return 0;
    s->date = e;                        //新结点的数据等于e
    s->next = NULL;                        //新结点的指针指向空
    //Q->rear->next = s;                    //原队尾结点的指针指向新结点
    Q->rear = s;                        //队尾指针指向新结点（使新结点成为队尾结点）

    // r->s,
    // r<-s,
    return 1;
}

int TreeNodeClass::DeQueue(TreeNodeClass::LinkQue *Q) {
    if (Q->front == Q->rear)                //判断队列是否为空
        return 0;
    LinkQuePtr s = (LinkQuePtr) malloc(sizeof(QNode));        //申请结点空间s
    //s = Q->front->next;                    //s结点等于队头结点（头指针所指向的结点）
    //Q->front->next = s->next;            //头结点的指针指向s结点的下一结点（使s结点的下一结点成为队头元素）

    //s <-q
    //s->q
//    s = Q->front->next;
//    Q->front->next = s->next;

     s = Q->front->next;
     Q->front->next = s->next;

    if (Q->rear == s)                    //判断s是否为队尾元素，若是，说明队列中仅有一个结点
        Q->rear = Q->front;                //使队尾结点指向头结点
    free(s);                            //释放s结点
    return 1;
}

void TreeNodeClass::CreatBiTree(TreeNodeClass::BiTree *T) {
    //结点数据
    //scanf("%d", &e);

    // (*T) = (BiTree) malloc(sizeof(BiTreeNode));        //申请结点空间
    LOGE("sizeof(BiTreeNode):%d\n", sizeof(BiTreeNode));
    (*T) = (BiTree) malloc(sizeof(BiTreeNode));
    (*T)->date = 3;

    LOGE("T.size:%d\n", sizeof(T) / sizeof(T[0]));

    //为当前结点赋值
    LOGE("请输入当前结点 %d 的左孩子，若没有左孩子，请输入-1\n", 2);
    (*T)->lChild = (BiTree) malloc(sizeof(BiTree));
    (*T)->lChild->date = 2;

    LOGE("请输入当前结点 %d 的右孩子，若没有右孩子，请输入-1\n", 1);
    (*T)->rChild = (BiTree) malloc(sizeof(BiTree));
    (*T)->rChild->date = 1;

}

void TreeNodeClass::PreorderTraversal(TreeNodeClass::BiTree T) {
    if (T == NULL)                            //判空
        return;
    LOGE("%d ", T->date);                    //打印当前结点
    PreorderTraversal(T->lChild);            //递归遍历左子树
    PreorderTraversal(T->rChild);            //递归遍历右子树

}

void TreeNodeClass::InorderTraversal(TreeNodeClass::BiTree T) {
    if (T == NULL)                            //判空
        return;
    InorderTraversal(T->lChild);            //递归左子树
    LOGE("%d ", T->date);                    //打印当前结点
    InorderTraversal(T->rChild);            //递归右子树
}

void TreeNodeClass::PostorderTraversal(TreeNodeClass::BiTree T) {
    if (T == NULL)                            //判空
        return;
    PostorderTraversal(T->lChild);            //递归左子树
    PostorderTraversal(T->rChild);            //递归右子树
    LOGE("%d ", T->date);                    //打印当前结点
}

void TreeNodeClass::LevelTraversal(TreeNodeClass::BiTree T) {
    if (T == NULL)                            //判空
        return;
    LinkQue Q;            //创建队Q
    Q.front = head;        //初始化队列
    Q.rear = head;
    // EnQueue(&Q, T);                            //将根结点入队
    EnQueue(&Q, T);
    while (Q.front != Q.rear)                //判断队列是否为空
    {
        BiTree s = Q.front->next->date;            //获得队列中第一个结点的数据
        LOGE("%d ", s->date);                    //打印当前结点的数据
        if (s->lChild)                            //若该结点有左孩子，将其左孩子入队
            EnQueue(&Q, s->lChild);
        if (s->rChild)                            //若该结点有右孩子，将其右孩子入队
            EnQueue(&Q, s->rChild);
        //DeQueue(&Q);                            //将队列中第一个结点出队
        DeQueue(&Q);
    }
}

int TreeNodeClass::Depth(TreeNodeClass::BiTree T) {
    if (T == NULL)                        //如果当前结点为空，返回0
        return 0;
    int L = Depth(T->lChild);            //遍历左子树
    int R = Depth(T->rChild);            //遍历右子树
    if (L > R)                            //取最大值返回
        return (L + 1);
    else
        return (R + 1);
}

void TreeNodeClass::Inorder_Traversal_Cue(TreeNodeClass::BiTree &T) {
    if (T) {
        Inorder_Traversal_Cue(T->lChild);            //递归左子树
        if (T->lChild == NULL)                        //左孩子为空
        {
            T->lFlag = 1;                            //左标记为1
            T->lChild = pre;                        //左指针指向前一结点
        } else {
            T->lFlag = 0;
        }
        if (pre->rChild == NULL)                        //前一结点的右孩子为空
        {
            pre->rFlag = 1;                            //前一结点的右标记为1
            pre->rChild = T;                        //前一结点的右指针指向当前结点
        } else {
            T->rFlag = 0;
        }
        pre = T;                                    //使当前结点成为前一结点
        Inorder_Traversal_Cue(T->rChild);            //递归右子树
    }
}

TreeNodeClass::BiTree TreeNodeClass::AddHead(TreeNodeClass::BiTree &T) {
    // BiTree head = (BiTree) malloc(sizeof(BiTreeNode));        //申请头结点
    BiTree head = (BiTree) malloc(sizeof(BiTreeNode));
    head->lFlag = 0;                //头结点左标记为0
    head->rFlag = 1;                //右标记为1
    if (!T)                            //若二叉树为空
    {
        head->lChild = head;        //左指针回指
        head->rChild = head;        //右指针回指
        return NULL;
    }
    pre = head;                        //前一结点指向头结点
    head->lChild = T;                //头结点的左孩子指向根结点
    Inorder_Traversal_Cue(T);        //中序线索化
    pre->rChild = head;                //为最后一个结点设置右指针指向头结点
    pre->rFlag = 1;                    //右标记为1
    head->rChild = pre;                //头结点的右指针指向尾结点
    return head;                    //返回头结点
}

void TreeNodeClass::TreeCueTraversal(TreeNodeClass::BiTree T) {
    BiTree p = T->lChild;                //申请结点p指向根结点
    while (p != T)                        //根结点不为空
    {
        while (p->lFlag == 0)            //一直寻找第一个左标记为1的结点
            p = p->lChild;
        LOGE("%d ", p->date);            //打印第一个结点
        while (p->rFlag == 1 && p->rChild != T)        //若右标记是1，且右孩子不是头结点
        {
            p = p->rChild;                //一直遍历
            LOGE("%d ", p->date);
        }
        p = p->rChild;                //若右标记为0，p赋值为p的右子树
    }
    LOGE("\n");
}
