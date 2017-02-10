const stream = weex.requireModule('stream')


export function fetch(path, method, params) {
    return new Promise((resolve, reject) => {
        stream.fetch({
            method: method,
            url: path,
            type: 'json'
        }, (response) => {
            if (response.status == 200) {
                resolve(response.data)
            } else {
                reject(response)
            }
        }, () => { }
        )
    })

}