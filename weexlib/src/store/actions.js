export function FETCH_LIST_NOTICE({commit, dispatch, state}) {
    commit('RECIVE_NOTICES')
}

export function ADD_NUM({commit}){
    setTimeout(() => {
        console.log('click num2')
     commit('TO_ADD_NUM')
    }, 1000)
    
}