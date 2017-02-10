
import fetch from '../utils/fetch'
const baseURL = 'https://hacker-news.firebaseio.com/v0'

export function FETCH_LIST_NOTICE({commit, dispatch, state}) {
    fetch(baseURL, 'GET', null).then((response) => {
        console.log(response)
    }).catch((error) => {
        console.log(error)
    })
    commit('RECIVE_NOTICES')
}

export function ADD_NUM({commit}) {
    setTimeout(() => {
        console.log('click num2')
        commit('TO_ADD_NUM')
    }, 1000)
}