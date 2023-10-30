import axios from "axios";
import config from "./config";

const USER_API_BASE_URL = 'http://localhost:8080/api/user';

class UserService {

    getAllUser(){ return axios.get(USER_API_BASE_URL, config)}
    getUser() { return axios.get(USER_API_BASE_URL + '/info', config); }
    getMySemester() { return axios.get(USER_API_BASE_URL + '/my-semester', config); }
    getDetailSemester(id) { return axios.get(USER_API_BASE_URL + '/my-semester/' + id + '/course', config); }

    getRequest() { return axios.get(USER_API_BASE_URL + '/my-request', config); }

}

export default new UserService()