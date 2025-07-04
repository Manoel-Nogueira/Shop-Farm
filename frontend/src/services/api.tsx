import axios from "axios";

const Api =  axios.create ({

    baseURL: "http://localhost:8080/api"

})

Api.interceptors.request.use(

    (config) => {

        const token = localStorage.getItem("token")

        if(token) {

            config.headers.Authorization = `Bearer ${token}`

        }

        return config

    },

    (error) => {

        return Promise.reject(error)

    }

)

Api.interceptors.response.use(

    (response) => {

        return response

    },
    (error) => {

        if(error.response && (error.response.status === 401 || error.response.status === 403)) {

            window.location.href = "/login"
            localStorage.removeItem("token")

        }

        return Promise.reject(error)

    }

)

export default Api