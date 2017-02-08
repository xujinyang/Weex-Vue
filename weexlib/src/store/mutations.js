export function RECIVE_NOTICES(state) {
    state.notices = [
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' },
        { title: '通知1', id: 1, content: '内容1' }
    ]
}

export function TO_ADD_NUM(state){
    console.log('click num3')
    state.sum++;
}