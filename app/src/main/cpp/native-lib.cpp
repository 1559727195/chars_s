#include <jni.h>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include "logs.h"


char *fa() {
    //char *pa = "123456"; // pa 指针在栈区，“123456”在常量区，该函数调用完后指针变量 pa 就被释放了

    char *p = NULL;
    //指针变量 p 在栈中分配 4 字节
    p = (char *) malloc(100);
    //本函数在这里开辟了一块堆区的内存空间，并把地址赋值给 p
    strcpy(p, "wudunxiong 1234566");

    //把常量区的字符串拷贝到堆区
    return p;
    //返回给主调函数 fb()，相对 fa 来说 fb 是主调函数，相对 main 来说，
    //fa(),fb()都是被调用函数
}

char *fb() {
    char *pstr = NULL;
    pstr = fa();
    return pstr;
    //指针变量 pstr 在这就结束
}

void selectSort(int *p, int n) {
    // LOGE("%d\n", p[0]);
    int tmp;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (p[i] > p[j]) {
                tmp = p[i];
                p[i] = p[j];
                p[j] = tmp;
            }
        }
    }
}


char *getMem(char **p, int n) {
    *p = (char *) (malloc(n));

    if (*p == NULL) return NULL;
    return *p;
}


void displayArray(int(*p)[4], int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 4; j++) {
            //LOGE("%d ", *(*(p + i) + j));
            LOGE("%d ", *(*(p + i) + j));
        }
        putchar(10);
    }
}


int mm = 500;

char *jstringToChar(jstring pJstring);

void func(int *p) {
    *p = 400;
}

void func2(int **pp) {
    // *pp = &mm;
    *pp = &mm;
}


//typedef struct node {
//    char *name;
//    char *passwd;
//    struct node *next;
//} Node;
//
//
//Node *createList() {
//    Node *head = static_cast<Node *>(malloc(sizeof(Node)));
//    head->next = NULL;
//    return head;
//}
//
//
//void insertList(Node *head, Node *item) {
//    item->next = head->next;
//    head->next = item;
//}
//
//
//void travereList(Node *head) {
//    head = head->next;
//
//    while (head) {
//        LOGE("name = %s passwd = %s\n", head->name, head->passwd);
//        head = head->next;
//    }
//}
//
//
//void trimStrSpace(char *str) {
//    char *t = str;
//    while (*str) {
//        if (*str != ' ') { *t++ = *str; }
//        str++;
//    }
//    *t = '\0';
//}


//void readConfigFile(char *fileName, Node *head) {
//    FILE *fp = fopen(fileName, "r");
//    if (fp == NULL)
//        return;
//
//    char buf[1024];
//    int len = 0;
//    char *p;
//    while (fgets(buf, 1024, fp) != NULL) {
//        trimStrSpace(buf);
//        if (*buf == '#' || *buf == '\n' || *buf == '[') continue;
//        len = strlen(buf);
//        LOGE("len:%d\n", len);
//        if (buf[len - 1] == '\n')
//            buf[len - 1] = '\0';
//        p = strtok(buf, "=");
//        LOGE("p:%s\n", p);
//        if (p == NULL) continue;
//        LOGE("cur:%d\n", sizeof(Node));
//        Node *cur = static_cast<Node *>(malloc(sizeof(Node)));
//        LOGE("p.size:%d\n", strlen(p) + 1);
//        cur->name = static_cast<char *>(malloc(strlen(p) + 1));
//        strcpy(cur->name, p);
//        p = strtok(NULL, "=");
//        LOGE("p.size:%d\n", strlen(p) + 1);
//        cur->passwd = static_cast<char *>(malloc(strlen(p) + 1));
//        strcpy(cur->passwd, p);
//        insertList(head, cur);
//    }
//
//
//}

void trimStrLeftSpace(char *str) {
    char *pTmp = str;
    while (*pTmp == ' ') pTmp++;
    while (*pTmp) *str++ = *pTmp++;
    *str = '\0';
}


#include <pthread.h>
#include <unistd.h>

pthread_key_t p_key;

void func1() {
    int *tmp = static_cast<int *>(pthread_getspecific(p_key));
    LOGE("%d is running in %s\n", *tmp, __func__);
}


void *thread_func(void *args) {
    pthread_setspecific(p_key, args);

    int *tmp = static_cast<int *>(pthread_getspecific(p_key));
    LOGE("%d is running in %s\n", *tmp, __func__);

    *tmp = (*tmp) * 100;
    func1();
    return (void *) 0;
}


int main_pthread() {
    pthread_t pa, pb;
    int a = 1;
    int b = 2;
    pthread_key_create(&p_key, NULL);
    pthread_create(&pa, NULL, thread_func, &a);
    pthread_create(&pb, NULL, thread_func, &b);
    pthread_join(pa, NULL);
    pthread_join(pb, NULL);
    return 0;
}


struct node1 {
    int num;
    struct node1 *next;
};

//struct node *creat();

void print(node1 *pNode1);

node1 *creat(node1 *pNode1);


void main_node() {
    struct node1 *head;
    head = NULL;//创建一个空表
    head = creat(head);//创建单链表
    print(head);//打印单链表
}

void print(struct node1 *head) {
    struct node1 *temp;
    temp = head;
    LOGE("\n\n\n链表存入的值为：\n");

    while (temp != NULL) {
        LOGE("%6d\n", temp->num);/*输出链表节点的值*/
        temp = temp->next;
    }
    LOGE("链表打印结束!!");
}


node1 *creat(node1 *head) {
    struct node1 *p1, *p2;
    int i = 1;

    p1 = p2 = static_cast<node1 *>(malloc(sizeof(struct node1)));

//    LOGE("请输入值，值小于等于0结束，值存放地址为：p1_ADDR= %p\n", p1);
    p1->num = 1;
    p1->next = NULL;
//    LOGE("请输入值，值小于等于0结束，值存放地址为：p1_ADDR= %p\n,p2_ADDR= %p\n", p1,p2);
    LOGE("请输入值，值小于等于0结束，值存放地址为：head= %p\n", head);
    LOGE("p1->num:%d\n,p2->num:%d\n", p1->num, p2->num);
    while (i < 5) {
        if (head == NULL) {
            head = p1;
        } else {
            p2->next = p1;
            // LOGE("请输入值，值小于等于0结束，值存放地址为：p1_ADDR= %p\n,p2_ADDR= %p\n", p1,p2);
            p2 = p1;
        }


//        LOGE("p1->num:%d\n,p2->num:%d\n",p1->num,p2->num);
        p1 = static_cast<node1 *>(malloc(sizeof(struct node1)));
        i += 1;
        // printf("请输入值，值小于等于0结束，值存放地址为：p%d_ADDR= %p\n", i, p2);
//       LOGE("请输入值，值小于等于0结束，值存放地址为：i:%d,head_ADDR= %p\n", i, head);
//        LOGE("请输入值，值小于等于0结束，值存放地址为：p%d_ADDR= %p\n", i, p2);
        p1->num = i + 1;
    }


    LOGE("head->num:%d\n,p2->num:%d\n", head->num, p2->num);
    free(p1);
    p1 = NULL;
    p2->next = NULL;
    LOGE("链表输入结束（END）\n");
    return head;
}


#define LEN sizeof(NODE)
typedef struct _NODE {
    int val;
    struct _NODE *next;
} NODE, *PNODE;


void print(PNODE head) {
    while (head) {
        LOGE("%3d", head->val);
        head = head->next;
    }
    LOGE("\n");
}


void insertHead(PNODE *pHead, int val) {//头插法
    PNODE n = static_cast<PNODE>(malloc(LEN));
    n->val = val;
    n->next = *pHead;
    *pHead = n;
}

void insertTail(PNODE *pHead, int val) {//尾插发

    PNODE t = *pHead;
    PNODE n = static_cast<PNODE>(malloc(LEN));
    n->val = val;
    n->next = NULL;

    if (*pHead == NULL) {
        n->next = *pHead;
        *pHead = n;
    } else {
        while (t->next) {
            t = t->next;
        }
        t->next = n;
    }
}

void deleteHead(PNODE *pHead) {//删除头
    if (*pHead == NULL) {
        return;
    } else {
        PNODE t = *pHead;
        *pHead = (*pHead)->next;
        free(t);
    }
}


void deleteTail(PNODE *pHead) {//删除尾
    PNODE t = *pHead;
    if (t == NULL) {
        return;
    } else if (t->next == NULL) {
        free(t);
        *pHead = NULL;
    } else {
        while (t->next->next != NULL) {
            t = t->next;
        }
        free(t->next);
        t->next = NULL;
    }
}

PNODE findByVal(PNODE head, int val) {
    while (head != NULL && head->val != val) {
        head = head->next;
    }

    return head;

}


PNODE findByIndex(PNODE head, int index) {//根据索引找节点
    if (index == 1) {
        return head;
    } else {
        int c = 1;
        while (head != NULL && index != c) {
            head = head->next;
            c++;
        }
    }
    return head;
}


void insertByIndex(PNODE *pHead, int index, int val) {
    if (index == 1) {
        insertHead(pHead, val);
    } else {
        //PNODE t = findByIndex(*pHead, index - 1);
        PNODE t = findByIndex(*pHead, index - 1);//front
        if (t == NULL) {
            return;
        } else {
//            PNODE n = t->next;
//            t->next = static_cast<_NODE *>(malloc(LEN));
//            t->next->next = n;
//            t->next->val = val;
            PNODE n = t->next;//target
            t->next = static_cast<_NODE *>(malloc(LEN));
            t->next->next = n;
            t->next->val = val;
        }
    }
}

void deleteByIndex(PNODE *pHead, int index) {
    if (index == 1) {
        deleteHead(pHead);
    } else {
        PNODE t = findByIndex(*pHead, index - 1);//front
        if (t == NULL || t->next == NULL) {
            return;
        } else {
//            PNODE  n = t->next->next;//back;
//            free(t->next);//target
//            t->next = n;
            PNODE n = t->next->next;
            free(t->next);
            t->next = n;
        }
    }
}

void deleteByVal(PNODE *pHead, int val) {
    if (*pHead == NULL) {
        return;
    } else {
        if ((*pHead)->val == val) {
            deleteHead(pHead);
        } else {
            PNODE t = *pHead;
            while (t->next != NULL && t->next->val != val) {
                t = t->next;
            }
            if (t->next) {//target
                PNODE n = t->next->next;//next
                free(t->next);
                t->next = n;
            }
        }
    }
}

void clear(PNODE *pHead)//清除链表
{
    //while ((*pHead) != NULL) {
    while ((*pHead) != NULL)
        deleteHead(pHead);//从头删除
}

#define gap8_fl1(x)             (31 - __builtin_clz((x)))

static int array[32];


void main_risc() {
    int test = 0xFFFFF00;
    while (test > 0) {
        test = test >> 1;
        LOGE("test:%x gap8:%d \n", test, gap8_fl1(test));
    }
}


void main_NODE() {
    PNODE head = NULL;

    insertTail(&head, 1);
//    deleteHead(&head);
    insertTail(&head, 2);
    insertTail(&head, 3);
    insertTail(&head, 4);
    insertTail(&head, 5);
    insertTail(&head, 6);
//    print(head);

    insertByIndex(&head, 6, 9);
    // print(head);

    deleteByVal(&head, 2);
    //print(head);
    clear(&head);
    // print(head);
    insertByIndex(&head, 1, 12);
    print(head);


}

void *my_memcpy(void *dst, const void *src, size_t n) {
    char *tmp = (char *) dst;//dst=head,tmp->递增游标
    //char *s_src = (char *) src;
    char *s_src = (char *) src;

//    while (n--) {
//        *tmp++ = *s_src++;
//    }
    while (n--) {
        *tmp++ = *s_src++;
    }
    return dst;
}

void main_memcpy() {
    int a[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    my_memcpy(a + 3, a, 5 * sizeof(int));
    int i = 0;
    for (int i = 0; i < 10; i++) {
        LOGE("%d,", a[i]);  //0,1,2,0,1,2,0,1,8,9,
    }
}


void main_env() {
    LOGE("PATH : %s\n", getenv("PATH"));
    LOGE("HOME : %s\n", getenv("HOME"));
    LOGE("ROOT : %s\n", getenv("ROOT"));

}


#define BLOCK_SIZE  1024*1024*256

void main_calloc() {
    int i = 0;
    char *buf[10];
    while (i < 10) {
        buf[i] = static_cast<char *>(calloc(1, BLOCK_SIZE));
        i++;
    }
}


#define NUM 5

int MyCompare(const void *elem1, const void *elem2) {
    int *p1 = (int *) elem1;
    int *p2 = (int *) elem2;
    // return *p1%10-*p2%10;
    return *p1 - *p2;
}


void main_compare() {
    int arr[NUM] = {1, 55, 7, 192, 32};
    // qsort(arr,NUM,sizeof(int),MyCompare);
    qsort(arr, NUM, sizeof(int), MyCompare);
    for (int i = 0; i < NUM; i++) {
//        cout<<arr[i]<<" ";
        LOGE("content:%d", arr[i]);
    }
}


void main_cmp() {
    char str1[15];
    char str2[15];
    int ret;

    memcpy(str1, "abcdef", 6);
    memcpy(str2, "ABCDEF", 5);

    ret = memcmp(str1, str2, 6);
    if (ret > 0) {
        LOGE("str2 小于 str1");
    } else if (ret < 0) {
        LOGE("str1 小于 str2");
    } else {
        LOGE("str1 等于 str2");
    }
}


#include"dLink.h"

void main_link() {

    dLink dLink;
    int arr[10] = {11, 55, 67, 90, 21, 45, 23, 59, 79, 10};
    LOGE("xxxxxxxxxxxxxxxxx\n");
    dLink.create_dLink();                    //创建链表
    //dLink.dLink_insert(0, &arr[0]);        //双向链表表头插入
    dLink.dLink_insert_tail(&arr[0]);
    dLink.dLink_insert_tail(&arr[1]);        //双向链表表头插入
    dLink.dLink_insert_tail(&arr[2]);        //双向链表表头插入
    dLink.dLink_insert_tail(&arr[3]);        //双向链表表头插入
    dLink.dLink_insert_tail(&arr[4]);        //双向链表表头插入
    dLink.dLink_insert_tail(&arr[5]);        //双向链表表头插入
    LOGE("is_empty_dLink()=%d\n", dLink.is_empty_dLink());    //双向链表是否为空
    LOGE("dLink_size()=%d\n", dLink.dLink_size());                    //双向链表的大小
    //遍历双向链表
    int i;
    int *p;
    int sz = dLink.dLink_size();
    for (i = 0; i < sz; i++) {
        p = (int *) dLink.dLink_get(i);
        LOGE("dLink_get(%d)=%d\n", i, *p);
    }
    dLink.destory_dLink();

}


#include "stack.h"

int main_stack() {
    stack *stack;
    stack::Stack *pstack;
    stack->init(pstack);

    //stack::StackNode pnode = (stack::StackNode) malloc(sizeof(node));
    stack::StackNode pnode = (stack::StackNode) malloc(sizeof(stack::node));
    pnode->name = "karno";
    pnode->grade = 100;
    pnode->age = 23;

    stack->push(pstack, pnode);
    stack->print(pstack);

    stack::StackNode result = stack->pop(pstack);
    stack->print(pstack);

    return 0;
}


void *test(void *arg) {
//    int i;
//    for (i = 0; i < 5; i++) {
//        sleep(2);
//        LOGE("tid:%ld task:%ld\n", pthread_self(), (long) arg);
//    }
    LOGE("tid:%ld task:%ld\n", pthread_self(), (long) arg);
    return NULL;
}

#include "Thread_pool.h"
#include "TreeNodeClass.h"


/*
 * 生产者消费者模式-队列-先进先出，线程锁
 *
2020-04-17 14:04:10.843 30152-30152/com.massky.chars_s E/C-test: press enter to terminate ...
2020-04-17 14:04:10.843 30152-30180/com.massky.chars_s E/C-test: tid:547466171472 task:0
2020-04-17 14:04:10.843 30152-30181/com.massky.chars_s E/C-test: tid:547461526608 task:1
2020-04-17 14:04:10.844 30152-30180/com.massky.chars_s E/C-test: tid:547466171472 task:2
2020-04-17 14:04:10.844 30152-30181/com.massky.chars_s E/C-test: tid:547461526608 task:3
2020-04-17 14:04:10.844 30152-30180/com.massky.chars_s E/C-test: tid:547466171472 task:4
*/

int main_threads() {
    long i = 0;
    Thread_pool threadPool;
    Thread_pool::thread_pool_t pool;

    pool = threadPool.thread_pool_create(2);

    for (i = 0; i < 5; i++) {
        threadPool.thread_pool_add_task(pool, test, (void *) i);
    }

    LOGE("press enter to terminate ...");
    getchar();

    threadPool.thread_pool_destroy(pool);
    return 0;

}


void main_tree_node() {
    TreeNodeClass *theeNodeClass = new TreeNodeClass();

    theeNodeClass->CreatBiTree(&(theeNodeClass->Tt));//创建节点
    theeNodeClass->LevelTraversal(theeNodeClass->Tt);
    //声明一个树变量
//    int dep = 0;                    //树深度变量
//    int a = 0;
//    while(true)
//    {
//       LOGE("请选择对二叉树的操作：\n");
//       LOGE("1.创建\n");
//       LOGE("2.先序遍历\n");
//       LOGE("3.中序遍历\n");
//       LOGE("4.后序遍历\n");
//       LOGE("5.层序遍历\n");
//       LOGE("6.获取深度\n");
//       LOGE("7.中序线索化\n");
//       LOGE("8.遍历线索化二叉树\n");
//       LOGE("9.退出\n");
//        a++;
//        switch(a)
//        {
//            case 1:
//                printf("请输入根节点：\n");
//                theeNodeClass-> CreatBiTree(&(theeNodeClass->Tt));
//                break;
//            case 2:
//                theeNodeClass->PreorderTraversal(theeNodeClass->Tt);
//                break;
//            case 3:
//                theeNodeClass->InorderTraversal(theeNodeClass->Tt);
//                break;
//            case 4:
//                theeNodeClass-> PostorderTraversal(theeNodeClass->Tt);
//                break;
//            case 5:
//                theeNodeClass-> LevelTraversal(theeNodeClass->Tt);
//                break;
//            case 6:
//                dep =   theeNodeClass->Depth(theeNodeClass->Tt);
//                printf("树的深度为 %d\n", dep);
//                break;
//            case 7:
//                theeNodeClass->Tt =   theeNodeClass->AddHead(theeNodeClass->Tt);
//                break;
//            case 8:
//                theeNodeClass->TreeCueTraversal(theeNodeClass->Tt);
//                break;
//            case 9:
//                return;
//            default:
//                printf("选择错误\n");
//                break;
//        }
//    }
}


#include "BSTree.h"
#include "MaxHeap.h"

int main_bst() {
    BSTree<int> t;
    t.insert(62);
    t.insert(58);
    t.insert(47);
    t.insert(51);
    t.insert(35);
    t.insert(37);
    t.insert(88);
    t.insert(73);
    t.insert(99);
    t.insert(93);
    t.insert(95);

    //cout << endl << "中序遍历：" << endl;
    LOGE("中序遍历：");
    t.inOrder();

   // cout << "最大元素:" << t.search_maximum() << endl;
   // cout << "最小元素:" << t.search_minimun() << endl;
        LOGE("最大元素:%d\n",  t.search_maximum());
        LOGE("最小元素:%d\n",  t.search_minimun());

    //cout << "删除元素99" << endl;
    LOGE("删除元素99");
    t.remove(99);

   // cout << "最大元素:" << t.search_maximum() << endl;

    LOGE("最大元素:%d\n",  t.search_maximum());

    t.destory();

    getchar();
    return 0;
}


void foo_add(char* &p)
{
    *p = 'X';
    p++;
}



void foo(char* p)
{
    *p = 'X';
    p++;
}

int main_cs()
{
   // char* p = "Hello";


    char *p = NULL;
    //指针变量 p 在栈中分配 4 字节
    p = (char *) malloc(100);
    //本函数在这里开辟了一块堆区的内存空间，并把地址赋值给 p
    strcpy(p, "Hello");
    //foo(p);
    foo_add(p);
   // cout << p << endl;
        LOGE("p:%c\n",p[0]);
    LOGE("str:%s\n",p);
    return 0;
}


int _tmain_heap()
{
    MaxHeap<int> heap(11);
    //逐个元素构建大顶堆
    for (int i = 0; i < 10; i++)
    {
        heap.insert(i);
    }
    heap.print();
    cout << endl;
    heap.remove(8);
    heap.print();
    cout << endl;

    //根据指定的数组创建大顶堆
    MaxHeap<int> heap2(11);
    int a[10] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    heap2.createMaxHeap(a, 10);
    heap2.print();
    getchar();
    return 0;
}




int main_s(char *path) {
    //main_cs();
    main_bst();
    // main_tree_node();
    //  main_threads();
    //main_stack();
    //main_link();
    //main_cmp();
    //main_compare();
    //main_env();
    //main_memcpy();
    // main_risc();
    // main_NODE();
//    main_node();
//    main_pthread();
//    Node *node = createList();
//    readConfigFile(path, node);
//    travereList(node);
    return 0;
}

char *jstringToChar(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
//    jclass clsstring = env->FindClass("java/lang/String");
//    jstring strencode = env->NewStringUTF("utf-8");
//    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");


    jclass clsstring = env->FindClass("java/lang/String");
    jstring strencode = env->NewStringUTF("utf-8");
    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");




//    jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, mid, strencode);
//    jsize alen = env->GetArrayLength(barr);
//    jbyte *ba = env->GetByteArrayElements(barr, JNI_FALSE);


    jbyteArray barr = static_cast<jbyteArray>(env->CallObjectMethod(jstr, mid, strencode));
    jsize alen = env->GetArrayLength(barr);
    jbyte *ba = env->GetByteArrayElements(barr, JNI_FALSE);


//    if (alen > 0) {
//        rtn = (char *) malloc(alen + 1);
//        memcpy(rtn, ba, alen);
//        rtn[alen] = 0;
//    }


    if (alen > 0) {
        rtn = static_cast<char *>(malloc(alen + 1));
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    // env->ReleaseByteArrayElements(barr, ba, 0);
    env->ReleaseByteArrayElements(barr, ba, 0);
    return rtn;
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_massky_chars_1s_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */, jstring path) {
    std::string hello = "Hello from C++";
    // const jchar *ss =  env->GetStringChars(path, 0);
    char *ss = jstringToChar(env, path);
    main_s(ss);
    env->ReleaseStringChars(path, reinterpret_cast<const jchar *>(ss));
    return env->NewStringUTF(hello.c_str());
}





