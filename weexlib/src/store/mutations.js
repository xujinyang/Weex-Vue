export function RECIVE_NOTICES(state) {
    state.notices = [
        { title: '通知1', id: 0, content: '内容1' },
        { title: '通知1', id: 1, content: '内容2' },
        { title: '通知2', id: 2, content: '内容3' },
        { title: '通知3', id: 3, content: '内容4' },
        { title: '通知4', id: 4, content: '内容5' },
        { title: '通知5', id: 5, content: '内容6' },
        { title: '通知6', id: 6, content: '内容7' },
        { title: '通知7', id: 7, content: '内容8' },
        { title: '通知8', id: 8, content: '内容9' },
        { title: '通知9', id: 9, content: '内容10' },
        { title: '通知10', id: 10, content: '内容11' },
        { title: '通知11', id: 11, content: '内容12' }
    ]
}

export function TO_ADD_NUM(state){
    console.log('click num3')
    state.sum++;
}