// 공통 모듈 mixins

import axios from 'axios' // HTTP 비동기 통신 라이브러리

export default {
    methods: {
        async $api(url, method, data) {
            return (await axios({
                method: method,
                url,
                data
            }).catch(e => {
                console.log(e)
                })
            ).data;
        }
    }
}