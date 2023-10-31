import axios from "axios";
import cookie from "react-cookies";
import {createHeaders} from "./header";

const USER_API_BASE_URL = 'http://localhost:8080/api/user';

class UserService {

    getAllUser(){ return axios.get(USER_API_BASE_URL, createHeaders())}
    getUser() { return axios.get(USER_API_BASE_URL + '/info', createHeaders()); }
    getMySemester() { return axios.get(USER_API_BASE_URL + '/my-semester', createHeaders()); }
    getDetailSemester(id) { return axios.get(USER_API_BASE_URL + '/my-semester/' + id + '/course', createHeaders()); }

    getRequest() { return axios.get(USER_API_BASE_URL + '/my-request', createHeaders()); }

}

export default new UserService()