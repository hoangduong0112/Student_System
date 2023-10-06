import axios from "axios";

const USER_API_BASE_URL = 'http://localhost:8080/api/user/info';

class UserService {
    getUser() { return axios.get(USER_API_BASE_URL); }
}

export default new UserService()