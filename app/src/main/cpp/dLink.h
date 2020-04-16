//
// Created by zhu on 2020/4/14.
//

#ifndef CHARS_S_DLINK_H

#include <string>

#define CHARS_S_DLINK_H


class dLink {
public:
    dLink();

    ~dLink();

    int create_dLink();

    int is_empty_dLink();

    int dLink_size();

    void *dLink_get(int index);


    void *dLink_getTail();

    int dLink_insert(int index, void *pVal);

    int dLink_insert_head(void *pVal);

    int dLink_insert_tail(void *pVal);

    int dLink_delete(int index);

    int dLink_delete_first();

    int destory_dLink();

    void *dLink_getFirst();

    int dLink_delete_tail();
};


#endif //CHARS_S_DLINK_H
