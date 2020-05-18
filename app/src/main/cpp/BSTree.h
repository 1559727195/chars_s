//
// Created by zhu on 2020/4/21.
//

#ifndef CHARS_S_BSTREE_H
#define CHARS_S_BSTREE_H

#include <string>
#include <ostream>
#include <iostream>
#include "logs.h"

using namespace std;

//二叉查找树的节点结构
template<typename T>
struct BSNode {
    BSNode(T t)
            : value(t), lchild(NULL), rchild(NULL) {}

    BSNode() = default;

    T value;
    ///BSNode<T> *lchild;
    ///BSNode<T> *rchild;
    ///BSNode<T> *parent;
    BSNode<T> *lchild;
    BSNode<T> *rchild;
    BSNode<T> *parent;

};

//二叉查找树类
template<typename T>
class BSTree {
public:
    BSTree();

    ~BSTree();

    void preOrder();    //前序遍历二叉树
    void inOrder();        //中序遍历二叉树
    void postOrder();    //后序遍历二叉树
    //void layerOrder();	//层次遍历二叉树

    BSNode<T> *search_recursion(T key);        //递归地进行查找
    BSNode<T> *search_Iterator(T key);        //迭代地进行查找

    T search_minimun(); //查找最小元素
    T search_maximum(); //查找最大元素

    BSNode<T> *successor(BSNode<T> *x);    //查找指定节点的后继节点
    //  BSNode<T> *predecessor(BSNode<T> *x);    //查找指定节点的前驱节点
    BSNode<T> *predecessor(BSNode<T> *x);

    void insert(T key);    //插入指定值节点
    void remove(T key);    //删除指定值节点
    void destory();        //销毁二叉树
    void print();        //打印二叉树


private:
    // BSNode<T> *root; //根节点
    BSNode<T> *root;
private:
  //  BSNode<T> *search(BSNode<T> *&p, T key);
    BSNode<T> *search(BSNode<T> *&p,T key);

    void remove(BSNode<T> *p, T key);

    void preOrder(BSNode<T> *p);

    void inOrder(BSNode<T> *p);

    void postOrder(BSNode<T> *p);

    T search_minimun(BSNode<T> *p);

    T search_maximum(BSNode<T> *p);

    void destory(BSNode<T> *&p);

};

/*默认构造函数*/
template<typename T>
BSTree<T>::BSTree() :root(NULL) {};

/*析构函数*/
template<typename T>
BSTree<T>::~BSTree() {
    destory(root);
};

/*插入函数*/
template<typename T>
void BSTree<T>::insert(T key) {
    BSNode<T> *pparent = NULL;
    BSNode<T> *pnode = root;

    while (pnode != NULL)        //寻找合适的插入位置
    {
        pparent = pnode;
        if (key > pnode->value)
            pnode = pnode->rchild;
        else if (key < pnode->value)
            pnode = pnode->lchild;
        else
            break;
    }

    pnode = new BSNode<T>(key);
    if (pparent == NULL)            //如果是空树
    {
        root = pnode;                //则新节点为根
    } else {
        if (key > pparent->value) {
            pparent->rchild = pnode;//否则新节点为其父节点的左孩
        } else
            pparent->lchild = pnode;//或右孩
    }
    pnode->parent = pparent;        //指明新节点的父节点

};

/*查找指定元素的节点（非递归）*/
template<typename T>
BSNode<T> *BSTree<T>::search_Iterator(T key) {
    BSNode<T> *pnode = root;
    while (pnode != NULL) {
        if (key == pnode->value)    //找到
            return pnode;
        if (key > pnode->value)        //关键字比节点值大，在节点右子树查找
            pnode = pnode->rchild;
        else
            pnode = pnode->lchild; //关键字比节点值小，在节点左子树查找
    }
    return NULL;
};

/*查找指定元素的节点（递归）*/
template<typename T>
BSNode<T> *BSTree<T>::search_recursion(T key) {
    return search(root, key);
};

/*private:search()*/
/*递归查找的类内部实现*/
template<typename T>
BSNode<T> *BSTree<T>::search(BSNode<T> *&pnode, T key) {
    if (pnode == NULL)
        return NULL;
    if (pnode->value == key)
        return pnode;
    //cout << "-->" << pnode->value << endl; //可以输出查找路径
    if (key > pnode->value)
        return search(pnode->rchild, key);
    return search(pnode->lchild, key);
};

/*删除指定节点*/
template<typename T>
void BSTree<T>::remove(T key) {
    remove(root, key);
};
/*删除指定节点*/
/*内部使用函数*/
template<typename T>
void BSTree<T>::remove(BSNode<T> *pnode, T key) {
    if (pnode != NULL) {
        if (pnode->value == key) {
            BSNode<T> *pdel = NULL;

            if (pnode->lchild == NULL || pnode->rchild == NULL)
                pdel = pnode;                    //情况二、三：被删节点只有左子树或右子树，或没有孩子
            else
                pdel = predecessor(pnode);      //情况一：被删节点同时有左右子树，则删除该节点的前驱

            //此时，被删节点只有一个孩子（或没有孩子）.保存该孩子指针
            BSNode<T> *pchild = NULL;
            if (pdel->lchild != NULL)
                pchild = pdel->lchild;
            else
                pchild = pdel->rchild;

            //让孩子指向被删除节点的父节点
            if (pchild != NULL)
                pchild->parent = pdel->parent;

            //如果要删除的节点是头节点，注意更改root的值
            if (pdel->parent == NULL)
                root = pchild;

                //如果要删除的节点不是头节点，要注意更改它的双亲节点指向新的孩子节点
            else if (pdel->parent->lchild == pdel) {
                pdel->parent->lchild = pchild;
            } else {
                pdel->parent->rchild = pchild;
            }

            if (pnode->value != pdel->value)
                pnode->value = pdel->value;
            delete pdel;
        }
            //进行递归删除
        else if (key > pnode->value) {
            remove(pnode->rchild, key);
        } else remove(pnode->lchild, key);
    }
};
/*寻找其前驱节点*/
/*
一个节点的前驱节点有3种情况：
1. 它有左子树，则左子树根节点为其前驱节点
2. 它没有左子树，且它本身为右子树，则其父节点为其前驱节点
3. 它没有左子树，且它本身为左子树，则它的前驱节点为“第一个拥有右子树的父节点”
*/
template<typename T>
BSNode<T> *BSTree<T>::predecessor(BSNode<T> *pnode) {
    if (pnode->lchild != NULL) {
        pnode = pnode->lchild;
        while (pnode->rchild != NULL) {
            pnode = pnode->rchild;
        }
        return pnode;
    }

  BSNode<T> *pparent = pnode->parent;
    while (pparent != NULL && pparent->lchild == pnode)//如果进入循环，则是第三种情况；否则为第二种情况
    {
        pnode = pparent;
        pparent = pparent->parent;
    }
    return pparent;
};

/*寻找其后继节点*/
/*
一个点有后继节点的情况：
1. 它有右子树；则其后继节点为其右子树的最左节点
2. 它没有右子树，但它本身是一个左孩子，则后继节点为它的双亲
3. 它没有右子树，但它本身是一个右孩子，则其后继节点为“具有左孩子的最近父节点”
*/
template<typename T>
BSNode<T> *BSTree<T>::successor(BSNode<T> *pnode) {
    if (pnode->rchild != NULL) {
        pnode = pnode->rchild;
        while (pnode->lchild != NULL) {
            pnode = pnode->lchild;
        }
        return pnode;
    }

    BSNode<T> *pparent = pnode->parent;
    while (pparent != NULL && pparent->rchild == pnode) {
        pnode = pparent;
        pparent = pparent->parent;
    }
    return pparent;
};


/*前序遍历算法*/
template<typename T>
void BSTree<T>::preOrder() {
    preOrder(root);
};

template<typename T>
void BSTree<T>::preOrder(BSNode<T> *p) {
    if (p != NULL) {
        // cout << p->value << endl;
        // LOGE(p->value);
        preOrder(p->lchild);
        preOrder(p->rchild);
    }
};

/*中序遍历算法*/
template<typename T>
void BSTree<T>::inOrder() {
    inOrder(root);
};

template<typename T>
void BSTree<T>::inOrder(BSNode<T> *p) {
    if (p != NULL) {
        inOrder(p->lchild);
        LOGE("p->value:%d\n",p->value);
        inOrder(p->rchild);
    }
};

/*后序遍历算法*/
template<typename T>
void BSTree<T>::postOrder() {
    postOrder(root);
};

template<typename T>
void BSTree<T>::postOrder(BSNode<T> *p) {
    if (p != NULL) {
        postOrder(p->lchild);
        postOrder(p->rchild);
        // LOGE(p->value);
    }
};

/*寻找最小元素*/
template<typename T>
T BSTree<T>::search_minimun() {
    return search_minimun(root);
};

template<typename T>
T BSTree<T>::search_minimun(BSNode<T> *p) {
    if (p->lchild != NULL)
        return search_minimun(p->lchild);
    return p->value;
};

/*寻找最大元素*/
template<typename T>
T BSTree<T>::search_maximum() {
    return search_maximum(root);
};

template<typename T>
T BSTree<T>::search_maximum(BSNode<T> *p) {
    if (p->rchild != NULL)
        return search_maximum(p->rchild);
    return p->value;
};

/*销毁二叉树*/
template<typename T>
void BSTree<T>::destory() {
    destory(root);
};

template<typename T>
void BSTree<T>::destory(BSNode<T> *&p) {
    if (p != NULL) {
        if (p->lchild != NULL)
            destory(p->lchild);
        if (p->rchild != NULL)
            destory(p->rchild);
        delete p;
        p = NULL;
    }

};


#endif //CHARS_S_BSTREE_H
